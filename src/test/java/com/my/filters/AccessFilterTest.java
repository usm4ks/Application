package com.my.filters;

import com.my.command.CommandConstants;
import com.my.entities.User;
import com.my.enums.UserRole;
import org.junit.Test;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class AccessFilterTest {

    @Test
    public void doFilterUserNull() throws ServletException, IOException {
        AccessFilter accessFilter = new AccessFilter();
        FilterChain filterChain = mock(FilterChain.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("command")).thenReturn(CommandConstants.ACCOUNT);
        when(request.getSession()).thenReturn(mock(HttpSession.class));
        when(request.getSession().getAttribute("user")).thenReturn(null);
        when(request.getRequestDispatcher("log_in?command=log_in")).thenReturn(mock(RequestDispatcher.class));
        accessFilter.init(mock(FilterConfig.class));
        accessFilter.doFilter(request, response, filterChain);
        verify(request.getRequestDispatcher("log_in?command=log_in"), times(1)).forward(request, response);

    }

    @Test
    public void doFilterUserCantDoCommand() throws ServletException, IOException {
        AccessFilter accessFilter = new AccessFilter();
        FilterChain filterChain = mock(FilterChain.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("command")).thenReturn(CommandConstants.ADD_LIBRARIAN);
        when(request.getSession()).thenReturn(mock(HttpSession.class));
        User user = mock(User.class);
        when(user.getRole()).thenReturn(UserRole.USER);
        when(request.getSession().getAttribute("user")).thenReturn(user);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("book_list?command=show_all_books")).thenReturn(requestDispatcher);
        accessFilter.init(mock(FilterConfig.class));
        accessFilter.doFilter(request, response, filterChain);
        verify(request.getRequestDispatcher("book_list?command=show_all_books"), times(1)).forward(request, response);
    }

    @Test
    public void doFilterUserCanDoCommand() throws ServletException, IOException {
        AccessFilter accessFilter = new AccessFilter();
        FilterChain filterChain = mock(FilterChain.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("command")).thenReturn(CommandConstants.ADD_LIBRARIAN);
        when(request.getSession()).thenReturn(mock(HttpSession.class));
        User user = mock(User.class);
        when(user.getRole()).thenReturn(UserRole.ADMIN);
        when(request.getSession().getAttribute("user")).thenReturn(user);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        accessFilter.init(mock(FilterConfig.class));
        accessFilter.doFilter(request, response, filterChain);
        verify(filterChain, times(1)).doFilter(request,response);

    }

}