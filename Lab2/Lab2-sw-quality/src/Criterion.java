public class Criterion {
    private String name;
    private double weight_value;
    private boolean higher_is_better;
    private double min_value;
    private double max_value;
    private String unit;
    private double measured_value;
    private boolean value_set;

        public Criterion(String name, double weight_value, boolean higher_is_better,double min_value, double max_value, String unit){
                        
                    this.name = name;
                    this.weight_value = weight_value;
                    this.higher_is_better=higher_is_better;
                    this.min_value=min_value;
                    this.max_value=max_value;
                    this.unit=unit;
                    this.value_set = false; 
                    this.measured_value = 0.0; 
        }
        public double calculateScore(){
            double score;
            if(higher_is_better){
                score = 1 +(measured_value -min_value)/(max_value-min_value)*4;      
            }else{
                score = 5-(measured_value-min_value)/(max_value-min_value)*4;   
            }
            score = Math.max(1.0, Math.min(5.0, score));
            score =Math.round(score*2.0)/2.0;


            return score;
        }
        public void setMeasuredValue(double measuredValue) {
            this.measured_value = measuredValue;
            this.value_set = true;
        }

        public String getName(){return name;}
        public double getWeight(){return weight_value;}
        public boolean isHigherIsBetter(){ return higher_is_better; }
        public double getMinValue(){ return min_value; }
        public double getMaxValue(){ return max_value; }
        public String getUnit(){ return unit; }
        public double getMeasuredValue(){ return measured_value; }
        public boolean isValueSet(){ return value_set; }

        public String toString() {
            String direction = higher_is_better ? "Higher is better" : "Lower is better";
            return String.format("%s: %.1f %s -> Score: %.1f (%s)" ,name,measured_value,unit,calculateScore(), direction);     
        }

}


