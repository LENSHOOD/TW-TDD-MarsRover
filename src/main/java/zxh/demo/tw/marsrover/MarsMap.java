package zxh.demo.tw.marsrover;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * MarsMap:
 *
 * @author zhangxuhai
 * @date 2020/1/19
 */
public class MarsMap {
    private Table<Integer, Integer, MarsRover> mapMarker = HashBasedTable.create();

    public MarsRover get(int x, int y) {
        return mapMarker.get(x, y);
    }

    void set(int x, int y, MarsRover marsRover) {
        mapMarker.put(x, y, marsRover);
    }
}
