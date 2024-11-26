package collection;

import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {

    public static void main(String[] args) {
        Queue<String> q = new LinkedList<>();

        q.offer("둘리");
        q.offer("마이콜");
        q.offer("또치");

        while (!q.isEmpty()) {
            System.out.println(q.poll());
        }

        // 큐 안에 아무것도 안들어 있으면 null 반환
        System.out.println(q.poll());

        q.offer("둘리");
        q.offer("마이콜");
        q.offer("또치");

        System.out.println(q.poll());
        System.out.println(q.peek());
        System.out.println(q.poll());
    }
}
