package zxh.demo.tw.marsrover;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * MarsRover:
 * @author zhangxuhai
 * @date 2020/1/13
*/
public class MarsRover {
    private Mars mars;
    private Posture posture;

    public void landing(Mars mars, Posture posture) {
        this.mars = Objects.requireNonNull(mars);
        Posture p = Objects.requireNonNull(posture);
        checkArgument(p.getX() <= mars.getSize() && p.getY() <= mars.getSize(),
                "Landing position out of boundary, the boundary is" + mars.getSize());
        this.posture = p;
    }

    public Posture getPosture() {
        return posture;
    }
}
