package bowlingScore;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by kon7993 on 2/2/2015.
 */
public class Score {

    public int calcScore(Game game){
        IntStream intStream = game.getFrames()
                .stream()
                .flatMapToInt(f -> f.getRolls()
                        .stream()
                        .mapToInt(i -> i));
//        System.out.printf("%s",intStream.peek());
        return intStream.sum();
    }

    public int calculateBonus(Game game, Frame frame, int bonuses){

        return 0;
    }
}
