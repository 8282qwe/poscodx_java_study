package prob02;

import java.util.Arrays;
import java.util.Scanner;

public class Sol02 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int[] intArray = new int[5];
		double sum = 0;

		/* 코드 작성 */
		System.out.println("5개의 숫자를 입력하세요.");

		for (int i = 0; i < 5; i++) {
			intArray[i] = Integer.parseInt(scanner.nextLine());
		}

		System.out.println("평균은 "+ Arrays.stream(intArray).average().getAsDouble()+"입니다.");
		scanner.close();
	}
}
