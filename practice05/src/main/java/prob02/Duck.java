package prob02;

public class Duck extends Bird{
    @Override
    void fly() {
        System.out.printf("오리(%s)는 날지 않습니다.\n",name);
    }

    @Override
    void sing() {
        System.out.printf("오리(%s)는 소리내어 웁니다.\n",name);
    }

    @Override
    public String toString() {
        return "오리의 이름은 " + name + " 입니다";
    }
}
