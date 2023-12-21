package org.grocery.controller;

import org.grocery.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemDaoImpl implements ItemDao{

    static Connection connection = DBConnection.getConnection();
    @Override
    public int add(Item item) throws SQLException {
        final String query = "insert into item(ItemName,ItemCode,BatchNumber) VALUES (?,?,?)";
        final PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, item.getItemName());
        ps.setString(2,item.getItemCode());
        ps.setString(3,item.getBatchNumber());
        return ps.executeUpdate();
    }
}
