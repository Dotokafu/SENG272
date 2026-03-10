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
    public void addCriterion(Criterion criterion){
        criteria.add(criterion);
        
    }
    public double calculateDimensionScore(){
        if (criteria.isEmpty()){return 0.0;}
        double weightSum=0;
        double metricSum=0;

        for(Criterion c: criteria){
            weightSum +=c.getWeight();
            metricSum += (c.calculateScore() *c.getWeight());
        }
        return metricSum/weightSum;
    }
    public String getQualityLabel(){
        double score =calculateDimensionScore();
        if(score>=4.5){
            return "Excellent Quality";
        }
        else if(score>=3.5){return "Good Quality";}
        else if(score>=2.5){return "Needs Improvement";}
        else{return "Poor Quality";}
        
    }
    public double getQualityGap(){
        return 5.0 - calculateDimensionScore();
    }

    public String getName(){return name;}
    public String getIsoCode(){return isoCode;}
    public double getWeight(){return weight;}
    public ArrayList<Criterion> getCriteria(){return criteria;}

    public String toString(){
        return String.format("--- %s  [%s] (Weight: %.0f) ---", name,isoCode,weight);

    }
}
