package zxh.demo.tw.marsrover.cmd;

import zxh.demo.tw.marsrover.PostureStatus;

/**
 * Commad:
 * @author zhangxuhai
 * @date 2020/1/18
*/
public abstract class Command {
    public PostureStatus doCommand(PostureStatus postureStatus) {
        return execute(postureStatus);
    }

    abstract protected PostureStatus execute(PostureStatus p);
}
