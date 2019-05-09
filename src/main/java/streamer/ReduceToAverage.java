package streamer;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class ReduceToAverage {
    static class Average {
        private double sum;
        private long count;

        public Average(double sum, long count) {
            this.sum = sum;
            this.count = count;
        }
        public Average include(double v) {
            return new Average(sum + v, count + 1);
        }
        public Average merge(Average other) {
            System.out.println("MERGE!!!");
            return new Average(this.sum + other.sum,
                    this.count + other.count);
        }
        public Optional<Double> get() {
            if (count == 0) return Optional.empty();
            else return Optional.of(this.sum/this.count);
        }
    }

    public static void main(String[] args) {
        long start = System.nanoTime();
        Stream.iterate(1.0, (x) -> ThreadLocalRandom.current().nextDouble(-Math.PI, Math.PI))
                .parallel()
                .limit(2_500_000_000L)
//                .map(Math::sin)
                .reduce(new Average(0,0),
                        Average::include,
                        Average::merge)
//                        (a, d) -> a.include(d),
//                        (a, a1) -> a.merge(a1))
                .get()
                .ifPresentOrElse(v -> System.out.println("Average is " + v),
                        () -> System.out.println("Empty data set, no average possible"));
        long time = System.nanoTime() - start;
        System.out.printf("Time taken: %7.4f seconds.\n", (time / 1_000_000_000.0));
    }
}
