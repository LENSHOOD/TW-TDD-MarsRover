package zxh.demo.tw.marsrover;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

import static zxh.demo.tw.marsrover.MovementCommand.*;
import static zxh.demo.tw.marsrover.Orientation.*;

/**
 * MarsRover:
 *
 * @author zhangxuhai
 * @date 2020/1/14
 */
public class MarsRover {
    @Getter
    public static class Position {
        private int x;
        private int y;
        private Orientation orientation;

        public Position(int x, int y, Orientation orientation) {
            this.x = x;
            this.y = y;
            this.orientation = Objects.requireNonNull(orientation, "Orientation cannot be null.");
        }
    }

    private Position position;
    private static Table<MovementCommand, Orientation, Consumer<Position>> operations = HashBasedTable.create();
    static {
        operations.put(M, NORTH, p -> p.y = move(p.y, 1));
        operations.put(M, SOUTH, p -> p.y = move(p.y, -1));
        operations.put(M, EAST, p -> p.x = move(p.x, 1));
        operations.put(M, WEST, p -> p.x = move(p.x, -1));

        Arrays.stream(Orientation.values()).forEach(o ->
                operations.put(R, o, p -> p.orientation = Orientation.values()[(o.ordinal() + 1) % 4]));

        Arrays.stream(Orientation.values()).forEach(o ->
                operations.put(L, o, p -> p.orientation = Orientation.values()[(o.ordinal() + 3) % 4]));
    }

    public MarsRover(Position position) {
        this.position = Objects.requireNonNull(position);
    }

    public Position getPosition() {
        return position;
    }

    public void execute(MovementCommand[] movementCommands) {
        Arrays.stream(movementCommands).forEach(cmd -> operations.get(cmd, position.orientation).accept(position));
    }

    private static int move(int initial, int step) {
        boolean isOutOfUpBound = initial == Integer.MAX_VALUE && step > 0;
        boolean isOutOfDownBound = initial == -Integer.MAX_VALUE && step < 0;
        if (isOutOfUpBound || isOutOfDownBound) {
            throw new MarsRoverOutOfBoundaryException();
        }

        return initial + step;
    }
}
