package collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add("둘리");
        list.add("마이콜");
        list.add("또치");

        // 순회 1
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        // 삭제
        list.remove(2);

        // 순회 2
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        // 순회 3
        for (String s : list) {
            System.out.println(s);
        }
    }
}
