package echo;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import static echo.EchoServer.log;

public class EchoRequestHandler extends Thread {
    private Socket socket;

    public EchoRequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
            System.out.printf("[Echo Server] connected by client[%s : %s]\n", inetRemoteSocketAddress.getAddress().getHostAddress(), inetRemoteSocketAddress.getPort());
            System.out.println("연결 성공");

            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

            while (true) {
                String data = br.readLine();
                if (data == null) {
                    log("Closed by client");
                    break;
                }
                log("received: " + data);

                pw.println(data);
            }
        } catch (IOException e) {
            log("Socket Exception" + e);
        } finally {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                log("Error: " + e);
            }
        }
    }
}
