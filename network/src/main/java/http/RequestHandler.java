package http;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class RequestHandler extends Thread {
    private Socket socket;
    private final String BASE_URL = "network/webapp";

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // get IOStream
            OutputStream outputStream = socket.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

            // logging Remote Host IP Address & Port
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
            consoleLog("connected from " + inetSocketAddress.getAddress().getHostAddress() + ":" + inetSocketAddress.getPort());

            String request = null;

            while (true) {
                String line = br.readLine();

                // 브라우저가 연결을 끊으면...
                if (line == null) {
                    return;
                }

                // SimpleHttpServer는 HTTP Header만 읽는다.
                if (line.isEmpty()) {
                    break;
                }

                // Request Header의 첫 줄만 읽음
                consoleLog(line);
                if (request == null) {
                    request = line;
                    break;
                }
            }

            String[] tokens = request.split(" ");
            if ("GET".equals(tokens[0])) {
                responseStaticResources(outputStream, tokens[1], tokens[2]);
            } else {
                // method : POST, DELETE, PUT, HEAD, CONNECT, ...
                // (현재 SimpleHttpServer에서는 무시) GET method 이외의 것이 들어온 경우, Bad Request(400) 응답
                // nio
                File file = new File(BASE_URL+"/error/400.html");
                byte[] body = Files.readAllBytes(file.toPath());
                String contentType = Files.probeContentType(file.toPath());

                outputStream.write((tokens[2] + " 400 Bad Request\n").getBytes(StandardCharsets.UTF_8));
                outputStream.write(("Content-Type:"+contentType+"; charset=utf-8\n").getBytes(StandardCharsets.UTF_8));
                outputStream.write("\n".getBytes());
                outputStream.write(body);

            }
        } catch (Exception ex) {
            consoleLog("error:" + ex);
        } finally {
            // clean-up
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }

            } catch (IOException ex) {
                consoleLog("error:" + ex);
            }
        }
    }

    private void responseStaticResources(OutputStream os, String url, String protocol) throws IOException {
        // default(welcome) file
        File file = new File(BASE_URL + ("/".equals(url) ? "/index.html" : url));

        if (!file.exists()) {
            // 404 response
            file = new File(BASE_URL+"/error/404.html");
            byte[] body = Files.readAllBytes(file.toPath());

            String contentType = Files.probeContentType(file.toPath());
            os.write((protocol + " 404 Not Found\n").getBytes(StandardCharsets.UTF_8));
            os.write(("Content-Type:"+contentType+"; charset=utf-8\n").getBytes(StandardCharsets.UTF_8));
            os.write("\n".getBytes());
            os.write(body);
            return;
        }

        // nio
        byte[] body = Files.readAllBytes(file.toPath());
        String contentType = Files.probeContentType(file.toPath());

        os.write((protocol + " 200 OK\n").getBytes(StandardCharsets.UTF_8));
        os.write(("Content-Type:" + contentType + "; charset=utf-8\n").getBytes(StandardCharsets.UTF_8));
        os.write("\n".getBytes());
        os.write(body);
    }

    public void consoleLog(String message) {
        System.out.println("[RequestHandler#" + threadId() + "] " + message);
    }
}
