package com.my.dao.book;

import com.my.db.DBConnector;
import com.my.entities.BookOnTicket;
import com.my.exception.ApplicationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface BookOnTicketDAO {

    void setDBConnector(DBConnector dbConnector);
    List<BookOnTicket> getAllBooksOnUserTicket(int userId) throws ApplicationException;
    void addBookOnUserTicket(Connection connection, int userId, int bookId, Date date) throws SQLException;
    BookOnTicket getBookOnUserTicket(int userId,int bookId) throws ApplicationException;
    List<BookOnTicket> getBookOnTicketByBookId(int bookId) throws ApplicationException;
    void updateFine();
}
