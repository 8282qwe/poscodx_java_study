package collection;

import java.util.Stack;

public class StackTest {

    public static void main(String[] args) {
        Stack<String> s = new Stack<>();

        s.push("둘리");
        s.push("마이콜");
        s.push("또치");

        while (!s.isEmpty()) {
            System.out.println(s.pop());
        }

        // 비어 있는 경우
//        s.peek();

        s.push("둘리");
        s.push("마이콜");
        s.push("또치");

        System.out.println(s.pop());
        System.out.println(s.peek());
    }
}
