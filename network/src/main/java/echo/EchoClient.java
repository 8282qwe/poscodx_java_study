package echo;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class EchoClient {
    private static final String SERVER_IP = "127.0.0.1";

    public static void main(String[] args) {
        Scanner scanner = null;
        Socket socket = null;

        try {
            Scanner in = new Scanner(System.in);
            socket = new Socket();

            socket.connect(new InetSocketAddress(SERVER_IP, EchoServer.PORT));

            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

            while (true) {
                System.out.print(">> ");
                String line = in.nextLine();

                if ("exit".equalsIgnoreCase(line)) {
                    break;
                }

                pw.println(line);

                String data = br.readLine();

                if (data == null) {
                    log("Closed by server");
                    break;
                }

                System.out.println("<< " + data);
            }
        } catch (SocketException e) {
            log("Socket Exception: " + e);
        } catch (IOException e) {
            log("Error: " + e);
        } finally {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }

                if (scanner != null) {
                    scanner.close();
                }
            } catch (IOException e) {
                log("Error: " + e);
            }
        }
    }

    public static void log(String message) {
        System.out.println("[Echo Client] " + message);
    }
}
