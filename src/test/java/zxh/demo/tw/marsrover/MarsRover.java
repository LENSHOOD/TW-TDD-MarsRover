package zxh.demo.tw.marsrover;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Objects;
import java.util.function.Consumer;

import static com.google.common.base.Preconditions.checkArgument;
import static zxh.demo.tw.marsrover.OperationCmd.*;

/**
 * MarsRover:
 *
 * @author zhangxuhai
 * @date 2020/1/13
 */
public class MarsRover {
    private Mars mars;
    private Posture posture;
    private EnumMap<OperationCmd, Consumer<Posture>> operations = Maps.newEnumMap(OperationCmd.class);
    {
        operations.put(M, p -> {
            switch (p.getOrientation()) {
                case EAST: {
                    p.setX(checkPosition(p.getX() + 1));
                    break;
                }
                case WEST: {
                    p.setX(checkPosition(p.getX() - 1));
                    break;
                }
                case NORTH: {
                    p.setY(checkPosition(p.getY() + 1));
                    break;
                }
                case SOUTH: {
                    p.setY(checkPosition(p.getY() - 1));
                    break;
                }
            }
        });

        operations.put(L, p -> {
            int index = p.getOrientation().ordinal();
            int newIndex = (index == 0 ? Orientation.values().length : index) - 1;
            p.setOrientation(Orientation.values()[newIndex]);
        });

        operations.put(R, p -> {
            int index = p.getOrientation().ordinal();
            int newIndex = index == Orientation.values().length -1  ? 0 : index + 1;
            p.setOrientation(Orientation.values()[newIndex]);
        });
    }

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

    public void executeCmds(OperationCmd[] operationCmds) {
        Arrays.stream(operationCmds).forEach(cmd -> operations.get(cmd).accept(posture));
    }

    private int checkPosition(int position) {
        if (position > mars.getSize()) {
            throw new MovementOutOfBoundaryException();
        }

        return position;
    }
}
