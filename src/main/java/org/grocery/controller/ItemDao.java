package org.grocery.controller;

import org.grocery.model.Item;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public interface ItemDao {

    int add(Item item) throws SQLException, FileNotFoundException;

    List<Item> getItems() throws SQLException;
}
