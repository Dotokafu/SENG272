package gui;

import model.AppState;
import model.Scenario;

import javax.swing.*;

import data.ScenarioRepository;

import java.awt.*;

import java.util.List;

public class Step2DefinePanel extends BaseStepPanel {
    

    private ButtonGroup qualityGroup;
    private JRadioButton rbProduct;
    private JRadioButton rbProcess;

    private ButtonGroup modeGroup;
    private JRadioButton rbHealth;
    private JRadioButton rbEducation;

    private ButtonGroup scenarioGroup;
    private JPanel scenarioButtonPanel;
    
    public Step2DefinePanel(AppState appState, MainFrame mainFrame) {
        super(appState,mainFrame);
        
        JPanel content = buildContent();
        JScrollPane scrollPane = new JScrollPane(content);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(20, 25, 40));
        add(scrollPane, BorderLayout.CENTER);
        add(buildNavButtons(), BorderLayout.SOUTH);
    }
    private JPanel buildContent() {
        JPanel content = new JPanel();
        content.setBackground(new Color(20, 25, 40));
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

        // Title
        JLabel title = new JLabel("Define Quality Dimensions");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(LEFT_ALIGNMENT);

        JLabel subtitle = new JLabel("Configure the scope of your measurement session");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 13));
        subtitle.setForeground(new Color(150, 160, 180));
        subtitle.setAlignmentX(LEFT_ALIGNMENT);

        content.add(title);
        content.add(Box.createVerticalStrut(6));
        content.add(subtitle);
        content.add(Box.createVerticalStrut(25));

        // 2a Quality Type
        content.add(makeSectionLabel("2a. Quality Type"));
        content.add(Box.createVerticalStrut(10));
        content.add(buildQualityTypePanel());
        content.add(Box.createVerticalStrut(20));

        // 2b Mode
        content.add(makeSectionLabel("2b. Measurement Mode"));
        content.add(Box.createVerticalStrut(10));
        content.add(buildModePanel());
        content.add(Box.createVerticalStrut(20));

        // 2c Scenario
        content.add(makeSectionLabel("2c. Scenario"));
        content.add(Box.createVerticalStrut(10));
        scenarioButtonPanel = new JPanel();
        scenarioButtonPanel.setBackground(new Color(20, 25, 40));
        scenarioButtonPanel.setLayout(new BoxLayout(scenarioButtonPanel, BoxLayout.Y_AXIS));
        scenarioButtonPanel.setAlignmentX(LEFT_ALIGNMENT);
        content.add(scenarioButtonPanel);

        return content;
    }
    
    private JPanel buildQualityTypePanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        panel.setBackground(new Color(20, 25, 40));
        panel.setAlignmentX(LEFT_ALIGNMENT);

        qualityGroup = new ButtonGroup();
        rbProduct = makeRadioButton("Product Quality",
            "Performance, security, usability", qualityGroup);
        rbProcess = makeRadioButton("Process Quality",
            "Sprint efficiency, code quality", qualityGroup);
        rbProduct.setSelected(true);

        panel.add(wrapInCard(rbProduct));
        panel.add(wrapInCard(rbProcess));
        return panel;
    }
    private JPanel buildModePanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        panel.setBackground(new Color(20, 25, 40));
        panel.setAlignmentX(LEFT_ALIGNMENT);

        modeGroup = new ButtonGroup();
        rbHealth    = makeRadioButton("Health",    "Health management system", modeGroup);
        rbEducation = makeRadioButton("Education", "Education LMS system",     modeGroup);
        rbHealth.setSelected(true);

        rbHealth.addActionListener(e -> refreshScenarios());
        rbEducation.addActionListener(e -> refreshScenarios());

        panel.add(wrapInCard(rbHealth));
        panel.add(wrapInCard(rbEducation));
        return panel;
    }
    private JRadioButton makeRadioButton(String text, String desc, ButtonGroup group) {
        JRadioButton rb = new JRadioButton(
            "<html><b>" + text + "</b><br><font color='#909090'>" + desc + "</font></html>");
        rb.setBackground(new Color(30, 35, 55));
        rb.setForeground(Color.WHITE);
        rb.setFont(new Font("Arial", Font.PLAIN, 13));
        rb.setFocusPainted(false);
        group.add(rb);
        return rb;
    }
    private JPanel wrapInCard(JRadioButton rb) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(30, 35, 55));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(60, 70, 100), 1),
            BorderFactory.createEmptyBorder(12, 16, 12, 16)
        ));
        card.setPreferredSize(new Dimension(220, 70));
        card.add(rb, BorderLayout.CENTER);
        return card;
    }
    public void refreshScenarios() {
        scenarioButtonPanel.removeAll();

        // Clear old scenario buttons
        if (scenarioGroup != null) {
            java.util.Enumeration<AbstractButton> buttons = scenarioGroup.getElements();
            while (buttons.hasMoreElements()) {
                scenarioGroup.remove(buttons.nextElement());
            }
        }
        scenarioGroup = new ButtonGroup();

        String mode = rbEducation.isSelected() ? "Education" : "Health";
        List<Scenario> scenarios = ScenarioRepository.getScenariosForMode(mode);

        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        row.setBackground(new Color(20, 25, 40));
        row.setAlignmentX(LEFT_ALIGNMENT);

        boolean first = true;
        for (Scenario s : scenarios) {
            JRadioButton rb = new JRadioButton(
                "<html><b>" + s.getName() + "</b><br>" +
                "<font color='#909090'>" + s.getQualityType() + " Quality</font></html>");
            rb.setBackground(new Color(30, 35, 55));
            rb.setForeground(Color.WHITE);
            rb.setFont(new Font("Arial", Font.PLAIN, 13));
            rb.setFocusPainted(false);
            rb.putClientProperty("scenario", s);
            scenarioGroup.add(rb);

            if (first) {
                rb.setSelected(true);
                first = false;
            }

            JPanel card = new JPanel(new BorderLayout());
            card.setBackground(new Color(30, 35, 55));
            card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 70, 100), 1),
                BorderFactory.createEmptyBorder(12, 16, 12, 16)
            ));
            card.setPreferredSize(new Dimension(260, 70));
            card.add(rb, BorderLayout.CENTER);
            row.add(card);
        }

        scenarioButtonPanel.add(row);
        scenarioButtonPanel.revalidate();
        scenarioButtonPanel.repaint();
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
        backBtn.setPreferredSize(new Dimension(110, 36));
        backBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backBtn.addActionListener(e -> mainFrame.navigateTo(1));

        JButton nextBtn = new JButton("Next →");
        nextBtn.setFont(new Font("Arial", Font.BOLD, 13));
        nextBtn.setBackground(new Color(64, 140, 255));
        nextBtn.setForeground(Color.WHITE);
        nextBtn.setFocusPainted(false);
        nextBtn.setBorderPainted(false);
        nextBtn.setPreferredSize(new Dimension(110, 36));
        nextBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        nextBtn.addActionListener(e -> onNext());

        nav.add(backBtn);
        nav.add(nextBtn);
        return nav;
    }
    private void onNext() {
        String qType = rbProduct.isSelected() ? "Product" : "Process";
        appState.setSelectedQualityType(qType);

        String mode = rbEducation.isSelected() ? "Education" : "Health";
        appState.setSelectedMode(mode);

        // Find selected scenario
        Scenario selected = null;
        java.util.Enumeration<AbstractButton> buttons = scenarioGroup.getElements();
        while (buttons.hasMoreElements()) {
            AbstractButton ab = buttons.nextElement();
            if (ab.isSelected()) {
                selected = (Scenario) ((JRadioButton) ab).getClientProperty("scenario");
                break;
            }
        }

        if (selected == null) {
            JOptionPane.showMessageDialog(this,
                "Please select a scenario to continue.",
                "Missing Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        appState.setSelectedScenario(selected);
        mainFrame.navigateTo(3);
    }

    public void onShow() {
        refreshScenarios();
    }
}