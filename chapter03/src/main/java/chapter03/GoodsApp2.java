package chapter03;

import mypackage.Goods2;

public class GoodsApp2 {
    public static void main(String[] args) {
        Goods2 goods2 = new Goods2();

        // public은 접근 제한이 없다.
        goods2.name = "camera";

        // protected는 같은 패키지에서 접근이 가능하다.
        //goods2.price = 10000;

        // default는 같은 패키지에서 접근이 가능하다.
        //goods2.countStock = 10;

        // private는 클래스 내부에서만 접근이 가능하다.
        goods2.m();

        System.out.println(goods2);
    }
}
