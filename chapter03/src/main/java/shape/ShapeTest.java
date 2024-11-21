package shape;

public class ShapeTest {
    public static void main(String[] args) {
        Point p1 = new Point();

        p1.setX(10);
        p1.setY(20);

        p1.show();

        Point p2 = new Point(100,200);
        p2.show(true);
        p2.show(false);
    }
}
