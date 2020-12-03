import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

class InfiniteListImpl<T> implements InfiniteList<T> {
    private final Lazy<T> head;
    private final Supplier<InfiniteList<T>> tail;

    private InfiniteListImpl(Lazy<T> head, Supplier<InfiniteList<T>> tail) {
        this.head = head;
        this.tail = tail;
    }
    // Data sources
    public static <T> InfiniteList<T> generate(Supplier<? extends T> s) {
        Lazy<T> newHead = Lazy.generate(s);
        Supplier<InfiniteList<T>> newTail = () -> InfiniteListImpl.generate(s);
        return new InfiniteListImpl<>(newHead, newTail);
    }

    public static <T> InfiniteList<T> iterate(T seed, UnaryOperator<T> next) {
        Lazy<T> newHead = Lazy.generate(() -> seed);
        Supplier<InfiniteList<T>> newTail = () -> InfiniteListImpl.iterate(next.apply(seed), next);
        return new InfiniteListImpl<>(newHead, newTail);
    }

    // Debugging operation
    public InfiniteList<T> peek() { 
        head.get().ifPresent(System.out::println);
        return this.tail.get();
    }

    // Stateless intermediate operations
    public <R> InfiniteList<R> map(Function<? super T, ? extends R> mapper) {
        Lazy<R> newHead = head.map(mapper);
        Supplier<InfiniteList<R>> newTail = () -> tail.get().map(mapper);
        return new InfiniteListImpl<>(newHead, newTail);
    }

    public InfiniteList<T> filter(Predicate<? super T> predicate) {
        Lazy<T> newHead = head.filter(predicate);
        Supplier<InfiniteList<T>> newTail = () -> tail.get().filter(predicate); 
        return new InfiniteListImpl<>(newHead, newTail);
    }

    // Stateful intermediate operations
    public InfiniteList<T> limit(long n) {
        if (n <= 0) {
            return new EmptyList<>();
        } else {
            return new InfiniteListImpl<>(head, 
                () -> head.get().isEmpty()
                        ? tail.get().limit(n) 
                        : n == 1
                        ? new EmptyList<>()
                        : tail.get().limit(n - 1));
        }
    }

    public InfiniteList<T> takeWhile(Predicate<? super T> predicate) {
        Lazy<T> newHead = head.filter(predicate);
        return new InfiniteListImpl<>(newHead, () -> 
                    (head.get().isEmpty() || newHead.get().isPresent())
                    ? tail.get().takeWhile(predicate)
                    : new EmptyList<>());
    }

    public InfiniteList<T> getNext() { 
        return this.tail.get();
    }

    // Terminal operations
    public void forEach(Consumer<? super T> action) {
        head.get().ifPresent(action);
        getNext().forEach(action);
    }

    public Object[] toArray() {
        List<Object> list = new ArrayList<>();
        forEach(x -> list.add(x));
        return list.toArray();
    }

    public long count() {
        return reduce(0, (x, y) -> x + 1);
    }

    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator) {
        U x = identity;
        if (head.get().isPresent()) {
            x = accumulator.apply(x, head.get().get());
        }
        return getNext().reduce(x, accumulator);
    }

    public boolean isEmpty() {
        return false;
    }
}
