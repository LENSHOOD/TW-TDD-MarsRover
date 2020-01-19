package zxh.demo.tw.marsrover;

import zxh.demo.tw.marsrover.cmd.*;

import java.util.Map;
import java.util.Optional;
import java.util.Random;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;
import static zxh.demo.tw.marsrover.MovementCommand.*;

/**
 * MarsRover:
 *
 * @author zhangxuhai
 * @date 2020/1/14
 */
public class MarsRover {
    private PostureStatus postureStatus;
    private MarsMap marsMap;

    private static Map<MovementCommand, Command> cmdMap = Map.of(
            M, new Move(),
            L, new TurnLeft(),
            R, new TurnRight(),
            B, new Back()
    );

    public MarsRover(PostureStatus postureStatus) {
        this.postureStatus = requireNonNull(postureStatus);
    }

    public PostureStatus getPostureStatus() {
        return postureStatus;
    }

    public Optional<MarsRover> execute(MovementCommand[] movementCommands) {
        for (MovementCommand cmd : movementCommands) {
            PostureStatus backUpPostureStatus = postureStatus;
            postureStatus = cmdMap.get(cmd).doCommand(postureStatus);

            if (nonNull(marsMap) && nonNull(marsMap.get(postureStatus.getX(), postureStatus.getY()))) {
                postureStatus = backUpPostureStatus;
                continue;
            }

            if (isDropIntoDitch()) {
                requireNonNull(marsMap).set(postureStatus.getX(), postureStatus.getY(), this);
                MarsRover newMarsRover = new MarsRover(new PostureStatus(0, 0, Orientation.NORTH, false));
                newMarsRover.setMarsMap(marsMap);
                return Optional.of(newMarsRover);
            }
        }

        return Optional.empty();
    }

    public void setMarsMap(MarsMap marsMap) {
        this.marsMap = marsMap;
    }

    private Random random = new Random();
    boolean isDropIntoDitch() {
        return random.nextInt(1) > 0.5;
    }
}
