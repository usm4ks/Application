package com.my.command;

import com.my.dao.DAOFactory;
import com.my.exception.ApplicationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command {
    public final DAOFactory daoFactory;

    protected Command(DAOFactory daoFactory){
        this.daoFactory = daoFactory;
    }

    public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws  ApplicationException;
}
