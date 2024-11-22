package prob04;

public class MyStackException extends Exception {
    @Override
    public String toString() {
        return "Prob04.MyStackException: stack is empty";
    }
}
