package zxh.demo.tw.marsrover;

import java.util.Objects;

/**
 * Posture:
 *
 * @author zhangxuhai
 * @date 2020/1/13
 */
public class Posture {
    private int x;
    private int y;
    private Orientation orientation;

    public Posture(int x, int y, Orientation orientation) {
        this.x = x;
        this.y = y;
        this.orientation = Objects.requireNonNull(orientation);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Orientation getOrientation() {
        return orientation;
    }
}
