import java.util.ArrayList;
import java.util.HashMap;

public class SWSystemData {
    public static HashMap<String, ArrayList<SWSystem>> getAllSystems() {
        HashMap<String, ArrayList<SWSystem>> map = new HashMap<>();
        ArrayList<SWSystem> webList = new ArrayList<>();
        webList.add(createECommercePlatform());
        webList.add(createBankingPortal());
        map.put("Web", webList);
        ArrayList<SWSystem> mobileList = new ArrayList<>();
        mobileList.add(createHealthApp());
        map.put("Mobile", mobileList);
        return map;
    }

    private static SWSystem createECommercePlatform() {

        SWSystem s = new SWSystem("ShopSphere", "Web", "3.2.1");
            QualityDimension funcSuit = new QualityDimension("Functional Suitability", "QC.FS", 25);
                funcSuit.addCriterion(new Criterion("Functional Completeness Ratio", 50, true, 50, 100, "%"));
                funcSuit.addCriterion(new Criterion("Functional Correctness Ratio", 50, true, 50, 100, "%"));
                s.addDimension(funcSuit);
            QualityDimension reliability = new QualityDimension("Reliability", "QC.RE", 25);
                reliability.addCriterion(new Criterion("Availability Ratio", 50, true, 95, 100, "%"));
                reliability.addCriterion(new Criterion("Defect Density", 50, false, 0, 20, "defect/KLOC"));
                s.addDimension(reliability);
            QualityDimension perfEffic = new QualityDimension("Performance Efficiency", "QC.PE", 25);
                perfEffic.addCriterion(new Criterion("Response Time", 50, false, 100, 600, "ms"));
                perfEffic.addCriterion(new Criterion("CPU Utilisation Ratio", 50, false, 0, 152, "%"));
                s.addDimension(perfEffic);
            QualityDimension maintain = new QualityDimension("Maintainability", "QC.MA", 25);
                maintain.addCriterion(new Criterion("Test Coverage Ratio", 50, true, 30, 100, "%"));
                maintain.addCriterion(new Criterion("Cyclomatic Complexity", 50, false, 1, 20, "score"));
                s.addDimension(maintain);
        

            return s;
    }
    private static SWSystem createBankingPortal() {
        SWSystem s = new SWSystem("MechaBank", "Web", "2.3.8");
            QualityDimension security = new QualityDimension("Security", "QC.SE", 40);
                security.addCriterion(new Criterion("Security Test Coverage", 50, true, 0, 100, "%"));
                security.addCriterion(new Criterion("Vulnerability Count", 50, false, 0, 50, "count"));
                s.addDimension(security);
            QualityDimension reliability = new QualityDimension("Reliability", "QC.RE", 35);
                reliability.addCriterion(new Criterion("Availability Ratio", 50, true, 95, 100, "%"));
                reliability.addCriterion(new Criterion("MTBF", 50, true, 0, 10000, "hours"));
                s.addDimension(reliability);
            QualityDimension perfEffic = new QualityDimension("Performance Efficiency", "QC.PE", 25);
                perfEffic.addCriterion(new Criterion("Response Time", 50, false, 100, 2000, "ms"));
                perfEffic.addCriterion(new Criterion("Throughput", 50, true, 10, 500, "req/s"));
                s.addDimension(perfEffic);

        return s;
    }
    private static SWSystem createHealthApp(){
       SWSystem s = new SWSystem("HealthApp", "Mobile", "4.1.9");
            QualityDimension usability = new QualityDimension("Usability", "QC.US", 40);
                usability.addCriterion(new Criterion("Task Completion Rate", 50, true, 0, 100, "%"));
                usability.addCriterion(new Criterion("User Error Rate", 50, false, 0, 100, "%"));
                s.addDimension(usability);
            QualityDimension reliability = new QualityDimension("Reliability", "QC.RE", 35);
                reliability.addCriterion(new Criterion("Availability Ratio", 50, true, 95, 100, "%"));
                reliability.addCriterion(new Criterion("Defect Density", 50, false, 0, 20, "defect/KLOC"));
                s.addDimension(reliability);
            QualityDimension perfEffic = new QualityDimension("Performance Efficiency", "QC.PE", 25);
                perfEffic.addCriterion(new Criterion("Response Time", 50, false, 100, 2000, "ms"));
                perfEffic.addCriterion(new Criterion("CPU Utilisation Ratio", 50, false, 0, 100, "%"));
                s.addDimension(perfEffic);
        return s; 
        

    }
}
