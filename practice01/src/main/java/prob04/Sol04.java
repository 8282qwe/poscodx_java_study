package prob04;

import java.util.Scanner;

public class Sol04 {

	public static void main(String[] args) {
		
		/* 코드 작성 */
		
		Scanner scanner = new Scanner(System.in);
		System.out.printf("문자열을 입력하세요: ");
		String target = scanner.nextLine();
		StringBuilder answer = new StringBuilder();
		for (char c : target.toCharArray()) {
			answer.append(c);
			System.out.println(answer);
		}
		scanner.close();
	}
}