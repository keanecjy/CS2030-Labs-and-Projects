public class LastDigitsOfHashCode implements Transformer<Object, Integer> {
    private int k;

    public LastDigitsOfHashCode(int k) {
        this.k = k;
    }

    @Override 
    public Integer transform(Object t) {
        int last = (int) Math.pow(10, k);
        return Math.abs(t.hashCode() % last);
    }
}
