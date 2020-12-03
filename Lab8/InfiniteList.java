import java.util.Optional;
import java.util.function.*;

interface InfiniteList<T> {

    public static <T> InfiniteList<T> generate(Supplier<? extends T> supplier) {
        return InfiniteListImpl.generate(supplier);
    }

    public static <T> InfiniteList<T> iterate(T seed, UnaryOperator<T> f) {
        return InfiniteListImpl.iterate(seed, f);
    }

    public InfiniteList<T> limit(long maxSize);

    public void forEach(Consumer<? super T> action);

    public Object[] toArray();

    public <S> InfiniteList<S> map(Function<? super T, ? extends S> mapper);

    public InfiniteList<T> filter(Predicate<? super T> predicate);

    public InfiniteList<T> takeWhile(Predicate<? super T> predicate);

    public long count();

    public Optional<T> reduce(BinaryOperator<T> accumulator);

    public T reduce(T identity, BinaryOperator<T> accumulator);

    public Optional<T> get();
}
