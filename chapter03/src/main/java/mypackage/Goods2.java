package mypackage;

public class Goods2 {
    public String name;     // 모든 접근이 가능하다(접근 제한이 없다.)
    protected int price;    // 같은 패키지 + 자식 클래스 접근이 가능
    int countStock;         // 같은 패키지 에서만(default)
    private int countSold;  // 클래스 내부에서만 접근 가능

    public void m() {
        countSold = 10;
    }


    @Override
    public String toString() {
        return "chapter03.Goods{" +
                "상품이름: '" + name + '\'' +
                ", 가격: " + price +
                ", 재고: " + countStock +
                ", 가격: " + countSold +
                '}';
    }
}
