package com.my.filters;

import com.my.command.CommandConstants;
import com.my.entities.User;
import com.my.enums.UserRole;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


@WebFilter(urlPatterns = {"/account","/book_list"})
public class AccessFilter implements Filter {
    private HashMap<String, UserRole> specialCommands;
    private Set<String> unauthorizedCommands;


    @Override
    public void init(FilterConfig filterConfig) {
        specialCommands = new HashMap<>();
        specialCommands.put(CommandConstants.USERS_SETTINGS, UserRole.ADMIN);
        specialCommands.put(CommandConstants.UNBLOCK_USER, UserRole.ADMIN);
        specialCommands.put(CommandConstants.BLOCK_USER, UserRole.ADMIN);
        specialCommands.put(CommandConstants.LIBRARIANS_SETTINGS, UserRole.ADMIN);
        specialCommands.put(CommandConstants.ADD_LIBRARIAN, UserRole.ADMIN);
        specialCommands.put(CommandConstants.DELETE_LIBRARIAN, UserRole.ADMIN);
        specialCommands.put(CommandConstants.EDIT_BOOK_FORM, UserRole.ADMIN);
        specialCommands.put(CommandConstants.EDIT_BOOK, UserRole.ADMIN);
        specialCommands.put(CommandConstants.ADD_NEW_BOOK_FORM, UserRole.ADMIN);
        specialCommands.put(CommandConstants.ADD_NEW_BOOK, UserRole.ADMIN);
        specialCommands.put(CommandConstants.DELETE_BOOK, UserRole.ADMIN);
        specialCommands.put(CommandConstants.ACCEPT_ORDER_BOOK, UserRole.LIBRARIAN);
        specialCommands.put(CommandConstants.SHOW_USER_INFO, UserRole.LIBRARIAN);
        specialCommands.put(CommandConstants.SHOW_ALL_ORDERS, UserRole.LIBRARIAN);
        specialCommands.put(CommandConstants.SHOW_ALL_USERS, UserRole.LIBRARIAN);
        unauthorizedCommands = new HashSet<>();
        unauthorizedCommands.add(CommandConstants.SEARCH_BOOK);
        unauthorizedCommands.add(CommandConstants.SHOW_ALL_BOOKS);
        unauthorizedCommands.add(CommandConstants.SHOW_SORTED_BOOKS);
        unauthorizedCommands.add(CommandConstants.SET_LANG);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String command = request.getParameter("command");
        User user = (User) request.getSession().getAttribute("user");
        if (user == null && !unauthorizedCommands.contains(command)) {
            request.getRequestDispatcher("log_in?command=log_in").forward(request,response);
        }
        if (user != null && specialCommands.containsKey(command) && !specialCommands.get(command).equals(user.getRole()))
            request.getRequestDispatcher("/WEB-INF/views/fail.jsp").forward(request,response);
        filterChain.doFilter(servletRequest,servletResponse);
    }



    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
