package opt;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UseOptional {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("Albert", "Jones");

        final String FIRST = "Al";
        try {
            String last = map.get(FIRST);
            String message = "Mr. " + last.toUpperCase();
            System.out.println(message);
        } catch (Throwable t) {
            System.out.println("oops!");
        }

        Optional.of(map)
                .map(m -> m.get(FIRST))
                .map(n -> "Mr. " + n.toUpperCase())
                .ifPresent(UseOptional::showIt);
        //      .ifPresent(x -> showIt(x));
//                .ifPresent(System.out::println);
    }

    public static void showIt(Object o) {
        System.out.println(o);
    }
}
