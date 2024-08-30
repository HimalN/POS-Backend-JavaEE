package lk.ijse.posbackend.dao.custom.impl;

import lk.ijse.posbackend.controller.CustomerController;
import lk.ijse.posbackend.dao.custom.CustomerDAO;
import lk.ijse.posbackend.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    static String SAVE_CUSTOMERS = "INSERT INTO customers VALUES (?,?,?,?)";
    static String GET_ALL_CUSTOMERS = "SELECT * FROM customers";
    static String DELETE_CUSTOMERS = "DELETE FROM customers WHERE iD = ?";
    static String UPDATE_CUSTOMERS = "UPDATE customers SET name = ?, address = ?, phoneNumber = ? WHERE id = ?";
    static String SEARCH_CUSTOMERS = "SELECT * FROM customers WHERE id = ? ";
    static Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Override
    public boolean save(Customer customer, Connection connection) throws SQLException {
        logger.info("Save Customer");
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
        var ps = connection.prepareStatement(GET_ALL_CUSTOMERS);
        logger.info("Get All Customer");
        var resultSet = ps.executeQuery();
        List<Customer> customerList = new ArrayList<>();
        while (resultSet.next()){
            Customer customers = new Customer(
                    resultSet.getString("id"),
                    resultSet.getString("name"),
                    resultSet.getString("address"),
                    resultSet.getString("phone")
            );
            customerList.add(customers);
        }
        return customerList;
    }

    @Override
    public boolean update(String id, Customer dto, Connection connection) throws SQLException {
        logger.info("Update Customer");
        try {
            var ps = connection.prepareStatement(UPDATE_CUSTOMERS);
            ps.setString(1, dto.getCustomerName());
            ps.setString(2, dto.getCustomerAddress());
            ps.setString(3, dto.getCustomerPhoneNumber());
            ps.setString(4, id);

            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public boolean delete(String id, Connection connection) throws SQLException {
        logger.info("Delete Customer");
        try {
            var ps = connection.prepareStatement(DELETE_CUSTOMERS);
            ps.setString(1, id);
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Customer search(String id, Connection connection) throws SQLException {
        Customer customer = null;

        var ps = connection.prepareStatement(SEARCH_CUSTOMERS);
        ps.setString(1, id);
        var rs = ps.executeQuery();

        System.out.println("result"+ps);
        System.out.println("result"+rs);

        System.out.println(rs.getString("name"));
        while (rs.next()) {
            String customerID = rs.getString("id");
            String customerName = rs.getString("name");
            String customerAddress = rs.getString("address");
            String customerPhoneNumber = rs.getString("phone");

            customer = new Customer(customerID, customerName, customerAddress, customerPhoneNumber);
        }
        System.out.println(customer);
        return customer;

    }
}
