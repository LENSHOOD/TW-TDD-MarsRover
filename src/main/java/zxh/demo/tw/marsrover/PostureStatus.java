package zxh.demo.tw.marsrover;

import lombok.Getter;

import java.util.Objects;

/**
 * Position:
 * @author zhangxuhai
 * @date 2020/1/18
*/
@Getter
public class PostureStatus {
    private int x;
    private int y;
    private Orientation orientation;
    private boolean isBack;

    public PostureStatus(int x, int y, Orientation orientation, boolean isBack) {
        this.x = x;
        this.y = y;
        this.orientation = Objects.requireNonNull(orientation, "Orientation cannot be null.");
        this.isBack = isBack;
    }
}
