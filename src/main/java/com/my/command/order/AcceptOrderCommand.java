package com.my.command.order;

import com.my.command.Command;
import com.my.enums.OrderType;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import com.my.services.OrderBookService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class AcceptOrderCommand  implements Command {

    private static final Logger LOGGER = Logger.getLogger(AcceptOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        String orderType = request.getParameter("type");
        if (orderType.equals(OrderType.ON_TICKET.getType())) {
            try {
                Date date = Date.valueOf(request.getParameter("until_date"));
                OrderBookService.getInstance().acceptOrder(userId, bookId, date,OrderType.ON_TICKET);
            } catch (ApplicationException e) {
                LOGGER.error(e);
                throw new CommandException("Can't accept order",e);
            } catch (IllegalArgumentException e){
                LOGGER.error(e);
                throw new CommandException("Incorrect data for date",e);
            }
        } else if (orderType.equals(OrderType.IN_HALL.getType())) {
            try {
                OrderBookService.getInstance().acceptOrder(userId,bookId,null,OrderType.IN_HALL);
            } catch (ApplicationException e) {
                LOGGER.error(e);
                //todo command exception
                throw new CommandException("Can't accept order",e);
            }
        }
        return "account?command=show_all_orders";
    }
}
