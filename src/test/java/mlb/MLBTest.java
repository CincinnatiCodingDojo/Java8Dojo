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
        assertTrue(attendanceStream.count() > 0);
    }

    @Test
    public void totalLeagueAttendanceTest()throws Exception {
        assertEquals(73780670, attendanceStream.mapToInt(MLBAttendance::getAttendance).sum());
    }

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
    public void testSplitAwayTeam() throws Exception{
        String homecity = "at Cincinnati 10 Pittsburgh 6";
        Pattern p = Pattern.compile("(at )?([a-zA-Z ]+) (\\d+)");
        Matcher matcher = p.matcher(homecity);
        boolean matches = matcher.matches();
        System.out.println(matcher.group(0));

    }
}
