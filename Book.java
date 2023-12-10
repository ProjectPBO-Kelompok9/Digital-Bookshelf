package Library;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Book {
    private final String TITLE;
    private final String AUTHOR;
    private final String GENRE;
    private final String RELEASEDATE;
    private final String FILENAME;
    private final HashMap<String, Integer> bookData;
    private int wordCount;
    private int lineCount;
    private BufferedReader fileIn;

    public Book(String title, String author, String genre, String releaseDate, String fileName) {
        this.TITLE = title;
        this.AUTHOR = author;
        this.GENRE = genre;
        this.RELEASEDATE = releaseDate;
        this.FILENAME = fileName;
        this.fileIn = null;
        this.wordCount = 0;
        this.lineCount = 0;
        this.bookData = new HashMap<>();
    }

    public String getTitle() {
        return TITLE;
    }

    public String getAuthor() {
        return AUTHOR;
    }

    public String getGenre() {
        return GENRE;
    }

    public String getFilename() {
        return FILENAME;
    }

    public String getReleaseDate() {
        return RELEASEDATE;
    }

    public HashMap<String, Integer> getBookData() {
        return this.bookData;
    }

    public int getWordCount() {
        return wordCount;
    }

    public int getLineCount() {
        return this.lineCount;
    }

    public Set<String> getWords() {
        return bookData.keySet();
    }

    public void incrementWordCount() {
        wordCount++;
    }

    public void incrementLineCount() {
        lineCount++;
    }

   public List<String> getTopWordList(int k) {
        int max = Integer.MIN_VALUE;
        for (Integer i : bookData.values()) {
            if (i > max) {
                max = i;
            }
        }

        List<String>[] bucket = new List[max + 1];
        for (String key : bookData.keySet()) {
            int frequency = bookData.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }
        List<String> retSet = new ArrayList<>();
        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] != null) {
                Collections.sort(bucket[i]);
                for (int j = 0; j < bucket[i].size() && retSet.size() < k; j++) {
                    retSet.add(bucket[i].get(j));
                }
            }
        }
        return retSet;
    }

   public synchronized void addBookData(String s) {
        bookData.put(s, bookData.getOrDefault(s, 0) + 1);
        incrementWordCount();
    }

   public String toString() {
        return (" Title: " + TITLE + " Author: " + AUTHOR + " Genre: " + GENRE + " FileName: " + FILENAME);
   }

   public boolean isValid() {
        if (TITLE != null && AUTHOR != null && GENRE != null && FILENAME != null) {
            File file = new File("resources/" + FILENAME);
            return file.exists() && file.isFile();
        }
        return false;
   }

    public BufferedReader getReader() {
        if (!isValid()) {
            return null;
        } else {
            try {
                fileIn = new BufferedReader(new FileReader("resources/" + FILENAME));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "An error occurred with the reader");
            }
            return fileIn;
        }
    }
}