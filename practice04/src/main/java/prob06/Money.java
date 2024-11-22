package prob06;

public class Money {
	private int amount;
	
	/* 코드 작성 */

	public Money(int amount) {
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

	public Money add(Money money) {
		return new Money(amount + money.getAmount());
	}
	public Money minus(Money money) {
		return new Money(amount - money.getAmount());
	}
	public Money multiply(Money money) {
		return new Money(amount * money.getAmount());
	}
	public Money divide(Money money) {
		return new Money(amount / money.getAmount());
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Money && ((Money) obj).getAmount() == amount;
	}
}
