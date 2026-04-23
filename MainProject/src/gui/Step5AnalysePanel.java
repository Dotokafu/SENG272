package gui;

import model.AppState;
import model.Scenario;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.List;

public class Step5AnalysePanel extends JPanel {

    private AppState appState;
    private MainFrame mainFrame;
    private JPanel contentPanel;

    public Step5AnalysePanel(AppState appState, MainFrame mainFrame) {
        this.appState = appState;
        this.mainFrame = mainFrame;
        setBackground(new Color(20, 25, 40));
        setLayout(new BorderLayout());
        buildUI();
    }
    private void buildUI() {
        // Header
        JPanel header = new JPanel();
        header.setBackground(new Color(20, 25, 40));
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBorder(BorderFactory.createEmptyBorder(25, 60, 10, 60));

        JLabel title = new JLabel("Analysis Results");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("Dimension scores, radar chart and gap analysis");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 13));
        subtitle.setForeground(new Color(150, 160, 180));

        header.add(title);
        header.add(Box.createVerticalStrut(4));
        header.add(subtitle);
        add(header, BorderLayout.NORTH);

        // Scrollable content
        contentPanel = new JPanel();
        contentPanel.setBackground(new Color(20, 25, 40));
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 60, 30, 60));

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(20, 25, 40));
        add(scrollPane, BorderLayout.CENTER);

        add(buildNavButtons(), BorderLayout.SOUTH);
    }

    public void onShow() {
           contentPanel.removeAll();

        Scenario scenario = appState.getSelectedScenario();
        if (scenario == null) return;

        // 5a — Dimension scores with progress bars
        contentPanel.add(makeSectionLabel("5a. Dimension Scores"));
        contentPanel.add(Box.createVerticalStrut(12));
        contentPanel.add(buildDimensionScores(scenario));
        contentPanel.add(Box.createVerticalStrut(24));

        // 5b — Radar chart (bonus)
        contentPanel.add(makeSectionLabel("5b. Radar Chart"));
        contentPanel.add(Box.createVerticalStrut(12));
        contentPanel.add(buildRadarChart(scenario));
        contentPanel.add(Box.createVerticalStrut(24));

        // 5c — Gap analysis
        contentPanel.add(makeSectionLabel("5c. Gap Analysis"));
        contentPanel.add(Box.createVerticalStrut(12));
        contentPanel.add(buildGapAnalysis(scenario));

        contentPanel.revalidate();
        contentPanel.repaint();
    }
    private JLabel makeSectionLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setForeground(new Color(64, 140, 255));
        label.setAlignmentX(LEFT_ALIGNMENT);
        return label;
    }
    private JPanel buildDimensionScores(Scenario scenario) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(30, 35, 55));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(60, 70, 100), 1),
            BorderFactory.createEmptyBorder(16, 20, 16, 20)
        ));
        panel.setAlignmentX(LEFT_ALIGNMENT);

        for (model.Dimension dim : scenario.getDimensions()) {
            double score = dim.getWeightedScore();
            Color scoreColor = getScoreColor(score);

            JPanel row = new JPanel(new BorderLayout(10, 0));
            row.setBackground(new Color(30, 35, 55));
            row.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 36));
            row.setAlignmentX(LEFT_ALIGNMENT);

            // Dimension name
            JLabel nameLabel = new JLabel(dim.getName());
            nameLabel.setFont(new Font("Arial", Font.BOLD, 13));
            nameLabel.setForeground(Color.WHITE);
            nameLabel.setPreferredSize(new java.awt.Dimension(200, 26));

            // Progress bar
            JProgressBar bar = new JProgressBar(0, 100);
            bar.setValue((int)((score / 5.0) * 100));
            bar.setStringPainted(false);
            bar.setBackground(new Color(20, 25, 40));
            bar.setForeground(scoreColor);
            bar.setBorder(BorderFactory.createLineBorder(new Color(60, 70, 100), 1));
            bar.setPreferredSize(new java.awt.Dimension(300, 22));

            // Score label
            JLabel scoreLabel = new JLabel(String.format("%.2f / 5.00", score));
            scoreLabel.setFont(new Font("Arial", Font.BOLD, 13));
            scoreLabel.setForeground(scoreColor);
            scoreLabel.setPreferredSize(new java.awt.Dimension(90, 26));

            row.add(nameLabel,  BorderLayout.WEST);
            row.add(bar,        BorderLayout.CENTER);
            row.add(scoreLabel, BorderLayout.EAST);

            panel.add(row);
            panel.add(Box.createVerticalStrut(10));
        }

        return panel;
    }
    private JPanel buildRadarChart(Scenario scenario) {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(new Color(30, 35, 55));
        wrapper.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(60, 70, 100), 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        wrapper.setAlignmentX(LEFT_ALIGNMENT);

        RadarChartPanel radar = new RadarChartPanel(scenario);
        radar.setPreferredSize(new java.awt.Dimension(400, 350));
        wrapper.add(radar, BorderLayout.CENTER);
        return wrapper;
    }
    private JPanel buildGapAnalysis(Scenario scenario) {
        model.Dimension weakest = scenario.getWeakestDimension();
        if (weakest == null) return new JPanel();

        double score = weakest.getWeightedScore();
        double gap   = 5.0 - score;
        String label = getQualityLabel(score);
        Color  color = getScoreColor(score);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(30, 35, 55));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color, 1),
            BorderFactory.createEmptyBorder(20, 24, 20, 24)
        ));
        panel.setAlignmentX(LEFT_ALIGNMENT);

        JLabel heading = new JLabel("⚠  Weakest Dimension: " + weakest.getName());
        heading.setFont(new Font("Arial", Font.BOLD, 16));
        heading.setForeground(color);
        heading.setAlignmentX(LEFT_ALIGNMENT);

        JLabel scoreLine = new JLabel(String.format("Score: %.2f / 5.00", score));
        scoreLine.setFont(new Font("Arial", Font.PLAIN, 13));
        scoreLine.setForeground(Color.WHITE);
        scoreLine.setAlignmentX(LEFT_ALIGNMENT);

        JLabel gapLine = new JLabel(String.format("Gap: %.2f  (distance from perfect score of 5.0)", gap));
        gapLine.setFont(new Font("Arial", Font.PLAIN, 13));
        gapLine.setForeground(Color.WHITE);
        gapLine.setAlignmentX(LEFT_ALIGNMENT);

        JLabel qualityLine = new JLabel("Quality Level: " + label);
        qualityLine.setFont(new Font("Arial", Font.BOLD, 13));
        qualityLine.setForeground(color);
        qualityLine.setAlignmentX(LEFT_ALIGNMENT);

        JLabel message = new JLabel(
            "This dimension has the lowest score and requires the most improvement.");
        message.setFont(new Font("Arial", Font.ITALIC, 13));
        message.setForeground(new Color(150, 160, 180));
        message.setAlignmentX(LEFT_ALIGNMENT);

        panel.add(heading);
        panel.add(Box.createVerticalStrut(12));
        panel.add(scoreLine);
        panel.add(Box.createVerticalStrut(6));
        panel.add(gapLine);
        panel.add(Box.createVerticalStrut(6));
        panel.add(qualityLine);
        panel.add(Box.createVerticalStrut(12));
        panel.add(message);

        return panel;
    }
    private Color getScoreColor(double score) {
        if (score >= 4.0) return new Color(50, 200, 100);
        if (score >= 3.0) return new Color(0, 200, 180);
        if (score >= 2.0) return new Color(255, 180, 50);
        return new Color(220, 70, 70);
    }
    private String getQualityLabel(double score) {
        if (score >= 4.5) return "Excellent";
        if (score >= 3.5) return "Good";
        if (score >= 2.5) return "Needs Improvement";
        return "Poor";
    }
    private JPanel buildNavButtons() {
        JPanel nav = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 12));
        nav.setBackground(new Color(20, 25, 40));
        nav.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0,
            new Color(60, 70, 100)));

        JButton backBtn = new JButton("← Back");
        backBtn.setFont(new Font("Arial", Font.BOLD, 13));
        backBtn.setBackground(new Color(40, 45, 65));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.setBorderPainted(false);
        backBtn.setPreferredSize(new java.awt.Dimension(110, 36));
        backBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backBtn.addActionListener(e -> mainFrame.navigateTo(4));

        JButton restartBtn = new JButton("↺ Restart");
        restartBtn.setFont(new Font("Arial", Font.BOLD, 13));
        restartBtn.setBackground(new Color(0, 200, 180));
        restartBtn.setForeground(Color.WHITE);
        restartBtn.setFocusPainted(false);
        restartBtn.setBorderPainted(false);
        restartBtn.setPreferredSize(new java.awt.Dimension(110, 36));
        restartBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        restartBtn.addActionListener(e -> mainFrame.navigateTo(1));

        nav.add(backBtn);
        nav.add(restartBtn);
        return nav;
    }
}