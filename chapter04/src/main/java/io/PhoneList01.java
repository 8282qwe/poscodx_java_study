package io;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class PhoneList01 {

    public static void main(String[] args) {
        BufferedReader br = null;

        try {
            File file = new File("chapter04/Phone.txt");
            if (!file.exists()) {
                System.out.println("File Not Found");
                return;
            }

            System.out.println("== 파일 정보 ==");
            System.out.println(file.getAbsolutePath());
            System.out.println(file.length() + " bytes");
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(file.lastModified())));

            System.out.println("== 전화번호 ==");

            // 1. 기반 스트림
            FileInputStream fis = new FileInputStream(file);

            // 2. 보조 스트림
            InputStreamReader isr = new InputStreamReader(fis);

            // 3. 보조 스트림 2
            br = new BufferedReader(isr);

            // 4. 처리
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, "\t ");
                int index = 0;
                while (st.hasMoreElements()) {
                    String token = st.nextToken();
                    if (index == 0) {
                        System.out.print( token + " : ");
                    } else if (index == 1) { // 전화번호 1
                        System.out.print(token + "-");
                    }else if (index == 2) { // 전화번호 2
                        System.out.print(token + "-");
                    }else {
                        System.out.print(token + "\n");
                    }
                    index++;
                }
            }

        } catch (IOException e) {
            System.out.println("error" + e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("error" + e);
            }
        }

    }
}
