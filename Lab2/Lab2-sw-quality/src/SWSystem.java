import java.util.ArrayList;

public class SWSystem {
    private String name;
    private String catagory;
    private String version;
    private ArrayList<QualityDimension>  characteristics;

    public SWSystem(String name,String catagory,String version){
        this.catagory=catagory;
        this.name=name;
        this.version=version;
        characteristics=new ArrayList<QualityDimension>();
    }
    public void addDimension(QualityDimension dimension){
        characteristics.add(dimension);
    }
    public double calculateOverallScore(){
        if (characteristics.isEmpty()){return 0.0;}
        double weightSum=0;
        double dimensionSum=0;

        for(QualityDimension d: characteristics){
            weightSum +=d.getWeight();
            dimensionSum += (d.calculateDimensionScore() *d.getWeight());
        }
        return dimensionSum/weightSum;

        
    }
    public QualityDimension findWeakestDimension(){
        if (characteristics.isEmpty()) return null;
        QualityDimension weakest = characteristics.get(0);
        for(QualityDimension d :characteristics){
            if(d.calculateDimensionScore()<weakest.calculateDimensionScore()){
                weakest = d;
            }
            
        }
        return weakest;
    }
    public String getQualityLabel(double score) {
    if (score >= 4.5) return "Excellent Quality";
    else if(score>=3.5){return "Good Quality";}
    else if(score>=2.5){return "Needs Improvement";}
    else{return "Poor Quality";}
    }
    public void printReport() {
    String separator = "========================================";
    System.out.println(separator);
    System.out.println("SOFTWARE QUALITY EVALUATION REPORT (ISO/IEC 25010)");
    System.out.printf("System: %s v%s (%s)%n",name,version,catagory); 
    System.out.println(separator);
    for (QualityDimension d : characteristics) {
        System.out.println(d.toString());
            for (Criterion c : d.getCriteria()) {
                System.out.println(c.toString());
        
            }
    System.out.printf(">> Dimension Score: %.1f/5 [%s]\n",d.calculateDimensionScore(),d.getQualityLabel());
    }
    System.out.println(separator);
    System.out.printf("OVERALL QUALITY SCORE: %.1f/5 [%s]\n",calculateOverallScore(),getQualityLabel(calculateOverallScore()));
    System.out.println(separator);
    System.out.println("GAP ANALYSIS (ISO/IEC 25010)");
    System.out.println(separator);
    QualityDimension weakest= findWeakestDimension();
    System.out.printf("Weakest Characteristic : %s [%s]\n",weakest.getName(),weakest.getIsoCode());
    System.out.printf("Score: %.1f/5 | Gap: %.1f\n",weakest.calculateDimensionScore(),weakest.getQualityGap());
    System.out.printf("Level: %s\n",weakest.getQualityLabel());
    System.out.println(">> This characteristic requires the most improvement.");
    System.out.println(separator);
}
public String getName(){return name;}
public String getCatagory(){return catagory;}
public String getVersion(){return version;}
public ArrayList<QualityDimension> getDimensions() { return characteristics; }
}
