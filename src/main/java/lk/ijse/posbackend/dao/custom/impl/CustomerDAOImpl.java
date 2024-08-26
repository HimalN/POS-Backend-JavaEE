package lk.ijse.posbackend.dao.custom.impl;

import lk.ijse.posbackend.dao.custom.CustomerDAO;
import lk.ijse.posbackend.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    static String SAVE_CUSTOMERS = "INSERT INTO customers VALUES (?,?,?,?)";

    @Override
    public boolean save(Customer customer, Connection connection) throws SQLException {
        try {
            var ps = connection.prepareStatement(SAVE_CUSTOMERS);
            ps.setString(1, customer.getCustomerID());
            ps.setString(2, customer.getCustomerName());
            ps.setString(3, customer.getCustomerAddress());
            ps.setString(4, customer.getCustomerPhoneNumber());

            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public List<Customer> getAll(Connection connection) throws SQLException {
        return List.of();
    }

    @Override
    public boolean update(String id, Customer dto, Connection connection) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id, Connection connection) throws SQLException {
        return false;
    }
}
