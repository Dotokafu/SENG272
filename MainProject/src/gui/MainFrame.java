package gui;

import model.AppState;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private AppState appState;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private StepIndicatorPanel stepIndicator;

    private Step1ProfilePanel step1;
    private Step2DefinePanel step2;
    private Step3PlanPanel step3;
    private Step4CollectPanel step4;
    private Step5AnalysePanel step5;

    public MainFrame() {
        appState = new AppState();

        setTitle("ISO 15939 Measurement Process Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setMinimumSize(new Dimension(900, 600));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Step indicator at top
        stepIndicator = new StepIndicatorPanel(1);
        add(stepIndicator, BorderLayout.NORTH);

        // Card layout for steps
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        step1 = new Step1ProfilePanel(appState, this);
        step2 = new Step2DefinePanel(appState, this);
        step3 = new Step3PlanPanel(appState, this);
        step4 = new Step4CollectPanel(appState, this);
        step5 = new Step5AnalysePanel(appState, this);

        cardPanel.add(step1, "1");
        cardPanel.add(step2, "2");
        cardPanel.add(step3, "3");
        cardPanel.add(step4, "4");
        cardPanel.add(step5, "5");

        add(cardPanel, BorderLayout.CENTER);

        cardLayout.show(cardPanel, "1");
    }

    public void navigateTo(int step) {
        // Notify panels when they become visible
        if (step == 2) step2.onShow();
        if (step == 3) step3.onShow();
        if (step == 4) step4.onShow();
        if (step == 5) step5.onShow();

        stepIndicator.setCurrentStep(step);
        cardLayout.show(cardPanel, String.valueOf(step));
    }
}