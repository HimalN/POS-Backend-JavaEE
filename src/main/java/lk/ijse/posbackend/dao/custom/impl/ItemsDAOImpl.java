package lk.ijse.posbackend.dao.custom.impl;

import lk.ijse.posbackend.dao.custom.ItemDAO;
import lk.ijse.posbackend.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemsDAOImpl implements ItemDAO {

    static String SAVE_ITEMS = "INSERT INTO item VALUES (?,?,?,?,?,?)";
    static String GET_ALL_ITEMS = "SELECT * FROM item";
    static String DELETE_ITEMS = "DELETE FROM item WHERE itemID = ?";
    static String UPDATE_ITEMS = "UPDATE item SET itemName = ?, category = ?, weight = ?, price = ?, Qty = ? WHERE itemID = ?";
    static String UPDATE_QTY = "UPDATE item SET itemQty = itemQty - ? WHERE itemID = ?";
    static String UPDATE_QTY_DELETED = "UPDATE item SET itemQty = itemQty + ? WHERE itemID = ?";
    static String UPDATE_QTY_ON_HAND = "UPDATE item SET itemQty = ? WHERE itemID = ?";
    static String SEARCH_ITEMS = "SELECT * FROM item WHERE itemID = ?";

    @Override
    public boolean save(Item item, Connection connection) throws SQLException {
        try {
            var ps = connection.prepareStatement(SAVE_ITEMS);
            ps.setString(1,item.getItemCode());
            ps.setString(2,item.getItemName());
            ps.setString(3, item.getCategory());
            ps.setString(4, item.getWeight());
            ps.setString(5,item.getPrice());
            ps.setString(6,item.getQty());

            return ps.executeUpdate() != 0;
        }catch (SQLException e){
            throw new SQLException(e);
        }
    }

    @Override
    public List<Item> getAll(Connection connection) throws SQLException {
        var ps = connection.prepareStatement(GET_ALL_ITEMS);
        var resultSet = ps.executeQuery();
        List<Item> itemsList = new ArrayList<>();
        while (resultSet.next()) {
            Item item = new Item(
                    resultSet.getString("id"),
                    resultSet.getString("itemName"),
                    resultSet.getString("category"),
                    resultSet.getString("weight"),
                    resultSet.getString("price"),
                    resultSet.getString("Qty")
            );
            itemsList.add(item);
        }
        return itemsList;
    }

    @Override
    public boolean update(String id, Item dto, Connection connection) throws SQLException {
        try {
            var ps = connection.prepareStatement(UPDATE_ITEMS);
            ps.setString(1, dto.getItemName());
            ps.setString(2, dto.getCategory());
            ps.setString(3, dto.getWeight());
            ps.setString(4, dto.getPrice());
            ps.setString(5, dto.getQty());
            ps.setString(6, dto.getItemCode());
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public boolean delete(String id, Connection connection) throws SQLException {
        try {
            var ps = connection.prepareStatement(DELETE_ITEMS);
            ps.setString(1, id);
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Item search(String id, Connection connection) throws SQLException {
        Item item = null;
        var ps = connection.prepareStatement(SEARCH_ITEMS);
        ps.setString(1, id);
        var resultSet = ps.executeQuery();
        while (resultSet.next()) {
            String itemID = resultSet.getString("id");
            String itemName = resultSet.getString("itemName");
            String itemPrice = resultSet.getString("category");
            String weight = resultSet.getString("weight");
            String price = resultSet.getString("price");
            String qty = resultSet.getString("Qty");


            item = new Item(itemID, itemName, itemPrice, weight, price, qty);
        }
        return item;
    }
}
