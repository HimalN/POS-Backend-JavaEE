package lk.ijse.posbackend.dao.custom.impl;

import lk.ijse.posbackend.dao.custom.ItemDAO;
import lk.ijse.posbackend.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ItemsDAOImpl implements ItemDAO {

    static String SAVE_ITEMS = "INSERT INTO Item VALUES (?,?,?,?,?,?)";

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
        return List.of();
    }

    @Override
    public boolean update(String id, Item dto, Connection connection) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id, Connection connection) throws SQLException {
        return false;
    }

    @Override
    public Item search(String id, Connection connection) throws SQLException {
        return null;
    }
}
