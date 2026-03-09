public class Criterion {
    private String name;
    private double weight_value;
    private boolean higer_is_better;
    private double min_value;
    private double max_value;
    private String unit;
    private double measured_value;
    private boolean value_set;

        public Criterion(String name, double weight_value, boolean higher_is_better,double min_value, double max_value, String unit){
                        
                    this.name = name;
                    this.weight_value = weight_value;
                    this.higer_is_better=higher_is_better;
                    this.min_value=min_value;
                    this.max_value=max_value;
                    this.unit=unit;
                    this.value_set = false; 
                    this.measured_value = 0.0; 
        }
        public double CalculateScore(){
            double score;
            if(higer_is_better){
                score = 1 +(measured_value -min_value)/(max_value-min_value)*4;      
            }else{
                score = 5-(measured_value-min_value)/(max_value-min_value)*4;   
            }


            return score;
        }



}


