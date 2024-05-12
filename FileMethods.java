import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileMethods {
    public static void writeFile(String path, String text) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, true))) {
            writer.println(text);
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    public static void removeBook(String path,int bookId,ArrayList<Book> books) throws NumberFormatException, IOException{
        PrintWriter writer = new PrintWriter(new FileWriter(path, false));
        writer.println("Id,Title,Author,Rating,Reviews,Status,TimeSpent,StartDate,EndDate");
        for(Book bb:books){
            if(bb.getBookId()!=bookId){
                writer.println(bb.toString());
            }
        }
        writer.close();
    }

    public static void adminEdit(int ID,ArrayList<Book> books,String newTitle,String newAuthor) throws NumberFormatException, IOException{
        PrintWriter writer = new PrintWriter(new FileWriter("Books.csv", false));
        writer.println("Title,Author");
        for(Book bb:books){
            if(bb.getBookId()-1 != ID){
                writer.println(bb.toStringGeneral());
            }
            else{
                writer.println(newTitle+","+newAuthor);
            }
        }
        writer.close();
    }    

    
    public static void adminRemove(int ID,ArrayList<Book> books) throws NumberFormatException, IOException{
        PrintWriter writer = new PrintWriter(new FileWriter("Books.csv", false));
        writer.println("Title,Author");
        for(Book bb:books){
            if(bb.getBookId()-1 != ID){
                writer.println(bb.toStringGeneral());
            }
        }
        writer.close();
    }

    // public static void deleteLine(String filePath, int lineNumber) {
    //     try {
    //         // Create a temporary file
    //         File tempFile = new File(filePath + ".tmp");
    //         BufferedReader reader = new BufferedReader(new FileReader(filePath));
    //         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

    //         String line;
    //         int currentLine = 1;

    //         // Read lines from the original file and write to the temporary file,
    //         // skipping the line to be deleted
    //         while ((line = reader.readLine()) != null) {
    //             if (currentLine != lineNumber) {
    //                 writer.write(line);
    //                 writer.newLine();
    //             }
    //             currentLine++;
    //         }

    //         writer.close();
    //         reader.close();

    //         // Replace the original file with the temporary file
    //         File originalFile = new File(filePath);
    //         if (!originalFile.delete()) {
    //             System.out.println("Could not delete the original file.");
    //             return;
    //         }
    //         if (!tempFile.renameTo(originalFile)) {
    //             System.out.println("Could not rename the temporary file.");
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }



    public static void editLine(String filePath, int lineNumber,Person user,Book NewBook) {
        try {
            // Create a temporary file
            File tempFile = new File(filePath + ".tmp");
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String NewText = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s",NewBook.getBookId(),NewBook.getTitle(),NewBook.getAuthor(),NewBook.getRating(),NewBook.getReviews(),NewBook.getStatus(),NewBook.getTimeSpent(),NewBook.getStartDate(),NewBook.getEndDate());
            String line;
            int currentLine = 1;
            lineNumber+=1;
            // Read lines from the original file and write to the temporary file,
            // skipping the line to be deleted
            while ((line = reader.readLine()) != null) {
                if (currentLine != lineNumber) {
                    writer.write(line);
                    writer.newLine();
                }
                if(currentLine == lineNumber){
                    writer.write(NewText);
                    writer.newLine();
                }
                currentLine++;
            }

            writer.close();
            reader.close();

            // Replace the original file with the temporary file
            File originalFile = new File(filePath);
            if (!originalFile.delete()) {
                System.out.println("Could not delete the original file.");
                return;
            }
            if (!tempFile.renameTo(originalFile)) {
                System.out.println("Could not rename the temporary file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public static ArrayList<Person> ReadUser(String FileName) throws NumberFormatException, IOException{
        ArrayList<Person> persons  = new ArrayList<>();
        
            String line;
            BufferedReader bfr = new BufferedReader(new FileReader(FileName));
            bfr.readLine();
            while((line=bfr.readLine())!=null){
                
                String [] arr = line.split(",");
                Person ps = new Person(arr[0],arr[1]);
                persons.add(ps);
            }
            bfr.close();
        return persons;
    }

    public static ArrayList<Book> ReadBookFull(String FileName) throws NumberFormatException, IOException{
        ArrayList<Book> books  = new ArrayList<>();
            String line;
            BufferedReader bfr = new BufferedReader(new FileReader(FileName));
            bfr.readLine();
            while((line=bfr.readLine())!=null){
                String [] arr = line.split(",");
                Book ps = new Book(Integer.parseInt(arr[0]),arr[1],arr[2],Double.parseDouble(arr[3]),arr[4],arr[5],Integer.parseInt(arr[6]),arr[7],arr[8]);
                // Book ps = new Book(arr[0],arr[1]);
                books.add(ps);
            }
            bfr.close();
        return books;
    }

    public static ArrayList<Book> ReadBook(String FileName) throws NumberFormatException, IOException{
        ArrayList<Book> books  = new ArrayList<>();
            String line;
            BufferedReader bfr = new BufferedReader(new FileReader(FileName));
            bfr.readLine();
            int[] quotes = new int[2];
            int quots=0;
            String[] main = new String[2];
            boolean contains = false;
            while((line=bfr.readLine())!=null){
                
                char[] chars = line.toCharArray();
                for(int i=0;i<chars.length;i++){
                    if(chars[i]=='"'){
                        contains=true;
                        quotes[quots]=i;
                        quots++;
                    }
                }
                quots=0;
                try {
                    if(contains){
                        main[0] = line.substring(quotes[0], quotes[1]+1);
                        main[1] = line.substring(quotes[1]+2, chars.length);
                    }
                    else{
                        main=line.split(",");
                    }    
                } catch (IndexOutOfBoundsException e) {
                    main[0] = line.substring(0,quotes[0]-1);
                    main[1] = line.substring(quotes[0],quotes[1]+1);
                }
                
                contains=false;
                // Book ps = new Book(arr[0],arr[1],Double.parseDouble(arr[2]),arr[3]);
                Book ps = new Book(main[0],main[1]);
                ps.setBookId(Book.AdminId);
                Book.AdminId++;
                books.add(ps);
            }
            bfr.close();
            Book.AdminId=1;
        return books;
    }
}
