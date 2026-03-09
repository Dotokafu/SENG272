import java.util.ArrayList;

public class QualityDimension {
    private String name;
    private String isoCode;
    private double weight;
    private ArrayList<Criterion> criteria;

    public QualityDimension(String name ,String isoCode, double weight){
        this.name=name;
        this.isoCode=isoCode;
        this.weight =weight;
        this.criteria = new ArrayList<>();
    }
    public void addCriterion(){
        
    }
}
