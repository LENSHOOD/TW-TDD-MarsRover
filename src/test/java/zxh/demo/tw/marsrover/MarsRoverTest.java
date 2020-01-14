package zxh.demo.tw.marsrover;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static zxh.demo.tw.marsrover.Orientation.NORTH;
import static zxh.demo.tw.marsrover.Orientation.WEST;

/**
 * MarsRoverTest:
 * @author zhangxuhai
 * @date 2020/1/14
*/
public class MarsRoverTest {
    @Test
    public void should_return_mars_rover_init_position_as_0_0_north() {
        MarsRover marsRover = new MarsRover(new Position(0, 0, NORTH));

        assertEquals(0, marsRover.getPosition().getX());
        assertEquals(0, marsRover.getPosition().getY());
        assertEquals(NORTH, marsRover.getPosition().getOrientation());
    }

    @Test
    public void should_return_mars_rover_init_position_as_1_10_23st() {
        MarsRover marsRover = new MarsRover(new Position(1, 10, WEST));

        assertEquals(1, marsRover.getPosition().getX());
        assertEquals(10, marsRover.getPosition().getY());
        assertEquals(WEST, marsRover.getPosition().getOrientation());
    }

    @Test
    public void should_npe_when_init_position_orientation_is_null() {
        assertThrows(NullPointerException.class, () -> new Position(0, 0, null));
    }
}
