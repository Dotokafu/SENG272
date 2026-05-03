package gui;

import model.AppState;
import model.Metric;
import model.Scenario;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.List;

public class Step4CollectPanel extends BaseStepPanel {

    
    private JPanel contentPanel;

    public Step4CollectPanel(AppState appState, MainFrame mainFrame) {
        super(appState, mainFrame);
        buildUI();
    }
     private void buildUI() {
        // Header
        JPanel header = new JPanel();
        header.setBackground(new Color(20, 25, 40));
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBorder(BorderFactory.createEmptyBorder(25, 60, 10, 60));

        JLabel title = new JLabel("Collect Data");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("Raw measurement values and automatically computed scores (1–5)");
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

        // Score legend
        contentPanel.add(buildLegend());
        contentPanel.add(Box.createVerticalStrut(16));

        // One table per dimension
        for (model.Dimension dim : scenario.getDimensions()) {
            contentPanel.add(buildDimensionTable(dim));
            contentPanel.add(Box.createVerticalStrut(16));
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private JPanel buildLegend() {
        JPanel legend = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        legend.setBackground(new Color(20, 25, 40));
        legend.setAlignmentX(LEFT_ALIGNMENT);

        JLabel lbl = new JLabel("Score legend:");
        lbl.setFont(new Font("Arial", Font.BOLD, 12));
        lbl.setForeground(new Color(150, 160, 180));
        legend.add(lbl);

        legend.add(makeChip("4.0–5.0  Excellent",      new Color(50, 200, 100)));
        legend.add(makeChip("3.0–3.5  Good",           new Color(0, 200, 180)));
        legend.add(makeChip("2.0–2.5  Fair",           new Color(255, 180, 50)));
        legend.add(makeChip("1.0–1.5  Poor",           new Color(220, 70, 70)));
        return legend;
    }
    private JLabel makeChip(String text, Color color) {
        JLabel chip = new JLabel("  " + text + "  ");
        chip.setFont(new Font("Arial", Font.PLAIN, 11));
        chip.setForeground(color);
        chip.setBorder(BorderFactory.createLineBorder(color, 1));
        return chip;
    }
    private JPanel buildDimensionTable(model.Dimension dim) {
        JPanel block = new JPanel(new BorderLayout(0, 10));
        block.setBackground(new Color(30, 35, 55));
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

        double dimScore = dim.getWeightedScore();
        JLabel scoreLabel = new JLabel(String.format("Score: %.1f", dimScore));
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 13));
        scoreLabel.setForeground(getScoreColor(dimScore));

        dimHeader.add(nameLabel);
        dimHeader.add(Box.createHorizontalStrut(10));
        dimHeader.add(scoreLabel);
        block.add(dimHeader, BorderLayout.NORTH);

        // Table
        String[] columns = {"Metric", "Direction", "Range", "Value", "Score (1–5)", "Coeff / Unit"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        for (Metric m : dim.getMetrics()) {
            model.addRow(new Object[]{
                m.getName(),
                m.getDirectionLabel(),
                m.getRangeLabel(),
                m.getValue(),
                m.getScore(),
                m.getCoefficient() + " / " + m.getUnit()
            });
        }

        JTable table = new JTable(model);
        styleTable(table);

        // Color score column
        table.getColumnModel().getColumn(4).setCellRenderer(
            new DefaultTableCellRenderer() {
                public Component getTableCellRendererComponent(
                        JTable t, Object v, boolean sel, boolean foc, int r, int c) {
                    super.getTableCellRendererComponent(t, v, sel, foc, r, c);
                    setBackground(r % 2 == 0 ?
                        new Color(30, 35, 55) : new Color(35, 42, 65));
                    double score = v == null ? 0 : (double) v;
                    setForeground(getScoreColor(score));
                    setFont(new Font("Arial", Font.BOLD, 13));
                    setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                    return this;
                }
            }
        );

        // Color direction column
        table.getColumnModel().getColumn(1).setCellRenderer(
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

        int height = dim.getMetrics().size() * 30 + 30;
        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createLineBorder(new Color(60, 70, 100), 1));
        sp.getViewport().setBackground(new Color(30, 35, 55));
        sp.setPreferredSize(new java.awt.Dimension(Integer.MAX_VALUE, height));
        sp.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, height));

        block.add(sp, BorderLayout.CENTER);
        return block;
    }
    
    private void styleTable(JTable table) {
        table.setBackground(new Color(30, 35, 55));
        table.setForeground(Color.WHITE);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.setRowHeight(30);
        table.setShowGrid(false);
        table.setIntercellSpacing(new java.awt.Dimension(0, 0));
        table.setFillsViewportHeight(true);
        table.getTableHeader().setBackground(new Color(20, 25, 40));
        table.getTableHeader().setForeground(new Color(150, 160, 180));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        table.getTableHeader().setBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(60, 70, 100)));

        DefaultTableCellRenderer base = new DefaultTableCellRenderer() {
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
            if (i != 4 && i != 1) {
                table.getColumnModel().getColumn(i).setCellRenderer(base);
            }
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
        backBtn.setPreferredSize(new java.awt.Dimension(110, 36));
        backBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backBtn.addActionListener(e -> mainFrame.navigateTo(3));

        JButton nextBtn = new JButton("Analyse →");
        nextBtn.setFont(new Font("Arial", Font.BOLD, 13));
        nextBtn.setBackground(new Color(64, 140, 255));
        nextBtn.setForeground(Color.WHITE);
        nextBtn.setFocusPainted(false);
        nextBtn.setBorderPainted(false);
        nextBtn.setPreferredSize(new java.awt.Dimension(120, 36));
        nextBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        nextBtn.addActionListener(e -> mainFrame.navigateTo(5));

        nav.add(backBtn);
        nav.add(nextBtn);
        return nav;
    }
}