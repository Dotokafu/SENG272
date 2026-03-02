package lab1_swing_fileio.src;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;

public class  ProjectFormPanel extends JLabel{
    public static JPanel main(String arr[]){
        
        JPanel panel = new JPanel(new GridLayout(1,5,5,5));
        panel.add(new JLabel("Project Name "));
        panel.add(new JTextField());
        panel.add(new JLabel("Team"));
        panel.add(new JTextField());
        panel.add(new JLabel("Team Size "));
        panel.add(new JComboBox<>());
        panel.add(new JLabel("ProjectType"));
        panel.add(new JComboBox<>());
        panel.add(new JLabel("StartDate"));
        panel.add(new JTextField());

        


        return panel;
    }
   
}
