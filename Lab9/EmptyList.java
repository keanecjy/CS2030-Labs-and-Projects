import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.Optional;
import java.util.List;

class EmptyList<T> implements InfiniteList<T> {
    
    public boolean isEmpty() {
        return true;
    }
    
    public EmptyList<T> limit(long n) {
        return this;
    }

    public EmptyList<T> takeWhile(Predicate<? super T> predicate) {
        return this;
    }

    public <R> EmptyList<R> map(Function<? super T, ? extends R> mapper) {
        return new EmptyList<R>();
    }

    public EmptyList<T> filter(Predicate<? super T> predicate) {
        return this;
    }

    public EmptyList<T> peek() {
        return this;
    }

    public void forEach(Consumer<? super T> action) {

    }

    public Object[] toArray() {
        return new Object[0];
    }

    public long count() {
        return 0;
    }

    public <U> U reduce (U identity, BiFunction<U, ? super T, U> accumulator) {
        return identity;
    }
}
