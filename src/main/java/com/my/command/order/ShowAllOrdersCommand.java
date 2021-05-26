package com.my.command.order;

import com.my.command.Command;
import com.my.dao.DAOFactory;
import com.my.dao.order.OrderDAO;
import com.my.entities.Order;
import com.my.exception.ApplicationException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAllOrdersCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(ShowAllOrdersCommand.class);


    public ShowAllOrdersCommand(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        OrderDAO orderDAO = daoFactory.getOrderDAO();
        try {
            List<Order> orderList = orderDAO.getAllOrders();
            request.setAttribute("order_list",orderList);
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new ApplicationException("Can't show all orders",e);
        }
        return "/WEB-INF/views/order_list.jsp";
    }
}
