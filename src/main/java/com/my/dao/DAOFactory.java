package com.my.dao;

import com.my.dao.book.BookDAO;
import com.my.dao.book.BookInHallDAO;
import com.my.dao.book.BookOnTicketDAO;
import com.my.dao.book.impl.BookDAOImpl;
import com.my.dao.book.impl.BookInHallDAOImpl;
import com.my.dao.book.impl.BookOnTicketDAOImpl;
import com.my.dao.order.OrderDAO;
import com.my.dao.order.impl.OrderDAOImpl;
import com.my.dao.user.impl.UserDAOImpl;
import com.my.dao.user.UserDAO;
import com.my.db.DBConnector;

public class DAOFactory {

    private final BookDAO bookDAO;
    private final UserDAO userDAO;
    private final BookInHallDAO bookInHallDAO ;
    private final BookOnTicketDAO  bookOnTicketDAO;
    private final OrderDAO orderDAO;

    {
        bookDAO = new BookDAOImpl();
        userDAO = new UserDAOImpl();
        bookInHallDAO = new BookInHallDAOImpl();
        bookOnTicketDAO = new BookOnTicketDAOImpl();
        orderDAO = new OrderDAOImpl();
    }

    public DAOFactory(DBConnector dbConnector){
        userDAO.setDBConnector(dbConnector);
        bookDAO.setDBConnector(dbConnector);
        bookInHallDAO.setDBConnector(dbConnector);
        bookOnTicketDAO.setDBConnector(dbConnector);
        orderDAO.setDBConnector(dbConnector);

    }

    public  BookDAO getBookDAO(){return bookDAO;}
    public  UserDAO getUserDAO(){return userDAO;}
    public  BookInHallDAO getBookInHallDAO(){return bookInHallDAO;}
    public  BookOnTicketDAO getBookOnTicketDAO(){return bookOnTicketDAO;}
    public  OrderDAO getOrderDAO(){return orderDAO;}
}
