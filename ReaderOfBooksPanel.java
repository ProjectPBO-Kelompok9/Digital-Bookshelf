package GUI;

import Library.Book;
import Library.BookProcessor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ReaderOfBooksPanel extends JPanel {

    private final ReaderPanel READERPANEL;
    private final BookInfoPanel INFOPANEL;

    public ReaderOfBooksPanel() {

        this.setPreferredSize(new Dimension(900, 650));
        this.setLayout(new BorderLayout());

        // Panel daftar buku
        LibraryPanel libraryPanel = new LibraryPanel(new BookButtonListener());
        this.add(libraryPanel, BorderLayout.WEST);

        // Panel yang menampilkan isi buku yang dipilih user
        READERPANEL = new ReaderPanel();
        this.add(READERPANEL, BorderLayout.CENTER);

        // Panel yang menampilkan informasi buku yang dipilih user
        INFOPANEL = new BookInfoPanel();
        this.add(INFOPANEL, BorderLayout.EAST);
    }

    private class BookButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Book book = ((BookButton) e.getSource()).getButtonBook();
            READERPANEL.setAttributes(book);

            if (book.getWordCount() <= 0) {
                BookProcessor processor = new BookProcessor();
                new Thread(() -> {
                    processor.getBookData(book);
                    INFOPANEL.setAttributes(book);
                }).start();
            } else {
                INFOPANEL.setAttributes(book);
            }
        }
    }
}
