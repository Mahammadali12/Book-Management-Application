import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class EditBookDialog implements ActionListener {
    JFrame MainFrame = new JFrame();
    private Person user;
    private int bookId;
    private JTextField ratingField;
    private JTextField reviewsField;
    private JButton saveButton;
    private JButton cancelButton;
    private JTable table;
    Book NewBook;
    ArrayList<Book> books;
    boolean IsAdmin = false;

    public EditBookDialog(Person user, int bookId, String title, String author, JTable table,Book NewBook,boolean IsAdmin) throws NumberFormatException, IOException {
        this.IsAdmin= IsAdmin;
        this.user = user;
        this.bookId = bookId;
        this.table = table;
        this.NewBook=NewBook;

        books = FileMethods.ReadBook("Books.csv");

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Rating:"));
        ratingField = new JTextField(NewBook.getRating().toString());
        panel.add(ratingField);
        panel.add(new JLabel("Review:"));
        reviewsField = new JTextField(NewBook.getReviews().toString());
        panel.add(reviewsField);

        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        cancelButton = new JButton("Cancel");
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
                if (!this.IsAdmin) {
                 
                int row = table.getSelectedRow();
                try {
                    Double newRating = Double.parseDouble(ratingField.getText());                
                    this.NewBook.setRating(newRating);
                    table.setValueAt(newRating, row, 3); // Assuming title is in the second column
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame, "Please enter number for rating field!!!");
                }
                String newReview = reviewsField.getText();
                this.NewBook.setReviews(newReview);

                // Update the table model with the new data
                table.setValueAt(newReview, row, 4); // Assuming author is in the third column
                FileMethods.editLine("Data/"+user.getUsername()+".csv",bookId,user,this.NewBook);


                // Close the dialog
                MainFrame.dispose();   
                }
            } 
            if (e.getSource() == cancelButton) {
                MainFrame.dispose();
            }
            // if(e.getSource()==saveButton){
                // if (this.IsAdmin) {
                    
                // int selectedRow = table.getSelectedRow();
                // if (selectedRow == -1) {
                //     JOptionPane.showMessageDialog(MainFrame, "Please select a row to delete.");
                //     return;
                // }

                // String newTitle = ratingField.getText();
                // this.NewBook.setTitle(newTitle);
                // table.setValueAt(newTitle, selectedRow, 0); 
                

                // String newAuthor = reviewsField.getText();
                // this.NewBook.setAuthor(newAuthor);
                // table.setValueAt(newAuthor, selectedRow, 1);

                // // FileMethods.editLine("Data/"+user.getUsername()+".csv",bookId,user,this.NewBook);
                // try {
                //     FileMethods.adminEdit(selectedRow,books,newTitle, newAuthor);
                // }catch(Exception ex){

                // }
            // }
    }    
}
