package gui;

import model.AppState;
import javax.swing.*;
import java.awt.*;

public class Step1ProfilePanel extends JPanel {
    private AppState appState;
    private MainFrame mainFrame;

    private JTextField usernameField;
    private JTextField schoolField;
    private JTextField sessionField;

    public Step1ProfilePanel(AppState appState, MainFrame mainFrame) {
        this.appState = appState;
        this.mainFrame = mainFrame;

        setBackground(new Color(20, 25, 40));
        setLayout(new GridBagLayout());

        JPanel card = buildCard();
        add(card);
        
    }
     private JPanel buildCard() {
        JPanel card = new JPanel();
        card.setBackground(new Color(30, 35, 55));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        card.setPreferredSize(new Dimension(450, 400));

        // Title
        JLabel title = new JLabel("Create Your Profile");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Enter your details to begin");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 13));
        subtitle.setForeground(new Color(150, 160, 180));
        subtitle.setAlignmentX(CENTER_ALIGNMENT);

        card.add(title);
        card.add(Box.createVerticalStrut(6));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(30));

        // Fields
        usernameField = makeField("e.g. john_doe");
        schoolField   = makeField("e.g. Ankara University");
        sessionField  = makeField("e.g. Sprint 7 Review");

        card.add(makeFieldGroup("Username", usernameField));
        card.add(Box.createVerticalStrut(15));
        card.add(makeFieldGroup("School / Institution", schoolField));
        card.add(Box.createVerticalStrut(15));
        card.add(makeFieldGroup("Session Name", sessionField));
        card.add(Box.createVerticalStrut(30));

        // Next button
        JButton nextBtn = new JButton("Next →");
        nextBtn.setFont(new Font("Arial", Font.BOLD, 14));
        nextBtn.setBackground(new Color(64, 140, 255));
        nextBtn.setForeground(Color.WHITE);
        nextBtn.setFocusPainted(false);
        nextBtn.setBorderPainted(false);
        nextBtn.setPreferredSize(new Dimension(200, 38));
        nextBtn.setMaximumSize(new Dimension(200, 38));
        nextBtn.setAlignmentX(CENTER_ALIGNMENT);
        nextBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        nextBtn.addActionListener(e -> onNext());

        card.add(nextBtn);

        return card;
    }
     private JPanel makeFieldGroup(String labelText, JTextField field) {
        JPanel group = new JPanel();
        group.setLayout(new BoxLayout(group, BoxLayout.Y_AXIS));
        group.setBackground(new Color(30, 35, 55));
        group.setMaximumSize(new Dimension(350, 60));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setForeground(new Color(150, 160, 180));
        label.setAlignmentX(LEFT_ALIGNMENT);

        field.setAlignmentX(LEFT_ALIGNMENT);

        group.add(label);
        group.add(Box.createVerticalStrut(4));
        group.add(field);

        return group;
    }

    private JTextField makeField(String placeholder) {
        JTextField field = new JTextField(placeholder);
        field.setFont(new Font("Arial", Font.PLAIN, 13));
        field.setForeground(new Color(150, 160, 180));
        field.setBackground(new Color(20, 25, 40));
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(60, 70, 100), 1),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));
        field.setMaximumSize(new Dimension(350, 34));
        field.setPreferredSize(new Dimension(350, 34));

        // Clear placeholder on click
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

    private String getValue(JTextField field, String placeholder) {
        String text = field.getText();
        return text.equals(placeholder) ? "" : text;
    }

    private void onNext() {
        String username = getValue(usernameField, "e.g. john_doe");
        String school   = getValue(schoolField,   "e.g. Ankara University");
        String session  = getValue(sessionField,  "e.g. Sprint 7 Review");

        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter your username to continue.",
                "Missing Information", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (school.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter your school or institution name.",
                "Missing Information", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (session.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter a session name before proceeding.",
                "Missing Information", JOptionPane.WARNING_MESSAGE);
            return;
        }

        appState.setUsername(username);
        appState.setSchool(school);
        appState.setSessionName(session);

        mainFrame.navigateTo(2);
    }

    public void onShow() {}
}