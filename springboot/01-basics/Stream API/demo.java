// TOPIC: Java Stream API
//
// A "Stream" is NOT a data structure - it's a pipeline that lets you process
// a collection (List, Set, etc.) in a declarative way: "what to do" instead
// of "how to loop". You take a source (a List, for example), chain together
// operations like filter/map/reduce, and get a result out the end.
//
// Below are the building blocks, kept as comments so you can uncomment and
// experiment with any of them one at a time.

// ---------------------------------------------------------------------
// 1) forEach + Consumer - the simplest way to visit every element.
// A Consumer<T> is just a functional interface: "give me a T, I do something
// with it, I return nothing". nums.forEach(...) is a shortcut for a for-loop
// that calls a Consumer on every element.
// ---------------------------------------------------------------------
// import java.util.*;
// import java.util.function.Consumer;
//
// public class demo {
//     public static void main(String[] args) {
//         List<Integer> nums = Arrays.asList(1, 2, 3, 3432, 232, 2, 3, 2, 32, 3, 232, 2, 3, 23, 4, 5);
//         Consumer<Integer> con = (Integer n) -> System.out.println(n);
//
//         for (int i = 0; i < nums.size(); i++) {
//             con.accept(nums.get(i));
//         }
//         for (int l : nums) {
//             con.accept(l);
//         }
//         nums.forEach(n -> System.out.println(n));
//     }
// }

// ---------------------------------------------------------------------
// 2) Predicate - a functional interface representing a true/false test.
// Streams use Predicates inside filter() to decide which elements survive.
// ---------------------------------------------------------------------
// Predicate<Integer> p1 = new Predicate<Integer>() {
//     public boolean test(Integer n) {
//         if (n % 2 == 0) {
//             return true;
//         } else {
//             return false;
//         }
//     }
// };

// ---------------------------------------------------------------------
// 3) filter / map / reduce - the core trio of the Stream API.
//    - filter(Predicate):  keep only elements that pass the test
//    - map(Function):      transform each element into something else
//    - reduce(identity, BinaryOperator): combine all elements into one result
// ---------------------------------------------------------------------
// int result = nums.stream()
//         .filter(n -> n % 2 == 0)
//         .map(n -> n * 2)
//         .reduce(0, (c, e) -> c + e);
// System.out.println(result);

// ---------------------------------------------------------------------
// 4) parallelStream - same pipeline, but Java splits the work across
// multiple CPU cores automatically. Handy for large collections, but the
// order of processing is no longer guaranteed.
// ---------------------------------------------------------------------
// int parallelResult = nums.parallelStream()
//         .filter(n -> n % 2 == 0)
//         .map(n -> n * 2)
//         .reduce(0, (c, e) -> c + e);
// System.out.println(parallelResult);

// ---------------------------------------------------------------------
// 5) Comparing stream() vs parallelStream() on a bigger list, and using
// mapToInt() to work with primitive ints (avoids boxing overhead).
// ---------------------------------------------------------------------
// import java.util.*;
//
// public class demo {
//     public static void main(String[] args) {
//         int size = 10000;
//         List<Integer> li = new ArrayList<>(size);
//         Random ran = new Random();
//         for (int i = 0; i < 10000; i++) {
//             li.add(ran.nextInt());
//         }
//
//         int sum = li.stream()
//                 .map(n -> n * 2)
//                 .reduce(0, (c, e) -> c + e);
//         System.out.println(sum);
//
//         int sum2 = li.stream()
//                 .map(n -> n * 2)
//                 .mapToInt(i -> i)
//                 .sum();
//         System.out.println(sum + " " + sum2);
//
//         int sum3 = li.parallelStream()
//                 .map(n -> n * 2)
//                 .mapToInt(i -> i)
//                 .sum();
//         System.out.println(sum + " " + sum2 + " " + sum3);
//     }
// }

// ---------------------------------------------------------------------
// 6) Optional - a box that either holds a value or is empty. It forces you
// to explicitly check "is there really a value here?" instead of getting a
// surprise NullPointerException. findFirst() returns an Optional because
// there may be no matching element at all.
// ---------------------------------------------------------------------
// import java.util.*;
//
// public class demo {
//     public static void main(String[] args) {
//         List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
//
//         Optional<String> optionalName = names.stream()
//                 .filter(n -> n.startsWith("A"))
//                 .findFirst();
//         if (optionalName.isPresent()) {
//             System.out.println(optionalName.get());
//         }
//
//         Optional<String> name = names.stream()
//                 .filter(str -> str.contains("A"))
//                 .findFirst();
//         System.out.println(name.get());
//     }
// }

// ---------------------------------------------------------------------
// 7) Method reference (System.out::println) - shorthand for a lambda that
// only calls one existing method. `x -> System.out.println(x)` and
// `System.out::println` do exactly the same thing; the method reference is
// just less to type/read.
// ifPresent(...) runs the given Consumer only if the Optional has a value,
// so there's no need for the isPresent()/get() dance from example 6.
// ---------------------------------------------------------------------
import java.util.*;

public class demo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        names.stream()
                .filter(str -> str.contains("A"))
                .findFirst()
                .ifPresent(System.out::println);
    }
}
