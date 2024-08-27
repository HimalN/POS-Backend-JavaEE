package lk.ijse.posbackend.bo.custom;

import lk.ijse.posbackend.bo.SuperBO;
import lk.ijse.posbackend.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ItemBO extends SuperBO {
    boolean saveItem(CustomerDTO customerDTO, Connection connection) throws SQLException;

    List<CustomerDTO> getAllItem(Connection connection) throws SQLException;

    boolean deleteItem(String customerID, Connection connection) throws SQLException;

    boolean updateItem(String customerID, CustomerDTO customerDTO, Connection connection) throws SQLException;
}
