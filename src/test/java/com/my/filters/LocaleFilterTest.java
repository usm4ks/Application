package com.my.filters;

import org.junit.Test;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class LocaleFilterTest {


    @Test
    public void doFilterShouldSetAttribute() throws ServletException, IOException {
        LocaleFilter localeFilter = new LocaleFilter();
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        FilterChain filterChain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("lang")).thenReturn(null);
        localeFilter.doFilter(request,response,filterChain);
        verify(session,times(1)).setAttribute("lang","en");
    }

    @Test
    public void doFilter() throws ServletException, IOException {
        LocaleFilter localeFilter = new LocaleFilter();
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        FilterChain filterChain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("lang")).thenReturn("en");
        localeFilter.doFilter(request,response,filterChain);
        verify(filterChain,times(1)).doFilter(request,response);
    }

}