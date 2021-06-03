package com.my.dao.book;

import com.my.db.DBConnector;
import com.my.entities.Book;
import com.my.exception.ApplicationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface BookDAO {

    void setDBConnector(DBConnector dbConnector);
    List<Book> getAllBooks(int page) throws ApplicationException;
    List<Book> searchBook(String search) throws ApplicationException;
    List<Book> getSortedBooks(int page,String type) throws ApplicationException;
    Book getBookById(int id) throws ApplicationException;
    void changeBookAmountById(Connection connection, int id,int amount) throws SQLException;
    void editBook(Book book) throws ApplicationException;
    void insertBook(Book book) throws ApplicationException;
    void deleteBook(int bookId) throws ApplicationException;
    Book getBookByBookInfo(String title,String author,String publishingHouse, int year) throws ApplicationException;
}
