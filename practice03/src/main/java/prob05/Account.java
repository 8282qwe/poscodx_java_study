package prob05;

public class Account {
    private String accountNo;
    private int balance;

    public Account(String accountNo) {
        this.accountNo = accountNo;
        this.balance = 0;
        System.out.println(accountNo + " 계좌가 개설 되었습니다.");
    }

    public void save(int balance) {
        this.balance += balance;
        System.out.printf("%s 계좌에 %d만원이 입금 되었습니다.\n",accountNo,balance);
    }

    public void deposit(int amount) {
        this.balance -= amount;
        System.out.printf("%s 계좌에 %d만원이 출금 되었습니다.\n",accountNo,amount);
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
