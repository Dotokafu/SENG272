package model;

public class Metric {
    private String name;
    private int coefficient;
    private boolean higherIsBetter;
    private double rangeMin;
    private double rangeMax;
    private String unit;
    private double value;
    private double score;

    public Metric(String name, int coefficient, boolean higherIsBetter,
                  double rangeMin, double rangeMax, String unit, double value) {
        this.name = name;
        this.coefficient = coefficient;
        this.higherIsBetter = higherIsBetter;
        this.rangeMin = rangeMin;
        this.rangeMax = rangeMax;
        this.unit = unit;
        this.value = value;
        this.score = calculateScore(value);
    }
    public double calculateScore(double val) {
        double range = rangeMax - rangeMin;
        if (range == 0) return 3.0;

        double raw;
        if (higherIsBetter) {
            raw = 1.0 + (val - rangeMin) / range * 4.0;
        } else {
            raw = 5.0 - (val - rangeMin) / range * 4.0;
        }
        raw = Math.max(1.0, Math.min(5.0, raw));
        return Math.round(raw * 2.0) / 2.0;
    }
    public String getDirectionLabel() {
        return higherIsBetter ? "Higher is better" : "Lower is better";
    }

    public String getRangeLabel() {
        return (int)rangeMin + "-" + (int)rangeMax;
    }
    public String getName()        { return name; }
    public int getCoefficient()    { return coefficient; }
    public boolean isHigherIsBetter() { return higherIsBetter; }
    public double getRangeMin()    { return rangeMin; }
    public double getRangeMax()    { return rangeMax; }
    public String getUnit()        { return unit; }
    public double getValue()       { return value; }
    public double getScore()       { return score; }
}

