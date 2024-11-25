package chapter04;

public class ObjectTest01 {

    public static void main(String[] args) {
        Point point = new Point();

        Class klass = point.getClass();         // reflection
        System.out.println(klass);

        System.out.println(point.hashCode());   // address 기반의 해싱 값
                                                // address X, reference 값 X

        System.out.println(point.toString());   //getClass().toString() + "@" + hashCode()
        System.out.println(point);
    }
}
