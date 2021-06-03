package com.my.dao.order.impl;

import com.my.dao.order.OrderDAO;
import com.my.db.DBConnector;
import com.my.entities.Order;
import com.my.enums.OrderType;
import com.my.exception.ApplicationException;

import com.my.util.InstanceBuilder;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OrderDAOImpl implements OrderDAO {

    private final static String SELECT_ALL_FROM_ORDERS = "SELECT user_id,email,password,first_name,last_name,role,blocked,book_id,title,author,publishing_house,year,amount,type FROM orders JOIN books b ON b.id = orders.book_id JOIN users u ON orders.user_id = u.id";
    private final static String INSERT_ORDER = "INSERT INTO orders(user_id, book_id, type) VALUES (?,?,?)";
    private final static String SELECT_ALL_ORDERED_BOOKS_BY_USER_ID = "SELECT user_id,email,password,first_name,last_name,role,blocked,book_id,title,author,publishing_house,year,amount,type FROM orders JOIN books b ON b.id = orders.book_id JOIN users u ON orders.user_id = u.id WHERE user_id=?";
    private final static String SELECT_ORDERED_BOOK_BY_USER_ID = "SELECT user_id,email,password,first_name,last_name," +
            "role,blocked,book_id,title,author,publishing_house,year,amount,type " +
            "FROM orders JOIN books b ON b.id = orders.book_id " +
            "JOIN users u ON orders.user_id = u.id WHERE user_id=? AND book_id=?";
    private static final String DELETE_ORDER = "DELETE FROM orders WHERE user_id=? AND book_id=?";

    private static final Logger LOGGER = Logger.getLogger(OrderDAOImpl.class);

    private DBConnector dbConnector;

    public void setDBConnector(DBConnector dbConnector){
        this.dbConnector = dbConnector;
    }

    public void deleteOrder(Connection connection, int userId, int bookId) throws SQLException {
        PreparedStatement pst = connection.prepareStatement(DELETE_ORDER);
        pst.setInt(1,userId);
        pst.setInt(2,bookId);
        pst.executeUpdate();
    }

    @Override
    public List<Order> getAllOrders() throws ApplicationException {
        List<Order> orderList = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DBConnector.getInstance().getConnection();
            st = con.createStatement();
            rs = st.executeQuery(SELECT_ALL_FROM_ORDERS);
            while (rs.next()){
                orderList.add(createOrder(rs));
            }
        } catch (SQLException e) {
            LOGGER.error("getAllOrders() error",e);
            throw new ApplicationException("Can't get all orders",e);
        } finally {
            dbConnector.close(rs,st,con);
        }
        return  orderList;
    }

    @Override
    public void addBookToOrder (Connection connection, int userId, int bookId, OrderType type) throws SQLException {
        PreparedStatement pst = connection.prepareStatement(INSERT_ORDER);
        pst.setInt(1, userId);
        pst.setInt(2, bookId);
        pst.setString(3, type.getType());
        pst.executeUpdate();
    }


    @Override
    public List<Order> getUserOrders(int userId) throws ApplicationException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Order> orderList = new ArrayList<>();
        try{
            con = dbConnector.getConnection();
            pst = con.prepareStatement(SELECT_ALL_ORDERED_BOOKS_BY_USER_ID);
            pst.setInt(1,userId);
            rs = pst.executeQuery();
            while (rs.next()){
                 orderList.add(createOrder(rs));
            }
        } catch (SQLException e) {
            LOGGER.error("getUserOrders() error",e);
            throw new ApplicationException("Can't get all orders was made by user",e);
        }finally {
            dbConnector.close(rs,pst,con);
        }
        return orderList;
    }

    @Override
    public Order getUserOrder(int userId,int bookId) throws ApplicationException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Order order = null;
        try{
            con = dbConnector.getConnection();
            pst = con.prepareStatement(SELECT_ORDERED_BOOK_BY_USER_ID);
            pst.setInt(1,userId);
            pst.setInt(2,bookId);
            rs = pst.executeQuery();
            if (rs.next()){
                order = createOrder(rs);
            }
        } catch (SQLException e) {
            LOGGER.error("getUserOrder() error",e);
            throw new ApplicationException("Can't get user order by book id and user id",e);
        }finally {
            dbConnector.close(rs,pst,con);
        }
        return order;
    }

    private Order createOrder(ResultSet rs) throws SQLException {
        return new Order(
                InstanceBuilder.buildUser(rs,true),
                InstanceBuilder.buildBook(rs,true),
                OrderType.valueOf(rs.getString("type").toUpperCase(Locale.ROOT)));
    }
}
