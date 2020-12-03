import java.util.Optional;
import java.util.function.*;
import java.util.List;
import java.util.ArrayList;

abstract class InfiniteListImpl<T> implements InfiniteList<T> {
    // Static methods
    public static <T> InfiniteListImpl<T> generate(Supplier<? extends T> supplier) {
        return new InfiniteListImpl<T>() {
            public Optional<T> get() {
                return Optional.ofNullable(supplier.get());
            }
        };
    }

    public static <T> InfiniteListImpl<T> iterate(T seed, UnaryOperator<T> f) {
        return new InfiniteListImpl<T>() {
            T element = seed;
            boolean flag = true;
            public Optional<T> get() {
                if (flag) {
                    flag = false;
                } else {
                    element = f.apply(element);
                }
                return Optional.ofNullable(element);
            }
        };
    }

    public InfiniteListImpl<T> limit(long maxSize) {
        if (maxSize < 0) {
            throw new IllegalArgumentException(String.valueOf(maxSize));
        }
        return new InfiniteListImpl<T>() {
            long num = 0;
            public Optional<T> get() {
                if (num < maxSize) {
                    num++;
                    return InfiniteListImpl.this.get();
                } else {
                    return Optional.empty();
                }
            }
        };       
    }

    // Non-terminal operations
    public <S> InfiniteListImpl<S> map(Function<? super T, ? extends S> mapper) {
        /*
           return new InfiniteListImpl<S>() {
           public Optional<S> get() {
           return InfiniteListImpl.this.get().map(mapper);
           }
           };

*/
        // We can also create a named class, but we choose not to since the 
        // name Mapper will not be used anywhere else
        class Mapper extends InfiniteListImpl<S> {
            public Optional<S> get() {
                return InfiniteListImpl.this.get().map(mapper);
            }
        }
        return new Mapper();


    }

    public InfiniteListImpl<T> filter(Predicate<? super T> predicate) {
        return new InfiniteListImpl<T>() {
            public Optional<T> get() {
                Optional<T> curr = InfiniteListImpl.this.get();
                if (curr.isEmpty()) {
                    return curr;
                } else {
                    Optional<T> next = curr.filter(predicate);
                    if (next.isEmpty()) {
                        return this.get();
                    } else {
                        return next;
                    }
                }
            }
        };      
    }

    public InfiniteListImpl<T> takeWhile(Predicate<? super T> predicate) {
        return new InfiniteListImpl<T>() {
            public Optional<T> get() {
                return InfiniteListImpl.this.get().filter(predicate);
            }
        };
    }

    // Terminal Operations
    public void forEach(Consumer<? super T> action) {
        Optional<T> curr = get();
        while (!curr.isEmpty()) {
            action.accept(curr.get());
            curr = get();
        }
    }

    public Object[] toArray() {
        List<T> list = new ArrayList<>();
        this.forEach(x -> list.add(x));
        return list.toArray();
    }

    public long count() {
        Optional<T> curr = get();
        long num = 0;
        while (!curr.isEmpty()) {
            curr = get();
            num++;
        }
        return num;
    }

    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        Optional<T> first = get();        
        Optional<T> second = get();
        while (second.isPresent()) {
            first = Optional.of(accumulator.apply(first.get(), second.get()));
            second = get();
        }
        return first;
    }

    public T reduce(T identity, BinaryOperator<T> accumulator) {
        T curr = identity;
        Optional<T> next = get();
        while (next.isPresent()) {
            curr = accumulator.apply(curr, next.get());
            next = get();
        }
        return curr;
    }
    // Serves to remind the programmer that the get() method needs to be overrided for 
    // non-terminal and static operations 
    public abstract Optional<T> get();
}
