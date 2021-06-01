package com.my.tags;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class BlockStatus extends TagSupport{

    private static final Logger LOGGER = Logger.getLogger(BlockStatus.class);

    private boolean status;

    public void setStatus(boolean status){ this.status = status; }



    public int doStartTag() {

        JspWriter out=pageContext.getOut();
        try{
            if (status){
                out.print("yes");
            } else{
                out.print("no");
            }
        }catch(IOException e){
            LOGGER.error("Tag exception",e);
        }
        return SKIP_BODY;
    }
}