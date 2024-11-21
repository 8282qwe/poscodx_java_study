package chapter03;

public class SwapTest03 {

    static class Value {
        private int i;

        public Value(int i) {
            this.i = i;
        }
    }

    public static void main(String[] args) {
        Value value = new Value(10);
        Value value1 = new Value(20);
        System.out.println(value.i +" , "+ value1.i);
        swap(value, value1);
        System.out.println(value.i +" , "+ value1.i);
    }

    public static void swap(Value v1, Value v2) {
        int temp = v1.i;
        v1.i = v2.i;
        v2.i = temp;
    }
}
