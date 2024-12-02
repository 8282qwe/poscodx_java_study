package chat;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

import static chat.ChatServer.ROOM_LIST;
import static chat.ChatServer.consoleLog;
import static echo.EchoServer.log;

/*
서버에서 각 클라이언트와 연결되는 thread
*/

public class ChatServerThread extends Thread {
    private Socket socket;
    private User user;
    private final Room DEFAULT_ROOM;
    private BufferedReader in;

    public ChatServerThread(Socket socket, Room room) {
        this.socket = socket;
        this.DEFAULT_ROOM = room;
        try {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            consoleLog("Error : " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
            System.out.printf("[Server] connected by client[%s : %s]\n", inetRemoteSocketAddress.getAddress().getHostAddress(), inetRemoteSocketAddress.getPort());
            consoleLog("연결 성공");

            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

            String nickname = unpacking(in.readLine())[1];
            this.user = new User(nickname, DEFAULT_ROOM, new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true));

            // 대기실 입장
            broadcast(DEFAULT_ROOM);
            Objects.requireNonNull(searchRoom("대기실")).addUser(user);

            while (true) {
                String data = in.readLine();

                if (data == null) {
                    log("Closed by client");
                    break;
                }
                doing(data);

                log("received: " + data);
            }
        } catch (IOException e) {
            consoleLog("Socket Exception" + e);
        } finally {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                consoleLog("Error: " + e);
            }
        }
    }


    public void broadcast(Room room) {
        for (User user : room.getUsers()) {
            user.sendMsg(this.user.getNickname() + "님이 " + room.getRoomName() + "에 입장하셨습니다.");
        }
    }

    public static String[] unpacking(String msg) {
        return msg.split(":");
    }

    private void doing(String msg) {
        String[] data = unpacking(msg);

        switch (data[0]) {
            case "JOIN" -> {
                Room room = searchRoom(data[1]);
                if (room != null) {
                    if (!room.getUsers().contains(user)) {
                        broadcast(room);
                        room.addUser(user);
                        Room r1 = user.getRoom();
                        r1.removeUser(user);
                        user.setRoom(room);
                    }
                }
            }
            case "ROOMLIST" -> {
                responseMsg(user, "ROOMLIST", roomList());
            }
            case "MAKE" -> {
                if (searchRoom(data[1]) == null) {
                    Room room = new Room(data[1], user);
                    ROOM_LIST.add(room);
                    user.getRoom().removeUser(user);
                    user.setRoom(room);
                }
            }
            case "NOW" -> {
                responseMsg(user, "NOW", user.getRoom().getRoomName());
            }
            case "MSG" -> {
                user.getRoom().getUsers().forEach(i -> responseMsg(i, this.user.getNickname() + "님:" + new String(Base64.getDecoder().decode(data[1]))));
            }
            case "LIST" -> {
                responseMsg(user, "LIST", userList());
            }
            case "QUIT" -> {
                if (user.getRoom() == DEFAULT_ROOM) {
                    DEFAULT_ROOM.removeUser(user);
                    responseMsg(user, "DISCONNECTED");
                }

                if (user.getRoom().getOwner() == user) {
                    ROOM_LIST.remove(user.getRoom());
                    for (User user1 : user.getRoom().getUsers()) {
                        user1.sendMsg("방이 해체 되었습니다.");
                        DEFAULT_ROOM.addUser(user1);
                        user1.setRoom(DEFAULT_ROOM);
                    }
                } else {
                    user.getRoom().removeUser(user);
                    user.getRoom().getUsers().forEach(i -> {
                        i.sendMsg(user.getNickname() + "님이 나가셨습니다.");
                    });
                    DEFAULT_ROOM.addUser(user);
                    user.setRoom(DEFAULT_ROOM);
                }

            }
            case "DM" -> {
                user.getRoom().getUsers().forEach(i -> {
                    if (i.getNickname().equals(data[1])) {
                        responseMsg(i, "DM", user.getNickname(), new String(Base64.getDecoder().decode(data[2])));
                    }
                });
            }
            case "DISCONNECT" -> {
                if (user.getRoom().getOwner() == user) {
                    ROOM_LIST.remove(user.getRoom());
                    for (User user1 : user.getRoom().getUsers()) {
                        user1.sendMsg("방이 해체 되었습니다.");
                        DEFAULT_ROOM.addUser(user1);
                        user1.setRoom(DEFAULT_ROOM);
                    }
                } else {
                    user.getRoom().removeUser(user);
                    user.getRoom().getUsers().forEach(i -> {
                        i.sendMsg(user.getNickname() + "님이 나가셨습니다.");
                    });
                }
                responseMsg(user, "DISCONNECTED");
            }
            default -> responseMsg(user,"ERROR");
        }
    }

    private String roomList() {
        StringBuilder builder = new StringBuilder();
        for (Room room : ROOM_LIST) {
            builder.append(room.getRoomName()).append(",");
        }
        return builder.toString();
    }

    private String userList() {
        StringBuilder builder = new StringBuilder();
        for (User user : user.getRoom().getUsers()) {
            builder.append(user.getNickname()).append(",");
        }
        return builder.toString();
    }

    private Room searchRoom(String roomName) {
        for (Room room : ChatServer.ROOM_LIST) {
            if (room.getRoomName().equals(roomName)) {
                return room;
            }
        }
        return null;
    }

    private void responseMsg(User user, String... args) {
        user.sendMsg(String.join(":", args));
    }
}
