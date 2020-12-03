public class Box<T> {
    private T t;
    
    private static final Box<?> EMPTY_BOX = new Box<>(null);

    private Box(T t) {
        this.t = t;
    }

    public static <U> Box<U> of(U t) {
        if (t == null) {
            return null;
        } else {
            return new Box<U>(t);
        }
    }

    public static <U> Box<U> ofNullable(U t) {
        if (t == null) {
            return empty();
        } else {
            return new Box<U>(t);
        }
    }

    @SuppressWarnings("unchecked")
    public static <U> Box<U> empty() {
        return (Box<U>) EMPTY_BOX;
    }
    
    public boolean isPresent() {
        return t != null;
    }

    public Box<T> filter(BooleanCondition<? super T> bool) {
        if (t == null || bool.test(t)) {
            return this;
        } else {
            return Box.empty();
        }
    }
    // Used <? super T> since any transformer that works on the superclasses of T
    // will also work on T. Used <? extends U> since it makes sense to put any
    // subtypes of U inside Box<U>. It does not, however, change any aspects in 
    // the code.
    public <U> Box<U> map(Transformer<? super T, ? extends U> transformer) {
        if (t == null) {
            return Box.empty();
        } else {
            return new Box<U>(transformer.transform(t));
        }
    }

    @Override 
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (this == Box.empty() || obj == Box.empty()) {
            return this == obj;
        } else if (obj instanceof Box) {
            Box<T> objBox = (Box<T>) obj;
            return this.t.equals(objBox.t);
        } else {
            return false;
        }
    }

    @Override 
    public String toString() {
        if (this.t == null) {
            return "[]";
        } else {
            return "[" + this.t + "]";
        }
    }
}
