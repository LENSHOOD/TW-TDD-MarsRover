package zxh.demo.tw.marsrover.cmd;

import zxh.demo.tw.marsrover.MarsRoverOutOfBoundaryException;
import zxh.demo.tw.marsrover.Orientation;
import zxh.demo.tw.marsrover.PostureStatus;

import java.util.Map;
import java.util.function.Function;

import static zxh.demo.tw.marsrover.Orientation.*;

/**
 * Move:
 * @author zhangxuhai
 * @date 2020/1/18
*/
public class Move extends Command {
    private static Map<Orientation, Function<PostureStatus, PostureStatus>> moveCmds = Map.of(
            NORTH, p -> new PostureStatus(p.getX(), move(p.getY(), 1, p.isBack()), p.getOrientation(), p.isBack()),
            EAST, p -> new PostureStatus(move(p.getX(), 1, p.isBack()), p.getY(), p.getOrientation(), p.isBack()),
            SOUTH, p -> new PostureStatus(p.getX(), move(p.getY(), -1, p.isBack()), p.getOrientation(), p.isBack()),
            WEST, p -> new PostureStatus(move(p.getX(), -1, p.isBack()), p.getY(), p.getOrientation(), p.isBack())
    );

    @Override
    protected PostureStatus execute(PostureStatus p) {
        return moveCmds.get(p.getOrientation()).apply(p);
    }

    private static int move(int initial, int step, boolean isBack) {
        boolean isOutOfUpBound = initial == Integer.MAX_VALUE && step > 0;
        boolean isOutOfDownBound = initial == -Integer.MAX_VALUE && step < 0;
        if (isOutOfUpBound || isOutOfDownBound) {
            throw new MarsRoverOutOfBoundaryException();
        }

        return initial + (isBack ? -step : step);
    }
}
