package com.my.filters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LocaleFilterTest {

    @Mock
    FilterChain filterChain;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;


    @Test
    public void doFilterShouldSetAttribute() throws ServletException, IOException {
        //given
        LocaleFilter localeFilter = new LocaleFilter();

        //when
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("lang")).thenReturn(null);

        //then
        localeFilter.doFilter(request,response,filterChain);
        verify(session,times(1)).setAttribute("lang","en");
    }

    @Test
    public void doFilter() throws ServletException, IOException {
        //given
        LocaleFilter localeFilter = new LocaleFilter();

        //when
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("lang")).thenReturn("en");

        //then
        localeFilter.doFilter(request,response,filterChain);
        verify(filterChain,times(1)).doFilter(request,response);
    }

}