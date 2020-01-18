package zxh.demo.tw.marsrover.cmd;

import zxh.demo.tw.marsrover.PostureStatus;

/**
 * Back:
 * @author zhangxuhai
 * @date 2020/1/18
*/
public class Back extends Command {
    @Override
    protected PostureStatus execute(PostureStatus p) {
        return new PostureStatus(p.getX(), p.getY(), p.getOrientation(), !p.isBack());
    }
}
