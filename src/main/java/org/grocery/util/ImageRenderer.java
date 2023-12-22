package org.grocery.util;

import com.mysql.cj.jdbc.Blob;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.sql.SQLException;

public class ImageRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        if (value instanceof Blob) {
            Blob blob = (Blob) value;
            try {
                int blobLength = (int) blob.length();
                byte[] blobAsBytes = blob.getBytes(1, blobLength);
                ImageIcon imageIcon = new ImageIcon(blobAsBytes);
                Image image = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(image));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return label;
    }
}
