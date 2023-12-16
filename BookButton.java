package GUI;

import Library.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BookButton extends JButton {

    private final Book BOOK;

    public BookButton(Book myBook, ActionListener myListener) {
        this.BOOK = myBook;
        // Mengatur jenis dan ukuran font untuk teks pada button.
        this.setFont(new Font("Arial", Font.PLAIN, 15));
        // Mengecek panjang judul buku. Jika panjang judul lebih dari atau sama dengan 20 karakter, maka judul akan dipotong dan diakhiri dengan "..." untuk menghindari tampilan button yang terlalu panjang.
        if (myBook.getTitle().length() >= 20) {
            // Mengambil dan menampilkan 17 karakter pertama dari judul buku serta menampilkan "..." diakhir penggalan judul buku.
            this.setText(myBook.getTitle().toUpperCase().substring(0, 17) + "...");
        } else {
            this.setText(myBook.getTitle().toUpperCase());
        }
        // Mengatur ukuran buttom.
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
