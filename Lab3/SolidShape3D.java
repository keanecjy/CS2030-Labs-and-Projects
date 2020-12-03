class SolidShape3D {
    Material m;
    Shape3D s;

    SolidShape3D(Shape3D s, Material m) {
        this.s = s;
        this.m = m;
    }

    public double getMass() {
        return s.getVolume() * getDensity();
    }

    public double getDensity() {
        return m.density;
    }

    public String toString() {
        return "Solid" + s.toString() + String.format(" with a mass of %.2f", getMass());
    }
}
