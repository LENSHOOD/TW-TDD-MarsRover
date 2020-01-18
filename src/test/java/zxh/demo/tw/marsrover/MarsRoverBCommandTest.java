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
        MarsRover marsRover = new MarsRover(new PostureStatus(1, 1, NORTH, false));
        marsRover.execute(new MovementCommand[]{B, M});

        assertEquals(1, marsRover.getPostureStatus().getX());
        assertEquals(0, marsRover.getPostureStatus().getY());
    }

    @Test
    public void should_move_to_1_2_when_mars_rover_take_b_m_at_1_1_south() {
        MarsRover marsRover = new MarsRover(new PostureStatus(1, 1, SOUTH, false));
        marsRover.execute(new MovementCommand[]{B, M});

        assertEquals(1, marsRover.getPostureStatus().getX());
        assertEquals(2, marsRover.getPostureStatus().getY());
    }

    @Test
    public void should_move_to_2_1_when_mars_rover_take_b_m_at_1_1_west() {
        MarsRover marsRover = new MarsRover(new PostureStatus(1, 1, WEST, false));
        marsRover.execute(new MovementCommand[]{B, M});

        assertEquals(2, marsRover.getPostureStatus().getX());
        assertEquals(1, marsRover.getPostureStatus().getY());
    }

    @Test
    public void should_move_to_0_1_when_mars_rover_take_b_m_at_1_1_east() {
        MarsRover marsRover = new MarsRover(new PostureStatus(1, 1, EAST, false));
        marsRover.execute(new MovementCommand[]{B, M});

        assertEquals(0, marsRover.getPostureStatus().getX());
        assertEquals(1, marsRover.getPostureStatus().getY());
    }

    @Test
    public void should_turn_to_west_when_take_b_r_at_north() {
        MarsRover marsRover = new MarsRover(new PostureStatus(0, 0, NORTH, false));
        marsRover.execute(new MovementCommand[]{B, R});

        assertEquals(WEST, marsRover.getPostureStatus().getOrientation());
    }

    @Test
    public void should_turn_to_east_when_take_b_r_at_south() {
        MarsRover marsRover = new MarsRover(new PostureStatus(0, 0, SOUTH, false));
        marsRover.execute(new MovementCommand[]{B, R});

        assertEquals(EAST, marsRover.getPostureStatus().getOrientation());
    }

    @Test
    public void should_turn_to_east_when_take_b_l_at_north() {
        MarsRover marsRover = new MarsRover(new PostureStatus(0, 0, NORTH, false));
        marsRover.execute(new MovementCommand[]{B, L});

        assertEquals(EAST, marsRover.getPostureStatus().getOrientation());
    }

    @Test
    public void should_turn_to_west_when_take_b_l_at_south() {
        MarsRover marsRover = new MarsRover(new PostureStatus(0, 0, SOUTH, false));
        marsRover.execute(new MovementCommand[]{B, L});

        assertEquals(WEST, marsRover.getPostureStatus().getOrientation());
    }

    @Test
    public void should_at_last_2_3_east__when_take_m_r_m_b_m_l_m_l_m_l_b_m_r_m_at_0_0_north() {
        MarsRover marsRover = new MarsRover(new PostureStatus(0, 0, NORTH, false));
        marsRover.execute(new MovementCommand[]{M, R, M, B, M, L, M, L, M, L, B, M, R, M});

        assertEquals(2, marsRover.getPostureStatus().getX());
        assertEquals(3, marsRover.getPostureStatus().getY());
        assertEquals(EAST, marsRover.getPostureStatus().getOrientation());
    }
}
