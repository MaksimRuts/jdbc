package by.gsu.epamlab.entity;

public class Line {
    private final int LENGTH;
    private int number;

    public Line() {
        this(0, 0);
    }

    public Line(int length, int number) {
        this.LENGTH = length;
        this.number = number;
    }

    public int getLength() {
        return LENGTH;
    }

    public int getNumber() {
        return number;
    }

    public String getStringToQuery() {
        return "('" +  LENGTH + "', '" + number + "');";
    }

    @Override
    public String toString() {
        return LENGTH + ";" + number;
    }
}
