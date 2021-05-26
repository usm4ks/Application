package com.my.dao.book.impl;

import com.my.dao.book.BookInHallDAO;
import com.my.db.DBConnector;
import com.my.entities.BookInHall;
import com.my.exception.ApplicationException;
import com.my.services.InstanceService1;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookInHallDAOImpl implements BookInHallDAO {

    private static final String SELECT_ALL_FROM_BOOKS_IN_HALL_BY_USER_ID = "SELECT user_id,email,password,first_name,last_name,role," +
            "blocked,book_id,title,author,publishing_house,year,amount " +
            "FROM books_in_hall JOIN books b ON b.id = books_in_hall.book_id " +
            "JOIN users u ON books_in_hall.user_id = u.id WHERE user_id=?";
    private static final String SELECT_ONE_BOOK_IN_HALL_BY_USER_ID = "SELECT user_id,email,password,first_name,last_name," +
            "role,blocked,book_id,title,author,publishing_house,year,amount " +
            "FROM books_in_hall JOIN books b ON b.id = books_in_hall.book_id " +
            "JOIN users u ON books_in_hall.user_id = u.id WHERE user_id=? and book_id=?";
    private static final String INSERT_BOOK_IN_HALL = "INSERT INTO books_in_hall (user_id, book_id) values (?,?)";
    private static final String SELECT_ALL_FROM_BOOKS_IN_HALL_BY_BOOK_ID = "SELECT user_id,email,password,first_name,last_name," +
            "role,blocked,book_id,title,author,publishing_house,year,amount " +
            "FROM books_in_hall JOIN books b ON b.id = books_in_hall.book_id " +
            "JOIN users u ON books_in_hall.user_id = u.id WHERE book_id=?";

    private static final Logger LOGGER = Logger.getLogger(BookInHallDAOImpl.class);

    private DBConnector dbConnector;

    public void setDBConnector(DBConnector dbConnector){
        this.dbConnector = dbConnector;
    }
    @Override
    public List<BookInHall> getAllUserBooksInHall(int userId) throws ApplicationException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<BookInHall> bookInHallList = new ArrayList<>();
        try {
            con = dbConnector.getConnection();
            pst = con.prepareStatement(SELECT_ALL_FROM_BOOKS_IN_HALL_BY_USER_ID);
            pst.setInt(1, userId);
            rs = pst.executeQuery();
            while (rs.next()) {
                bookInHallList.add(createBookInHall(rs));
            }
        } catch (SQLException e) {
            LOGGER.error("getAllUserBooksInHall() error",e);
            throw new ApplicationException("Can't get all books that user have taken in hall",e);
        } finally {
            dbConnector.close(rs,pst,con);
        }
        return bookInHallList;
    }

    @Override
    public BookInHall getUserBookInHall(int userId, int bookId) throws ApplicationException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        BookInHall bookInHall = null;
        try {
            con = dbConnector.getConnection();
            pst = con.prepareStatement(SELECT_ONE_BOOK_IN_HALL_BY_USER_ID);
            pst.setInt(1, userId);
            pst.setInt(2, bookId);
            rs = pst.executeQuery();
            if (rs.next()) {
                bookInHall = createBookInHall(rs);
            }
        } catch (SQLException e) {
            LOGGER.error("getUserBookInHall() error",e);
            throw new ApplicationException("Can't get book in hall by book id and user id",e);
        } finally {
            dbConnector.close(rs,pst,con);
        }
        return bookInHall;
    }

    @Override
    public List<BookInHall> getBookInHallByBookId(int bookId) throws ApplicationException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<BookInHall> bookInHallList = new ArrayList<>();
        try{
            con = dbConnector.getConnection();
            pst = con.prepareStatement(SELECT_ALL_FROM_BOOKS_IN_HALL_BY_BOOK_ID);
            pst.setInt(1,bookId);
            rs = pst.executeQuery();
            while (rs.next()){
                 bookInHallList.add(createBookInHall(rs));
            }
        } catch (SQLException e) {
            LOGGER.error("getBookInHallByBookId() error",e);
            throw new ApplicationException("Can't get all books in hall by book id",e);
        }finally {
            dbConnector.close(rs,pst,con);
        }
        return bookInHallList;
    }

    @Override
    public void addUserBookInHall(Connection connection, int userId, int bookId) throws SQLException {
        PreparedStatement pst = connection.prepareStatement(INSERT_BOOK_IN_HALL);
        pst.setInt(1, userId);
        pst.setInt(2, bookId);
        pst.executeUpdate();
    }

    private BookInHall createBookInHall(ResultSet rs) throws SQLException {
        BookInHall bookInHall = new BookInHall();
        bookInHall.setBook(InstanceService1.buildBook(rs,true));
        bookInHall.setUser(InstanceService1.buildUser(rs,true));
        return bookInHall;
    }
}
