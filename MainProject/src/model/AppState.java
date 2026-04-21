package model;

public class AppState {
     private String username;
    private String school;
    private String sessionName;
    private String selectedQualityType;
    private String selectedMode;
    private Scenario selectedScenario;

    // Getters and Setters
    public String getUsername()             { return username; }
    public void setUsername(String v)       { this.username = v; }

    public String getSchool()               { return school; }
    public void setSchool(String v)         { this.school = v; }

    public String getSessionName()          { return sessionName; }
    public void setSessionName(String v)    { this.sessionName = v; }

    public String getSelectedQualityType()          { return selectedQualityType; }
    public void setSelectedQualityType(String v)    { this.selectedQualityType = v; }

    public String getSelectedMode()                 { return selectedMode; }
    public void setSelectedMode(String v)           { this.selectedMode = v; }

    public Scenario getSelectedScenario()           { return selectedScenario; }
    public void setSelectedScenario(Scenario v)     { this.selectedScenario = v; }
}

