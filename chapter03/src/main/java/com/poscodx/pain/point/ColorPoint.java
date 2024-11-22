package com.poscodx.pain.point;

public class ColorPoint extends Point {
    private String color;

    @Override
    public void draw() {
        this.show();
    }

    public ColorPoint(int x, int y, String color) {
        super(x, y);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void show() {
        System.out.printf("점[x=%d,y=%d,color=%s]을 그렸습니다.\n", getX(), getY(), color);
    }
}
