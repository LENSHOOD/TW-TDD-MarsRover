# TW-TDD-MarsRover
TW TDD Training Lesson 2 - MarsRover

### Tasking
1. Initialize mars rover, report latest status
    - init mars rover with position x, y, orientation N, S, E, W
        - given size to init map, default map size is max_integer * max_integer, throw exception if size exceed max_integer
        - given posture contains of integer x, y as position, any of enum N,S,E,W as orientation, throw exception if exceed map boundary or null orientation
    - report self location and orientation
        - return current posture
2. Execute command sequence
    - build cmd sequence from cmd array, cmd enum contains M, L, R 
    - execute cmd, L to turn left 90 degree, R to turn right 90 degree, M to move 1 step at current orientation
        - orientation index minus 1 when execute L, if orientation index is 0, minus 1 become 3  
        - orientation index plus 1 when execute L, if orientation index is 3, plus 1 become 0  
        - N + M = y++
        - S + M = y--
        - E + M = x++
        - W + M = x--
        - throw exception when position exceed the map