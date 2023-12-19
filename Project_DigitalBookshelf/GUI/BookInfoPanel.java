package GUI;

import Library.Book;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;

public class BookInfoPanel extends JPanel implements iGUI {
    private final JPanel BOOKDATAPANEL;
    private final JLabel LINECOUNTFIELD;
    private final JLabel WORDCOUNTFIELD;

    public BookInfoPanel() {

        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder("Book Data"));

        // Membuat panel statPanel untuk menampilkan informasi tentang panjang buku (total baris dan total kata) dengan menggunakan BorderLayout.
        JPanel statPanel = new JPanel();
        // Membuat dan mengatur label untuk menampilkan total baris dan total kata.
        statPanel.setBorder(BorderFactory.createTitledBorder("Length Info"));

        LINECOUNTFIELD = new JLabel("Total Lines: ");
        statPanel.add(LINECOUNTFIELD, BorderLayout.NORTH);

        WORDCOUNTFIELD = new JLabel("Total Words: ");
        statPanel.add(WORDCOUNTFIELD, BorderLayout.SOUTH);

        // Membuat panel BOOKDATAPANEL yang akan menampung seratus kata teratas beserta jumlah kemunculannya.
        BOOKDATAPANEL = new JPanel();
        BOOKDATAPANEL.setLayout(new BoxLayout(BOOKDATAPANEL, BoxLayout.Y_AXIS));

        // Membuat JScrollPane (bookDataScrollPane) untuk memungkinkan panel BOOKDATAPANEL dapat di-scroll jika isinya tidak cukup untuk muat di layar.
        JScrollPane bookDataScrollPane = new JScrollPane(BOOKDATAPANEL);
        bookDataScrollPane.setBorder(BorderFactory.createTitledBorder("Top 100 words"));
        bookDataScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        bookDataScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        bookDataScrollPane.getVerticalScrollBar().setBlockIncrement(100);

        this.add(statPanel, BorderLayout.NORTH);
        this.add(bookDataScrollPane, BorderLayout.CENTER);

    }

    // Metode untuk mengatur label LINECOUNTFIELD dengan informasi total baris buku.
    public void setLINECOUNTFIELD(Book book) {
        NumberFormat numForm = NumberFormat.getInstance(Locale.US);
        this.LINECOUNTFIELD.setText("Total Lines: " + numForm.format(book.getLineCount()));
    }

    // Metode untuk mengatur label WORDCOUNTFIELD dengan informasi total kata buku.
    public void setWORDCOUNTFIELD(Book book) {
        NumberFormat numForm = NumberFormat.getInstance(Locale.US);
        this.WORDCOUNTFIELD.setText("Total Words: " + numForm.format(book.getWordCount()));
    }

    // Metode untuk mengatur panel BOOKDATAPANEL dengan menambahkan tombol kata (WordButton) yang merepresentasikan seratus kata teratas beserta jumlah kemunculannya.
    public void setWordArea(Book book) {
        BOOKDATAPANEL.removeAll();
        for (String s : book.getTopWordList(100)) {
            WordButton button = new WordButton(null, s + ":   " + book.getBookData().get(s));
            BOOKDATAPANEL.add(button);
            revalidate();
            repaint();
        }
    }

    // Metode ini digunakan untuk mengatur semua atribut pada panel (total baris, total kata, dan seratus kata teratas). Memanggil metode setLINECOUNTFIELD, setWORDCOUNTFIELD, dan setWordArea untuk melakukan hal ini.
    public void setAttributes(Book book) {
        this.setLINECOUNTFIELD(book);
        this.setWORDCOUNTFIELD(book);
        this.setWordArea(book);
    }
}