package com.my.entities;

import com.my.enums.OrderType;

public class Order {
    private User user;
    private Book book;
    private OrderType type;

    public Order(User user, Book book, OrderType type) {
        this.user = user;
        this.book = book;
        this.type = type;
    }
    public Order() {}

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public OrderType getType() {
        return type;
    }
}
