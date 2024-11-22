package com.poscodx.pain.point;

import com.poscodx.pain.i.Drawable;

public class Point implements Drawable {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void show() {
        System.out.printf("점[x=%d,y=%d]을 그렸습니다.\n", x, y);
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
    }

    public void show(boolean visible) {
        if (visible) {
            this.show();
        } else System.out.printf("점[x=%d,y=%d]을 지웠습니다.\n", x, y);
    }

    @Override
    public void draw() {
        show();
    }
}
