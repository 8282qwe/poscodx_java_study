package creational.singleton;

public class Singleton {
    private static final Singleton instance = new Singleton();

    // 생성자에 private를 활용함으로써 외부에서 생성자 호출이 불가능하게 함
    private Singleton() {
    }

    public static Singleton getInstance() {
        return instance;
    }
}
