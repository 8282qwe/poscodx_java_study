package chapter03;

public class GoodsApp {

    public static void main(String[] args) {
        Goods goods = new Goods();

//        goods.name = "nikon";
//        goods.price = 400000;
//        goods.countSold = 10;
//        goods.countStock = 20;
        goods.setName("nikon");
        goods.setPrice(400000);
        goods.setCountSold(10);
        goods.setCountStock(20);

        goods.printInfo();

        Goods goods2 = new Goods();
        Goods goods3 = new Goods();

        goods2.printInfo();
        goods3.printInfo();
    }
}
