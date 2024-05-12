import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login implements ActionListener {

    JFrame myFrame = new JFrame();
    JPanel myPanel = new JPanel();

    JTextField Username = new JTextField(10);   
    JTextField Password = new JTextField(10);   

    JPanel log = new JPanel();
    JPanel pas = new JPanel();
    //JPanel Conpas = new JPanel();


    JButton Login = new JButton("Login");
    JButton back = new JButton("Go To Register Page");
    Login() throws NumberFormatException, IOException{

        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(400,400);
        myFrame.setVisible(true);
        myFrame.setLocationRelativeTo(null);
    
        myPanel.setLayout(new GridLayout(3,1));

        log.add(new JLabel("Username"));
        log.add(Username);
        log.setBackground(Color.red);

        pas.setLayout(new GridLayout(2,1));
        pas.add(new JLabel("Password"));
        pas.add(Password);
        pas.setBackground(Color.YELLOW);


        Login.setPreferredSize(new Dimension(100,50));
        back.setPreferredSize(new Dimension(150, 50));
        Login.addActionListener(this);
        back.addActionListener(this);
         JPanel buttonPanel = new JPanel();
        buttonPanel.add(Login);
        buttonPanel.add(back);

        myPanel.add(log );
        myPanel.add(pas);
        myPanel.add(buttonPanel);


        //int result = JOptionPane.showConfirmDialog(null, myPanel, "Login", JOptionPane.OK_CANCEL_OPTION);
        myFrame.add(myPanel);

        // if(result == JOptionPane.OK_OPTION){
        //     //JOptionPane.showMessageDialog(null, "Welcome "+Username.getText(), "Login", JOptionPane.INFORMATION_MESSAGE);
        //     System.out.println("ASDSA");
        // }
        


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Login){
            Person user = new Person(Username.getText(), Password.getText());
                try {
                    boolean flag = false;
                    ArrayList<Person> users = ReadCsv("Users.csv");
                    for(int i=0;i<users.size();i++){
                        if(user.getUsername().equals(users.get(i).getUsername())){
                            if(user.getPassword().equals(users.get(i).getPassword())){
                                flag = true;
                            }  
                        }
                    }
                    if(flag){
                        // new Page(user);
                        String path = "Data/"+user.getUsername()+".csv";
                        new MyBooks(user,FileMethods.ReadBookFull(path));
                        myFrame.dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(myFrame  , "Invalid username or password" ,"Error", JOptionPane.ERROR_MESSAGE);
                   }
                } catch (NumberFormatException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                
        }
        if (e.getSource() == back) {
            myFrame.dispose(); 
            new Register();
        }
    }

    public static ArrayList<Person> ReadCsv(String FileName) throws NumberFormatException, IOException{
        ArrayList<Person> users  = new ArrayList<>();

            String line;
            BufferedReader bfr = new BufferedReader(new FileReader(FileName));
            bfr.readLine();
            while((line=bfr.readLine())!=null){
                
                String [] arr = line.split(",");
                // Book ps = new Book(arr[0],arr[1],Double.parseDouble(arr[2]),arr[3]);
                Person ps = new Person(arr[0],arr[1]);
                users.add(ps);
                
            }
            bfr.close();
        return users;
    }

    
    public static void writeFile(String path, String text){
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, true))) {
            writer.println(text);
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }
}