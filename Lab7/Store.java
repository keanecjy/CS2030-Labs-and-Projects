class Store {
    public final int max;
    public final int min;
    public final double sum;
    public final int count;
    
    public Store(int max, int min, double sum, int count) {
        this.max = max;
        this.min = min;
        this.sum = sum;
        this.count = count;
    }

    public Store add(int x) {
        return new Store(Math.max(x, this.max), Math.min(x, this.min),
                        x + this.sum, 1 + this.count);
    }

    public Store add(Store s) {
        return new Store(Math.max(s.max, this.max), Math.min(s.min, this.min),
                        s.sum + this.sum, s.count + this.count);
    }

    public double getMean() {
       if (min == max || count == 0) {
            return 0;
        } else {
            return (sum / count - min) / (max - min);
        }
    }
}       
