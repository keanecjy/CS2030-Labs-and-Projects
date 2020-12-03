public class SolidSphere extends Sphere {
    private final double density;

    SolidSphere(double radius, double density) {
        super(radius);
        this.density = density;
    }

    public double getDensity() {
        return density;
    }

    public double getMass() {
        return getVolume() * getDensity();
    }
    
    SolidSphere setRadius(double radius) {
        return new SolidSphere(radius, this.density);
    }

    @Override
    public String toString() {
        return "Solid" + super.toString() + String.format(" with a mass of %.2f", getMass());
    }
}
