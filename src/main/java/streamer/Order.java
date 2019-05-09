package streamer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Grabber implements Collector<Integer, List<Integer>, List<Integer>> {

    @Override
    public Supplier<List<Integer>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<Integer>, Integer> accumulator() {
        return (li, i) -> li.add(i);
    }

    @Override
    public BinaryOperator<List<Integer>> combiner() {
        return (li, li2) -> {
            ArrayList<Integer> res = new ArrayList<>();
            res.addAll(li);
            res.addAll(li2);
            return res;
        };
    }

    @Override
    public Function<List<Integer>, List<Integer>> finisher() {
        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.IDENTITY_FINISH/*, Characteristics.UNORDERED*/);
    }
}

public class Order {
    public static void main(String[] args) {
        System.out.println(Stream.iterate(1, x -> x + 1)
                .parallel()
//                .unordered()
                .limit(10000)
                .collect(new Grabber()));

    }
}
