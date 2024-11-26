package prob01;

import java.util.Arrays;

public class Printer {

//     제네릭을 활용하여 제네릭 메소드 작성
    public <T> void println(T e) {
        System.out.println(e);
    }

    public int sum(Integer... nums) {
        return Arrays.stream(nums).mapToInt(i -> i).sum();
    }

    @SafeVarargs
    public final <T> void println(T... ts) {
        Arrays.stream(ts).forEach(System.out::println);
    }

//    public void println(String text) {
//        System.out.println(text);
//    }
//    public void println(int text) {
//        System.out.println(text);
//    }
//    public void println(double text) {
//        System.out.println();
//    }
//    public void println(boolean text) {
//        System.out.println(text);
//    }
}
