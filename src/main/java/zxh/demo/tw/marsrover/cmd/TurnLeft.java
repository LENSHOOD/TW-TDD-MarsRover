package zxh.demo.tw.marsrover.cmd;

import zxh.demo.tw.marsrover.Orientation;
import zxh.demo.tw.marsrover.PostureStatus;

/**
 * TurnLeft:
 * @author zhangxuhai
 * @date 2020/1/18
*/
public class TurnLeft extends Command {
    @Override
    protected PostureStatus execute(PostureStatus p) {
        Orientation orientation = p.isBack()
                ? Orientation.values()[(p.getOrientation().ordinal() + 1) % Orientation.values().length]
                : Orientation.values()[
                        (p.getOrientation().ordinal() + Orientation.values().length - 1) % (Orientation.values().length)];
        return new PostureStatus(p.getX(), p.getY(), orientation, p.isBack());
    }
}
