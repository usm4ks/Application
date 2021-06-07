package com.my.command.order;

import com.my.command.Command;
import com.my.dao.order.OrderDAO;
import com.my.entities.Order;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAllOrdersCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ShowAllOrdersCommand.class);

    private final OrderDAO orderDAO;


    public ShowAllOrdersCommand(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            List<Order> orderList = orderDAO.getAllOrders();
            request.setAttribute("order_list",orderList);
        } catch (ApplicationException e) {
            LOGGER.error(e);
            throw new CommandException("Can't show all orders",e);
        }
        return "/WEB-INF/views/order_list.jsp";
    }
}
