package by.gsu.epamlab.entity;

public class Line {
    private final int LENGTH;
    private int number;
    private static final int DEFAULT_NUMBER = 1;

    public Line() {
        this(0);
    }

    public Line(int length) {
        this.LENGTH = length;
        number = DEFAULT_NUMBER;
    }

    public int getLENGTH() {
        return LENGTH;
    }

    public int getNumber() {
        return number;
    }

    public void increaseNumber() {
        this.number++;
    }


}
