import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.Optional;
import java.util.List;
import java.util.*;

public interface InfiniteList<T> {

    public static <T> InfiniteList<T> generate(Supplier<? extends T> s) {
        return InfiniteListImpl.generate(s);
    }

    public static <T> InfiniteList<T> iterate(T seed, UnaryOperator<T> next) {
        return InfiniteListImpl.iterate(seed, next); 
    }

    public <R> InfiniteList<R> map(Function<? super T, ? extends R> mapper);
    public InfiniteList<T> filter(Predicate<? super T> predicate);
    public InfiniteList<T> limit(long n);
    public InfiniteList<T> takeWhile(Predicate<? super T> predicate);
    public InfiniteList<T> peek();

    public void forEach(Consumer<? super T> action);
    public Object[] toArray();
    public long count();
    public <U> U reduce (U identity, BiFunction<U, ? super T, U> accumulator);

    public boolean isEmpty();
    
}
