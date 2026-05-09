package gui;

import javax.swing.*;
import java.awt.*;

public class StepIndicatorPanel extends JPanel {

    private static final String[] STEP_NAMES = {
        "Profile", "Define", "Plan", "Collect", "Analyse"
    };

    private int currentStep;

    public StepIndicatorPanel(int currentStep) {
        this.currentStep = currentStep;
        setBackground(new Color(30, 30, 30));
        setPreferredSize(new Dimension(0, 90));
    }

    public void setCurrentStep(int step) {
        this.currentStep = step;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        int n = STEP_NAMES.length;
        int w = getWidth();
        int h = getHeight();
        int stepW = w / n;
        int cy = h / 2 - 18;
        int r = 16;

        for (int i = 0; i < n; i++) {
            int cx = stepW / 2 + i * stepW;
            boolean done   = (i + 1) < currentStep;
            boolean active = (i + 1) == currentStep;

            // Connector line
            if (i < n - 1) {
                int nextCx = stepW / 2 + (i + 1) * stepW;
                g2.setColor(done ? new Color(50, 200, 100) : new Color(70, 70, 70));
                g2.setStroke(new BasicStroke(2));
                g2.drawLine(cx + r, cy + r, nextCx - r, cy + r);
            }

            // Circle
            if (done)        g2.setColor(new Color(50, 200, 100));
            else if (active) g2.setColor(new Color(64, 140, 255));
            else             g2.setColor(new Color(70, 70, 70));
            g2.fillOval(cx - r, cy, r * 2, r * 2);

           
            if (done) {
            // Draw checkmark manually using lines
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            int ckx = cx - 5;
            int cky = cy + r;
            g2.drawLine(ckx,     cky + 3,  ckx + 4, cky + 7);
            g2.drawLine(ckx + 4, cky + 7,  ckx + 10, cky - 1);
        } else {
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 12));
            FontMetrics fm = g2.getFontMetrics();
            String label = String.valueOf(i + 1);
            int tx = cx - fm.stringWidth(label) / 2;
            int ty = cy + r + (fm.getAscent() - fm.getDescent()) / 2;
            g2.drawString(label, tx, ty);
        }




            // Step name below
            g2.setFont(new Font("Arial", active ? Font.BOLD : Font.PLAIN, 11));
            g2.setColor(active ? Color.WHITE : new Color(150, 150, 150));
            FontMetrics fm2 = g2.getFontMetrics();
            String name = STEP_NAMES[i];
            g2.drawString(name,
                cx - fm2.stringWidth(name) / 2,
                cy + r * 2 + 14);
        }

        g2.dispose();
    }
}
