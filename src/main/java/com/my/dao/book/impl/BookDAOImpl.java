package com.my.dao.book.impl;

import com.my.dao.book.BookDAO;
import com.my.db.DBConnector;
import com.my.entities.Book;
import com.my.exception.ApplicationException;
import com.my.services.InstanceService;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {


    private static final String SELECT_ALL_BOOKS = "SELECT * FROM books LIMIT ?,5";
    private static final String SELECT_BOOKS_BY_TITLE_OR_AUTHOR = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ?";
    private static final String SELECT_BOOK_BY_BOOK_INFO = "SELECT * FROM books WHERE title=? AND author=? AND publishing_house=? AND year=?";
    private static final String SELECT_ALL_BOOKS_ORDER_BY_TITLE = "SELECT * FROM books ORDER BY title LIMIT ?,5";
    private static final String SELECT_ALL_BOOKS_ORDER_BY_AUTHOR = "SELECT * FROM books ORDER BY author LIMIT ?,5";
    private static final String SELECT_ALL_BOOKS_ORDER_BY_PUBLISHING = "SELECT * FROM books ORDER BY publishing_house LIMIT ?,5";
    private static final String SELECT_ALL_BOOKS_ORDER_BY_YEAR = "SELECT * FROM books ORDER BY year DESC LIMIT ?,5";
    private final static String SELECT_BOOK_BY_ID = "SELECT * FROM books WHERE id=?";
    private final static String UPDATE_BOOKS_SET_AMOUNT_DECREMENT_BY_ID = "UPDATE books SET amount= amount - 1 WHERE id=?";
    private final static String UPDATE_BOOK_VALUES = "UPDATE books SET title=?, author=?,publishing_house=?,year=?,amount=? WHERE id=?";
    private final static String INSERT_NEW_BOOK = "INSERT INTO books(title, author, publishing_house, year, amount) VALUES (?,?,?,?,?)";
    private final static String DELETE_BOOK = "DELETE FROM books WHERE id=?";

    private static final Logger LOGGER = Logger.getLogger(BookDAOImpl.class);

    private DBConnector dbConnector;


    public void setDBConnector(DBConnector dbConnector){
        this.dbConnector = dbConnector;
    }



    @Override
    public List<Book> getAllBooks(int page) throws ApplicationException {
        List<Book> bookList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = dbConnector.getConnection();
            pst = con.prepareStatement(SELECT_ALL_BOOKS);
            pst.setInt(1,page*5-5);
            rs = pst.executeQuery();
            while (rs.next()){
                bookList.add(InstanceService.buildBook(rs,false));
            }
        } catch (SQLException e) {
            LOGGER.error("getAllBooks() error",e);
            System.out.println("SQLException - getAllBooks");
            throw new ApplicationException("Can't get all books",e);
        } finally {
            dbConnector.close(rs,pst,con);
        }
        return  bookList;
    }

    @Override
    public List<Book> getSortedBooks(int page, String type) throws ApplicationException {
        List<Book> bookList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = dbConnector.getConnection();
            pst = con.prepareStatement(getTypeOfSorter(type));
            pst.setInt(1,page*5-5);
            rs = pst.executeQuery();
            while (rs.next()){
                bookList.add(InstanceService.buildBook(rs,false));
            }
        } catch (SQLException e) {
            LOGGER.error("getSortedBooks() error",e);
            System.out.println("SQLException - getAllBooks");
            throw new ApplicationException("Can't get sorted books",e);
        } finally {
            dbConnector.close(rs,pst,con);
        }
        return  bookList;
    }

    @Override
    public List<Book> searchBook(String search) throws ApplicationException {
        List<Book> bookList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            con = dbConnector.getConnection();
            pst = con.prepareStatement(SELECT_BOOKS_BY_TITLE_OR_AUTHOR);
            String parameter = "%" + search + "%";
            pst.setString(1,parameter);
            pst.setString(2,parameter);
            rs = pst.executeQuery();
            while (rs.next()){
                bookList.add(InstanceService.buildBook(rs,false));
            }
        } catch (SQLException e) {
            LOGGER.error("searchBook() error",e);
            throw  new ApplicationException("Can't search book",e);
        } finally {
            dbConnector.close(rs,pst,con);
        }
        return bookList;
    }

    @Override
    public Book getBookById(int id) throws ApplicationException {
        Book book = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = dbConnector.getConnection();
            pst = con.prepareStatement(SELECT_BOOK_BY_ID);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            if (rs.next()){
                book = InstanceService.buildBook(rs,false);
            }
        } catch (SQLException e) {
            LOGGER.error("getBookById() error",e);
            throw new ApplicationException("Can't get book by id",e);

        } finally {
            dbConnector.close(rs,pst,con);
        }
        return book;
    }

    public Book getBookByBookInfo(String title,String author,String publishingHouse, int year) throws ApplicationException {
        Book book = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            con = dbConnector.getConnection();
            pst = con.prepareStatement(SELECT_BOOK_BY_BOOK_INFO);
            pst.setString(1,title);
            pst.setString(2,author);
            pst.setString(3,publishingHouse);
            pst.setInt(4,year);
            rs = pst.executeQuery();
            if (rs.next()){
                book = InstanceService.buildBook(rs,false);
            }
        } catch (SQLException e) {
            LOGGER.error("getBookByBookInfo() error",e);
            throw new ApplicationException("Can't get book by id",e);
        }finally {
            dbConnector.close(rs,pst,con);
        }
        return book;
    }

    @Override
    public void decrementBookAmountById(Connection connection, int id) throws SQLException  {
        PreparedStatement pst = connection.prepareStatement(UPDATE_BOOKS_SET_AMOUNT_DECREMENT_BY_ID);
        pst.setInt(1, id);
        pst.executeUpdate();
    }

    @Override
    public void insertBook(Book book) throws ApplicationException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            con = dbConnector.getConnection();
            con.setAutoCommit(false);
            pst = con.prepareStatement(INSERT_NEW_BOOK,Statement.RETURN_GENERATED_KEYS);
            pst.setString(1,book.getTitle());
            pst.setString(2,book.getAuthor());
            pst.setString(3,book.getPublishingHouse());
            pst.setInt(4,book.getYear());
            pst.setInt(5,book.getAmount());
            pst.executeUpdate();
            rs = pst.getGeneratedKeys();
            if (rs.next()){
                book.setId(rs.getInt(1));
            }
            con.commit();
        } catch (SQLException e) {
            LOGGER.error("insertBook() error",e);
            rollBack(con);
            throw new ApplicationException("Can't insert book",e);
        } finally {
            dbConnector.close(rs,pst,con);
        }
    }

    @Override
    public void editBook(Book book) throws ApplicationException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = dbConnector.getConnection();
            con.setAutoCommit(false);
            pst = con.prepareStatement(UPDATE_BOOK_VALUES);
            pst.setString(1,book.getTitle());
            pst.setString(2,book.getAuthor());
            pst.setString(3,book.getPublishingHouse());
            pst.setInt(4,book.getYear());
            pst.setInt(5,book.getAmount());
            pst.setInt(6,book.getId());
            pst.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            LOGGER.error("editBook() error",e);
            rollBack(con);
            throw new ApplicationException("Can't edit book",e);
        }finally {
            dbConnector.close(pst,con);
        }
    }

    @Override
    public void deleteBook(int bookId) throws ApplicationException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = dbConnector.getConnection();
            con.setAutoCommit(false);
            pst = con.prepareStatement(DELETE_BOOK);
            pst.setInt(1,bookId);
            pst.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            LOGGER.error("deleteBook() error",e);
            throw new ApplicationException("Can't delete book",e);
        }finally {
           dbConnector.close(pst,con);
        }
    }

    private String getTypeOfSorter(String type){
        switch (type){
            case "title" : return SELECT_ALL_BOOKS_ORDER_BY_TITLE;
            case "author" : return SELECT_ALL_BOOKS_ORDER_BY_AUTHOR;
            case "publishing_house" : return SELECT_ALL_BOOKS_ORDER_BY_PUBLISHING;
            case "year" : return SELECT_ALL_BOOKS_ORDER_BY_YEAR;
            default: return null;
        }
    }

    private void rollBack(Connection connection){
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.error("connection.rollback() error",e);
        }
    }

}
