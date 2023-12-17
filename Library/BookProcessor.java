package Library;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class BookProcessor {
    private HashSet<String> invalidSet;
    private long executionTime;

    public BookProcessor() {
        invalidSet = null;
        executionTime = 0;
    }

    //getters

    public long getExecutionTime() {
        return executionTime;
    }

    public void getBookData(Book book) {
        createInvalidSet("invalid1.csv");
        try {
            Scanner scan = new Scanner(new File("resources/" + book.getFilename()));
            //Starting execution timer
            executionTime = System.nanoTime();
            while (scan.hasNextLine()) {
                //not case-sensitive
                String line = scan.nextLine().trim().toLowerCase();

                if (line.length() != 0) {
                    cleanAndCount(line, book);
                    book.incrementLineCount();
                }
            }
            scan.close();
            //execution time in milliseconds
            executionTime = (System.nanoTime() - executionTime) / 1000000;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public HashSet<String> createInvalidSet(String fileName) {
        HashSet<String> set = new HashSet<>();
        try {
            Scanner scan = new Scanner(new File("resources/InvalidLists/" + fileName));
            while (scan.hasNextLine()) {
                Scanner lineScan = new Scanner(scan.nextLine());
                lineScan.useDelimiter(",");
                while (lineScan.hasNext()) {
                    set.add(lineScan.next().toLowerCase());
                }
                lineScan.close();
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        invalidSet = set;
        return set;
    }

    public void cleanAndCount(String str, Book book) {
        str = str.replaceAll("[^a-zA-Z\\s]", "");
        countWords(str.replaceAll(" +", " "), book);
    }

    public void countWords(String str, Book book) {
        for (String s : str.split(" ")) {
            if (s.equals("")) {
                continue;
            }
            if (invalidSet == null || !invalidSet.contains(s)) {
                book.addBookData(s);
            }
        }
    }

}
