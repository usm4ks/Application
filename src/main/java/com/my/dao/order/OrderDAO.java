package com.my.dao.order;

import com.my.db.DBConnector;
import com.my.entities.Order;
import com.my.enums.OrderType;
import com.my.exception.ApplicationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {

    void setDBConnector(DBConnector dbConnector);
    List<Order> getAllOrders() throws ApplicationException;
    void addBookToOrder (Connection connection, int userId, int bookId, OrderType type) throws SQLException;
    void deleteOrder(Connection connection, int userId, int bookId) throws SQLException;

    List<Order> getUserOrders(int userId) throws ApplicationException;
    Order getUserOrder(int userId,int bookId) throws ApplicationException;
}
