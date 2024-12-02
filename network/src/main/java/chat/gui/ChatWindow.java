package chat.gui;

import chat.ChatServer;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class ChatWindow {

    private Frame frame;
    private Panel pannel;
    private Button buttonSend;
    private TextField textField;
    private TextArea textArea;

    private static final String SERVER_IP = "127.0.0.1";
    private static PrintWriter pw = null;

    public ChatWindow(String name) {
        frame = new Frame(name);
        pannel = new Panel();
        buttonSend = new Button("Send");
        textField = new TextField();
        textArea = new TextArea(30, 80);
    }

    public void show() {
        // Button
        buttonSend.setBackground(Color.GRAY);
        buttonSend.setForeground(Color.WHITE);
        buttonSend.addActionListener(actionEvent -> sendMessage());

        // Textfield
        textField.setColumns(80);
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }
        });

        // Pannel
        pannel.setBackground(Color.LIGHT_GRAY);
        pannel.add(textField);
        pannel.add(buttonSend);
        frame.add(BorderLayout.SOUTH, pannel);

        // TextArea
        textArea.setEditable(false);
        frame.add(BorderLayout.CENTER, textArea);

        // Frame
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                finish();
            }
        });
        frame.setVisible(true);
        frame.pack();

        Socket socket = null;

        // 소켓 연결 후 읽기 쓰레드 시작
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(SERVER_IP, ChatServer.PORT));

            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
            pw.println(String.join(":", "JOIN", frame.getTitle()));
            new ChatClientThread(socket).start();
        } catch (IOException e) {
            consoleLog("ERROR: " + e.getMessage());
        }
    }

    public static void consoleLog(String msg) {
        System.out.println("[Client] " + msg);
    }

    private void sendMessage() {
        String message = textField.getText();

        if (message.isBlank()) {
            textField.setText("");
            textField.requestFocus();
        }


        if (message.startsWith("/")) {
            commandMsg(message);
        } else {
            pw.println(String.join(":", "MSG", Base64.getEncoder().encodeToString(message.getBytes(StandardCharsets.UTF_8))));
        }

        textField.setText("");
        textField.requestFocus();

        // ChatClientThread에서 서버로 부터 받은 메세지가 있음
    }

    private void updateTextArea(String message) {
        textArea.append(message + "\n");
    }

    private void finish() {
        // quit 프로토콜 구현
        commandMsg("/DISCONNECT");
        // exit java application
//        System.exit(0);
    }

    public static void commandMsg(String msg) {
        String type = msg.toUpperCase().replace("/", "").split(" ")[0];

        switch (type) {
            case "ROOMLIST", "NOW", "QUIT", "LIST", "DISCONNECT" -> {
                pw.println(String.join(":", type.toUpperCase()));
            }
            case "JOIN", "MAKE" -> {
                if (msg.split(" ").length < 2) {
                    return;
                }
                pw.println(String.join(":", type.toUpperCase(), msg.split(" ")[1]));
            }
            case "DM" -> {
                if (msg.split(" ").length < 3) {
                    return;
                }
                pw.println(String.join(":", type.toUpperCase(), msg.split(" ")[1], Base64.getEncoder().encodeToString(String.join(" ", Arrays.copyOfRange(msg.split(" "), 2, msg.split(" ").length)).getBytes(StandardCharsets.UTF_8))));
            }
        }
    }

    private class ChatClientThread extends Thread {

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

                    if (message == null) {
                        consoleLog("End Connection!");
                        break;
                    }

                    view(message);
                }
            } catch (IOException e) {
                consoleLog("Error :" + e.getMessage());
            }
        }

        public void view(String msg) {
            if (msg.startsWith("SUCCESS")) return;

            if (msg.startsWith("ROOMLIST")) {
                updateTextArea("==== 방 리스트 정보 ====");
                for (String str : msg.split(":")[1].split(",")) {
                    updateTextArea(str);
                }
                updateTextArea("=".repeat(16));
            } else if (msg.startsWith("NOW")) {
                updateTextArea("현재 위치한 방 이름 : " + msg.split(":")[1]);
            } else if (msg.startsWith("LIST")) {
                updateTextArea("==== 방의 유저 정보 ====");
                Arrays.stream(msg.split(":")[1].split(",")).forEach(ChatWindow.this::updateTextArea);
                updateTextArea("=".repeat(16));
            } else if (msg.startsWith("DISCONNECTED")) {
                System.exit(0);
            } else if (msg.startsWith("DM")) {
                updateTextArea(msg.split(":")[1] + "님에게 온 메세지 : " + msg.split(":")[2]);
            } else if (msg.startsWith("ERROR")) {
                updateTextArea("통신 중 오류가 생겼습니다.");
            } else updateTextArea(msg);
        }
    }
}
