package GUI;

import Library.Book;
import Library.Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class LibraryPanel extends JPanel {

    // class instances
    private final Library MYLIBRARY;
    private final JPanel BOOKBUTTONPANEL;
    private final ActionListener BOOKBUTTONLISTENER;

    public LibraryPanel(ActionListener myListener) {
        this.BOOKBUTTONLISTENER = myListener;

        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder("Library"));

        MYLIBRARY = new Library();

        BOOKBUTTONPANEL = new JPanel();
        BOOKBUTTONPANEL.setLayout(new BoxLayout(BOOKBUTTONPANEL, BoxLayout.Y_AXIS));
        MYLIBRARY.loadLibraryFromCSV("booklist-full.csv");
            for (Book thisBook : MYLIBRARY.getBooks()) {
                BookButton bookButton = new BookButton(thisBook, BOOKBUTTONLISTENER);
                BOOKBUTTONPANEL.add(bookButton);
                revalidate();
                repaint();
            }

        JScrollPane bookListScrollPane = new JScrollPane(BOOKBUTTONPANEL);
        bookListScrollPane.setBorder(BorderFactory.createTitledBorder("Book List"));
        bookListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        bookListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

       this.add(bookListScrollPane, BorderLayout.CENTER);

    }

}
