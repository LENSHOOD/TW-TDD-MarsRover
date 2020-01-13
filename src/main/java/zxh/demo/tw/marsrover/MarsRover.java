package zxh.demo.tw.marsrover;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

import static zxh.demo.tw.marsrover.OperationCmd.*;
import static zxh.demo.tw.marsrover.Orientation.*;

/**
 * MarsRover:
 *
 * @author zhangxuhai
 * @date 2020/1/13
 */
public class MarsRover {
    public static class Posture {
        private int x;
        private int y;
        private Orientation orientation;

        public Posture(int x, int y, Orientation orientation) {
            this.x = x;
            this.y = y;
            this.orientation = Objects.requireNonNull(orientation);
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

    private Mars mars;
    private Posture posture;
    private Table<OperationCmd, Orientation, Consumer<Posture>> operations = HashBasedTable.create();
    {
        operations.put(M, EAST, p -> p.x = checkPosition(p.getX() + 1));
        operations.put(M, WEST, p -> p.x = checkPosition(p.getX() - 1));
        operations.put(M, NORTH, p -> p.y = checkPosition(p.getY() + 1));
        operations.put(M, SOUTH, p -> p.y = checkPosition(p.getY() - 1));

        operations.put(L, EAST, p -> p.orientation = NORTH);
        operations.put(L, NORTH, p -> p.orientation = WEST);
        operations.put(L, WEST, p -> p.orientation = SOUTH);
        operations.put(L, SOUTH, p -> p.orientation = EAST);

        operations.put(R, EAST, p -> p.orientation = SOUTH);
        operations.put(R, SOUTH, p -> p.orientation = WEST);
        operations.put(R, WEST, p -> p.orientation = NORTH);
        operations.put(R, NORTH, p -> p.orientation = EAST);
    }

    public void landing(Mars mars, Posture posture) {
        this.mars = Objects.requireNonNull(mars);
        Posture p = Objects.requireNonNull(posture);
        Preconditions.checkArgument(p.getX() <= mars.getSize() && p.getY() <= mars.getSize(),
                "Landing position out of boundary, the boundary is" + mars.getSize());
        this.posture = p;
    }

    public Posture getPosture() {
        return posture;
    }

    public void executeCmds(OperationCmd[] operationCmds) {
        Arrays.stream(operationCmds).forEach(cmd -> operations.get(cmd, posture.orientation).accept(posture));
    }

    private int checkPosition(int position) {
        if (position > mars.getSize()) {
            throw new MovementOutOfBoundaryException();
        }

        return position;
    }
}
