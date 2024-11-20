package prob06;

import java.util.Random;
import java.util.Scanner;

public class Sol06 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {

            // 정답 램덤하게 만들기
            Random random = new Random();
            int correctNumber = random.nextInt(100) + 1;
            System.out.println("수를 결정하였습니다. 맞추어 보세요.");

            /* 게임 작성 */
            int gameCount = 1;
            int start = 1;
            int end = 100;
            while (true) {
                System.out.println(start + "-" + end);
                System.out.printf(gameCount + ">>");
                int guessNum = Integer.parseInt(scanner.nextLine());
                if (guessNum < start || guessNum > end) {
                    System.out.println("잘못된 입력입니다.");
                    continue;
                }

                if (guessNum == correctNumber) break;
                else if (guessNum < correctNumber) {
                    start = guessNum + 1;
                    System.out.println("더 높게");
                } else {
                    end = guessNum - 1;
                    System.out.println("더 낮게");
                }
                gameCount++;
            }

            System.out.println("정답은 : "+correctNumber+"입니다.");
            //새 게임 여부 확인하기
            System.out.print("다시 하겠습니까(y/n)>>");
            String answer = scanner.nextLine().toLowerCase();
            if (!"y".equals(answer)) {
                break;
            }
        }

        scanner.close();
    }
}