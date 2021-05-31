package com.my.dao.book.impl;

import com.my.dao.DAOFactory;
import com.my.db.DBConnector;
import com.my.entities.Book;
import com.my.exception.ApplicationException;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BookDAOImplTest {

    @Test
    public void getBookByIdShouldReturnNullIfBookNotFound() throws ApplicationException, SQLException {
        DBConnector dbConnector = mock(DBConnector.class);
        DAOFactory daoFactory = new DAOFactory(dbConnector);
        Connection connection = mock(Connection.class);
        when(dbConnector.getConnection()).thenReturn(connection);
        PreparedStatement pst = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenReturn(pst);
        ResultSet rs = mock(ResultSet.class);
        when(pst.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(false);
        Book book = daoFactory.getBookDAO().getBookById(2);
        assertNull(book);
    }

    @Test
    public void searchBookShouldReturnEmptyListIfBookNotFound() throws ApplicationException, SQLException {
        DBConnector dbConnector = mock(DBConnector.class);
        DAOFactory daoFactory = new DAOFactory(dbConnector);
        Connection connection = mock(Connection.class);
        when(dbConnector.getConnection()).thenReturn(connection);
        PreparedStatement pst = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenReturn(pst);
        ResultSet rs = mock(ResultSet.class);
        when(pst.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(false);
        List<Book> bookList = daoFactory.getBookDAO().searchBook("abc");
        assertEquals(0, bookList.size());
    }

}