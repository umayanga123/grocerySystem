package org.grocery.controller;

import org.grocery.model.Item;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemDaoImpl implements ItemDao{

    static Connection connection = DBConnection.getConnection();
    @Override
    public int add(Item item) throws SQLException, FileNotFoundException {
        final String query = "insert into item(ItemName,ItemCode,BatchNumber,Image) VALUES (?,?,?,?)";
        final PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, item.getItemName());
        ps.setString(2,item.getItemCode());
        ps.setString(3,item.getBatchNumber());
        InputStream in = new FileInputStream(item.getImagePath());
        ps.setBlob(4,in);
        return ps.executeUpdate();
    }
}
