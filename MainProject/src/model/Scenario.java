package model;

import java.util.ArrayList;
import java.util.List;

public class Scenario {
    private String name;
    private String qualityType;
    private String mode;
    private List<Dimension> dimensions;

    public Scenario(String name, String qualityType, String mode) {
        this.name = name;
        this.qualityType = qualityType;
        this.mode = mode;
        this.dimensions = new ArrayList<>();
    }

    public void addDimension(Dimension dimension) {
        dimensions.add(dimension);
    }

    public Dimension getWeakestDimension() {
        Dimension weakest = null;
        double lowestScore = Double.MAX_VALUE;
        for (Dimension d : dimensions) {
            if (d.getWeightedScore() < lowestScore) {
                lowestScore = d.getWeightedScore();
                weakest = d;
            }
        }
        return weakest;
    }

    // Getters
    public String getName()        { return name; }
    public String getQualityType() { return qualityType; }
    public String getMode()        { return mode; }
    public List<Dimension> getDimensions() { return dimensions; }
}
