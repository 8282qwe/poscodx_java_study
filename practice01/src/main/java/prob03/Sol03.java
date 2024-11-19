package prob03;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Sol03 {

    public static void main(String[] args) {

        /* 코드 작성 */
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.printf("수를 입력하세요: (\"_\" 입력시 종료) ");
            String s = in.nextLine();
            if (s.equals("_")) break;

            int target = Integer.parseInt(s);
            int[] sum = IntStream.range(1,target+1).toArray();

            if (target % 2 == 0) System.out.println("결과값: "+Arrays.stream(sum).filter(i -> i % 2 == 0).sum());
            else System.out.println("결과값: "+Arrays.stream(sum).filter(i -> i % 2 == 1).sum());
        }
    }
}
