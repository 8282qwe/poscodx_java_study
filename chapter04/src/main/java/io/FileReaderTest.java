package io;

import java.io.*;

public class FileReaderTest {

    public static void main(String[] args) {
        Reader in = null;
        InputStream is = null;

        try {
            in = new FileReader("chapter04/test.txt");
            is = new FileInputStream("chapter04/test.txt");

            int count = 0;
            int data = -1;
            while ((data = in.read()) != -1) {
                System.out.print((char)data);
                count++;
            }

            System.out.println();
            System.out.println("count: " + count);
            System.out.println("=".repeat(25));

            count = 0;
            while ((data = is.read()) != -1) {
                System.out.print((char)data);
                count++;
            }
            System.out.println();
            System.out.println("count: " + count);

        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        } catch (IOException e) {
            System.out.println("I/O exception" + e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                System.out.println("I/O exception" + e);
            }
        }
    }
}
