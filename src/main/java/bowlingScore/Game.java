package bowlingScore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kon7993 on 2/2/2015.
 */
public class Game {
    private final Frame[] frames;

    public Game(Frame... frames) {
        this.frames = frames;
    }

    public List<Frame> getFrames() {
        return Arrays.asList(frames);
    }
}
