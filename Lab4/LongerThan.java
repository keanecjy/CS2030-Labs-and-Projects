public class LongerThan implements BooleanCondition<String> {
    private int x;

    public LongerThan(int x) {
        this.x = x;
    }

    @Override
    public boolean test(String s) {
        int slen = s.length();
        return slen > x;
    }
}

