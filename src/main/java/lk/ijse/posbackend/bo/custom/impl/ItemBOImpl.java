package lk.ijse.posbackend.bo.custom.impl;

import lk.ijse.posbackend.bo.custom.ItemBO;
import lk.ijse.posbackend.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    @Override
    public boolean saveItem(CustomerDTO customerDTO, Connection connection) throws SQLException {
        return false;
    }

    @Override
    public List<CustomerDTO> getAllItem(Connection connection) throws SQLException {
        return List.of();
    }

    @Override
    public boolean deleteItem(String customerID, Connection connection) throws SQLException {
        return false;
    }

    @Override
    public boolean updateItem(String customerID, CustomerDTO customerDTO, Connection connection) throws SQLException {
        return false;
    }
}
