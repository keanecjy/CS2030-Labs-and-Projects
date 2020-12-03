public class SolidCuboid extends Cuboid implements Shape3D {
    
    private final double density;

    SolidCuboid(double height, double width, double length, double density) {
        super(height, width, length);
        this.density = density;
    }

    public double getDensity() {
        return density;
    }
    
    public double getMass() {
        return getDensity() * getVolume();
    }

    SolidCuboid setHeight(double height) {
        return new SolidCuboid(height, super.width, super.length, density);
    }

    SolidCuboid setWidth(double width) {
        return new SolidCuboid(super.height, width, super.length, density);
    }

    SolidCuboid setLength(double length) {
        return new SolidCuboid(super.height, super.width, length, density);
    }   

    public String toString() {
        return "Solid" + super.toString() + 
            String.format(" with a mass of %.2f", getMass());
    }
}
