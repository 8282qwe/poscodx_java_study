package io;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedOutputStreamTest {

    public static void main(String[] args) {

        BufferedOutputStream bos = null;

        try {
            //데코레이터 패턴 = 케이크 위에 장식
            // 기반 스트림 = decorator
            // 기반 스트림
            FileOutputStream fos = new FileOutputStream("chapter04/hello.txt");

            // 보조 스트림
            bos = new BufferedOutputStream(fos);

            // for(char i = 'A'; i <= 'Z'; i++)
            for (int i = 65; i <= 90; i++) {
                bos.write(i);
            }


        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException" + e);
        } catch (IOException e) {
            System.out.println("IOException" + e);
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                System.out.println("IOException" + e);
            }
        }
    }
}
