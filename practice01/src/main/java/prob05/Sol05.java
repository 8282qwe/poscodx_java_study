package prob05;

public class Sol05 {
	public static void main(String[] args) {

		/* 코드 작성 */
		for (int i = 1; i <= 100; i++) {
			int count = (int) String.valueOf(i).chars().filter(c -> (c == '3' || c == '6' || c == '9')).count();
			if (count != 0) System.out.println(i+" "+ "짝".repeat(count));
		}
	}
}
