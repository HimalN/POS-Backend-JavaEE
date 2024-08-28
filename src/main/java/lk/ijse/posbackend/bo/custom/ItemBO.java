package lk.ijse.posbackend.bo.custom;

import lk.ijse.posbackend.bo.SuperBO;
import lk.ijse.posbackend.dto.CustomerDTO;
import lk.ijse.posbackend.dto.ItemDTO;
import lk.ijse.posbackend.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ItemBO extends SuperBO {
    boolean saveItem(ItemDTO itemDTO, Connection connection) throws SQLException;

    List<ItemDTO> getAllItem(Connection connection) throws SQLException;

    boolean deleteItem(String customerID, Connection connection) throws SQLException;

    boolean updateItem(String customerID, CustomerDTO customerDTO, Connection connection) throws SQLException;

    ItemDTO searchItem(String itemID, Connection connection) throws SQLException;
}
