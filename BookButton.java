package GUI;

import Library.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BookButton extends JButton {
    private final Book BOOK;
    public BookButton(Book myBook, ActionListener myListener) {
        this.BOOK = myBook;
        this.setFont(new Font("Arial", Font.PLAIN, 15));

        if (myBook.getTitle().length() >= 20) {
            this.setText(myBook.getTitle().toUpperCase().substring(0, 17) + "...");
        } else {
            this.setText(myBook.getTitle().toUpperCase());
        }
        this.setPreferredSize(new Dimension(230, 40));
        this.setMaximumSize(new Dimension(230, 40));
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.addActionListener(myListener);
        this.setToolTipText(myBook.getTitle().toUpperCase());
    }

    public Book getButtonBook() {
        return BOOK;
    }
}