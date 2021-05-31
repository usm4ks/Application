package com.my.filters;

import org.junit.Test;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;
public class EncodingFilterTest {


    @Test
    public void doFilter() throws ServletException, IOException {
        EncodingFilter encodingFilter = new EncodingFilter();
        ServletRequest servletRequest = mock(ServletRequest.class);
        ServletResponse servletResponse = mock(ServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        encodingFilter.doFilter(servletRequest,servletResponse,filterChain);
        verify(servletRequest,times(1)).setCharacterEncoding("UTF-8");
        verify(servletResponse,times(1)).setCharacterEncoding("UTF-8");
        verify(filterChain,times(1)).doFilter(servletRequest,servletResponse);
    }

}