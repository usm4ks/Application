package com.my.dao.book.impl;

import com.my.dao.book.BookOnTicketDAO;
import com.my.db.DBConnector;
import com.my.entities.BookOnTicket;
import com.my.exception.ApplicationException;
import com.my.util.InstanceUtils;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class BookOnTicketDAOImpl implements BookOnTicketDAO {

    private static final String SELECT_ALL_FROM_BOOKS_ON_TICKET_BY_USER_ID ="SELECT user_id,email,password,first_name,last_name,role," +
            "blocked,book_id,title,author,publishing_house,year,amount,until_date,fine " +
            "FROM books_on_ticket JOIN books b ON b.id = books_on_ticket.book_id " +
            "JOIN users u ON books_on_ticket.user_id = u.id WHERE user_id=?";
    private static final String INSERT_BOOK_ON_TICKET = "INSERT INTO books_on_ticket (user_id, book_id, until_date) values (?,?,?)";
    private static final String SELECT_ONE_BOOK_ON_TICKET_BY_USER_ID ="SELECT user_id,email,password,first_name,last_name," +
            "role,blocked,book_id,title,author,publishing_house,year,amount,until_date,fine  " +
            "FROM books_on_ticket JOIN books b ON b.id = books_on_ticket.book_id " +
            "JOIN users u ON books_on_ticket.user_id = u.id WHERE user_id=? AND book_id=?";
    private static final String SELECT_ALL_FROM_BOOKS_ON_TICKET_BY_BOOK_ID = "SELECT user_id,email,password,first_name,last_name,role," +
            "blocked,book_id,title,author,publishing_house,year,amount,until_date,fine " +
            " FROM books_on_ticket JOIN books b ON b.id = books_on_ticket.book_id " +
            "JOIN users u ON books_on_ticket.user_id = u.id WHERE book_id=?";
    private static final String UPDATE_FINE_WHERE_UNTIL_DATE_BEFORE_CURRENT="UPDATE books_on_ticket SET fine=50 WHERE until_date<current_date AND fine=0 AND user_id=?";

    private static final Logger LOGGER = Logger.getLogger(BookOnTicketDAOImpl.class);

    private DBConnector dbConnector;

    public void setDBConnector(DBConnector dbConnector){
        this.dbConnector = dbConnector;
    }



    @Override
    public List<BookOnTicket> getAllBooksOnUserTicket(int userId) throws ApplicationException {
        Connection con = null;
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        ResultSet rs = null;
        List<BookOnTicket> bookOnTicketList = new ArrayList<>();
        try{
            con = dbConnector.getConnection();
            con.setAutoCommit(false);
            pst2 = con.prepareStatement(UPDATE_FINE_WHERE_UNTIL_DATE_BEFORE_CURRENT);
            pst2.setInt(1,userId);
            pst2.executeUpdate();
            con.commit();
            pst = con.prepareStatement(SELECT_ALL_FROM_BOOKS_ON_TICKET_BY_USER_ID);
            pst.setInt(1,userId);
            rs = pst.executeQuery();
            while (rs.next()){
                bookOnTicketList.add(createBookOnTicket(rs));
            }
        } catch (SQLException e) {
            LOGGER.error("Get all books on user ticket failed with error",e);
            try {
                con.rollback();
            } catch (SQLException exception) {
                LOGGER.error(exception);
            }
            throw new ApplicationException("Can't get all books on user ticket",e);
        }finally {
            dbConnector.close(rs,pst,con);
            if (pst2 != null) {
                try {
                    pst2.close();
                } catch (SQLException e) {
                    LOGGER.error(e);
                }
            }
        }
        return bookOnTicketList;
    }

    @Override
    public void addBookOnUserTicket(Connection connection, int userId, int bookId, Date date) throws SQLException {
        PreparedStatement pst = connection.prepareStatement(INSERT_BOOK_ON_TICKET);
        pst.setInt(1, userId);
        pst.setInt(2, bookId);
        pst.setDate(3, (java.sql.Date) date);
        pst.executeUpdate();
    }

    @Override
    public BookOnTicket getBookOnUserTicket(int userId,int bookId) throws ApplicationException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        BookOnTicket bookOnTicket = null;
        try{
            con = dbConnector.getConnection();
            pst = con.prepareStatement(SELECT_ONE_BOOK_ON_TICKET_BY_USER_ID);
            pst.setInt(1,userId);
            pst.setInt(2,bookId);
            rs = pst.executeQuery();
            if (rs.next()){
                bookOnTicket = createBookOnTicket(rs);
            }
        } catch (SQLException e) {
            LOGGER.error("Get book on user ticket failed with error",e);
            throw new ApplicationException("Can't get book on user ticket",e);
        }finally {
            dbConnector.close(rs,pst,con);
        }
        return bookOnTicket;
    }

    @Override
    public List<BookOnTicket> getBookOnTicketByBookId(int bookId) throws ApplicationException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<BookOnTicket> bookOnTicketList = new ArrayList<>();
        try{
            con = dbConnector.getConnection();
            pst = con.prepareStatement(SELECT_ALL_FROM_BOOKS_ON_TICKET_BY_BOOK_ID);
            pst.setInt(1,bookId);
            rs = pst.executeQuery();
            while (rs.next()){
                bookOnTicketList.add(createBookOnTicket(rs));
            }
        } catch (SQLException e) {
            LOGGER.error("Get book on ticket by book id failed with error",e);
            throw new ApplicationException("Can't get book on users tickets",e);
        }finally {
            dbConnector.close(rs,pst,con);
        }
        return bookOnTicketList;
    }


    private BookOnTicket createBookOnTicket(ResultSet rs) throws SQLException {
        BookOnTicket bookOnTicket = new BookOnTicket();
        bookOnTicket.setBook(InstanceUtils.buildBook(rs,true));
        bookOnTicket.setUser(InstanceUtils.buildUser(rs,true));
        bookOnTicket.setUntilDate(rs.getDate("until_date"));
        bookOnTicket.setFine(rs.getInt("fine"));
        return bookOnTicket;
    }
}
