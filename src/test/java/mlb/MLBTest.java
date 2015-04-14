package mlb;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class MLBTest {

    Stream<MLBAttendance> attendanceStream;

    @Before
    public void setup() throws IOException {
        attendanceStream = AttendanceLoader.load("C:\\projects\\java8-koans\\src\\main\\2014MLBDailyAttendance.csv");
    }

    @Test
    public void should_load_csv_file() throws IOException {
        assertNotNull(attendanceStream);
        assertTrue(attendanceStream.count() == 2433);
    }



//    @Test
//    public void totalLeagueAttendanceTest()throws Exception {
//        assertEquals(73780670, attendanceStream.mapToInt(MLBAttendance::getAttendance).sum());
//    }

    @Test
    public void totalForReds() throws Exception {
        assertEquals(2476664, attendanceStream.filter(s -> s.getHomeTeam().equals("Reds")).mapToInt(MLBAttendance::getAttendance).sum());
    }

    @Test
    public void testBestDayAttendanceOverall() throws Exception {
        Map<String, Integer> attendanceByDay = attendanceStream
                .collect(Collectors.groupingBy(s -> s.getDay(), Collectors.summingInt(s -> s.getAttendance())));
        Optional<Map.Entry<String, Integer>> max = attendanceByDay.entrySet().stream().max(Map.Entry.comparingByValue());
        assertThat(max.get().getKey(), is("Sat"));
        assertThat(max.get().getValue(), is(13917526));
    }

    @Test
    public void testWorstDayEver() {
        Map<String, Integer> attendanceByDay = attendanceStream
                .collect(Collectors.groupingBy(s -> s.getDay(), Collectors.summingInt(s -> s.getAttendance())));
        Optional<Map.Entry<String, Integer>> min = attendanceByDay.entrySet().stream().min(Map.Entry.comparingByValue());
        assertThat(min.get().getKey(), is("Mon"));
        assertThat(min.get().getValue(), is(7255796));
    }

    @Test
    public void testSplitHomeTeam() throws Exception{
        assertEquals("Cincinnati", MLBAttendance.filterHomeCity("at Cincinnati 10 Pittsburgh 6"));
    }

    @Test
    public void testSplitHomeTeamNY() throws Exception{
        assertEquals("NY Mets", MLBAttendance.filterHomeCity("Cincinnati 10 at NY Mets 6"));
    }

    @Test
    public void testSplitAwayTeam() throws Exception{
        assertEquals("Pittsburgh", MLBAttendance.filterAwayCity("at Cincinnati 10 Pittsburgh 6"));
    }

    @Test
    public void testSplitAwayTeamNY() throws Exception {
        MLBAttendance first = attendanceStream.findFirst().get();
        assertEquals("LA Dodgers", first.getAwayCity());
    }

    @Test
    public void highestAwayAttendance() throws Exception {
        Map<String, Integer> attendanceByAwayCity = attendanceStream
                .collect(Collectors.groupingBy(s -> s.getAwayCity(), Collectors.summingInt(s -> s.getAttendance())));
        Optional<Map.Entry<String, Integer>> max = attendanceByAwayCity.entrySet().stream().max(Map.Entry.comparingByValue());
        assertThat(max.get().getKey(), is("NY Yankees"));
        assertThat(max.get().getValue(), is(2876501));
    }

    @Test
    public void testMilwaukee_at_San_Diego(){
        String game = "Milwaukee 10 at San Diego 1";
        assertEquals("Milwaukee", MLBAttendance.filterAwayCity("Milwaukee 10 at San Diego 1"));
    }
}
