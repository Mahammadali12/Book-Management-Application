import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MyBooks implements ActionListener {
    JFrame myFrame = new JFrame();
    JPanel Buttons = new JPanel();
    JPanel SortButtons = new JPanel();
    JButton Title = new JButton("Title");
    JButton Author = new JButton("Author");
    JButton Rating = new JButton("Rating");
    JButton Reviews = new JButton("Reviews");
    JButton AddButton = new JButton("Add Book");
    JButton EditButton = new JButton("Edit");
    JButton Logout = new JButton("Logout");
    JButton Remove = new JButton("Remove");
    JLabel SortBy = new JLabel();
    Person user;
    JTable DefaultTable;
    JScrollPane scp;
    boolean isAdmin= false;
    MyBooks(Person p, ArrayList<Book> books) throws NumberFormatException, IOException {
        if (p.getUsername().equals("admin") && p.getPassword().equals("admin")) {
            isAdmin=true;
        }
        myFrame.setSize(800, 500);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLayout(new GridLayout(2, 1));
        myFrame.setLocationRelativeTo(null);
        user = p;

        SortBy.setText("SortBy");
        DefaultTable = readTable(user, books);
        DefaultTable.getTableHeader().setBounds(50, 25, 200, 25);

        Title.addActionListener(this);
        Title.setPreferredSize(new Dimension(50,50));

        Author.addActionListener(this);
        Author.setPreferredSize(new Dimension(50,50));
        Rating.addActionListener(this);
        Reviews.addActionListener(this);
        AddButton.addActionListener(this);
        Logout.addActionListener(this);
        Remove.addActionListener(this);
        
        EditButton.addActionListener(this);

        SortButtons.setLayout(new GridLayout(5,1,0,15));
        Buttons.setLayout(new GridLayout(1,6,20,0));

        SortButtons.add(SortBy);
        SortButtons.add(Title);
        SortButtons.add(Author);
        SortButtons.add(Rating);
        SortButtons.add(Reviews);

        Buttons.add(SortButtons);
        Buttons.add(AddButton);
        Buttons.add(EditButton);
        Buttons.add(Logout);
        Buttons.add(Remove);

        scp = new JScrollPane(DefaultTable);
        myFrame.add(scp);
        myFrame.add(Buttons);

        myFrame.setVisible(true);
    }

    public JTable readTable(Person p, ArrayList<Book> SortedBooks) throws NumberFormatException, IOException {

        String[] columns = { "Id", "TITLES", "AUTHORS", "Rating", "Reviews", "Status", "TimeSpent", "StartDate",
                "EndDate" };
        Object[] arr = SortedBooks.toArray();
        Object[][] arrr = new Object[arr.length][9];
        for (int i = 0; i < arr.length; i++) {
            arrr[i][0] = SortedBooks.get(i).getBookId();
            arrr[i][1] = SortedBooks.get(i).getTitle();
            arrr[i][2] = SortedBooks.get(i).getAuthor();
            arrr[i][3] = SortedBooks.get(i).getRating();
            arrr[i][4] = SortedBooks.get(i).getReviews();
            arrr[i][5] = SortedBooks.get(i).getStatus();
            arrr[i][6] = SortedBooks.get(i).getTimeSpent();
            arrr[i][7] = SortedBooks.get(i).getStartDate();
            arrr[i][8] = SortedBooks.get(i).getEndDate();
        }
        return new JTable(arrr, columns);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String csvPath = String.format("Data/%s.csv", user.getUsername());
            ArrayList<Book> booksSorted = FileMethods.ReadBookFull(csvPath);
            if (e.getSource() == Title) {
                Comparator<Book> Title = (a, b) -> a.getTitle().compareTo(b.getTitle());
                Collections.sort(booksSorted, Title);
                DefaultTable = readTable(user, booksSorted);
                myFrame.dispose();
                new MyBooks(user, booksSorted);
            }

            if (e.getSource() == Author) {
                Comparator<Book> Title = (a, b) -> a.getAuthor().compareTo(b.getAuthor());
                Collections.sort(booksSorted, Title);
                DefaultTable = readTable(user, booksSorted);
                myFrame.dispose();
                new MyBooks(user, booksSorted);
            }

            if (e.getSource() == Rating) {
                Comparator<Book> Title = (a, b) -> a.getRating().compareTo(b.getRating());
                Collections.sort(booksSorted, Title);
                DefaultTable = readTable(user, booksSorted);
                myFrame.dispose();
                new MyBooks(user, booksSorted);
            }

            if (e.getSource() == Reviews) {
                Comparator<Book> Title = (a, b) -> a.getReviews().compareTo(b.getReviews());
                Collections.sort(booksSorted, Title);
                DefaultTable = readTable(user, booksSorted);
                myFrame.dispose();
                new MyBooks(user, booksSorted);
            }
            if (e.getSource() == AddButton) {
                myFrame.dispose();
                new Page(user);
            }
            if (e.getSource() == EditButton) {
                // Get the selected row
                int selectedRow = DefaultTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(myFrame, "Please select a row to edit.");
                    return;
                }

                // Retrieve the book details from the selected row
                int bookId = (int) DefaultTable.getValueAt(selectedRow, 0);
                String title = (String) DefaultTable.getValueAt(selectedRow, 1);
                String author = (String) DefaultTable.getValueAt(selectedRow, 2);
                Book NewBook = new Book((Integer)bookId, title, author,(Double)DefaultTable.getValueAt(selectedRow, 3), (String)DefaultTable.getValueAt(selectedRow, 4), (String)DefaultTable.getValueAt(selectedRow, 5), (Integer)DefaultTable.getValueAt(selectedRow, 6), (String)DefaultTable.getValueAt(selectedRow, 7), (String)DefaultTable.getValueAt(selectedRow, 8));
                // Create an edit dialog passing the book details
                new EditBookDialog(user, bookId, title, author, DefaultTable,NewBook,isAdmin);
            }
                
            if (e.getSource() == Logout) {
                myFrame.dispose();
                new Login();
            }
            if(e.getSource()== Remove){
                    int selectedRow = DefaultTable.getSelectedRow();
                    if (selectedRow == -1) {
                        JOptionPane.showMessageDialog(myFrame, "Please select a row to edit.");
                        return;
                    }
                    int bookId = (int) DefaultTable.getValueAt(selectedRow, 0);

                    FileMethods.removeBook(csvPath, bookId,booksSorted);
                    JOptionPane.showMessageDialog(myFrame, "The book with "+bookId+" id was removed");
                    myFrame.dispose();
                    new MyBooks(user,FileMethods.ReadBookFull(csvPath));    
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
