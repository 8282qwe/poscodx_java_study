package com.poscodx.pain.shape;

public class Circle extends Shape {
    private double radius;

    @Override
    public void draw() {
        System.out.println("원을 그렸습니다.");
    }
}
