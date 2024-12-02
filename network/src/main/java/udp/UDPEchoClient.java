package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static udp.UDPEchoServer.BUFFER_SIZE;
import static udp.UDPEchoServer.PORT;

public class UDPEchoClient {
    public static final String SERVER_IP = "127.0.0.1";

    public static void main(String[] args) {
        Scanner scanner = null;
        DatagramSocket socket = null;


        try {
            // 1. 스캐너 생성
            scanner = new Scanner(System.in);

            // 2. 소켓 생성
            socket = new DatagramSocket();

            while (true) {
                String message = scanner.nextLine();

                if ("quit".equalsIgnoreCase(message)) {
                    break;
                }

                // 3. 데이터 송신
                byte[] sndData = message.getBytes(StandardCharsets.UTF_8);

                DatagramPacket sndPacket = new DatagramPacket(sndData, sndData.length,new InetSocketAddress(SERVER_IP,PORT));
                socket.send(sndPacket);

                // 4. 데이터 수신
                DatagramPacket rcvPacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
                socket.receive(rcvPacket);

                byte[] rcvData = rcvPacket.getData();
                int offset = rcvPacket.getLength();

                message = new String(rcvData, 0, offset, StandardCharsets.UTF_8);
                System.out.println("[UDP Echo Client] received: " + message);
            }

            // 3.
        } catch (SocketException e) {
            System.out.println("[UDP Echo Client] Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("[UDP Echo Client] Error: " + e.getMessage());
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
