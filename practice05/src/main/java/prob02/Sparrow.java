package prob02;

public class Sparrow extends Bird{
    @Override
    void fly() {
        System.out.printf("참새(%s)는 날아다닙니다.\n",name);
    }

    @Override
    void sing() {
        System.out.printf("참새(%s)는 소리내어 웁니다.\n",name);
    }

    @Override
    public String toString() {
        return "참새의 이름은 " + name + " 입니다";
    }
}
