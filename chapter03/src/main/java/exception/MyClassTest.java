package exception;

import java.io.IOException;

public class MyClassTest {

    public static void main(String[] args) {
        try {
            new Myclass().danger();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
