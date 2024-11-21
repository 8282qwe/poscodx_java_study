package prob02;

public class Goods {
	private String name;
	private int price;
	private int stock;

	public Goods(String name, int price, int stock) {
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	public void printInfo() {
		System.out.printf("%s(가격: %d원)이 %d개 입고 되었습니다.\n",name,price,stock);
	}
}