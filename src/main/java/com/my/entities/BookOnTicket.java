package com.my.entities;

import java.util.Date;

public class BookOnTicket {

    private User user;
    private Book book;
    private Date untilDate;
    private Integer fine;

    public void setUser(User user) {
        this.user = user;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setUntilDate(Date untilDate) {
        this.untilDate = untilDate;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public Date getUntilDate() {
        return untilDate;
    }

    public Integer getFine() {
        return fine;
    }


}
