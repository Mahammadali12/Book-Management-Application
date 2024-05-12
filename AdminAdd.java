import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminAdd implements ActionListener {
    JFrame MainFrame = new JFrame();
    JTextField titleField = new JTextField();
    JTextField authorField = new JTextField();
    JButton saveButton = new JButton("Save");
    JButton cancelButton = new JButton("Cancel");
    Book NewBook;
    JPanel panel = new JPanel();
    public AdminAdd() {

        panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Author:"));
        panel.add(authorField);

        saveButton.addActionListener(this);
        cancelButton.addActionListener(this);

        panel.add(saveButton);
        panel.add(cancelButton);

        MainFrame.add(panel);

        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainFrame.setSize(300, 150);
        MainFrame.setLocationRelativeTo(null);
        MainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            String NewTitle = titleField.getText();  
            String NewAuthor = authorField.getText();
            NewBook = new Book(NewTitle, NewAuthor);
            FileMethods.writeFile("Books.csv", NewBook.toStringGeneral());
            JOptionPane.showMessageDialog(MainFrame, "New Book was added to general database");
            MainFrame.dispose();
        } else if (e.getSource() == cancelButton) {
            MainFrame.dispose();
        }
    }
}
