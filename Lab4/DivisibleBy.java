public class DivisibleBy implements BooleanCondition<Integer> {
    private Integer x;

    public DivisibleBy(Integer x) {
        this.x = x;
    }

    @Override
    public boolean test(Integer t) {
        return t % x == 0;
    }
}
