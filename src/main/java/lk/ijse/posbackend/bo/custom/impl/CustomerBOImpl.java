package lk.ijse.posbackend.bo.custom.impl;

import lk.ijse.posbackend.bo.custom.CustomerBO;
import lk.ijse.posbackend.controller.CustomerController;
import lk.ijse.posbackend.dao.DAOFactory;
import lk.ijse.posbackend.dao.custom.CustomerDAO;
import lk.ijse.posbackend.dto.CustomerDTO;
import lk.ijse.posbackend.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO, Connection connection) throws SQLException {
        return customerDAO.save(new Customer(customerDTO.getCustomerID(), customerDTO.getCustomerName(), customerDTO.getCustomerAddress(),customerDTO.getCustomerPhoneNumber()), connection);
    }

    @Override
    public List<CustomerDTO> getAllCustomers(Connection connection) throws SQLException {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        List<Customer> customers = customerDAO.getAll(connection);
        for (Customer customer : customers) {
            customerDTOS.add(new CustomerDTO(customer.getCustomerID(), customer.getCustomerName(), customer.getCustomerAddress(), customer.getCustomerPhoneNumber()));
        }
        return customerDTOS;
    }

    @Override
    public boolean deleteCustomer(String customerID, Connection connection) throws SQLException {
        return customerDAO.delete(customerID,connection);
    }

    @Override
    public boolean updateCustomer(String customerID, CustomerDTO customerDTO, Connection connection) throws SQLException {
        return customerDAO.update(customerID,
                new Customer(customerDTO.getCustomerID(), customerDTO.getCustomerName(), customerDTO.getCustomerAddress(), customerDTO.getCustomerPhoneNumber()), connection);
    }

    @Override
    public CustomerDTO searchCustomerByID(String customerID, Connection connection) throws SQLException {
        Customer customer = customerDAO.search(customerID,connection);
        if (customer != null) {
            return new CustomerDTO(customer.getCustomerID(), customer.getCustomerName(), customer.getCustomerAddress(), customer.getCustomerPhoneNumber());
        } else {
            return null;
        }
    }
}
