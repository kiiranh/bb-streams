package concordance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Concordance {
    public static final Pattern WORD_BOUNDARIES = Pattern.compile("\\W+");

    public static void main(String[] args) {
        try (Stream<String> stream = Files.lines(Paths.get("PrideAndPrejudice.txt"))) {
            stream
            .flatMap(WORD_BOUNDARIES::splitAsStream)
            .filter(s -> s.length() > 0)
            .map(String::toLowerCase)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(200)
            .map(e -> String.format("%20s : %5d", e.getKey(), e.getValue()))
            .forEach(System.out::println);
            ;
        } catch (IOException ioe) {
            System.out.println("oops that broke");
        }
    }
}
