package exception;

import java.io.IOException;

public class Myclass {
    public void danger() throws IOException {
        System.out.println("some code 1...");
        System.out.println("some code 1...");

        if (2-2 == 0 ) throw new IOException("문제발생!!!");

        System.out.println("some code 3...");
        System.out.println("some code 4...");
    }
}
