package GUI;

// Mengimpor kelas Book dari package Library. Ini menunjukkan bahwa kelas Book digunakan dalam kelas BookButton.
import Library.Book;

import javax.swing.*;
import java.awt.*;
// Mengimpor kelas ActionListener dari paket java.awt.event, yang akan digunakan untuk menangani peristiwa (events) yang terjadi pada button.
import java.awt.event.ActionListener;

public class BookButton extends JButton {

    // Membuat variabel instance private BOOK yang bertipe Book (kelas dari package Library). Ini akan menyimpan referensi ke objek buku yang terkait dengan tombol ini.
    private final Book BOOK;

    // Mendefinisikan konstruktor untuk kelas BookButton yang menerima dua parameter: objek myBook dari kelas Book dan objek myListener dari kelas ActionListener.
    public BookButton(Book myBook, ActionListener myListener) {
        // Menginisialisasi variabel instance BOOK dengan objek buku yang diterima sebagai parameter.
        this.BOOK = myBook;
        // Mengatur font untuk teks pada tombol.
        this.setFont(new Font("Arial", Font.PLAIN, 15));
        // Kode berikutnya mengecek panjang judul buku. Jika panjang judul lebih dari atau sama dengan 20 karakter, maka judul akan dipotong dan diakhiri dengan "..." untuk menghindari tampilan tombol yang terlalu panjang.
        // Selain itu, judul akan ditampilkan secara uppercase (huruf besar).
        if (myBook.getTitle().length() >= 20) {
            // Mengambil dan menampilkan 17 karakter pertama dari judul buku serta menampilkan "..." diakhir penggalan judul buku.
            this.setText(myBook.getTitle().toUpperCase().substring(0, 17) + "...");
        } else {
            this.setText(myBook.getTitle().toUpperCase());
        }
        // Mengatur ukuran preferensi tombol. Ini adalah lebar dan tinggi tombol.
        this.setPreferredSize(new Dimension(230, 40));
        // Mengatur ukuran maksimum tombol.
        this.setMaximumSize(new Dimension(230, 40));
        // Mengatur poros horizontal (X-axis) tempat tombol akan diatur di dalam kontainer.
        this.setAlignmentX(CENTER_ALIGNMENT);
        // Mengatur aksi ketika tombol ditekan.
        this.addActionListener(myListener);
        // Mengatur tooltip untuk tombol, yang akan muncul saat mouse mengarah ke tombol. Tooltip ini berisi judul buku dalam huruf besar.
        this.setToolTipText(myBook.getTitle().toUpperCase());
    }

    // Mengembalikan objek buku terkait dengan tombol ini.
    public Book getButtonBook() {
        return BOOK;
    }
}