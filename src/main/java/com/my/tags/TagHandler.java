package com.my.tags;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class TagHandler extends TagSupport{

    private static final Logger LOGGER = Logger.getLogger(TagHandler.class);

    private boolean status;

    public void setStatus(boolean status){
        this.status = status;
    }



    public int doStartTag() {

        JspWriter out=pageContext.getOut();
        try{
            if (status){
                out.print("yes");
            } else{
                out.print("no");
            }
        }catch(Exception e){
            LOGGER.error("Tag exception",e);
        }
        return SKIP_BODY;
    }
}