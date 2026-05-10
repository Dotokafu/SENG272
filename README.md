============================================================
  ISO/IEC 15939 Measurement Process Simulator
  Course: Software Project II — Individual Assignment
============================================================

Student Name : Unay Şenocak
Student ID   : 202428047

------------------------------------------------------------
  DESCRIPTION
------------------------------------------------------------

  A Java Swing desktop application that simulates the 5-step
  ISO/IEC 15939 software measurement process. Users can define
  quality dimensions and metrics of a software system, collect
  data, and analyze results through a wizard-style interface.

------------------------------------------------------------
  COMPILATION & RUN
------------------------------------------------------------

  Open Command Prompt or PowerShell inside the MainProject
  folder:

  cd <path to MainProject folder>

  Compile:
  javac -cp src src\model\*.java src\data\*.java src\gui\*.java Main.java

  Run:
  java -cp "src;." Main

------------------------------------------------------------
  APPLICATION STEPS
------------------------------------------------------------

  Step 1 - Profile  : Enter username, school, session name
  Step 2 - Define   : Select quality type, mode, scenario
  Step 3 - Plan     : View dimensions and metrics (read-only)
  Step 4 - Collect  : View raw data and calculated scores
  Step 5 - Analyse  : View scores, radar chart, gap analysis

------------------------------------------------------------
  AVAILABLE SCENARIOS
------------------------------------------------------------

  Health Mode:
    Scenario A - Hospital Portal     (Product Quality)
    Scenario B - Clinic Management   (Process Quality)

  Education Mode:
    Scenario C - Team Alpha          (Product Quality)
    Scenario D - Team Beta           (Product Quality)
    Scenario E - Dev Team Process    (Process Quality)

  Custom Mode:
    Define your own dimensions and metrics from scratch

------------------------------------------------------------
  BONUS FEATURES
------------------------------------------------------------

  - Radar Chart  : Drawn with Graphics2D (Java 2D)
  - Custom Mode  : User defined dimensions and metrics

------------------------------------------------------------
  TECHNICAL DETAILS
------------------------------------------------------------

  - Java SE 21
  - Java Swing — no external libraries used
  - MVC Pattern — model/ and gui/ packages separated
  - CardLayout wizard — one JPanel per step
  - Inheritance — all step panels extend BaseStepPanel
  - Polymorphism — onShow() overridden in each step panel
  - Collections — ArrayList and HashMap used throughout

------------------------------------------------------------
  SCREENSHOT
------------------------------------------------------------

  See screenshots/ folder in the repository.

------------------------------------------------------------
  AI USAGE
------------------------------------------------------------

  AI tools were used as a coding assistant during development.
  All code was reviewed, understood, and modified by the student.

============================================================
