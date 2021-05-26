package com.my.services;

import com.my.entities.Book;
import com.my.entities.User;
import com.my.enums.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class InstanceService1 {


    public static Book buildBook(ResultSet rs,Boolean isComplex) throws SQLException {
        Book book = new Book();
        if (isComplex){
            book.setId(rs.getInt("book_id"));
        }else {
            book.setId(rs.getInt("id"));
        }
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setPublishingHouse(rs.getString("publishing_house"));
        book.setYear(rs.getInt("year"));
        book.setAmount(rs.getInt("amount"));
        return book;
    }

    public static User buildUser(ResultSet rs,Boolean isComplex) throws SQLException {
        User user = new User();
        if (isComplex){
            user.setId(rs.getInt("user_id"));
        } else {
            user.setId(rs.getInt("id"));
        }
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setRole(UserRole.valueOf(rs.getString("role").toUpperCase(Locale.ROOT)));
        user.setBlocked(rs.getBoolean("blocked"));
        return user;
    }

}
