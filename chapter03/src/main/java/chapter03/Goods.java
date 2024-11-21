package chapter03;

public class Goods {
    private String name;
    private int price;
    private int countStock;
    private int countSold;

    public static int countOfGoods = 0;

    public Goods() {
        countOfGoods++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCountStock(int countStock) {
        this.countStock = countStock;
    }

    public void setCountSold(int countSold) {
        this.countSold = countSold;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getCountStock() {
        return countStock;
    }

    public int getCountSold() {
        return countSold;
    }

    public void printInfo() {
        System.out.println("chapter03.Goods{" +
                "상품갯수: '" + countOfGoods + '\''+
                "상품이름: '" + name + '\'' +
                ", 가격: " + price +
                ", 재고: " + countStock +
                ", 가격: " + countSold +
                '}');
    }
}
