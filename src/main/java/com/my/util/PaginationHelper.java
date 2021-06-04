package com.my.util;

import com.my.exception.ApplicationException;

import javax.servlet.http.HttpServletRequest;

public class PaginationHelper {


    private PaginationHelper(){}

    public static int getPage(HttpServletRequest request) throws ApplicationException {
        String pageNum = request.getParameter("page");
        int page = 1;
        if (pageNum != null){
            try {
                page = Integer.parseInt(pageNum);
            }catch (NumberFormatException exception){
                throw new ApplicationException("Incorrect data",exception);
            }
        }
        return page;
    }
}
