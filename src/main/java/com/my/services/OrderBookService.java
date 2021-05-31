package com.my.services;


import com.my.dao.DAOFactory;
import com.my.dao.book.BookDAO;
import com.my.dao.order.OrderDAO;
import com.my.db.DBConnector;
import com.my.enums.OrderType;
import com.my.exception.ApplicationException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class OrderBookService {
    private static OrderBookService instance;
    private DAOFactory daoFactory;
    private DBConnector dbConnector;

    private static final Logger LOGGER = Logger.getLogger(OrderBookService.class);

    private OrderBookService(){}

    public void setUp(DAOFactory daoFactory,DBConnector dbConnector){
        this.daoFactory = daoFactory;
        this.dbConnector = dbConnector;
    }

    public static synchronized OrderBookService getInstance() {
        if (instance == null) {
            instance = new OrderBookService();
        }
        return instance;
    }


    public void orderBook(int userId, int bookId, OrderType orderType) throws ApplicationException {
        BookDAO bookDAO = daoFactory.getBookDAO();
        OrderDAO orderDAO = daoFactory.getOrderDAO();
        Connection connection = dbConnector.getConnection();
        try {
            connection.setAutoCommit(false);
            orderDAO.addBookToOrder(connection,userId,bookId,orderType);
            bookDAO.decrementBookAmountById(connection,bookId);
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error("orderBook() error",e);
            try {
                connection.rollback();
            } catch (SQLException exception) {
                LOGGER.error("connection.rollback() error",exception);
            }
            throw new ApplicationException("Can't order book",e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("connection.close() error",e);
            }
        }

    }
    public void acceptOrder(int userId, int bookId, Date date, OrderType orderType) throws ApplicationException {
        Connection connection = dbConnector.getConnection();
        OrderDAO orderDAO = daoFactory.getOrderDAO();
        try {
            connection.setAutoCommit(false);
            orderDAO.deleteOrder(connection,userId,bookId);
            if (orderType.equals(OrderType.ON_TICKET)){
                daoFactory.getBookOnTicketDAO().addBookOnUserTicket(connection,userId,bookId,date);
            }else {
                daoFactory.getBookInHallDAO().addUserBookInHall(connection,userId,bookId);
            }
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error("acceptOrder() error",e);
            try {
                connection.rollback();
            } catch (SQLException exception) {
                LOGGER.error("connection.rollback() error",exception);
            }
            throw new ApplicationException("Can't accept order",e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("connection.close() error",e);
            }
        }
    }


}
