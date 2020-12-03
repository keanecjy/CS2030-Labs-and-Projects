class Circle {
    public Point pt;
    double radius;
    private Circle(Point pt, double radius) {
        this.pt = pt;
        this.radius = radius;
    }
    public static Circle getCircle(Point p, double r) {
        if (r <= 0) {
            return null;
        } else {
            return new Circle(p, r);
        }
    }
    public boolean contains(Point p) {
        return this.pt.distBetween(p) <= radius;
    }
    @Override
    public String toString() {
        return "circle of radius " + 
        this.radius + " centered at "
        + pt;
    }
}


