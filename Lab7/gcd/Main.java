import java.util.stream.Stream;
import java.util.Optional;

class Main {
   static int gcd(int m, int n) {
        return Stream.iterate(
                new Pair(m > n ? m : n, n < m ? n : m), 
                pair -> pair.y != 0,
                pair -> new Pair(pair.y, pair.x % pair.y))
            .reduce((first, second) -> second)
            .get().y;
        // Alternative sol
        /* 
            return Stream.of(m, n).reduce((x, y) -> y == 0
                                                ? x
                                                : gcd(y, x % y))
                                                .get();
        */
    }
}
