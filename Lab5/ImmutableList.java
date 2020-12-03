import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.Collections;
import java.util.Comparator;

class ImmutableList<T> {

    private final List<T> list;
    private final int size;

    ImmutableList(List<T> list) {
        List<T> temp = new ArrayList<T>(list);
        this.list = temp;
        this.size = temp.size();
    }
    
    @SafeVarargs
    ImmutableList(T... list) {
        List<T> temp = Arrays.asList(list);
        List<T> temp2 = new ArrayList<>(temp);
        this.list = temp2;
        this.size = temp2.size();
    }
    
    Boolean isEmpty() {
        return size == 0;
    }

    ImmutableList<T> add(T num) {
        List<T> temp = new ArrayList<T>(list);
        temp.add(num);
        return new ImmutableList<T>(temp);
    }

    ImmutableList<T> remove(T num) {
        List<T> temp = new ArrayList<T>(list);
        temp.remove(num);
        return new ImmutableList<T>(temp);
    }
    
    ImmutableList<T> replace(T target, T change) {
        List<T> temp = new ArrayList<T>(list);
        temp.replaceAll(x -> x == target ? change : x);
        return new ImmutableList<T>(temp);
    }
    
    ImmutableList<T> filter(Predicate<? super T> pred) {
        List<T> temp = new ArrayList<T>();
        for (T item : list) {
            if (pred.test(item)) {
                temp.add(item);
            }
        }
        return new ImmutableList<T>(temp);
    }

    <R> ImmutableList<R> map(Function<? super T, ? extends R> func) {
        List<R> temp = new ArrayList<R>();
        for (T item : list) {
            temp.add(func.apply(item));
        }
        return new ImmutableList<R>(temp);
    }

    ImmutableList<T> limit(long len) throws IllegalArgumentException {
        if (len < 0) {
            throw new IllegalArgumentException("limit size < 0");
        }
        List<T> temp = new ArrayList<T>();
        long count = 0;
        for (T item : list) {
            if (count == len)  {
                break;
            }
            temp.add(item);
            count++;
        }
        return new ImmutableList<T>(temp);
    }

    ImmutableList<T> sorted() throws IllegalStateException {
        if (isEmpty()) {
            return this;
        }
        List<T> temp = new ArrayList<T>(list);
        if (!(temp.get(0) instanceof Comparable)) {
            throw new IllegalStateException("List elements do not implement Comparable");
        }
        @SuppressWarnings("unchecked")
        Comparator<T> compare =  (Comparator<T>)Comparator.naturalOrder();
        Collections.sort(temp, compare);
        return new ImmutableList<T>(temp);
    }
    
    ImmutableList<T> sorted(Comparator<? super T> cmp) throws NullPointerException {
        if (cmp == null) {
            throw new NullPointerException("Comparator is null");
        }
        List<T> temp = new ArrayList<T>(list);
        Collections.sort(temp, cmp);
        return new ImmutableList<T>(temp);
    }
    
    Object[] toArray() {
        return list.toArray();
    }
    
    <R> R[] toArray(R[] given) throws NullPointerException, ArrayStoreException {
        try {
            return list.toArray(given);
        } catch (ArrayStoreException ex) {
            String str = "Cannot add element to array as it is the wrong type";
            throw new ArrayStoreException(str);
        } catch (NullPointerException ex) {
            String str = "Input array cannot be null";
            throw new NullPointerException(str);
        }
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
