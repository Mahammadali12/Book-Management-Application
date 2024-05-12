import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Register implements ActionListener {

    JFrame myFrame = new JFrame();
    JPanel myPanel = new JPanel();

    JTextField Username = new JTextField(10);
    JTextField Password = new JTextField(10);
    JTextField ConPassword = new JTextField(10);

    JPanel log = new JPanel();
    JPanel pas = new JPanel();
    JPanel buttons = new JPanel(); 

    // JPanel Conpas = new JPanel();

    JButton but = new JButton("Register");
    JButton backButton = new JButton("Go To Login Page"); 

    Register() {

        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(400, 400);
        myFrame.setVisible(true);
        myFrame.setLocationRelativeTo(null);

        myPanel.setLayout(new GridLayout(3, 1));

        log.add(new JLabel("Username"));
        log.add(Username);
        log.setBackground(Color.blue);

        pas.setLayout(new GridLayout(2, 1));
        pas.add(new JLabel("Password"));
        pas.add(Password);
        pas.add(new JLabel("ConfrimPassword"));
        pas.add(ConPassword);
        pas.setBackground(Color.YELLOW);

        but.setPreferredSize(new Dimension(100, 50));
        backButton.setPreferredSize(new Dimension(100, 50));
        but.addActionListener(this);
        backButton.addActionListener(this);
        buttons.add(but);
        buttons.add(backButton);
        myPanel.add(log);
        myPanel.add(pas);
        myPanel.add(buttons);

        myFrame.add(myPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == but) {
            String text = String.format("%s,%s", Username.getText(), Password.getText());
            String path = String.format("Data/%s.csv", Username.getText());
            ArrayList<Person> users;
            try {
                users = FileMethods.ReadUser("Users.csv");
            } catch (IOException e1) {
                System.out.println("Error reading Users.csv: " + e1.getMessage());
                return;
            }
            if (Username.getText().length() < 6) {
                JOptionPane.showMessageDialog(myFrame, "Username is too short. It must be at least 6 characters.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
           
            if (Password.getText().length() < 6) {
                JOptionPane.showMessageDialog(myFrame, "The password is too short. It must be at least 6 characters.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean usernameExists = false;
            for (Person user : users) {
                if (Username.getText().equals(user.getUsername())) {
                    usernameExists = true;
                    break;
                }
            }

            if (usernameExists) {
                JOptionPane.showMessageDialog(myFrame, "Username already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(Password.getText().equals(ConPassword.getText())){                
                try {
                    new Login();
                    BufferedWriter writer = new BufferedWriter(new FileWriter(path));
                    FileMethods.writeFile("Users.csv", text);
                    FileMethods.writeFile(path, "Id,Title,Author,Rating,Reviews,Status,TimeSpent,StartDate,EndDate");
                    myFrame.dispose();
                    JOptionPane.showMessageDialog(myFrame,"Succesfully registered");
                    writer.close();
                } catch (NumberFormatException e1) {
                    System.out.println(path);
                    e1.printStackTrace();
                } catch (IOException e1) {
                    System.out.println(path);
                    e1.printStackTrace();
                }
                finally{
                    System.out.println(path);
                }
            }else {
                JOptionPane.showMessageDialog(myFrame, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == backButton) {
            myFrame.dispose(); 
            try {
                new Login();
            } catch (NumberFormatException | IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}