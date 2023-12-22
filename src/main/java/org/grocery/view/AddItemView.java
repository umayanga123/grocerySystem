package org.grocery.view;

import org.grocery.controller.ItemDao;
import org.grocery.controller.ItemDaoImpl;
import org.grocery.model.Item;
import org.grocery.util.ImageFileFilter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class AddItemView extends JFrame {
    private JTextField productNameField;
    private JTextField productCodeField;

    private JTextField batchNumberField;
    private JButton browseImageButton;
    private JButton removeImageButton;
    private JButton saveButton;

    private JButton updateButton;

    private JButton deleteButton;

    private JButton searchButton;
    private JButton cancelButton;
    private JLabel imageViewer;
    private ImageIcon defaultImage;

    public AddItemView() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Add Item");
        setSize(700, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("src/main/resources/apple.png").getImage());

        // Create panels for organizing components
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);

        // Product Name
        JLabel productNameLabel = new JLabel("Product Name:");
        productNameField = new JTextField();
        productNameField.setPreferredSize(new Dimension(200, 25));

        // Product Code
        JLabel productCodeLabel = new JLabel("Product Code:");
        productCodeField = new JTextField();
        productCodeField.setPreferredSize(new Dimension(200, 25));

        // Product Name
        JLabel batchNumberLabel = new JLabel("Batch Number:");
        batchNumberField = new JTextField();
        batchNumberField.setPreferredSize(new Dimension(200, 25));

        // Image Browse Button
        browseImageButton = new JButton("Browse");

        // Remove Image Button
        removeImageButton = new JButton("Remove");
        removeImageButton.setEnabled(false);

        // Save and Cancel Buttons
        saveButton = new JButton("Save Product");
        updateButton =new JButton("Update Product");
        deleteButton =new JButton("Delete Product");
        searchButton =new JButton("Search Product");
        cancelButton = new JButton("Exit");

        // Image Viewer
        imageViewer = new JLabel();
        imageViewer.setHorizontalAlignment(SwingConstants.CENTER);
        imageViewer.setVerticalAlignment(SwingConstants.CENTER);
        imageViewer.setBorder(BorderFactory.createEtchedBorder());
        defaultImage = new ImageIcon("src/main/resources/default_image.jpg"); // Change to your default image file path
        imageViewer.setIcon(defaultImage);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(imageViewer, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(productNameLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(productNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(productCodeLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(productCodeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(batchNumberLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(batchNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(browseImageButton, gbc);

        gbc.gridx = 1;
        mainPanel.add(removeImageButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(saveButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        mainPanel.add(updateButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        mainPanel.add(deleteButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        mainPanel.add(searchButton, gbc);

        gbc.gridy = 7;
        mainPanel.add(cancelButton, gbc);

        // Action listener for Browse Button
        browseImageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.addChoosableFileFilter(new ImageFileFilter());

                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
                    Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                    imageViewer.setIcon(new ImageIcon(image));
                    removeImageButton.setEnabled(true);
                }
            }
        });

        // Action listener for Remove Button
        removeImageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                imageViewer.setIcon(defaultImage);
                removeImageButton.setEnabled(false);
            }
        });

        // Action listener for Save Button
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement save logic here
                // This is where you'd save the product details entered
                // For now, let's just display a message
                final Item item=new Item();
                item.setItemName(productNameField.getText());
                item.setItemCode(productCodeField.getText());
                item.setBatchNumber(batchNumberField.getText());
                item.setImagePath(imageViewer.getIcon().toString());
                ItemDao itemDao = new ItemDaoImpl();
                try {
                    int add = itemDao.add(item);
                    JOptionPane.showMessageDialog(null, "Product Saved!","", add);
                } catch (FileNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Product Not Saved!","", JOptionPane.ERROR_MESSAGE);
                }


            }
        });

        // Action listener for Cancel Button
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the window when Cancel is clicked
            }
        });

        add(mainPanel);
        // Centering the frame on the screen
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
