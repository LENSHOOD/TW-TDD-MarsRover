package zxh.demo.tw.marsrover;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static zxh.demo.tw.marsrover.MovementCommand.M;
import static zxh.demo.tw.marsrover.Orientation.NORTH;

/**
 * MarsRoverRadarTest:
 * @author zhangxuhai
 * @date 2020/1/19
*/
public class MarsRoverDropTest {
    @Test
    public void should_mark_at_map_when_drop_into_ditch() {
        MarsRover marsRover = spy(new MarsRover(new PostureStatus(0, 0, NORTH, false)));
        MarsMap marsMap = new MarsMap();
        marsRover.setMarsMap(marsMap);
        when(marsRover.isDropIntoDitch()).thenReturn(true);

        marsRover.execute(new MovementCommand[]{M});

        assertEquals(marsRover, marsMap.get(0, 1));
    }

    @Test
    public void should_throw_npe_when_mars_rover_not_set_map() {
        MarsRover marsRover = spy(new MarsRover(new PostureStatus(0, 0, NORTH, false)));
        when(marsRover.isDropIntoDitch()).thenReturn(true);

        assertThrows(NullPointerException.class, () -> marsRover.execute(new MovementCommand[]{M}));
    }

    @Test
    public void should_return_new_mars_rover_when_old_drop() {
        MarsRover marsRover = spy(new MarsRover(new PostureStatus(0, 0, NORTH, false)));
        MarsMap marsMap = new MarsMap();
        marsRover.setMarsMap(marsMap);
        when(marsRover.isDropIntoDitch()).thenReturn(true);

        Optional<MarsRover> newMR = marsRover.execute(new MovementCommand[]{M});

        assertTrue(newMR.isPresent());
        assertNotEquals(marsRover, newMR.get());
    }

    @Test
    public void should_ignore_such_command_if_such_position_already_have_a_fallen_mars_rover() {
        MarsRover marsRover = spy(new MarsRover(new PostureStatus(0, 0, NORTH, false)));
        MarsMap marsMap = new MarsMap();
        marsRover.setMarsMap(marsMap);
        when(marsRover.isDropIntoDitch()).thenReturn(true);

        MarsRover newMR = marsRover.execute(new MovementCommand[]{M}).get();

        newMR.execute(new MovementCommand[]{M});

        assertEquals(0, newMR.getPostureStatus().getX());
        assertEquals(0, newMR.getPostureStatus().getY());
    }
}
