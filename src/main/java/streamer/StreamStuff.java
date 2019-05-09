package streamer;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamStuff {
    public static void main(String[] args) {
//        boolean b =
        IntStream.iterate(1, x -> x + 1)
                .filter(x -> x < 100)
                .sorted()
                .peek(x -> System.out.println("peeking: " + x))
//                .allMatch(x -> x < 100)
                ;
//                .forEach(System.out::println);

//        System.out.println("All match? " + b);
        System.out.println("done...");
    }
}
