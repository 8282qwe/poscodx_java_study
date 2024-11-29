package chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

public class ChatServer {
    public static final int PORT = 9001;
    private static final Room MAIN_ROOM = new Room("대기실");
    public static final List<Room> ROOM_LIST = new Vector<>();

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        ROOM_LIST.add(MAIN_ROOM);

        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT));
            consoleLog("Start!.... [port: " + PORT + "]");

            while (true) {
                Socket socket = serverSocket.accept();

                Thread t = new ChatServerThread(socket,MAIN_ROOM);
                t.start();
            }

        } catch (IOException e) {
            consoleLog("Error" + e.getMessage());
        } finally {
            try {
                if (serverSocket != null && !serverSocket.isClosed()) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                consoleLog("Error" + e.getMessage());
            }
        }
    }

    public static void consoleLog(String msg) {
        System.out.println("[Server] " + msg);
    }
}
