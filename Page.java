// import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Page implements ActionListener  {
    JFrame myFrame = new JFrame();

    JLabel Admin = new JLabel();
    JPanel AdminPanel = new JPanel();
    boolean isAdmin = false;

    JButton AdminAdd = new JButton("AdminAdd");
    JButton AdminRemove = new JButton("AdminRemove");
    JButton AdminEdit = new JButton("AdminEdit");
    
    JTextField add_Title = new JTextField(10);
    JTextField add_Author = new JTextField(10);
    JTextField add_Rating = new JTextField(10);
    JTextField add_Reviews = new JTextField(10);
    JTextField add_Status = new JTextField(10);
    JTextField add_TimeSpent = new JTextField(10);
    JTextField add_StartDate = new JTextField(10);
    JTextField add_EndDate = new JTextField(10);

    JPanel saul = new JPanel();
    JTable table;

    JPanel Title = new JPanel();
    JPanel Author = new JPanel();
    JPanel Rating = new JPanel();
    JPanel Reviews = new JPanel();
    JPanel Status = new JPanel();
    JPanel TimeSpent = new JPanel();
    JPanel StartDate = new JPanel();
    JPanel EndDate = new JPanel();

    JLabel book_Title = new JLabel();
    JLabel book_Author = new JLabel();
    JLabel book_Rating = new JLabel();
    JLabel book_Reviews = new JLabel();
    JLabel book_Status = new JLabel();
    JLabel book_TimeSpent = new JLabel();
    JLabel book_StartDate= new JLabel();
    JLabel book_EndDate = new JLabel();

    Person user;
    Book book = new Book();

    JPanel buttons = new JPanel();
    String path;


    JButton button1 =new JButton("ADD");
    JButton button2 =new JButton("MyBooks");
    ArrayList<Book> books;
    Page(Person p) throws NumberFormatException, IOException{
        
        books = FileMethods.ReadBook("Books.csv");
        path = String.format("Data/%s.csv", p.getUsername());
        Object [] arr= books.toArray();
        Object [][] arrr = new Object[arr.length][3];

        for(int i=0;i<arr.length;i++){
            arrr[i][0]=books.get(i).getTitle();
            arrr[i][1]=books.get(i).getAuthor();
            
            Double sum=0.0;
            Integer count=0;
            for(Person u:FileMethods.ReadUser("Users.csv")){
                String path = "Data/"+u.getUsername()+".csv";
                ArrayList<Book> UserBooks =FileMethods.ReadBookFull(path);
                
                for(Book bb:UserBooks){
                    if(bb.getTitle().equals(books.get(i).getTitle())){
                        sum=sum+bb.getRating();
                        count++;
                        break;
                    }
                }
            }
            arrr[i][2]=(sum/count)+"("+count.toString()+")";
            sum=0.0;
            count=0;
        }

        Admin.setText("You are Logged in as an admin");

        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if(p.getUsername().equals("admin") && p.getPassword().equals("admin")){
            myFrame.setLayout(new GridLayout(5,1));
            myFrame.setSize(500,500);
            myFrame.add(Admin);
            AdminPanel.setLayout(new GridLayout(1,3));
            AdminPanel.add(AdminAdd);
            AdminPanel.add(AdminRemove);
            AdminPanel.add(AdminEdit);
            isAdmin = true;
        }
        else{
            myFrame.setLayout(new GridLayout(3,1));
            myFrame.setSize(400,400);

        }

        myFrame.setLocationRelativeTo(null);

        String[] columnNames = {"Title","Author","Ratings"};
        table = new JTable(arrr,columnNames);
        table.getTableHeader().setBounds(50,25,200,25);
        //table.setBounds(50, 50, 200, 200);

        JScrollPane scp = new JScrollPane(table);
        //scp.setBounds(0,0,400,300);
        myFrame.add(scp);
        button1.addActionListener(this);
        button2.addActionListener(this);
        AdminAdd.addActionListener(this);
        AdminRemove.addActionListener(this);
        AdminEdit.addActionListener(this);

        saul.setLayout(new GridLayout(8,2));

        book_Title.setText("Title");
        book_Author.setText("Author");
        book_Rating.setText("Rating");
        book_Reviews.setText("Reviews");
        book_Status.setText("Status");
        book_TimeSpent.setText("TimeSpent");
        book_StartDate.setText("StartDate");
        book_EndDate.setText("EndDate");
        

        Title.setLayout(new GridLayout(1,2));
        Title.add(book_Title);
        Title.add(add_Title);

        Author.setLayout(new GridLayout(1,2));
        Author.add(book_Author);
        Author.add(add_Author);

        Rating.setLayout(new GridLayout(1,2));
        Rating.add(book_Rating);
        Rating.add(add_Rating);

        Reviews.setLayout(new GridLayout(1,2));
        Reviews.add(book_Reviews);
        Reviews.add(add_Reviews);

        Status.setLayout(new GridLayout(1,2));
        Status.add(book_Status);
        Status.add(add_Status);

        TimeSpent.setLayout(new GridLayout(1,2));
        TimeSpent.add(book_TimeSpent);
        TimeSpent.add(add_TimeSpent);

        StartDate.setLayout(new GridLayout(1,2));
        StartDate.add(book_StartDate);
        StartDate.add(add_StartDate);

        EndDate.setLayout(new GridLayout(1,2));
        EndDate.add(book_EndDate);
        EndDate.add(add_EndDate);

        saul.add(Title);
        saul.add(Author);
        saul.add(Rating);
        saul.add(Reviews);
        saul.add(Status);
        saul.add(TimeSpent);
        saul.add(StartDate);
        saul.add(EndDate);
        
        //add.setBounds(50, 300, 200, 20);
        buttons.setLayout(new GridLayout(1,2));
        buttons.add(button1);
        buttons.add(button2);

        myFrame.add(saul);
        myFrame.add(buttons);

        if(isAdmin)
        myFrame.add(AdminPanel);

        user = p;

        myFrame.setVisible(true);
    }


    public void addBook(Person p, Book book) throws NumberFormatException, IOException {

        boolean exist = false;
        boolean added = false;
        // writeFile(path, book.toString());
        books = FileMethods.ReadBook("Books.csv");
        String path = String.format("Data/%s.csv", p.getUsername());
        ArrayList<Book> myBooks = FileMethods.ReadBookFull(path);
        
        for (Book mb: myBooks) {
            if (add_Title.getText().equals(mb.getTitle())) {
                exist = true;
            }
        }
        if (exist == true) {
            JOptionPane.showMessageDialog(myFrame, "The Book already exists");
            return;
        }

        ArrayList<Book> books = FileMethods.ReadBook("Books.csv");
        for(Book b: books){
            if(add_Title.getText().equals(b.getTitle())){
                if(add_Author.getText().equals(b.getAuthor())){
                    try{
                    book.setTitle(add_Title.getText());
                    book.setAuthor(add_Author.getText());
                    book.setRating(Double.parseDouble(add_Rating.getText()));
                    book.setReviews(add_Reviews.getText());
                    book.setStatus(add_Status.getText());
                    book.setTimeSpent(Integer.parseInt(add_TimeSpent.getText()));
                    book.setStartDate(add_StartDate.getText());
                    book.setEndDate(add_EndDate.getText());
                    book.setBookId(Book.Id);
                    Book.Id++;
                    FileMethods.writeFile(path, book.toString());
                    JOptionPane.showMessageDialog(myFrame, "The Book was succesfully added\n"+book);
                    }
                    catch(NumberFormatException e){
                        JOptionPane.showMessageDialog(myFrame, "Enter valid number for rating");
                    }
                }
                else{
                    added = true;
                }
            }
        }

        if (added){
            JOptionPane.showMessageDialog(myFrame, "Invalid Title or Author");
            return;
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button1){
            try {
                addBook(user, book);
            } catch (NumberFormatException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if(e.getSource()==button2){
            try {
                new MyBooks(user,FileMethods.ReadBookFull(path));
                myFrame.dispose();
            } catch (NumberFormatException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if(e.getSource() == AdminRemove){
            
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(myFrame, "Please select a row to delete.");
                return;
            }
            String title = (String) table.getValueAt(selectedRow, 0);


            try {
                ArrayList<Book> GeneralBooks = FileMethods.ReadBook("Books.csv");
                FileMethods.adminRemove(selectedRow, GeneralBooks);
                myFrame.dispose();
                new Page(user);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            JOptionPane.showMessageDialog(myFrame, "The book with "+title+" title was removed from general database");
        }
        if (e.getSource()== AdminAdd) {
            new AdminAdd();
        }
        if(e.getSource() == AdminEdit){
             // Get the selected row
             int selectedRow = table.getSelectedRow();
             if (selectedRow == -1) {
                 JOptionPane.showMessageDialog(myFrame, "Please select a row to edit.");
                 return;
             }

             // Retrieve the book details from the selected row
             String title = (String) table.getValueAt(selectedRow, 0);
             String author = (String) table.getValueAt(selectedRow, 1);
            //  Book NewBook = new Book((Integer)bookId, title, author,(Double)DefaultTable.getValueAt(selectedRow, 3), (String)DefaultTable.getValueAt(selectedRow, 4), (String)DefaultTable.getValueAt(selectedRow, 5), (Integer)DefaultTable.getValueAt(selectedRow, 6), (String)DefaultTable.getValueAt(selectedRow, 7), (String)DefaultTable.getValueAt(selectedRow, 8));
            Book AdminBook = new Book(title, author);
             // Create an edit dialog passing the book details
            try {
                new EditBookDialog(user, selectedRow, title, author, table,AdminBook,isAdmin);
            } catch (Exception e1) {
            
            }
        
        }
    }
}
