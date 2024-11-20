package prob01;

import java.util.Scanner;

public final class Sol01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final int[] MONEYS = {50000, 10000, 5000, 1000, 500, 100, 50, 10, 5, 1};
        int money = Integer.parseInt(scanner.nextLine());
        /* 코드 작성 */
        for (int coin : MONEYS) {
            System.out.println(coin+"원 : "+money/coin+"개");
            money%=coin;
        }
        scanner.close();
    }
}