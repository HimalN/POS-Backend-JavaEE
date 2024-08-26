package lk.ijse.posbackend.bo.custom;

import lk.ijse.posbackend.bo.SuperBO;
import lk.ijse.posbackend.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    boolean saveCustomer(CustomerDTO customerDTO, Connection connection) throws SQLException;

    List<CustomerDTO> getAllCustomers(Connection connection) throws SQLException;

    boolean deleteCustomer(String customerID, Connection connection) throws SQLException;

    boolean updateCustomer(String customerID, CustomerDTO customerDTO, Connection connection) throws SQLException;
}
