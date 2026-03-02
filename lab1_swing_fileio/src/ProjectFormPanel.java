package lab1_swing_fileio.src;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;

public class  ProjectFormPanel extends JPanel{
    public ProjectFormPanel(){
        
        setLayout(new GridLayout(0, 2, 5, 5));
        add(new JLabel("Project Name "));
        add(new JTextField());

        add(new JLabel("Team"));
        add(new JTextField());

        add(new JLabel("Team Size "));
        add(new JComboBox<>());

        add(new JLabel("ProjectType"));
        add(new JComboBox<>());
        
        add(new JLabel("StartDate"));
        add(new JTextField());

        add(new JButton("Save"));
        add(new JButton("Clear"));

        


    }
   
}
