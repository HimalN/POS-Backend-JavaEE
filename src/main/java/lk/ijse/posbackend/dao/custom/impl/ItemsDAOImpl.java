package lk.ijse.posbackend.dao.custom.impl;

import lk.ijse.posbackend.dao.custom.ItemDAO;
import lk.ijse.posbackend.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ItemsDAOImpl implements ItemDAO {
    @Override
    public boolean save(Item dto, Connection connection) throws SQLException {
        return false;
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
}
