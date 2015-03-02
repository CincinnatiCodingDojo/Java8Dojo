package farkle;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.IntStream;


public class Dice {
    private int score = 0;

    public void add(int score) {
      this.score += score;
    }

    public void setScore(int score) {
      this.score = score;
    }

    public int getScore() {
      return score;
    }

    public int score(List<Integer> roll) {
      Dice score = new Dice();
      Map<Integer, Integer> frequencyMap = mapRollToFrequency(roll);

      scoreIsolatedFives(score, frequencyMap);
      scoreIsolatedOnes(score, frequencyMap);
      scoreThreeOrMoreOfAKind(score, frequencyMap, multipleScoringByDoubling);
      scoreStraight(score, frequencyMap);

      return score.getScore();
    }

  private void scoreStraight(Dice score, Map<Integer, Integer> frequencyMap) {
    if (frequencyMap.values().stream().allMatch(count -> count == 1)) {
      score.setScore(2500);
    }
  }

  private void scoreIsolatedFives(Dice score, Map<Integer, Integer> frequencyMap) {
    if (frequencyMap.get(5) > 0 && frequencyMap.get(5) < 3) {
      score.add(50 * frequencyMap.get(5));
    }
  }

  private void scoreIsolatedOnes(Dice score, Map<Integer, Integer> frequencyMap) {
    if (frequencyMap.get(1) > 0 && frequencyMap.get(1) < 3) {
      score.add(100 * frequencyMap.get(1));
    }
  }

  private void scoreThreeOrMoreOfAKind(Dice score, Map<Integer, Integer> frequencyMap, BiFunction<Integer, Integer, Integer> scoringFn) {
    IntStream.rangeClosed(3, 6).forEach(ofAKind -> score.add(
            frequencyMap.entrySet().stream()
                    .filter(entry -> entry.getValue() == ofAKind)
                    .mapToInt(Map.Entry::getKey)
                    .reduce(0, (acc, pip) -> acc + scoringFn.apply(ofAKind, pip))
    ));
  }

    private int scoreMultiples(int ofAKind, int i) {
        return (i == 1 ? (1000 * (ofAKind - 2)) : (100 * (ofAKind - 2)) * i);
    }

    private BiFunction<Integer, Integer, Integer> multipleScoring = (ofAKind, pip) -> (pip == 1 ? (1000 * (ofAKind - 2)) : (100 * (ofAKind - 2)) * pip);
//    private BiFunction<Integer, Integer, Integer> multipleScoringByDoubling = (ofAKind, pip) -> (pip == 1 ? 1000 : (100 * (ofAKind - 2)) * pip);
   private BiFunction<Integer, Integer, Integer> multipleScoringByDoubling = (ofAKind, pip) -> {
       Integer threeOfAKind  ;
       threeOfAKind = ( pip == 1 ? 1000 : (100 * pip)) ;
       return (int)(Math.pow(2, ofAKind - 3)*threeOfAKind);
   };

    public Map<Integer, Integer> mapRollToFrequency(List<Integer> roll) {
      Map<Integer, Integer> frequencyMap = new HashMap<>();
      IntStream.rangeClosed(1, 6).forEach(pip -> frequencyMap.put(pip, Collections.frequency(roll, pip)));
      return frequencyMap;
    }
  }