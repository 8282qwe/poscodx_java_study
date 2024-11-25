package chapter04;

import java.util.Objects;

public class Rect {
    private int w;
    private int h;

    public Rect(int w, int h) {
        this.w = w;
        this.h = h;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Rect rect = (Rect) o;
        return w == rect.w && h == rect.h;
    }

    @Override
    public int hashCode() {
        return Objects.hash(w, h);
    }

    @Override
    public String toString() {
        return "Rect [" +
                "w=" + w +
                ", h=" + h +
                ']';
    }

}
