package by.gsu.epamlab.entity;

public class Coordinate {
    private double x1;
    private double x2;

    public Coordinate() {
    }

    public Coordinate(double x1, double x2) {
        this.x1 = x1;
        this.x2 = x2;
    }

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    public String toString() {
        return "(" + x1 + ";" + x2 + ')';
    }
}
