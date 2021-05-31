package com.my.services;

import com.my.entities.Book;
import com.my.entities.User;
import com.my.enums.UserRole;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InstanceServiceTest {

    @Test
    public void buildBookShouldReturnCorrectBook() throws SQLException {
        Book book = new Book();
        book.setId(1);
        book.setTitle("title");
        book.setAuthor("author");
        book.setPublishingHouse("publishing_house");
        book.setYear(2021);
        book.setAmount(1);
        ResultSet rs = mock(ResultSet.class);
        when(rs.getInt("id")).thenReturn(1);
        when(rs.getString("title")).thenReturn("title");
        when(rs.getString("author")).thenReturn("author");
        when(rs.getString("publishing_house")).thenReturn("publishing_house");
        when(rs.getInt("year")).thenReturn(2021);
        when(rs.getInt("amount")).thenReturn(1);
        assertEquals(book,InstanceService.buildBook(rs,false));
    }

    @Test
    public void buildUserShouldReturnCorrectUser() throws SQLException {
        User user = new User();
        user.setId(1);
        user.setEmail("email");
        user.setPassword("password");
        user.setFirstName("fName");
        user.setLastName("lName");
        user.setRole(UserRole.USER);
        user.setBlocked(false);
        ResultSet rs = mock(ResultSet.class);
        when(rs.getInt("id")).thenReturn(1);
        when(rs.getString("email")).thenReturn("email");
        when(rs.getString("password")).thenReturn("password");
        when(rs.getString("first_name")).thenReturn("fName");
        when(rs.getString("last_name")).thenReturn("lName");
        when(rs.getString("role")).thenReturn("user");
        when(rs.getBoolean("blocked")).thenReturn(false);
        assertEquals(user,InstanceService.buildUser(rs,false));
    }
}