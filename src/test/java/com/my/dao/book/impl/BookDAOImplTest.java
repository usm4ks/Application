package com.my.dao.book.impl;

import com.my.dao.DAOFactory;
import com.my.db.DBConnector;
import com.my.exception.ApplicationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class BookDAOImplTest {

    @Mock
    DBConnector dbConnector;
    @Mock
    Connection connection;
    @Mock
    PreparedStatement pst;
    @Mock
    ResultSet rs;

    @Test
    public void getBookByIdShouldReturnNullIfBookNotFound() throws ApplicationException, SQLException {
        //given
        DAOFactory daoFactory = new DAOFactory(dbConnector);

        //when
        when(dbConnector.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(pst);
        when(pst.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(false);

        //then
        assertNull(daoFactory.getBookDAO().getBookById(2));
    }

    @Test
    public void searchBookShouldReturnEmptyListIfBookNotFound() throws ApplicationException, SQLException {
        //given
        DAOFactory daoFactory = new DAOFactory(dbConnector);

        //when
        when(dbConnector.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(pst);
        when(pst.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(false);

        //then
        assertEquals(0, daoFactory.getBookDAO().searchBook("abc").size());
    }

}