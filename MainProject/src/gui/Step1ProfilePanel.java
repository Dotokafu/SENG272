package gui;

import model.AppState;
import javax.swing.*;
import java.awt.*;

public class Step1ProfilePanel extends JPanel {
    public Step1ProfilePanel(AppState appState, MainFrame mainFrame) {
        setBackground(new Color(20, 20, 20));
        add(new JLabel("Step 1 - Profile (coming soon)"));
    }
    public void onShow() {}
}