package zxh.demo.tw.marsrover;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static zxh.demo.tw.marsrover.MovementCommand.*;
import static zxh.demo.tw.marsrover.MovementCommand.L;
import static zxh.demo.tw.marsrover.Orientation.*;
import static zxh.demo.tw.marsrover.Orientation.EAST;

/**
 * MarsRoverBCommandTest:
 * @author zhangxuhai
 * @date 2020/1/16
*/
public class MarsRoverBCommandTest {
    @Test
    public void should_move_to_1_0_when_mars_rover_take_b_m_at_1_1_north() {
        MarsRover marsRover = new MarsRover(new MarsRover.Position(1, 1, NORTH));
        marsRover.execute(new MovementCommand[]{B, M});

        assertEquals(1, marsRover.getPosition().getX());
        assertEquals(0, marsRover.getPosition().getY());
    }

    @Test
    public void should_move_to_1_2_when_mars_rover_take_b_m_at_1_1_south() {
        MarsRover marsRover = new MarsRover(new MarsRover.Position(1, 1, SOUTH));
        marsRover.execute(new MovementCommand[]{B, M});

        assertEquals(1, marsRover.getPosition().getX());
        assertEquals(2, marsRover.getPosition().getY());
    }

    @Test
    public void should_move_to_2_1_when_mars_rover_take_b_m_at_1_1_west() {
        MarsRover marsRover = new MarsRover(new MarsRover.Position(1, 1, WEST));
        marsRover.execute(new MovementCommand[]{B, M});

        assertEquals(2, marsRover.getPosition().getX());
        assertEquals(1, marsRover.getPosition().getY());
    }

    @Test
    public void should_move_to_0_1_when_mars_rover_take_b_m_at_1_1_east() {
        MarsRover marsRover = new MarsRover(new MarsRover.Position(1, 1, EAST));
        marsRover.execute(new MovementCommand[]{B, M});

        assertEquals(0, marsRover.getPosition().getX());
        assertEquals(1, marsRover.getPosition().getY());
    }

    @Test
    public void should_turn_to_west_when_take_b_r_at_north() {
        MarsRover marsRover = new MarsRover(new MarsRover.Position(0, 0, NORTH));
        marsRover.execute(new MovementCommand[]{R});

        assertEquals(WEST, marsRover.getPosition().getOrientation());
    }

    @Test
    public void should_turn_to_east_when_take_b_r_at_south() {
        MarsRover marsRover = new MarsRover(new MarsRover.Position(0, 0, SOUTH));
        marsRover.execute(new MovementCommand[]{R});

        assertEquals(EAST, marsRover.getPosition().getOrientation());
    }

    @Test
    public void should_turn_to_east_when_take_b_l_at_north() {
        MarsRover marsRover = new MarsRover(new MarsRover.Position(0, 0, NORTH));
        marsRover.execute(new MovementCommand[]{L});

        assertEquals(EAST, marsRover.getPosition().getOrientation());
    }

    @Test
    public void should_turn_to_west_when_take_b_l_at_south() {
        MarsRover marsRover = new MarsRover(new MarsRover.Position(0, 0, SOUTH));
        marsRover.execute(new MovementCommand[]{L});

        assertEquals(WEST, marsRover.getPosition().getOrientation());
    }
}
