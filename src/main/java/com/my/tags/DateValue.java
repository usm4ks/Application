package com.my.tags;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateValue extends TagSupport {

    private static final Logger LOGGER = Logger.getLogger(DateValue.class);
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @Override
    public int doStartTag() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,7);
        JspWriter out=pageContext.getOut();
        try{
            out.print(dateFormat.format(calendar.getTime()));
        }catch(IOException e){
            LOGGER.error("Tag exception",e);
        }
        return SKIP_BODY;
    }
}
