package lab1_swing_fileio.src;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

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
            if(ProjectName.length() ==0  || TeamName.length() ==0 || startDate.length() == 0){
                JOptionPane.showMessageDialog(null, "Please enter all the information");

            }
            else{
                LocalDateTime current = LocalDateTime.now();
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
                String formatedDateTime = current.format(format);
                try (FileWriter writer =new FileWriter("projects.txt",true)){
                    writer.write("=== Project Entry ===\n");
                    writer.write("Project Name  : " + ProjectName +"\n");
                    writer.write("Team Name     : " + TeamName +"\n");
                    writer.write("Team Size     : " + teamSize +"\n");
                    writer.write("Project Type  : " + projectType +"\n");
                    writer.write("Start Date    : " + startDate +"\n");
                    writer.write("RecordTime    : " + formatedDateTime + "\n");
                    writer.write("====================\n");
                }   
                catch (IOException w) {
                    System.out.println("An error occurred");
                }
            }


        });
        ClearButton.addActionListener(e->{
            projectName.setText("");
            Team.setText("");
            TeamSize.setSelectedIndex(0);
            ProjectType.setSelectedIndex(0);
            StartDate.setText("");
        });
      

        


    }
   
}
