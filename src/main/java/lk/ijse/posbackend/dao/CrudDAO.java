package lk.ijse.posbackend.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO{
    boolean save(T dto, Connection connection) throws SQLException;
    List<T> getAll(Connection connection) throws SQLException;
    boolean update(String id,T dto, Connection connection) throws SQLException;
    boolean delete(String id,Connection connection) throws SQLException;
    T search(String id, Connection connection) throws SQLException;
}
