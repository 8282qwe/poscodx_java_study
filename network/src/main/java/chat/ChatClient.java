package chat;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

public class ChatClient {
    private static final String SERVER_IP = "127.0.0.1";
    private static PrintWriter pw = null;
    private static final Scanner in = new Scanner(System.in);


    public static void main(String[] args) {
        Scanner scanner = null;
        Socket socket = null;

        try {
            socket = new Socket();

            // 소켓 연결 후 읽기 쓰레드 시작
            socket.connect(new InetSocketAddress(SERVER_IP, ChatServer.PORT));
            Thread t = new ChatClientThread(socket);
            t.start();

            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);

            // 닉네임 입력받기
            System.out.print("닉네임을 설정해 주세요 : ");
            pw.println(packing("JOIN", in.nextLine()));

            while (true) {
                String line = in.nextLine();

                if (line.isBlank()) continue;

                if (line.startsWith("/")) {
                    commandMsg(line);
                } else {
                    pw.println(String.join(":", "MSG", Base64.getEncoder().encodeToString(line.getBytes(StandardCharsets.UTF_8))));
                }
            }
        } catch (SocketException e) {
            consoleLog("Socket Exception: " + e.getMessage());
        } catch (IOException e) {
            consoleLog("Error: " + e.getMessage());
        } finally {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }

                if (scanner != null) {
                    scanner.close();
                }
            } catch (IOException e) {
                consoleLog("Error: " + e.getMessage());
            }
        }
    }

    public static void consoleLog(String msg) {
        System.out.println("[Client] " + msg);
    }

    public static String packing(String type, String msg) {
        switch (type.toUpperCase()) {
            case "JOIN":
                return String.join(":", type, msg);
        }
        return null;
    }

    public static void commandMsg(String msg) {
        String type = msg.toUpperCase().replace("/", "").split(" ")[0];

        switch (type) {
            case "ROOMLIST", "NOW", "QUIT", "LIST" -> {
                pw.println(String.join(":", type.toUpperCase()));
            }
            case "JOIN", "MAKE" -> {
                if (msg.split(" ").length < 2) {
                    return;
                }
                pw.println(String.join(":", type.toUpperCase(), msg.split(" ")[1]));
            }
            case "DM" -> {
                if (msg.split(" ").length < 2) {
                    return;
                }
                System.out.print(msg.split(" ")[1]+"님에게 보낼 메세지를 입력하세요:");
                String message = in.nextLine();
                pw.println(String.join(":", type.toUpperCase(), msg.split(" ")[1],Base64.getEncoder().encodeToString(message.getBytes(StandardCharsets.UTF_8))));
            }
        }
    }
}