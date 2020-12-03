import java.util.stream.IntStream;
import java.util.function.IntPredicate;
import java.util.stream.Stream;
import java.util.Optional;

class Main {
    static int[] twinPrimes(int n) {
        return IntStream
            .iterate(3, i ->  i <= n, i -> i + 2)
            .filter(i -> isPrime(i) && (isPrime(i - 2) || isPrime(i + 2)))
            .toArray();
    }

    static boolean isPrime(int n) {
        return IntStream.rangeClosed(2, (int) Math.sqrt(n)).noneMatch(x -> n % x == 0);
    }

    static int gcd(int m, int n) {
        return Stream
            .iterate(new Pair(m, n), 
                pair -> pair.y != 0,
                pair -> new Pair(pair.y, pair.x % pair.y))
            .reduce((first, second) -> second)
            .get()
            .y;

        /*Imperative ver
            while (n != 0) {
                int temp = n;
                n = m % n;
                m = temp;
            }
            return m;
        */

        // Alternative sol
        /* 
            return Stream.of(m, n).reduce((x, y) -> y == 0
                                                ? x
                                                : gcd(y, x % y))
                                                .get();
        */
    }

    static long countRepeats(int... array) {
        return IntStream
            .range(1, array.length)
            .filter(i -> isRepeat(i, array))
            .count();
    }

    static boolean isRepeat(int i, int... array) {
        if (i == 1) {
            return array[1] == array[0];
        }
        // i >= 2 cases
        return array[i] == array[i - 1] && array[i] != array[i - 2];
    }

    static double normalizedMean(Stream<Integer> stream) {
        return stream
            .reduce(new Store(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0),
                    (s1, x) -> s1.add(x),
                    (s1, s2) -> s1.add(s2))
            .getMean();
    }
}
