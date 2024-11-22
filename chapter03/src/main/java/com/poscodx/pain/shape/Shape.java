package com.poscodx.pain.shape;

import com.poscodx.pain.i.Drawable;
import com.poscodx.pain.point.Point;

public abstract class Shape implements Drawable {
    private Point[] points;
    private String lineColor;
    private String fillColor;

    public abstract void draw();
}
