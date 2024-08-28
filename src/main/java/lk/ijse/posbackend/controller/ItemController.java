package lk.ijse.posbackend.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.posbackend.bo.BOFactory;
import lk.ijse.posbackend.bo.custom.ItemBO;
import lk.ijse.posbackend.dto.ItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/itemController")
public class ItemController extends HttpServlet {
    private Connection connection;
    static Logger logger = LoggerFactory.getLogger(ItemController.class);
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEMS);

    @Override
    public void init(ServletConfig config) throws ServletException {
        logger.info("Initializing Item Controller");
        try {
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/Registration");
            this.connection = pool.getConnection();
        } catch (NamingException e) {
            throw new RuntimeException(e);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("Adding Item");
        if (!req.getContentType().toLowerCase().startsWith("application/json") || req.getContentType() == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
            try(var writer = resp.getWriter()){
                Jsonb jsonb = JsonbBuilder.create();
                ItemDTO itemDTO = jsonb.fromJson(req.getReader(),ItemDTO.class);

                logger.info(itemDTO.toString());
                boolean isSaved  = itemBO.saveItem(itemDTO,connection);

                if(isSaved){
                    writer.write("Item Saved Successfully");
                    resp.setStatus(HttpServletResponse.SC_CREATED);
                }else {
                    writer.write("Something went wrong");
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }

            } catch (SQLException e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                throw new RuntimeException(e);
            }
    }
}
