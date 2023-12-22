package org.grocery.view;

import org.grocery.controller.ItemDao;
import org.grocery.controller.ItemDaoImpl;
import org.grocery.model.Item;
import org.grocery.util.ImageRenderer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class SearchProductView extends JDialog {

    private final JTextField filterTextField;
    private final JButton backButton;
    private final JTable table;

    public SearchProductView() {
        setTitle("Search Products");
        //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Filter for Product Name
        filterTextField = new JTextField(20);
        add(filterTextField, BorderLayout.NORTH);

        // Table to display product information
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Back button
        backButton = new JButton("Back");
        add(backButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // You'll need to populate the table with data from your database here
        // Using a DefaultTableModel to represent the table data structure
        ItemDao itemDao = new ItemDaoImpl();
        try {

            String[] columns = {"ID", "ItemName", "ItemCode", "BatchNumber", "Image"};
            DefaultTableModel model = new DefaultTableModel(columns, 0);
            table.setModel(model);

            // Fetch data from the database and add it to the table
            // Sample data addition:
            // Populate the table model with the fetched data
            List<Item> items = itemDao.getItems();
            //sorting
            Stream<Item> sorted = items.stream().sorted((Comparator.comparing(Item::getBatchNumber)));
            sorted.map(item -> new Object[]{item.getItemId(), item.getItemName(), item.getItemCode(), item.getBatchNumber(), item.getImage()}).forEachOrdered(model::addRow);

            // Set a custom cell renderer for the image column (assuming it's the fifth column)
            table.getColumnModel().getColumn(4).setCellRenderer(new ImageRenderer());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        filterTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filter();
            }
        });


        backButton.addActionListener(e -> dispose());

    }


    private void filter() {
        // Get the text from the filterTextField
        String text = filterTextField.getText().trim().toLowerCase();

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) table.getModel());
        table.setRowSorter(sorter);

        // Set the row filter based on the text entered in the filterTextField for product name
        // Filter based on the second column (Product Name)
        sorter.setRowFilter(text.trim().isEmpty() ? null : RowFilter.regexFilter("(?i)" + text, 1));
    }


}
