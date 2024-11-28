package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class TCPServer {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            // 1. 서버소켓 생성
            serverSocket = new ServerSocket();

            // 2. 바인딩 (binding)
            //    Socket에 InetSocketAddress[InetAddress(IPAddress) + port]
            //    IPAddress : 0.0.0.0 : 특정 호스트 IP를 바인딩 하지 않는다.
            // hostname 필드는 allow IP (0.0.0.0 은 모든 주소 허용)
            serverSocket.bind(new InetSocketAddress("0.0.0.0", 50000));

            // 3. accept
            Socket socket = serverSocket.accept(); //blocking(=sleep)

            try {
                InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
                System.out.printf("[server] connected by client[%s : %s]", inetRemoteSocketAddress.getAddress().getHostAddress(), inetRemoteSocketAddress.getPort());
                System.out.println("연결 성공");

                // 4. IO Stream 받아오기
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();

                // 5. 데이터 읽기
                while (true) {
                    byte[] buffer = new byte[256];
                    int readByteCount = is.read(buffer); //blocking(=sleep)

                    if (readByteCount == -1) {
                        // close by client
                        System.out.println("[server] closed by client");
                        break;
                    }

                    String data = new String(buffer, 0, readByteCount, StandardCharsets.UTF_8);
                    System.out.println("[server] received: " + data);

                    // 6. 데이터 쓰기
                    os.write(data.getBytes(StandardCharsets.UTF_8));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            }
        } catch (SocketException e) {
            System.out.println("[server] Socket Exception: " + e);
        } catch (IOException e) {
            System.out.printf("[server] error: " + e);
        } finally {
            try {
                if (serverSocket != null && !serverSocket.isClosed()) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
