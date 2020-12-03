public class Sphere implements Shape3D {
    
    protected final double radius;
    
    Sphere(double radius) {
        this.radius = radius;
    }
    
    @Override
    public double getVolume() {
        return (4 * Math.PI * radius * radius * radius) / 3;
    }

    @Override
    public double getSurfaceArea() {
        return 4 * Math.PI * radius * radius;
    }

    Sphere setRadius(double radius) {
        return new Sphere(radius);
    }

    @Override
    public String toString() {
        return String.format("Sphere [%.2f]", this.radius);
    }

}
