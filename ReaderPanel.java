package GUI;

import Library.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.IOException;

public class ReaderPanel extends JPanel {
    private final JScrollPane CONTENTSCROLLPANE;
    private final JLabel TITLELABEL;
    private final JLabel BYLABEL;
    private final JLabel PAGELABEL;
    private final JTextArea CONTENTAREA;


    public ReaderPanel() {

        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder("Reader"));


        JPanel informationPanel = new JPanel();
        informationPanel.setBorder(BorderFactory.createTitledBorder("Information"));


        TITLELABEL = new JLabel("Title:");
        TITLELABEL.setPreferredSize(new Dimension(300, 20));
        informationPanel.add(TITLELABEL);

        BYLABEL = new JLabel("By:");
        BYLABEL.setPreferredSize(new Dimension(150, 20));
        informationPanel.add(BYLABEL);

        PAGELABEL = new JLabel("Page:");
        PAGELABEL.setPreferredSize(new Dimension(100, 20));
        informationPanel.add(PAGELABEL);
        this.add(informationPanel, BorderLayout.NORTH);

        CONTENTAREA = new JTextArea();
        CONTENTAREA.setMargin(new Insets(10, 80, 10, 10));

        CONTENTSCROLLPANE = new JScrollPane(CONTENTAREA);
        CONTENTSCROLLPANE.getVerticalScrollBar().addAdjustmentListener(new ContentAdjustmentListener());
        CONTENTSCROLLPANE.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        CONTENTSCROLLPANE.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        CONTENTSCROLLPANE.setBorder(BorderFactory.createTitledBorder("Content"));

        this.add(CONTENTSCROLLPANE, BorderLayout.CENTER);

    }

    public JScrollPane getContentScrollPane() {
        return CONTENTSCROLLPANE;
    }

    public void setContentArea(Book book) {
        if (book.getReader() == null) {
            JOptionPane.showMessageDialog(null, "Your file could not be found");
        } else {
            try {
                this.CONTENTAREA.read(book.getReader(), null);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "An error occurred with the reader");
            }
        }
    }

    public void setTitleLabel(Book book) {
        this.TITLELABEL.setText("Title: " + book.getTitle());
    }

    public void setByLabel(Book book) {
        this.BYLABEL.setText("By: " + book.getAuthor());
    }

    public void setAttributes(Book book) {
        this.setByLabel(book);
        this.setContentArea(book);
        this.setTitleLabel(book);
        this.getContentScrollPane().getVerticalScrollBar().setValue(0);
    }

    private class ContentAdjustmentListener implements AdjustmentListener {

        @Override
        public void adjustmentValueChanged(AdjustmentEvent e) {
            int maxInt = CONTENTSCROLLPANE.getVerticalScrollBar().getMaximum();
            int blockIncrement = CONTENTSCROLLPANE.getVerticalScrollBar().getBlockIncrement(1);
            int currentPageValue = CONTENTSCROLLPANE.getVerticalScrollBar().getValue();
            int currentPage = ((currentPageValue + blockIncrement) / blockIncrement);
            int totalPages = (maxInt / blockIncrement);

            PAGELABEL.setText("Page: " + currentPage + "/" + totalPages);
        }
    }
}
