package com.my.listener;

import com.my.command.CommandFactory;
import com.my.dao.DAOFactory;
import com.my.db.DBConnector;
import com.my.services.InstanceService;
import com.my.services.OrderBookService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Listener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DAOFactory daoFactory = new DAOFactory(DBConnector.getInstance());
        OrderBookService.getInstance().setDep(daoFactory,DBConnector.getInstance());
        InstanceService.getInstance().setDAOFactory(daoFactory);
        CommandFactory commandFactory = new CommandFactory(daoFactory);
        ServletContext ctx = sce.getServletContext();
        ctx.setAttribute("dbConnector",DBConnector.getInstance());
        ctx.setAttribute("commandFactory",commandFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
