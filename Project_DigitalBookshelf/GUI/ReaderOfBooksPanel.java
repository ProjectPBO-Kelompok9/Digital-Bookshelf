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
        // Menginisialisasi panel ReaderOfBooksPanel dengan ukuran 900x650 pixel dan menetapkan tata letak BorderLayout.
        this.setPreferredSize(new Dimension(900, 650));
        this.setLayout(new BorderLayout());

        // Constructor ini membuat dan menambahkan panel LibraryPanel, panel ReaderPanel, dan panel BookInfoPanel ke dalam panel ReaderOfBooksPanel (panel utama).
        // Posisi panel di sebelah kiri
        LibraryPanel libraryPanel = new LibraryPanel(new BookButtonListener());
        this.add(libraryPanel, BorderLayout.WEST);

        // Posisi panel di tengah
        READERPANEL = new ReaderPanel();
        this.add(READERPANEL, BorderLayout.CENTER);

        // Posisi panel di sebelah kanan
        INFOPANEL = new BookInfoPanel();
        this.add(INFOPANEL, BorderLayout.EAST);
    }

    // Implementasi dari interface ActionListener yang digunakan untuk menangani peristiwa ketika tombol buku di panel LibraryPanel (daftar buku) diklik.
    private class BookButtonListener implements ActionListener {

        // Method ini dipanggil ketika tombol buku di LibraryPanel diklik. Ini mengambil buku yang sesuai dengan tombol yang diklik, mengatur atribut panel pembaca berdasarkan buku tersebut, dan memeriksa apakah buku telah diproses sebelumnya. Jika belum, maka proses pemrosesan buku dilakukan dalam thread terpisah menggunakan BookProcessor.
        @Override
        public void actionPerformed(ActionEvent e) {
            Book book = ((BookButton) e.getSource()).getButtonBook();
            READERPANEL.setAttributes(book);

            // Jika buku belum diproses (jumlah kata masih nol), maka objek BookProcessor dibuat, dan pemrosesan buku dilakukan dalam thread terpisah. Setelah pemrosesan selesai, atribut panel informasi buku diatur.
            if (book.getWordCount() <= 0) {
                BookProcessor processor = new BookProcessor();
                new Thread(() -> {
                    processor.getBookData(book);
                    INFOPANEL.setAttributes(book);
                }).start();
            // Jika buku telah diproses sebelumnya (jumlah kata tidak nol), maka atribut panel informasi buku diatur tanpa melakukan pemrosesan ulang.
            } else {
                INFOPANEL.setAttributes(book);
            }
        }
    }
}