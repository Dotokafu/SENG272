package lab1_swing_fileio.src;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class  ProjectFormPanel extends JPanel{
    public ProjectFormPanel(){
        
        setLayout(new GridLayout(0, 2, 5, 5));
        add(new JLabel("Project Name "));
        JTextField projectName = new JTextField();
        add(projectName);

        add(new JLabel("Team"));
        JTextField Team = new JTextField();
        add(Team);

        add(new JLabel("Team Size "));
        JComboBox TeamSize = new JComboBox<>(new String[]{"1-3","4-6","7-10","10+"});
        add(TeamSize);

        add(new JLabel("ProjectType"));
        JComboBox ProjectType = new JComboBox<>(new String[]{"Web","Mobile","Desktop","API"});
        add(ProjectType);

        add(new JLabel("StartDate"));
        JTextField StartDate = new JTextField();
        add(StartDate);

        JButton ClearButton =new JButton("Clear");
        JButton SaveButton =new JButton("Save");
        
        add(SaveButton);
        add(ClearButton);
        SaveButton.addActionListener(e->{
            String ProjectName = projectName.getText();
            String TeamName = Team.getText();
            String teamSize = (String) TeamSize.getSelectedItem();
            String projectType = (String) ProjectType.getSelectedItem();
            String startDate = StartDate.getText();
        });
      

        


    }
   
}
