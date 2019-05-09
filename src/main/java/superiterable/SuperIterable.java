package superiterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {
    private Iterable<E> self;

    public SuperIterable(Iterable<E> self) {
        this.self = self;
    }

//    Already in "forEach"
//    public void doWithAll(Consumer<? super E> op) {
//        for (E i : self) op.accept(i);
//    }

    public SuperIterable<E> filter(Predicate<? super E> pr) {
        List<E> res = new ArrayList<>();
        for (E e : self) {
            if (pr.test(e)) res.add(e);
        }
        return new SuperIterable<>(res);
    }

    public <F> SuperIterable<F> map(Function<? super E, ? extends F> op) {
        List<F> res = new ArrayList<>();
        for (E e : self) {
            res.add(op.apply(e));
        }
        return new SuperIterable<>(res);
    }

    public <F> SuperIterable<F> flatMap(Function<? super E, SuperIterable<? extends F>> op) {
        List<F> res = new ArrayList<>();

        self.forEach(e -> op.apply(e).forEach(f -> res.add(f)));
        return new SuperIterable<>(res);
    }

    @Override
    public Iterator<E> iterator() {
        return self.iterator();
    }
}
