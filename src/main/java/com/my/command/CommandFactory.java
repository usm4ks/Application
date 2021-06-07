package com.my.command;

import com.my.command.book.edit.*;
import com.my.command.book.search.SearchBookCommand;
import com.my.command.book.show.ShowAllBooksCommand;
import com.my.command.book.sort.ShowSortedBooks;
import com.my.command.order.AcceptOrderCommand;
import com.my.command.user.authorization.LogOutCommand;
import com.my.command.user.info.ShowAllUsersCommand;
import com.my.command.user.info.ShowUserInfoCommand;
import com.my.command.order.OrderBookCommand;
import com.my.command.order.ShowAllOrdersCommand;
import com.my.command.user.*;
import com.my.command.user.authorization.RegistrationCommand;
import com.my.command.user.librarian.AddLibrarianCommand;
import com.my.command.user.librarian.DeleteLibrarianCommand;
import com.my.command.user.librarian.LibrarianSettingsCommand;
import com.my.command.user.authorization.LogInCommand;
import com.my.command.user.settings.BlockUserCommand;
import com.my.command.user.settings.UnblockUserCommand;
import com.my.command.user.settings.UsersSettingsCommand;
import com.my.dao.DAOFactory;
import org.apache.log4j.Logger;

import java.util.HashMap;

public class CommandFactory {

    private static final Logger LOGGER = Logger.getLogger(CommandFactory.class);

    HashMap<String,Command> commands = new HashMap<>();
    DAOFactory daoFactory;

    public CommandFactory(DAOFactory daoFactory){
        this.daoFactory = daoFactory;
        init();
    }

    private void init() {
        commands.put(CommandConstants.SHOW_ALL_BOOKS, new ShowAllBooksCommand(daoFactory.getBookDAO()));
        commands.put(CommandConstants.REGISTRATION, new RegistrationCommand(daoFactory.getUserDAO()));
        commands.put(CommandConstants.LOG_IN, new LogInCommand(daoFactory.getUserDAO()));
        commands.put(CommandConstants.LOG_OUT, new LogOutCommand());
        commands.put(CommandConstants.SEARCH_BOOK, new SearchBookCommand(daoFactory.getBookDAO()));
        commands.put(CommandConstants.SHOW_SORTED_BOOKS, new ShowSortedBooks(daoFactory.getBookDAO()));
        commands.put(CommandConstants.ACCOUNT, new ShowAccountCommand(daoFactory.getOrderDAO(),daoFactory.getBookOnTicketDAO(),daoFactory.getBookInHallDAO()));
        commands.put(CommandConstants.ORDER_BOOK, new OrderBookCommand(daoFactory.getUserDAO(),daoFactory.getBookDAO(),daoFactory.getBookOnTicketDAO(),daoFactory.getBookInHallDAO(),daoFactory.getOrderDAO()));
        commands.put(CommandConstants.SHOW_ALL_ORDERS, new ShowAllOrdersCommand(daoFactory.getOrderDAO()));
        commands.put(CommandConstants.SHOW_ALL_USERS, new ShowAllUsersCommand(daoFactory.getUserDAO()));
        commands.put(CommandConstants.SHOW_USER_INFO, new ShowUserInfoCommand(daoFactory.getUserDAO(),daoFactory.getBookOnTicketDAO(),daoFactory.getBookInHallDAO()));
        commands.put(CommandConstants.ACCEPT_ORDER_BOOK, new AcceptOrderCommand());
        commands.put(CommandConstants.USERS_SETTINGS, new UsersSettingsCommand(daoFactory.getUserDAO()));
        commands.put(CommandConstants.UNBLOCK_USER, new UnblockUserCommand(daoFactory.getUserDAO()));
        commands.put(CommandConstants.BLOCK_USER, new BlockUserCommand(daoFactory.getUserDAO()));
        commands.put(CommandConstants.LIBRARIANS_SETTINGS, new LibrarianSettingsCommand(daoFactory.getUserDAO()));
        commands.put(CommandConstants.ADD_LIBRARIAN, new AddLibrarianCommand(daoFactory.getUserDAO()));
        commands.put(CommandConstants.DELETE_LIBRARIAN, new DeleteLibrarianCommand(daoFactory.getUserDAO()));
        commands.put(CommandConstants.EDIT_BOOK_FORM, new EditBookFormCommand(daoFactory.getBookDAO()));
        commands.put(CommandConstants.EDIT_BOOK, new EditBookCommand(daoFactory.getBookDAO()));
        commands.put(CommandConstants.ADD_NEW_BOOK_FORM, new AddNewBookFormCommand());
        commands.put(CommandConstants.ADD_NEW_BOOK, new AddNewBookCommand(daoFactory.getBookDAO()));
        commands.put(CommandConstants.DELETE_BOOK, new DeleteBookCommand(daoFactory.getBookDAO(),daoFactory.getBookOnTicketDAO(),daoFactory.getBookInHallDAO()));
        commands.put(CommandConstants.SET_LANG, new SetLangCommand());
    }

    public  Command getCommand(String command){
       Command c = commands.get(command);
       if (c == null){
           LOGGER.error("Command not found");
           return new ShowAllBooksCommand(daoFactory.getBookDAO());
       }
       return c;
    }
}
