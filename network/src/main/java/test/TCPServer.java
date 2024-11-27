package test;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
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
            serverSocket.bind(new InetSocketAddress("0.0.0.0", 5001));

            // 3. accept
            Socket socket = serverSocket.accept(); //blocking(=sleep)

            // 4. IO Stream 받아오기
            InputStream is = socket.getInputStream();

            // 5. 데이터 읽기
            byte[] buffer = new byte[256];
            int readByteCount = is.read(buffer); //blocking(=sleep)

            if (readByteCount == -1) {
                // close by client
                System.out.println("[server] closed by client");
                return;
            }

            System.out.println("[server] receive: "+new String(buffer, 0, readByteCount, StandardCharsets.UTF_8));

            System.out.println("연결 성공");
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
