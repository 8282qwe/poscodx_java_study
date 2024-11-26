package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderTest {

    public static void main(String[] args) {
        BufferedReader br = null;

        try {
            // 기반 스트림
            FileReader fr = new FileReader("chapter04/src/main/java/io/BufferedReaderTest.java");

            // 보조 스트림
            br = new BufferedReader(fr);

            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

//            간단하게 forEach를 활용하는 방법
//            br.lines().forEach(System.out::println);

        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        } catch (IOException e) {
            System.out.println("I/O exception" + e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing BufferedReader" + e);
            }
        }

    }
}
