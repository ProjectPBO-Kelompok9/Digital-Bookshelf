package Library;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Library extends aLibrary {

    private final ArrayList<Book> BOOKS;

    public Library() {
        BOOKS = new ArrayList<>();
    }

    public ArrayList<Book> getBooks() {
        return (ArrayList<Book>) BOOKS.clone();
    }

    public String toString() {
        StringBuilder allMyBooks = new StringBuilder();
        for (Book myBooks : BOOKS) {
            allMyBooks.append("Index ").append(BOOKS.indexOf(myBooks)).append(myBooks.toString()).append(myBooks.getFilename()).append("\n");
        }
        return allMyBooks.toString();
    }

    public void loadLibraryFromCSV(String csvFilename) {
        BOOKS.clear();

        try {
            Scanner fileScan = new Scanner(new File("resources/etext/" + csvFilename));

            while (fileScan.hasNextLine()) {

                Scanner lineScan = new Scanner(fileScan.nextLine());
                lineScan.useDelimiter(",");
                //creating book object using csv data
                Book csvBook = new Book(lineScan.next(), lineScan.next(), lineScan.next(), lineScan.next(), lineScan.next());

                BOOKS.add(csvBook);

                lineScan.close();
            }
            fileScan.close();

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Your file could not be found");
        }
    }

}
