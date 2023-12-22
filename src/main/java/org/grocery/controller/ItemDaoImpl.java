package org.grocery.controller;

import org.grocery.model.Item;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        ps.setBlob(4,item.getImage());
        return ps.executeUpdate();
    }

    @Override
    public List<Item> getItems() throws SQLException {
        final String query = "Select * from Item";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet resultSet = ps.executeQuery();
        List<Item> ls = new ArrayList<>();
        while (resultSet.next()) {
            Item item = new Item();
            item.setItemId(resultSet.getInt("id"));
            item.setItemName(resultSet.getString("itemName"));
            item.setItemCode(resultSet.getString("itemCode"));
            item.setBatchNumber(resultSet.getString("batchNumber"));
            item.setImage(resultSet.getBlob("image"));
            ls.add(item);
        }
        return ls;
    }


}
