package bowlingScore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kon7993 on 2/2/2015.
 */
public class Frame {
    private final Integer[] rolls;

    public Frame(Integer... rolls) {
      this.rolls = rolls;
    }

    public List<Integer> getRolls() {
        return Arrays.asList(rolls);
    }
}
