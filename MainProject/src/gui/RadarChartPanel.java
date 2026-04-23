package gui;

import model.Scenario;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.List;

public class RadarChartPanel extends JPanel {

    private Scenario scenario;

    public RadarChartPanel(Scenario scenario) {
        this.scenario = scenario;
        setBackground(new Color(30, 35, 55));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();
        int cx = w / 2;
        int cy = h / 2;
        int radius = Math.min(w, h) / 2 - 60;

        List<model.Dimension> dims = scenario.getDimensions();
        int n = dims.size();
        if (n < 3) return;

        double[] scores = new double[n];
        String[] names  = new String[n];
        for (int i = 0; i < n; i++) {
            scores[i] = dims.get(i).getWeightedScore();
            names[i]  = dims.get(i).getName();
        }

        // Draw grid circles
        g2.setStroke(new BasicStroke(0.5f));
        for (int level = 1; level <= 5; level++) {
            int r = (int)(radius * level / 5.0);
            g2.setColor(new Color(60, 70, 100));
            g2.drawOval(cx - r, cy - r, r * 2, r * 2);

            // Level label
            g2.setColor(new Color(100, 110, 140));
            g2.setFont(new Font("Arial", Font.PLAIN, 10));
            g2.drawString(String.valueOf(level), cx + 4, cy - r + 4);
        }

        // Draw axis lines and labels
        g2.setStroke(new BasicStroke(0.8f));
        for (int i = 0; i < n; i++) {
            double angle = Math.PI / 2 + 2 * Math.PI * i / n;
            int x = (int)(cx + radius * Math.cos(angle));
            int y = (int)(cy - radius * Math.sin(angle));

            g2.setColor(new Color(60, 70, 100));
            g2.drawLine(cx, cy, x, y);

            // Dimension name label
            int lx = (int)(cx + (radius + 20) * Math.cos(angle));
            int ly = (int)(cy - (radius + 20) * Math.sin(angle));

            g2.setColor(new Color(150, 160, 180));
            g2.setFont(new Font("Arial", Font.BOLD, 11));
            FontMetrics fm = g2.getFontMetrics();

            // Shorten name if too long
            String name = names[i];
            if (name.length() > 12) name = name.substring(0, 12) + "..";

            int lw = fm.stringWidth(name);
            g2.drawString(name, lx - lw / 2, ly);
        }

        // Draw filled polygon (scores)
        int[] px = new int[n];
        int[] py = new int[n];
        for (int i = 0; i < n; i++) {
            double angle = Math.PI / 2 + 2 * Math.PI * i / n;
            double r = radius * scores[i] / 5.0;
            px[i] = (int)(cx + r * Math.cos(angle));
            py[i] = (int)(cy - r * Math.sin(angle));
        }

        // Fill with transparent color
        g2.setColor(new Color(64, 140, 255, 60));
        g2.fillPolygon(px, py, n);

        // Draw polygon border
        g2.setColor(new Color(64, 140, 255));
        g2.setStroke(new BasicStroke(2f));
        g2.drawPolygon(px, py, n);

        // Draw score dots
        for (int i = 0; i < n; i++) {
            g2.setColor(new Color(64, 140, 255));
            g2.fillOval(px[i] - 5, py[i] - 5, 10, 10);
            g2.setColor(Color.WHITE);
            g2.fillOval(px[i] - 3, py[i] - 3, 6, 6);
        }

        g2.dispose();
    }
}