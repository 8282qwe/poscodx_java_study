package prob01;

public class SmartPhone extends MusicPhone {

    public void execute(String function) {
        if (function.equals("앱")) {
            System.out.println("앱 실행");
        }
        else super.execute(function);
    }
}
