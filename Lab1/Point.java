class Point {
    double x;
    double y;
    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Point midPoint(Point p2) {
        return new Point((this.x + p2.x) / 2,
        (this.y + p2.y) / 2);
    }
    public double angleTo(Point newPoint) {
        double chX = newPoint.x - this.x;
        double chY = newPoint.y - this.y;
        double ang = Math.atan2(chY, chX);
        return ang;
    }
    public Point moveTo(double angle, double d) {
        double x = this.x + d * Math.cos(angle);
        double y = this.y + d * Math.sin(angle);
        return new Point(x, y);
    }
    public double distBetween(Point p2) {
        double chX = this.x - p2.x;
        double chY = this.y - p2.y;
        return Math.sqrt(chX * chX + chY * chY);
    }
        
    @Override
    public String toString() {
        return "point (" + String.format("%.3f",this.x) + ", " +
        String.format("%.3f",this.y) + ")";    
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Point) {
            Point p = (Point) obj;
            return this.x - p.x == 0 && this.y - p.y == 0;
        } else {
            return false;
        }
    }
}


