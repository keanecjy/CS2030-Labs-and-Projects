public class Cuboid implements Shape3D {
    protected final double height;
    protected final double width;
    protected final double length;

    Cuboid(double height, double width, double length) {
        this.height = height;
        this.width = width;
        this.length = length;
    }

    @Override
    public double getVolume() {
        return height * width * length;
    }

    @Override
    public double getSurfaceArea() {
        double sides = 2 * (width + length) * height;
        double topAndBot = 2 * width * length;
        return sides + topAndBot;
    }

    Cuboid setHeight(double height) {
        return new Cuboid(height, this.width, this.length);
    }

    Cuboid setWidth(double width) {
        return new Cuboid(this.height, width, this.length);
    }

    Cuboid setLength(double length) {
        return new Cuboid(this.height, this.width, length);
    }   

    @Override
    public String toString() {
        return String.format("Cuboid [%.2f x %.2f x %.2f]", 
                    this.height, this.width, this.length);
    }
}


