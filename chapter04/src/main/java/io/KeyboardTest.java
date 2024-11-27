package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class KeyboardTest {

    public static void main(String[] args) {
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {
            // 1. 기반 스트림(표준 입력, stdin, System.in)

            // 2. 보조 스트림 (byte|byte|byte -> char)
            isr = new InputStreamReader(System.in, StandardCharsets.UTF_8);

            // 3. 보조스트림 (char1|char2|char3|\n -> "char1char2char3)
            br = new BufferedReader(isr);

            String line = null;
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                if ("quit".equals(line)) {
                    break;
                }
                System.out.println(line);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
