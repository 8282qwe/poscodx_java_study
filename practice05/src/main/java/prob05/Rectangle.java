package prob05;

public class Rectangle extends Shape implements Resizable{
    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void resize(double rate) {
        this.width = this.width * rate;
        this.height = this.height * rate;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return (width + height) * 2;
    }
}
