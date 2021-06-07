package com.my.services;

import com.my.entities.Book;
import com.my.entities.User;
import com.my.enums.UserRole;
import com.my.util.InstanceUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class InstanceUtilsTest {

    @Mock
    ResultSet rs;

    @Test
    public void buildBookShouldReturnCorrectBook() throws SQLException {
        //given
        Book book = new Book();
        book.setId(1);
        book.setTitle("title");
        book.setAuthor("author");
        book.setPublishingHouse("publishing_house");
        book.setYear(2021);
        book.setAmount(1);

        //when
        when(rs.getInt("id")).thenReturn(1);
        when(rs.getString("title")).thenReturn("title");
        when(rs.getString("author")).thenReturn("author");
        when(rs.getString("publishing_house")).thenReturn("publishing_house");
        when(rs.getInt("year")).thenReturn(2021);
        when(rs.getInt("amount")).thenReturn(1);

        //then
        assertEquals(book, InstanceUtils.buildBook(rs,false));
    }

    @Test
    public void buildUserShouldReturnCorrectUser() throws SQLException {
        //given
        User user = new User();
        user.setId(1);
        user.setEmail("email");
        user.setPassword("password");
        user.setFirstName("fName");
        user.setLastName("lName");
        user.setRole(UserRole.USER);
        user.setBlocked(false);

        //when
        when(rs.getInt("id")).thenReturn(1);
        when(rs.getString("email")).thenReturn("email");
        when(rs.getString("password")).thenReturn("password");
        when(rs.getString("first_name")).thenReturn("fName");
        when(rs.getString("last_name")).thenReturn("lName");
        when(rs.getString("role")).thenReturn("user");
        when(rs.getBoolean("blocked")).thenReturn(false);

        //then
        assertEquals(user, InstanceUtils.buildUser(rs,false));
    }
}