package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Dimension;
import model.Metric;
import model.Scenario;

public class ScenarioRepository {
     private static final Map<String, List<Scenario>> scenarioMap = new HashMap<>();

    static {
        buildEducationScenarios();
        buildHealthScenarios();
    }

    private static void buildEducationScenarios() {
        List<Scenario> list = new ArrayList<>();

        // Scenario C – Team Alpha
        Scenario sC = new Scenario("Scenario C — Team Alpha", "Product", "Education");

        Dimension usability = new Dimension("Usability", 25);
        usability.addMetric(new Metric("SUS score",        50, true,  0,   100, "points", 89));
        usability.addMetric(new Metric("Onboarding time",  50, false, 0,   60,  "min",    5));
        sC.addDimension(usability);

        Dimension perf = new Dimension("Performance Efficiency", 20);
        perf.addMetric(new Metric("Video start time",  50, false, 0,  15,  "sec",   2.5));
        perf.addMetric(new Metric("Concurrent exams",  50, true,  0,  600, "users", 450));
        sC.addDimension(perf);

        Dimension access = new Dimension("Accessibility", 20);
        access.addMetric(new Metric("WCAG compliance",     50, true, 0, 100, "%", 91));
        access.addMetric(new Metric("Screen reader score", 50, true, 0, 100, "%", 78));
        sC.addDimension(access);

        Dimension reliability = new Dimension("Reliability", 20);
        reliability.addMetric(new Metric("Uptime", 50, true,  95, 100, "%",   99.5));
        reliability.addMetric(new Metric("MTTR",   50, false, 0,  120, "min", 8));
        sC.addDimension(reliability);

        Dimension func = new Dimension("Functional Suitability", 15);
        func.addMetric(new Metric("Feature completion",    50, true, 0, 100, "%", 95));
        func.addMetric(new Metric("Assignment submit rate",50, true, 0, 100, "%", 82));
        sC.addDimension(func);

        list.add(sC);

        // Scenario D – Team Beta
        Scenario sD = new Scenario("Scenario D — Team Beta", "Product", "Education");

        Dimension usability2 = new Dimension("Usability", 25);
        usability2.addMetric(new Metric("SUS score",       50, true,  0,  100, "points", 72));
        usability2.addMetric(new Metric("Onboarding time", 50, false, 0,  60,  "min",    22));
        sD.addDimension(usability2);

        Dimension perf2 = new Dimension("Performance Efficiency", 20);
        perf2.addMetric(new Metric("Video start time", 50, false, 0,  15,  "sec",   7));
        perf2.addMetric(new Metric("Concurrent exams", 50, true,  0,  600, "users", 300));
        sD.addDimension(perf2);

        Dimension access2 = new Dimension("Accessibility", 20);
        access2.addMetric(new Metric("WCAG compliance",     50, true, 0, 100, "%", 65));
        access2.addMetric(new Metric("Screen reader score", 50, true, 0, 100, "%", 50));
        sD.addDimension(access2);

        Dimension reliability2 = new Dimension("Reliability", 20);
        reliability2.addMetric(new Metric("Uptime", 50, true,  95, 100, "%",   97));
        reliability2.addMetric(new Metric("MTTR",   50, false, 0,  120, "min", 35));
        sD.addDimension(reliability2);

        Dimension func2 = new Dimension("Functional Suitability", 15);
        func2.addMetric(new Metric("Feature completion",     50, true, 0, 100, "%", 78));
        func2.addMetric(new Metric("Assignment submit rate", 50, true, 0, 100, "%", 61));
        sD.addDimension(func2);

        list.add(sD);
        // Scenario E – Development Team Process
        Scenario sE = new Scenario("Scenario E — Dev Team Process", "Process", "Education");

        model.Dimension sprint = new model.Dimension("Sprint Efficiency", 35);
        sprint.addMetric(new Metric("Velocity",        50, true,  0, 100, "pts/sprint", 72));
        sprint.addMetric(new Metric("Sprint burndown", 50, true,  0, 100, "%",          85));
        sE.addDimension(sprint);

    model.Dimension codeQ = new model.Dimension("Code Quality", 35);
    codeQ.addMetric(new Metric("Code coverage",  50, true,  0, 100, "%",         80));
    codeQ.addMetric(new Metric("Defect density", 50, false, 0, 20,  "bugs/KLOC", 2.5));
    sE.addDimension(codeQ);

    model.Dimension collab = new model.Dimension("Team Collaboration", 30);
    collab.addMetric(new Metric("PR review rate",     50, true, 0, 100, "%", 88));
    collab.addMetric(new Metric("Meeting attendance", 50, true, 0, 100, "%", 92));
    sE.addDimension(collab);

    list.add(sE);


        scenarioMap.put("Education", list);
    }

    private static void buildHealthScenarios() {
        List<Scenario> list = new ArrayList<>();

        // Scenario A – Hospital Portal
        Scenario sA = new Scenario("Scenario A — Hospital Portal", "Product", "Health");

        Dimension security = new Dimension("Security", 30);
        security.addMetric(new Metric("Auth failure rate",   50, false, 0,  10,  "%",      1.2));
        security.addMetric(new Metric("Encryption coverage", 50, true,  0,  100, "%",      98));
        sA.addDimension(security);

        Dimension usability = new Dimension("Usability", 25);
        usability.addMetric(new Metric("SUS score",       50, true, 0, 100, "points", 80));
        usability.addMetric(new Metric("Task completion", 50, true, 0, 100, "%",      88));
        sA.addDimension(usability);

        Dimension reliability = new Dimension("Reliability", 25);
        reliability.addMetric(new Metric("System uptime", 50, true,  95, 100, "%",   99.8));
        reliability.addMetric(new Metric("MTTR",          50, false, 0,  120, "min", 12));
        sA.addDimension(reliability);

        Dimension perfH = new Dimension("Performance", 20);
        perfH.addMetric(new Metric("Page load time",   50, false, 0, 10,  "sec",   1.8));
        perfH.addMetric(new Metric("Concurrent users", 50, true,  0, 500, "users", 350));
        sA.addDimension(perfH);

        list.add(sA);

        // Scenario B – Clinic Management
        Scenario sB = new Scenario("Scenario B — Clinic Management", "Process", "Health");

        Dimension sprint = new Dimension("Sprint Efficiency", 35);
        sprint.addMetric(new Metric("Velocity",         50, true, 0, 100, "pts/sprint", 68));
        sprint.addMetric(new Metric("Sprint burndown",  50, true, 0, 100, "%",          82));
        sB.addDimension(sprint);

        Dimension code = new Dimension("Code Quality", 35);
        code.addMetric(new Metric("Code coverage",  50, true,  0, 100, "%",          74));
        code.addMetric(new Metric("Defect density", 50, false, 0, 20,  "bugs/KLOC",  3.5));
        sB.addDimension(code);

        Dimension collab = new Dimension("Team Collaboration", 30);
        collab.addMetric(new Metric("PR review rate",      50, true, 0, 100, "%", 91));
        collab.addMetric(new Metric("Meeting attendance",  50, true, 0, 100, "%", 85));
        sB.addDimension(collab);

        list.add(sB);

        scenarioMap.put("Health", list);
    }

    public static List<Scenario> getScenariosForMode(String mode) {
        return scenarioMap.getOrDefault(mode, new ArrayList<>());
    }
}
