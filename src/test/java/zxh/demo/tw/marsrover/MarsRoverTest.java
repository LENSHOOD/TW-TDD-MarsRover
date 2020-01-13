package zxh.demo.tw.marsrover;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static zxh.demo.tw.marsrover.Orientation.*;

/**
 * MarsRoverTest:
 * @author zhangxuhai
 * @date 2020/1/13
*/
public class MarsRoverTest {
    @Test
    public void should_return_mars_with_size_100_when_init_size_to_100() {
        assertEquals(100, new Mars(100).getSize());
    }

    @Test
    public void should_return_mars_with_size_max_integer_when_no_init_size() {
        assertEquals(Integer.MAX_VALUE, new Mars().getSize());
    }

    @Test
    public void should_get_position_as_0_0_and_orientation_as_north_when_landing_mars_rover_to_mars() {
        Mars mars = new Mars();
        MarsRover marsRover = new MarsRover();
        marsRover.landing(mars, new Posture(0, 0, NORTH));
        assertEquals(0, marsRover.getPosture().getX());
        assertEquals(0, marsRover.getPosture().getY());
        assertEquals(NORTH, marsRover.getPosture().getOrientation());
    }

    @Test
    public void should_throw_illegal_argument_exception_when_posture_exceed_mars_boundary() {
        MarsRover marsRover = new MarsRover();
        assertThrows(IllegalArgumentException.class,
                () -> marsRover.landing(new Mars(100), new Posture(101, 99, NORTH)));
    }

    @Test
    public void should_throw_npe_when_mars_or_posture_is_null() {
        MarsRover marsRover = new MarsRover();
        assertThrows(NullPointerException.class, () -> marsRover.landing(null, new Posture(0, 0, NORTH)));
        assertThrows(NullPointerException.class, () -> marsRover.landing(new Mars(), null));
    }

    @Test
    public void should_return_position_1_2_when_move_to_north() {
        MarsRover marsRover = new MarsRover();
        marsRover.landing(new Mars(), new Posture(1, 1, NORTH));
        marsRover.executeCmds(new OperationCmd[]{OperationCmd.M});
        assertEquals(1, marsRover.getPosture().getX());
        assertEquals(2, marsRover.getPosture().getY());
    }

    @Test
    public void should_return_position_1_0_when_move_to_south() {
        MarsRover marsRover = new MarsRover();
        marsRover.landing(new Mars(), new Posture(1, 1, SOUTH));
        marsRover.executeCmds(new OperationCmd[]{OperationCmd.M});
        assertEquals(1, marsRover.getPosture().getX());
        assertEquals(0, marsRover.getPosture().getY());
    }

    @Test
    public void should_return_position_0_1_when_move_to_west() {
        MarsRover marsRover = new MarsRover();
        marsRover.landing(new Mars(), new Posture(1, 1, WEST));
        marsRover.executeCmds(new OperationCmd[]{OperationCmd.M});
        assertEquals(0, marsRover.getPosture().getX());
        assertEquals(1, marsRover.getPosture().getY());
    }

    @Test
    public void should_return_position_2_1_when_move_to_east() {
        MarsRover marsRover = new MarsRover();
        marsRover.landing(new Mars(), new Posture(1, 1, EAST));
        marsRover.executeCmds(new OperationCmd[]{OperationCmd.M});
        assertEquals(2, marsRover.getPosture().getX());
        assertEquals(1, marsRover.getPosture().getY());
    }

    @Test
    public void should_throw_movement_out_of_boundary_exception_when_move_to_out_of_boundary() {
        MarsRover marsRover = new MarsRover();
        marsRover.landing(new Mars(100), new Posture(1, 100, NORTH));
        assertThrows(MovementOutOfBoundaryException.class,
                () -> marsRover.executeCmds(new OperationCmd[]{OperationCmd.M}));
    }

    @Test
    public void should_return_north_when_turn_left_at_south() {
        MarsRover marsRover = new MarsRover();
        marsRover.landing(new Mars(), new Posture(1, 1, SOUTH));
        marsRover.executeCmds(new OperationCmd[]{OperationCmd.L});
        assertEquals(NORTH, marsRover.getPosture().getOrientation());
    }

    @Test
    public void should_return_west_when_turn_left_at_north() {
        MarsRover marsRover = new MarsRover();
        marsRover.landing(new Mars(), new Posture(1, 1, NORTH));
        marsRover.executeCmds(new OperationCmd[]{OperationCmd.L});
        assertEquals(WEST, marsRover.getPosture().getOrientation());
    }

    @Test
    public void should_return_west_when_turn_right_at_east() {
        MarsRover marsRover = new MarsRover();
        marsRover.landing(new Mars(), new Posture(1, 1, EAST));
        marsRover.executeCmds(new OperationCmd[]{OperationCmd.R});
        assertEquals(WEST, marsRover.getPosture().getOrientation());
    }

    @Test
    public void should_return_north_when_turn_left_at_west() {
        MarsRover marsRover = new MarsRover();
        marsRover.landing(new Mars(), new Posture(1, 1, WEST));
        marsRover.executeCmds(new OperationCmd[]{OperationCmd.R});
        assertEquals(NORTH, marsRover.getPosture().getOrientation());
    }

}
