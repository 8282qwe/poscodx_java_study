package prob04;

public class MyStack03<T> {
    private int top;
    private T[] buffer;

    public MyStack03(int capacity) {
        /* 구현하기 */
        this.top = -1;
        this.buffer = (T[]) new Object[capacity];
    }

    public void push(Object s) {
        /* 구현하기 */
        if (top == buffer.length - 1) {
            resize();
        }
        buffer[++top] = (T) s;
    }

    public T pop() throws MyStackException {
        /* 구현하기 */
        if (isEmpty()) {
            throw new MyStackException();
        }
        return buffer[top--];
    }

    public boolean isEmpty() {
        /* 구현하기 */
        return top == -1;
    }

    private void resize() {
        /* 구현하기 */
        T[] newBuffer = (T[])new Object[buffer.length * 2];
        System.arraycopy(buffer, 0, newBuffer, 0, buffer.length);
        buffer = newBuffer;
    }
}