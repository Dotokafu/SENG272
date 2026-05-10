package gui;

import model.AppState;
import model.Dimension;
import model.Metric;
import model.Scenario;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CustomScenarioPanel extends BaseStepPanel {
     private List<Dimension> customDimensions = new ArrayList<>();

    private JPanel dimensionListPanel;
    private JTextField dimNameField;
    private JTextField dimCoeffField;
    public CustomScenarioPanel(AppState appState, MainFrame mainFrame) {
        super(appState, mainFrame);
        buildUI();
    }
     private void buildUI() {
        // Header
        JPanel header = new JPanel();
        header.setBackground(new Color(20, 25, 40));
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBorder(BorderFactory.createEmptyBorder(25, 60, 10, 60));
        header.add(makeTitleLabel("Custom Scenario Builder"));
        header.add(Box.createVerticalStrut(4));
        header.add(makeSubtitleLabel("Define your own dimensions and metrics"));
        add(header, BorderLayout.NORTH);

        // Main content
        JPanel content = new JPanel();
        content.setBackground(new Color(20, 25, 40));
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(10, 60, 20, 60));

        // Add dimension form
        content.add(makeSectionLabel("Add a Dimension"));
        content.add(Box.createVerticalStrut(10));
        content.add(buildAddDimensionForm());
        content.add(Box.createVerticalStrut(20));

        // Dimension list
        content.add(makeSectionLabel("Your Dimensions"));
        content.add(Box.createVerticalStrut(10));

        dimensionListPanel = new JPanel();
        dimensionListPanel.setBackground(new Color(20, 25, 40));
        dimensionListPanel.setLayout(new BoxLayout(dimensionListPanel, BoxLayout.Y_AXIS));
        dimensionListPanel.setAlignmentX(LEFT_ALIGNMENT);
        content.add(dimensionListPanel);

        JScrollPane scrollPane = new JScrollPane(content);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(20, 25, 40));
        add(scrollPane, BorderLayout.CENTER);

        add(buildNavButtons(), BorderLayout.SOUTH);
    }
    private JPanel buildAddDimensionForm() {
        JPanel form = new JPanel();
        form.setBackground(new Color(30, 35, 55));
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(60, 70, 100), 1),
            BorderFactory.createEmptyBorder(16, 20, 16, 20)
        ));
        form.setAlignmentX(LEFT_ALIGNMENT);
        form.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 120));

        // Dimension name and coefficient row
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        row1.setBackground(new Color(30, 35, 55));
        row1.setAlignmentX(LEFT_ALIGNMENT);

        dimNameField  = makeInputField("Dimension name e.g. Usability", 220);
        dimCoeffField = makeInputField("Coefficient e.g. 25", 100);

        row1.add(makeFieldLabel("Dimension Name:"));
        row1.add(dimNameField);
        row1.add(Box.createHorizontalStrut(20));
        row1.add(makeFieldLabel("Coefficient:"));
        row1.add(dimCoeffField);

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        row2.setBackground(new Color(30, 35, 55));
        row2.setAlignmentX(LEFT_ALIGNMENT);

        // Add dimension button
        JButton addDimBtn = makeNavButton("+ Add Dimension", true);
        addDimBtn.addActionListener(e -> addDimension());
        row2.add(addDimBtn);

        form.add(row1);
        form.add(Box.createVerticalStrut(12));
        form.add(row2);

        

        return form;
    }
    private void addDimension() {
        String name  = dimNameField.getText().trim();
        String coeff = dimCoeffField.getText().trim();

        if (name.isEmpty() || name.equals("Dimension name e.g. Usability")
             || coeff.isEmpty() || coeff.equals("Coefficient e.g. 25")) {
            JOptionPane.showMessageDialog(this,
                "Please enter both dimension name and coefficient.",
                "Missing Information", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int coeffVal;
        try {
            coeffVal = Integer.parseInt(coeff);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Coefficient must be a number.",
                "Invalid Input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Create dimension with 2 default metrics
        Dimension dim = new Dimension(name, coeffVal);
        dim.addMetric(new Metric("Metric 1", 50, true,  0, 100, "%",   75));
        dim.addMetric(new Metric("Metric 2", 50, false, 0, 100, "min", 20));
        customDimensions.add(dim);

        // Clear fields
        dimNameField.setText("Dimension name e.g. Usability");
        dimNameField.setForeground(new Color(150, 160, 180));
        dimCoeffField.setText("Coefficent e.g. 25");
        dimCoeffField.setForeground(new Color(150, 160, 180));

        refreshDimensionList();
    }
    private void refreshDimensionList() {
        dimensionListPanel.removeAll();

        if (customDimensions.isEmpty()) {
            JLabel empty = new JLabel("No dimensions added yet.");
            empty.setFont(new Font("Arial", Font.ITALIC, 13));
            empty.setForeground(new Color(150, 160, 180));
            empty.setAlignmentX(LEFT_ALIGNMENT);
            dimensionListPanel.add(empty);
        } else {
            for (int i = 0; i < customDimensions.size(); i++) {
                Dimension d = customDimensions.get(i);
                JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
                row.setBackground(new Color(30, 35, 55));
                row.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(60, 70, 100), 1),
                    BorderFactory.createEmptyBorder(8, 14, 8, 14)
                ));
                row.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 44));
                row.setAlignmentX(LEFT_ALIGNMENT);

                JLabel lbl = new JLabel(
                    (i + 1) + ".  " + d.getName() +
                    "   (Coefficient: " + d.getCoefficient() + ")" +
                    "   Metrics: " + d.getMetrics().size());
                lbl.setFont(new Font("Arial", Font.PLAIN, 13));
                lbl.setForeground(Color.WHITE);

                // Remove button
                JLabel removeBtn = new JLabel("X",SwingConstants.CENTER);
                removeBtn.setFont(new Font("Arial", Font.BOLD, 12));
                removeBtn.setBackground(new Color(220, 70, 70));
                removeBtn.setForeground(Color.WHITE);
                removeBtn.setOpaque(true);
                removeBtn.setPreferredSize(new java.awt.Dimension(36, 26));
                removeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                final int index = i;
                removeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                    customDimensions.remove(index);
                    refreshDimensionList();
                     }
                });

                row.add(lbl);
                row.add(removeBtn);
                dimensionListPanel.add(row);
                dimensionListPanel.add(Box.createVerticalStrut(8));
            }
        }

        dimensionListPanel.revalidate();
        dimensionListPanel.repaint();
    }
    private JTextField makeInputField(String placeholder, int width) {
        JTextField field = new JTextField();
        field.setText(placeholder);
        field.setFont(new Font("Arial", Font.PLAIN, 13));
        field.setForeground(new Color(150, 160, 180));
        field.setBackground(new Color(20, 25, 40));
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(60, 70, 100), 1),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));
        field.setPreferredSize(new java.awt.Dimension(width, 34));

        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.WHITE);
                }
            }
            public void focusLost(java.awt.event.FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(new Color(150, 160, 180));
                }
            }
        });
        return field;
    }
    private JLabel makeFieldLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setForeground(new Color(150, 160, 180));
        return label;
    }
     private JPanel buildNavButtons() {
        JPanel nav = makeNavBar();

        JButton backBtn = makeNavButton("← Back", false);
        backBtn.addActionListener(e -> mainFrame.navigateTo(2));

        JButton nextBtn = makeNavButton("Next →", true);
        nextBtn.addActionListener(e -> onNext());

        nav.add(backBtn);
        nav.add(nextBtn);
        return nav;
    }
    private void onNext() {
        if (customDimensions.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please add at least one dimension before continuing.",
                "No Dimensions", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Build custom scenario from user input
        Scenario custom = new Scenario(
            "Custom Scenario", 
            appState.getSelectedQualityType(), 
            "Custom"
        );
        for (Dimension d : customDimensions) {
            custom.addDimension(d);
        }

        appState.setSelectedScenario(custom);
        mainFrame.navigateTo(3);
    }

    public void onShow() {
        customDimensions.clear();
        refreshDimensionList();
    }



}
