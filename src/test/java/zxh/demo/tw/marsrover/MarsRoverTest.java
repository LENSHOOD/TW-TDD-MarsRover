package zxh.demo.tw.marsrover;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static zxh.demo.tw.marsrover.MovementCommand.*;
import static zxh.demo.tw.marsrover.Orientation.*;

/**
 * MarsRoverTest:
 * @author zhangxuhai
 * @date 2020/1/14
*/
public class MarsRoverTest {
    @Test
    public void should_return_mars_rover_init_position_as_0_0_north() {
        MarsRover marsRover = new MarsRover(new MarsRover.Position(0, 0, NORTH));

        assertEquals(0, marsRover.getPosition().getX());
        assertEquals(0, marsRover.getPosition().getY());
        assertEquals(NORTH, marsRover.getPosition().getOrientation());
    }

    @Test
    public void should_return_mars_rover_init_position_as_1_10_23st() {
        MarsRover marsRover = new MarsRover(new MarsRover.Position(1, 10, WEST));

        assertEquals(1, marsRover.getPosition().getX());
        assertEquals(10, marsRover.getPosition().getY());
        assertEquals(WEST, marsRover.getPosition().getOrientation());
    }

    @Test
    public void should_npe_when_init_position_orientation_is_null() {
        assertThrows(NullPointerException.class, () -> new MarsRover.Position(0, 0, null));
    }

    @Test
    public void should_npe_when_init_mars_rover_when_position_is_null() {
        assertThrows(NullPointerException.class, () -> new MarsRover(null));
    }

    @Test
    public void should_move_to_1_2_when_mars_rover_take_m_at_1_1_north() {
        MarsRover marsRover = new MarsRover(new MarsRover.Position(1, 1, NORTH));
        marsRover.execute(new MovementCommand[]{M});

        assertEquals(1, marsRover.getPosition().getX());
        assertEquals(2, marsRover.getPosition().getY());
    }

    @Test
    public void should_move_to_1_0_when_mars_rover_take_m_at_1_1_south() {
        MarsRover marsRover = new MarsRover(new MarsRover.Position(1, 1, SOUTH));
        marsRover.execute(new MovementCommand[]{M});

        assertEquals(1, marsRover.getPosition().getX());
        assertEquals(0, marsRover.getPosition().getY());
    }

    @Test
    public void should_move_to_0_1_when_mars_rover_take_m_at_1_1_west() {
        MarsRover marsRover = new MarsRover(new MarsRover.Position(1, 1, WEST));
        marsRover.execute(new MovementCommand[]{M});

        assertEquals(0, marsRover.getPosition().getX());
        assertEquals(1, marsRover.getPosition().getY());
    }

    @Test
    public void should_move_to_2_1_when_mars_rover_take_m_at_1_1_east() {
        MarsRover marsRover = new MarsRover(new MarsRover.Position(1, 1, EAST));
        marsRover.execute(new MovementCommand[]{M});

        assertEquals(2, marsRover.getPosition().getX());
        assertEquals(1, marsRover.getPosition().getY());
    }

    @Test
    public void should_throw_mars_rover_out_of_boundary_exception_when_move_to_out_of_max_integer() {
        MarsRover marsRover = new MarsRover(new MarsRover.Position(Integer.MAX_VALUE, Integer.MAX_VALUE, EAST));
        assertThrows(MarsRoverOutOfBoundaryException.class, () -> marsRover.execute(new MovementCommand[]{M}));
    }

    @Test
    public void should_throw_mars_rover_out_of_boundary_exception_when_move_to_out_of_min_integer() {
        MarsRover marsRover = new MarsRover(new MarsRover.Position(-Integer.MAX_VALUE, -Integer.MAX_VALUE, SOUTH));
        assertThrows(MarsRoverOutOfBoundaryException.class, () -> marsRover.execute(new MovementCommand[]{M}));
    }


    @Test
    public void should_turn_to_east_when_take_r_at_north() {
        MarsRover marsRover = new MarsRover(new MarsRover.Position(0, 0, NORTH));
        marsRover.execute(new MovementCommand[]{R});

        assertEquals(EAST, marsRover.getPosition().getOrientation());
    }

    @Test
    public void should_turn_to_west_when_take_r_at_south() {
        MarsRover marsRover = new MarsRover(new MarsRover.Position(0, 0, SOUTH));
        marsRover.execute(new MovementCommand[]{R});

        assertEquals(WEST, marsRover.getPosition().getOrientation());
    }

    @Test
    public void should_turn_to_west_when_take_l_at_north() {
        MarsRover marsRover = new MarsRover(new MarsRover.Position(0, 0, NORTH));
        marsRover.execute(new MovementCommand[]{L});

        assertEquals(WEST, marsRover.getPosition().getOrientation());
    }

    @Test
    public void should_turn_to_east_when_take_l_at_south() {
        MarsRover marsRover = new MarsRover(new MarsRover.Position(0, 0, SOUTH));
        marsRover.execute(new MovementCommand[]{L});

        assertEquals(EAST, marsRover.getPosition().getOrientation());
    }

    @Test
    public void should_at_last_0_1_south__when_take_m_r_m_m_l_m_l_m_l_m_r_m_at_0_0_north() {
        MarsRover marsRover = new MarsRover(new MarsRover.Position(0, 0, NORTH));
        marsRover.execute(new MovementCommand[]{M, R, M, M, L, M, L, M, L, M, R, M});

        assertEquals(0, marsRover.getPosition().getX());
        assertEquals(1, marsRover.getPosition().getY());
        assertEquals(WEST, marsRover.getPosition().getOrientation());
    }

}
