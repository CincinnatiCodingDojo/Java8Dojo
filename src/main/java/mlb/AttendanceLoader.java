package mlb;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Stream;

public class AttendanceLoader {

    public static Stream<MLBAttendance> load(String fileName) throws IOException {
        return Files.lines(Paths.get(fileName))
                .skip(1)
                .filter(line -> !(line.contains("Postponed") ||
                             line.contains("Suspended")))
                .map((line) -> new MLBAttendance(line.split(",",-1)));
    }
}
