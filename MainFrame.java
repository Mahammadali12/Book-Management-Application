
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainFrame extends JFrame implements ActionListener {
    JButton log = new JButton("Log In");
    JButton reg = new JButton("Register");
    
    public void initialize() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 
        panel.add(Box.createVerticalGlue());
        JLabel label = new JLabel("Hello! Please Log In or Register");
        this.setLocationRelativeTo(null);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); 
        
        log.addActionListener(this);
       
        reg.addActionListener(this);

        log.setFocusable(false);
        reg.setFocusable(false);
        

        buttonPanel.add(log);
        buttonPanel.add(reg);
        
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(label);
        panel.add(Box.createVerticalStrut(10)); 
        panel.add(buttonPanel);
        panel.add(Box.createVerticalGlue()); 
        
        setTitle("Dostayevskis project");
        setSize(300,200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panel);
        setVisible(true);
    
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == log ){
            try {
                new Login();
                this.dispose();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        if (e.getSource() == reg){
            new Register();
            this.dispose();
        }
    }

}