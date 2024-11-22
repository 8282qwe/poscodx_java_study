package prob04;

public class MyStack {
	private int top;
	private String[] buffer;

	public MyStack(int capacity) {
		/* 구현하기 */
		this.buffer = new String[capacity];
	}

	public void push(String s) {
		/* 구현하기 */
		if (top == buffer.length) {
			resize();
		}
		buffer[top] = s;
		top++;
	}

	public String pop() throws MyStackException {
		/* 구현하기 */
		if (top == 0) {
			throw new MyStackException();
		}
		return buffer[--top];
	}

	public boolean isEmpty() {
		/* 구현하기 */
		return top == 0;
	}

	private void resize() {
		/* 구현하기 */
		String[] newBuffer = new String[buffer.length * 2];
		System.arraycopy(buffer, 0, newBuffer, 0, buffer.length);
		buffer = newBuffer;

	}	
}