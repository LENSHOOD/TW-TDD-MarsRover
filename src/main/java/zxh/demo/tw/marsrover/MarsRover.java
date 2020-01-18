package zxh.demo.tw.marsrover;

import zxh.demo.tw.marsrover.cmd.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import static zxh.demo.tw.marsrover.MovementCommand.*;

/**
 * MarsRover:
 *
 * @author zhangxuhai
 * @date 2020/1/14
 */
public class MarsRover {
    private PostureStatus postureStatus;

    private static Map<MovementCommand, Command> cmdMap = Map.of(
            M, new Move(),
            L, new TurnLeft(),
            R, new TurnRight(),
            B, new Back()
    );

    public MarsRover(PostureStatus postureStatus) {
        this.postureStatus = Objects.requireNonNull(postureStatus);
    }

    public PostureStatus getPostureStatus() {
        return postureStatus;
    }

    public void execute(MovementCommand[] movementCommands) {
        Arrays.stream(movementCommands).forEach(cmd -> postureStatus = cmdMap.get(cmd).doCommand(postureStatus));
    }
}
