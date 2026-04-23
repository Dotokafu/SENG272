package gui;

import model.AppState;
import model.Metric;
import model.Scenario;


import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Dimension;
import java.awt.*;

public class Step3PlanPanel extends JPanel {
    private AppState appState;
    private MainFrame mainFrame;
    private JPanel contentPanel;

    public Step3PlanPanel(AppState appState, MainFrame mainFrame) {
        this.appState =appState;
        this.mainFrame=mainFrame;

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

        JLabel title = new JLabel("Measurement Plan");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("Review the dimensions and metrics for your selected scenario");
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
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 60, 20, 60));

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

        // Scenario info banner
        JPanel banner = new JPanel(new FlowLayout(FlowLayout.LEFT));
        banner.setBackground(new Color(30, 50, 80));
        banner.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(64, 140, 255), 1),
            BorderFactory.createEmptyBorder(8, 14, 8, 14)
        ));
        banner.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        banner.setAlignmentX(LEFT_ALIGNMENT);

        JLabel bannerLabel = new JLabel(
            scenario.getName() + "   |   " +
            scenario.getQualityType() + " Quality   |   " +
            scenario.getMode() + " Mode"
        );
        bannerLabel.setFont(new Font("Arial", Font.BOLD, 13));
        bannerLabel.setForeground(new Color(64, 140, 255));
        banner.add(bannerLabel);

        contentPanel.add(banner);
        contentPanel.add(Box.createVerticalStrut(20));

        // One table per dimension
        for (model.Dimension dim : scenario.getDimensions()) {
            contentPanel.add(buildDimensionBlock(dim));
            contentPanel.add(Box.createVerticalStrut(16));
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    

    }
     private JPanel buildDimensionBlock(model.Dimension dim) {
        JPanel block = new JPanel();
        block.setBackground(new Color(30, 35, 55));
        block.setLayout(new BorderLayout(0, 10));
        block.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(60, 70, 100), 1),
            BorderFactory.createEmptyBorder(14, 16, 14, 16)
        ));
        block.setAlignmentX(LEFT_ALIGNMENT);

        // Dimension header
        JPanel dimHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        dimHeader.setBackground(new Color(30, 35, 55));

        JLabel nameLabel = new JLabel(dim.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        nameLabel.setForeground(new Color(0, 200, 180));

        JLabel coeffLabel = new JLabel("Coefficient: " + dim.getCoefficient());
        coeffLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        coeffLabel.setForeground(new Color(150, 160, 180));
        coeffLabel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 200, 180), 1),
            BorderFactory.createEmptyBorder(2, 8, 2, 8)
        ));

        dimHeader.add(nameLabel);
        dimHeader.add(coeffLabel);
        block.add(dimHeader, BorderLayout.NORTH);

        // Metrics table
        block.add(buildMetricsTable(dim.getMetrics()), BorderLayout.CENTER);

        return block;
    }
     private JScrollPane buildMetricsTable(List<Metric> metrics) {
        String[] columns = {"Metric", "Coefficient", "Direction", "Range", "Unit"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        for (Metric m : metrics) {
            model.addRow(new Object[]{
                m.getName(),
                m.getCoefficient(),
                m.getDirectionLabel(),
                m.getRangeLabel(),
                m.getUnit()
            });
        }

        JTable table = new JTable(model);
        styleTable(table);

        // Color direction column
        table.getColumnModel().getColumn(2).setCellRenderer(
            new DefaultTableCellRenderer() {
                public Component getTableCellRendererComponent(
                        JTable t, Object v, boolean sel, boolean foc, int r, int c) {
                    super.getTableCellRendererComponent(t, v, sel, foc, r, c);
                    setBackground(r % 2 == 0 ?
                        new Color(30, 35, 55) : new Color(35, 42, 65));
                    String val = v == null ? "" : v.toString();
                    setForeground(val.contains("Higher") ?
                        new Color(50, 200, 100) : new Color(255, 180, 50));
                    setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                    return this;
                }
            }
        );

        int height = metrics.size() * 30 + 30;
        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createLineBorder(new Color(60, 70, 100), 1));
        sp.getViewport().setBackground(new Color(30, 35, 55));
        sp.setPreferredSize(new Dimension(Integer.MAX_VALUE, height));
        sp.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        return sp;
    }
     private void styleTable(JTable table) {
        table.setBackground(new Color(30, 35, 55));
        table.setForeground(Color.WHITE);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.setRowHeight(30);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setFillsViewportHeight(true);
        table.getTableHeader().setBackground(new Color(20, 25, 40));
        table.getTableHeader().setForeground(new Color(150, 160, 180));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        table.getTableHeader().setBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(60, 70, 100)));

        // Alternating row colors
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(
                    JTable t, Object v, boolean sel, boolean foc, int r, int c) {
                super.getTableCellRendererComponent(t, v, sel, foc, r, c);
                setBackground(r % 2 == 0 ?
                    new Color(30, 35, 55) : new Color(35, 42, 65));
                setForeground(Color.WHITE);
                setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                return this;
            }
        };

        for (int i = 0; i < table.getColumnCount(); i++) {
            if (i != 2) table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
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
        backBtn.addActionListener(e -> mainFrame.navigateTo(2));

        JButton nextBtn = new JButton("Next →");
        nextBtn.setFont(new Font("Arial", Font.BOLD, 13));
        nextBtn.setBackground(new Color(64, 140, 255));
        nextBtn.setForeground(Color.WHITE);
        nextBtn.setFocusPainted(false);
        nextBtn.setBorderPainted(false);
        nextBtn.setPreferredSize(new Dimension(110, 36));
        nextBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        nextBtn.addActionListener(e -> mainFrame.navigateTo(4));

        nav.add(backBtn);
        nav.add(nextBtn);
        return nav;
    }
}