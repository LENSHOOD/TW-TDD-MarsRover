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
}
