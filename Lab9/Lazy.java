import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.Optional;

class Lazy<T> { 

    private T v;
    private final Supplier<? extends T> s;
    private boolean computed;

    private Lazy(T v) {
        this.v = v;
        this.s = null;
        this.computed = true;
    }

    public Lazy(Supplier<? extends T> supplier) {
        this.s = supplier;
        this.v = null;
        this.computed = false;
    }

    static <T> Lazy<T> ofNullable(T v) {
        return new Lazy<>(v); 
    }

    static <T> Lazy<T> generate(Supplier<? extends T> supplier) {
        return new Lazy<>(supplier);
    }

    <R> Lazy<R> map(Function<? super T, ? extends R> mapper) {
        return Lazy.generate(() -> this.get().isEmpty() ? null : mapper.apply(v));
    }

    Lazy<T> filter(Predicate<? super T> predicate) {
        return Lazy.generate(() -> this.get().isEmpty() 
                ? null 
                : predicate.test(v)
                ? v
                : null);
    }

    Optional<T> get() {
        if (!computed) {
            v = s.get();
            computed = true;
        }
        return Optional.ofNullable(v);
    }

    @Override
    public String toString() {
        if (!computed) {
            return "?";
        } else {
            if (v == null) {
                return "null";
            } else {
                return v.toString();
            }
        }
    }
}
