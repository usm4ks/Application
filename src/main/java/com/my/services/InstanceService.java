package com.my.services;

import com.my.dao.DAOFactory;
import com.my.entities.Book;
import com.my.entities.User;
import com.my.exception.ApplicationException;

public class InstanceService {
    private static InstanceService instance;
    private  DAOFactory daoFactory;

    public void setDAOFactory(DAOFactory bookDAO){
        this.daoFactory = bookDAO;
    }

    public static synchronized InstanceService getInstance() {
        if (instance == null) {
            instance = new InstanceService();
        }
        return instance;
    }

    public Book getBookInstanceById(int bookId){
        try {
            return daoFactory.getBookDAO().getBookById(bookId);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        return null;
    }
    public User getUserInstanceById(int userId) {
        try {
            return daoFactory.getUserDAO().getUserById(userId);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        return null;
    }
}