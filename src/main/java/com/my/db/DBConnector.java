package com.my.db;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class DBConnector {

    private static final Logger LOGGER = Logger.getLogger(DBConnector.class);
    private static final String CLOSE_ERROR = "close() error";


    private DBConnector(){
    }

    private static DBConnector instance = null;

    public static DBConnector getInstance(){
        if (instance==null)
            instance = new DBConnector();
        return instance;
    }

    public Connection getConnection(){
        Context ctx;
        Connection c;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/TestDB");
            c = ds.getConnection();
        } catch (NamingException | SQLException e) {
            LOGGER.error("getConnection() error",e);
            throw new RuntimeException();
        }
        return c;
    }

    public void close(PreparedStatement pst, Connection con) {
        try {
            if (pst != null)
                pst.close();
                close(con);
        } catch (SQLException e) {
            LOGGER.error(CLOSE_ERROR,e);
        }

    }
    public void close(ResultSet rs, PreparedStatement pst, Connection con) {
        try {
            if (rs != null)
                rs.close();
            close(pst, con);
        } catch (SQLException e) {
            LOGGER.error(CLOSE_ERROR,e);
        }

    }
    public void close(Statement st, Connection con) {
        try {
            if (st != null)
                st.close();
            close(con);
        } catch (SQLException e) {
            LOGGER.error(CLOSE_ERROR,e);
        }
    }
    public void close(ResultSet rs, Statement st, Connection con) {
        try {
            if (rs != null)
                rs.close();
            close(st, con);
        } catch (SQLException e) {
            LOGGER.error(CLOSE_ERROR,e);
        }
    }

    public void close(Connection con){
        try {
            con.close();
        } catch (SQLException e) {
            LOGGER.error(CLOSE_ERROR,e);
        }
    }
}
