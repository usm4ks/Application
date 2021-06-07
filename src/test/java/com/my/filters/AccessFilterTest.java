package com.my.filters;

import com.my.command.CommandConstants;
import com.my.entities.User;
import com.my.enums.UserRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccessFilterTest {

    @Mock
    FilterChain filterChain;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    RequestDispatcher requestDispatcher;
    @Mock
    FilterConfig filterConfig;
    @Mock
    User user;

    @Test
    public void doFilterUserNull() throws ServletException, IOException {
        //given
        AccessFilter accessFilter = new AccessFilter();

        //when
        when(request.getParameter("command")).thenReturn(CommandConstants.ACCOUNT);
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("user")).thenReturn(null);
        when(request.getRequestDispatcher("log_in?command=log_in")).thenReturn(requestDispatcher);

        //then
        accessFilter.init(filterConfig);
        accessFilter.doFilter(request, response, filterChain);
        verify(request.getRequestDispatcher("log_in?command=log_in"), times(1)).forward(request, response);

    }

    @Test
    public void doFilterUserCantDoCommand() throws ServletException, IOException {
        //given
        AccessFilter accessFilter = new AccessFilter();

        //when
        when(request.getParameter("command")).thenReturn(CommandConstants.ADD_LIBRARIAN);
        when(request.getSession()).thenReturn(session);
        when(user.getRole()).thenReturn(UserRole.USER);
        when(request.getSession().getAttribute("user")).thenReturn(user);
        when(request.getRequestDispatcher("/WEB-INF/views/fail.jsp")).thenReturn(requestDispatcher);

        //then
        accessFilter.init(filterConfig);
        accessFilter.doFilter(request, response, filterChain);
        verify(request.getRequestDispatcher("/WEB-INF/views/fail.jsp"), times(1)).forward(request, response);
    }

    @Test
    public void doFilterUserCanDoCommand() throws ServletException, IOException {
        //given
        AccessFilter accessFilter = new AccessFilter();

        //when
        when(request.getParameter("command")).thenReturn(CommandConstants.ADD_LIBRARIAN);
        when(request.getSession()).thenReturn(session);
        when(user.getRole()).thenReturn(UserRole.ADMIN);
        when(request.getSession().getAttribute("user")).thenReturn(user);

        //then
        accessFilter.init(filterConfig);
        accessFilter.doFilter(request, response, filterChain);
        verify(filterChain, times(1)).doFilter(request,response);

    }

}