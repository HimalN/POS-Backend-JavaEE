package lk.ijse.posbackend.bo.custom.impl;

import lk.ijse.posbackend.bo.custom.ItemBO;
import lk.ijse.posbackend.dao.DAOFactory;
import lk.ijse.posbackend.dao.custom.ItemDAO;
import lk.ijse.posbackend.dto.CustomerDTO;
import lk.ijse.posbackend.dto.ItemDTO;
import lk.ijse.posbackend.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);


    @Override
    public boolean saveItem(ItemDTO itemDTO, Connection connection) throws SQLException {
        return itemDAO.save(new Item(itemDTO.getItemCode(), itemDTO.getItemName(), itemDTO.getCategory(), itemDTO.getWeight(), itemDTO.getPrice(), itemDTO.getQty()), connection);
    }

    @Override
    public List<ItemDTO> getAllItem(Connection connection) throws SQLException {
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

    @Override
    public ItemDTO searchItem(String itemID, Connection connection) throws SQLException {
        Item item = itemDAO.search(itemID,connection);
        if (item != null) {
            return new ItemDTO(item.getItemCode(), item.getItemName(), item.getCategory(), item.getWeight(),item.getPrice(),item.getQty());
        } else {
            return null;
        }
    }
}
