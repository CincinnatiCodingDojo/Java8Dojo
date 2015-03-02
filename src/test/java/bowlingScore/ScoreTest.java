package bowlingScore;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ScoreTest {
    Score score;

    @Before
    public void setup() {
        score = new Score();
    }

    @Test
    public void should_return_zero_with_no_hits(){
        assertEquals(0, score.calcScore(new Game()));
    }

    @Test
    public void should_return_same_if_score_less_than_10() {
        assertEquals(9, score.calcScore(new Game(new Frame(7, 2))));
    }

    @Test
    public void should_score_multiple_frames(){
        assertEquals(20,score.calcScore(new Game(new Frame(3, 4),
                new Frame(3, 4), new Frame(3, 3))));
    }

    @Test
    public void should_score_a_single_spare_as_first_frame(){
        assertEquals(0,score.calcScore(new Game(new Frame(3, 7))));
    }

    //@Test
    public void should_score_a_spare_with_a_second_frame(){
        assertEquals(17,score.calcScore(new Game(new Frame(3, 7), new Frame(2, 3))));
    }

   /* @Test
    public void should_handle_spares(){
        assertEquals(22,score.calcScore(Arrays.asList(8,2,5,2)));
    }*/
}