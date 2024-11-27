package test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHost {

    public static void main(String[] args) {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            // 호스트 네임(네트워크 연결에 지장 없음)
            System.out.println(inetAddress.getHostName());

            // 호스트의 IP(내 아이피) 찾기
            System.out.println(inetAddress.getHostAddress());

            byte[] address = inetAddress.getAddress();

            for (byte b : address) {
                System.out.println(b);
            }
        } catch (UnknownHostException e) {
            System.out.println("error: " + e);
        }
    }
}
