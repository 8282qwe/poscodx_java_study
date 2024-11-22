package exception;

public class ExceptionTest {
    public static void main(String[] args) {
        try {
            int a = 9;
            int b = 10 - a;
            int result = (1 + 2 + 3) / b;
        } catch (ArithmeticException e) {
            // 예외처리
            // 1. 로깅
            System.out.println("error:" + e);

            // 2.사과
            System.out.println("미안합니다...");

            // 3. 정상 종료ß
            return;
        } finally {
            System.out.println("자원정리");
        }
    }
}
