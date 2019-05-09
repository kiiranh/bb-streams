package students;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class School {

    public static <F, E extends F> void doWithAll(
            List<E> l, Consumer<F> op) {
        for (E i : l) op.accept(i);
    }

    public static <E> List<E> select(List<E> l, Predicate<? super E> pr) {
        List<E> res = new ArrayList<>();
        for (E e : l) {
            if (pr.test(e)) res.add(e);
        }
        return res;
    }

    public static void main(String[] args) {
        List<String> lst = List.of("Fred", "Jim", "Sheila");
        Consumer<CharSequence> cs = s -> System.out.println(s);
        doWithAll(lst, cs);
        System.out.println("---------------------------");
        doWithAll(select(lst, s -> s.length() > 3), s -> System.out.println(s));
    }
}
