package exception;

import java.io.FileInputStream;
import java.io.IOException;

public class FileTest {

    public static void main(String[] args) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("chapter03/hello.txt");
            int data = fis.read();
            System.out.println((char)data);
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
            System.out.println("에러입니다. 죄송합니다.");
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                System.out.println("error: " + e.getMessage());
                System.out.println("에러입니다. 죄송합니당");
            }

        }
    }
}
