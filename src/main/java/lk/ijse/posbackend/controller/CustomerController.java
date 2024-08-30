package lk.ijse.posbackend.controller;

import jakarta.json.JsonException;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.posbackend.bo.BOFactory;
import lk.ijse.posbackend.bo.custom.CustomerBO;
import lk.ijse.posbackend.dto.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.PrintWriter;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@WebServlet(urlPatterns = "/customerController")
public class CustomerController extends HttpServlet {
    Connection connection;
    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        logger.info("Customer Controller Initialized");
        try {
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/Registration");
            this.connection = pool.getConnection();
        } catch (NamingException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*Save Details*/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(!req.getContentType().toLowerCase().startsWith("application/json")|| req.getContentType() == null){
            //send error
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        logger.info("Adding Customers");
        // Persist Data
        try (var writer = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();
            var customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
            logger.info(customerDTO.toString());
            boolean isSaved = customerBO.saveCustomer(customerDTO, connection);
            if (isSaved){
                logger.info("Customer saved");
                writer.println("Customer saved");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }else{
                logger.info("Customer not saved");
                writer.println("Customer not saved");
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }

        } catch (JsonException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("customerID")!=null){
            logger.info("Searching customer by ID");
            searchCustomerByID(req,resp);
        }else {
            loadAllCustomers(req,resp);
        }
    }

    private void searchCustomerByID(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var customerID = req.getParameter("customerID");
        try (var writer = resp.getWriter()){
            CustomerDTO customer = customerBO.searchCustomerByID(customerID,connection);
            var jsonb = JsonbBuilder.create();
            resp.setContentType("application/json");
            jsonb.toJson(customer,writer);

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllCustomers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var writer = resp.getWriter()) {
            List<CustomerDTO> customerDTOList = customerBO.getAllCustomers(connection);
            if (customerDTOList != null) {
                resp.setContentType("application/json");
                Jsonb jsonb = JsonbBuilder.create();
                jsonb.toJson(customerDTOList, writer);
            } else {
                writer.write("No customers found");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getContentType().toLowerCase().startsWith("application/json") || req.getContentType() == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        try(var writer = resp.getWriter()) {
            var customerID = req.getParameter("customerID");
            Jsonb jsonb = JsonbBuilder.create();
            CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
            boolean isUpdated = customerBO.updateCustomer(customerID, customerDTO, connection);
            if (isUpdated) {
                resp.getWriter().write("Customer updated successfully");
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                writer.write("Something went wrong Customer did not updated successfully");
            }
        } catch (JsonbException | SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*Todo: Delete Details*/

        var customerID = req.getParameter("id");
        try {
            boolean isDeleted = customerBO.deleteCustomer(customerID,connection);
            if (isDeleted) {
                resp.getWriter().write("Customer deleted successfully");
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                resp.getWriter().write("Customer not deleted");
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
