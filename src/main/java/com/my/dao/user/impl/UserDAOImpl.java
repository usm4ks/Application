package com.my.dao.user.impl;

import com.my.dao.user.UserDAO;
import com.my.db.DBConnector;
import com.my.entities.User;
import com.my.exception.ApplicationException;
import com.my.services.InstanceService;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private final static String INSERT_NEW_USER = "INSERT INTO users(email, password, first_name, last_name) VALUES (?,?,?,?)";
    private final static String SELECT_ALL_USERS_BY_EMAIL = "SELECT * FROM users WHERE email=?";
    private final static String SELECT_USERS_BY_ID = "SELECT * FROM users WHERE id=?";
    private final static String SELECT_ALL_USERS_BY_ROLE = "SELECT * FROM users WHERE role=?";
    private final static String CHANGE_BLOCK_STATUS_USER_BY_ID = "UPDATE users SET blocked=? WHERE id=?";
    private final static String CHANGE_ROLE_USER_BY_ID = "UPDATE users SET role=? WHERE id=?";

    private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class);

    private DBConnector dbConnector;

    public void setDBConnector(DBConnector dbConnector){
        this.dbConnector = dbConnector;
    }

    @Override
    public void registrationUser(User user) throws ApplicationException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            con = dbConnector.getConnection();
            con.setAutoCommit(false);
            pst = con.prepareStatement(INSERT_NEW_USER, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1,user.getEmail());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getFirstName());
            pst.setString(4,user.getLastName());
            pst.executeUpdate();
            rs = pst.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
            con.commit();
        } catch (SQLException e) {
            LOGGER.error("registrationUser() error",e);
            rollBack(con);
            throw new ApplicationException("Can't registration user",e);
        } finally {
            dbConnector.close(rs,pst,con);
        }
    }

    @Override
    public User getUserByEmail(String email) throws ApplicationException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        User user = null;
        try {
            con = dbConnector.getConnection();
            pst = con.prepareStatement(SELECT_ALL_USERS_BY_EMAIL);
            pst.setString(1,email);
            rs = pst.executeQuery();
            if (rs.next()){
                user = InstanceService.buildUser(rs,false);
            }
        } catch (SQLException e) {
            LOGGER.error("getUserByEmail() error",e);
            throw new ApplicationException("Can't get user by email",e);
        } finally {
            dbConnector.close(rs,pst,con);
        }
        return user;
    }

    public User getUserById(int id) throws ApplicationException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        User user = null;
        try {
            con = dbConnector.getConnection();
            pst = con.prepareStatement(SELECT_USERS_BY_ID);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            if (rs.next()){
                user = InstanceService.buildUser(rs,false);
            }
        } catch (SQLException e) {
            LOGGER.error("getUserById() error",e);
            throw new ApplicationException("Can't get user by id",e);
        }finally {
           dbConnector.close(rs,pst,con);
        }
        return user;
    }

    @Override
    public List<User> getAllUsersByRole(String role) throws ApplicationException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<>();
        try{
            con = dbConnector.getConnection();
            pst = con.prepareStatement(SELECT_ALL_USERS_BY_ROLE);
            pst.setString(1,role);
            rs = pst.executeQuery();
            while (rs.next()){
                userList.add(InstanceService.buildUser(rs,false));
            }
        } catch (SQLException e) {
            LOGGER.error("getAllUsersByRole() error",e);
            throw new ApplicationException("Can't get all users by role",e);
        }finally {
            dbConnector.close(rs,pst,con);
        }
        return userList;
    }


    public void changeBlockStatusUserById(int userId,int status) throws ApplicationException {
        Connection con = null;
        PreparedStatement pst = null;
        try{
            con = dbConnector.getConnection();
            con.setAutoCommit(false);
            pst = con.prepareStatement(CHANGE_BLOCK_STATUS_USER_BY_ID);
            pst.setInt(1,status);
            pst.setInt(2,userId);
            pst.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            LOGGER.error("changeBlockStatusUserById() error",e);
            rollBack(con);
            throw new ApplicationException("Can't change user block status",e);
        }finally {
            dbConnector.close(pst,con);
        }
    }

    public void changeRoleUserById(int userId,String role) throws ApplicationException {
        Connection con = null;
        PreparedStatement pst = null;
        try{
            con = dbConnector.getConnection();
            con.setAutoCommit(false);
            pst = con.prepareStatement(CHANGE_ROLE_USER_BY_ID);
            pst.setString(1,role);
            pst.setInt(2,userId);
            pst.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            LOGGER.error("changeRoleUserById() error",e);
            rollBack(con);
            throw new ApplicationException("Can't change user role",e);
        }finally {
            dbConnector.close(pst,con);
        }
    }


    private void rollBack(Connection connection){
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
