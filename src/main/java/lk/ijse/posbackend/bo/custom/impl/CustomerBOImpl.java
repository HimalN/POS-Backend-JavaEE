package lk.ijse.posbackend.bo.custom.impl;

import lk.ijse.posbackend.bo.custom.CustomerBO;
import lk.ijse.posbackend.dao.DAOFactory;
import lk.ijse.posbackend.dao.custom.CustomerDAO;
import lk.ijse.posbackend.dto.CustomerDTO;
import lk.ijse.posbackend.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO, Connection connection) throws SQLException {
        return customerDAO.save(new Customer(customerDTO.getCustomerID(), customerDTO.getCustomerName(), customerDTO.getCustomerAddress(),customerDTO.getCustomerPhoneNumber()), connection);
    }

    @Override
    public List<CustomerDTO> getAllCustomers(Connection connection) throws SQLException {
        return List.of();
    }

    @Override
    public boolean deleteCustomer(String customerID, Connection connection) throws SQLException {
        return false;
    }

    @Override
    public boolean updateCustomer(String customerID, CustomerDTO customerDTO, Connection connection) throws SQLException {
        return false;
    }
}
