package chat;

import java.io.PrintWriter;

public class User {
    private final String nickname;
    private Room room;
    private PrintWriter printWriter;

    private User(String nickname) {
        this.nickname = nickname;
    }

    public User(String nickname, Room room) {
        this(nickname);
        this.room = room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public User(String nickname, Room room, PrintWriter printWriter) {
        this(nickname, room);
        this.printWriter = printWriter;
    }

    public String getNickname() {
        return nickname;
    }

    public void sendMsg(String msg) {
        printWriter.println(msg);
    }
}
