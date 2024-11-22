package com.poscodx.pain.main;

import com.poscodx.pain.i.Drawable;
import com.poscodx.pain.point.ColorPoint;
import com.poscodx.pain.point.Point;
import com.poscodx.pain.shape.Circle;
import com.poscodx.pain.shape.Rectangle;
import com.poscodx.pain.shape.Shape;
import com.poscodx.pain.shape.Triangle;
import com.poscodx.pain.text.GraphicText;

public class ShapeTest {
    public static void main(String[] args) {
        Point p1 = new Point();

        p1.setX(10);
        p1.setY(20);

//        p1.show();
        draw(p1);

        Point p2 = new Point(100,200);
//        p2.show(true);
        draw(p2);
        p2.show(false);

        ColorPoint p3 = new ColorPoint(50,100,"red");
//        p3.setX(50);
//        p3.setY(100);
        p3.setColor("red");

//        drawColorPoint(p3);
//        drawPoint(p3);
//        drawShape(new Triangle());
//        drawShape(new Rectangle());
//        drawShape(new Circle());

        draw(new Triangle());
        draw(new Rectangle());
        draw(new Circle());
        draw(new GraphicText("안녕 "));

        // instanceof 연산자
        Circle c = new Circle();

        System.out.println(c instanceof Circle);
        System.out.println(c instanceof Shape);
        System.out.println(c instanceof Object);

        // 연산자 우측항이 인터페이스 인 경우
        // hierachy 상관없이 연산자를 사용할 수 있다.
        System.out.println(c instanceof Drawable);
        System.out.println(c instanceof Runnable);
    }

    public static void draw(Drawable drawable) {
        drawable.draw();
    }

//    public static void drawPoint(Point point) {
//        point.show();
//    }
//
//    public static void drawShape(Shape shape) {
//        shape.draw();
//    }

//    public static void drawColorPoint(ColorPoint colorPoint) {
//        colorPoint.show();
//    }

    public static void drawTriangle(Triangle triangle) {
        triangle.draw();
    }

    public static void drawRectangle(Rectangle rectangle) {
        rectangle.draw();
    }
}
