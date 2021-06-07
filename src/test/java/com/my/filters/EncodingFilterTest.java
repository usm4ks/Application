package com.my.filters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EncodingFilterTest {

    @Mock
    FilterChain filterChain;
    @Mock
    ServletRequest servletRequest;
    @Mock
    ServletResponse servletResponse;


    @Test
    public void doFilter() throws ServletException, IOException {
        //given
        EncodingFilter encodingFilter = new EncodingFilter();

        //then
        encodingFilter.doFilter(servletRequest,servletResponse,filterChain);
        verify(servletRequest,times(1)).setCharacterEncoding("UTF-8");
        verify(servletResponse,times(1)).setCharacterEncoding("UTF-8");
        verify(filterChain,times(1)).doFilter(servletRequest,servletResponse);
    }

}