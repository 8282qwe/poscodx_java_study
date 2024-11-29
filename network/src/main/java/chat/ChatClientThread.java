package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static chat.ChatClient.consoleLog;

public class ChatClientThread extends Thread {
    private Socket socket;

    public ChatClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

            while (true) {
                String message = br.readLine();

                view(message);
            }
        } catch (IOException e) {
            consoleLog("Error :" + e.getMessage());
        }
    }

    public void view(String msg) {
        if (msg.startsWith("SUCCESS")) return;

        if (msg.startsWith("ROOMLIST")) {
            System.out.println("==== 방 리스트 정보 ====");
            for (String str : msg.split(":")[1].split(",")) {
                System.out.println(str);
            }
            System.out.println("=".repeat(20));
        } else if (msg.startsWith("NOW")) {
            System.out.println("현재 위치한 방 이름 : " + msg.split(":")[1]);
        } else if (msg.startsWith("LIST")) {
            System.out.println("==== 방의 유저 정보 ====");
            Arrays.stream(msg.split(":")[1].split(",")).forEach(System.out::println);
            System.out.println("=".repeat(20));
        } else if (msg.startsWith("DISCONNECTED")) {
            System.exit(0);
        } else if (msg.startsWith("DM")) {
            System.out.printf("%s님에게 온 메세지 : %s\n",msg.split(":")[1],msg.split(":")[2]);
        } else if (msg.startsWith("ERROR")) {
            System.out.println("통신 중 오류가 생겼습니다.");
        } else System.out.println(msg);
    }
}
