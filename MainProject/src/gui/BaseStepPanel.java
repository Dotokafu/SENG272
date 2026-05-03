package gui;

import model.AppState;
import javax.swing.*;
import java.awt.*;

public abstract class BaseStepPanel extends JPanel {
    protected AppState appState;
    protected MainFrame mainFrame;

    public BaseStepPanel(AppState appState, MainFrame mainFrame) {
        this.appState = appState;
        this.mainFrame = mainFrame;
        setBackground(new Color(20, 25, 40));
        setLayout(new BorderLayout());
    }

     public abstract void onShow();

      protected JLabel makeTitleLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 22));
        label.setForeground(Color.WHITE);
        return label;
    }
      protected JLabel makeSubtitleLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 13));
        label.setForeground(new Color(150, 160, 180));
        return label;
    }
     protected JLabel makeSectionLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setForeground(new Color(64, 140, 255));
        label.setAlignmentX(LEFT_ALIGNMENT);
        return label;
    }
     protected JButton makeNavButton(String text, boolean primary) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setBackground(primary ?
            new Color(64, 140, 255) : new Color(40, 45, 65));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new java.awt.Dimension(120, 36));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }
     protected JPanel makeNavBar() {
        JPanel nav = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 12));
        nav.setBackground(new Color(20, 25, 40));
        nav.setBorder(BorderFactory.createMatteBorder(
            1, 0, 0, 0, new Color(60, 70, 100)));
        return nav;
    }
     protected Color getScoreColor(double score) {
        if (score >= 4.0) return new Color(50, 200, 100);
        if (score >= 3.0) return new Color(0, 200, 180);
        if (score >= 2.0) return new Color(255, 180, 50);
        return new Color(220, 70, 70);
    }
        protected String getQualityLabel(double score) {
        if (score >= 4.5) return "Excellent";
        if (score >= 3.5) return "Good";
        if (score >= 2.5) return "Needs Improvement";
        return "Poor";
    }

}
