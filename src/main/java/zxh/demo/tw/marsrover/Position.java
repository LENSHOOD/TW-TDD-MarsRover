package zxh.demo.tw.marsrover;

import java.util.Objects;

/**
 * Position:
 *
 * @author zhangxuhai
 * @date 2020/1/14
 */
public class Position {
    private int x;
    private int y;
    private Orientation orientation;

    public Position(int x, int y, Orientation orientation) {
        this.x = x;
        this.y = y;
        this.orientation = Objects.requireNonNull(orientation, "Orientation cannot be null.");
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
