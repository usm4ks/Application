package com.my.dao.book;

import com.my.db.DBConnector;
import com.my.entities.BookInHall;
import com.my.exception.ApplicationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface BookInHallDAO {
    void setDBConnector(DBConnector dbConnector);
    List<BookInHall> getAllUserBooksInHall(int userId) throws ApplicationException;
    BookInHall getUserBookInHall(int userId,int bookId) throws ApplicationException;
    void addUserBookInHall(Connection connection, int userId, int bookId) throws SQLException;
    List<BookInHall> getBookInHallByBookId(int bookId) throws ApplicationException;
}
