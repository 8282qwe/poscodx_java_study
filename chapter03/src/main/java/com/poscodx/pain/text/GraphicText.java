package com.poscodx.pain.text;

import com.poscodx.pain.i.Drawable;

public class GraphicText implements Drawable {
    private String text;

    public GraphicText(String text) {
        this.text = text;
    }

    @Override
    public void draw() {
        System.out.println("데스트 '"+text+"'를 그렸습니다.");
    }
}
