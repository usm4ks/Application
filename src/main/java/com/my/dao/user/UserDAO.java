package com.my.dao.user;

import com.my.db.DBConnector;
import com.my.entities.User;
import com.my.exception.ApplicationException;

import java.util.List;

public interface UserDAO {

    void setDBConnector(DBConnector dbConnector);
    void registrationUser(User user) throws ApplicationException;
    User getUserByEmail(String email) throws ApplicationException;
    User getUserById(int id) throws ApplicationException;
    List<User> getAllUsersByRole(String role) throws ApplicationException;
    void changeBlockStatusUserById(int userId,int status) throws ApplicationException;
    void changeRoleUserById(int userId,String role) throws ApplicationException;
}
