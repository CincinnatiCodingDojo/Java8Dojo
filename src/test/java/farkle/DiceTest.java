package farkle;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DiceTest {

  Dice dice;

  @Before
  public void setup(){
    dice = new Dice();
  }

  @Test
  public void testScoreTriples(){
    assertEquals(1000, dice.score(asList(1, 1, 1)));
    assertEquals(200, dice.score(Arrays.asList(2, 2, 2)));
    assertEquals(1200, dice.score(Arrays.asList(1, 2, 1, 2, 1, 2)));
    assertEquals(600, dice.score(Arrays.asList(6,6,6)));
    assertEquals(400, dice.score(Arrays.asList(4,2,4,6,4)));
  }

  @Test
  public void testScoreSingles(){
    assertEquals(150, dice.score(asList(1, 2, 4, 4, 5, 6)));
  }

  @Test
  public void testScore1sAnd5s() {
    assertEquals(200, dice.score(asList(1, 1, 2, 3, 4, 6)));
    assertEquals(100, dice.score(asList(2, 3, 4, 5, 5, 6)));
    assertEquals(300, dice.score(asList(1, 1, 5, 5, 6, 6)));
    assertEquals(1100, dice.score(asList(1, 1, 1, 5, 5)));

  }

  @Test
  public void test4ofAKind() {
    assertEquals(2000, dice.score(asList(1, 1, 1, 1)));
//    assertEquals(2100, dice.score(asList(1, 1, 1, 1, 5, 5)));
//    assertEquals(2000, dice.score(asList(1, 1, 1, 1, 3, 4)));
  }

  @Test
  public void testStraight() {
    assertEquals(2500, dice.score(asList(1, 2, 3, 4, 5, 6)));
  }

  @Test
  public void testMapRollToFrequencyMap() {
    assertEquals(Integer.valueOf(3), dice.mapRollToFrequency(asList(1, 1, 1, 2, 3, 4)).get(1));
  }
}
