package com.my.command.user.settings;

import com.my.dao.DAOFactory;
import com.my.dao.user.impl.UserDAOImpl;
import com.my.enums.UserRole;
import com.my.exception.ApplicationException;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsersSettingsCommandTest {


    @Test
    public void executeShouldReturnPath() throws ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        UsersSettingsCommand usersSettingsCommand = new UsersSettingsCommand(daoFactory);
        when(daoFactory.getUserDAO()).thenReturn(mock(UserDAOImpl.class));
        when(daoFactory.getUserDAO().getAllUsersByRole(UserRole.USER.getRoleName())).thenReturn(new ArrayList<>());
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Assert.assertEquals("/WEB-INF/views/users_settings.jsp",usersSettingsCommand.execute(request,response));
    }

    @Test(expected = ApplicationException.class)
    public void executeShouldThrowException() throws ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        UsersSettingsCommand usersSettingsCommand = new UsersSettingsCommand(daoFactory);
        when(daoFactory.getUserDAO()).thenReturn(mock(UserDAOImpl.class));
        when(daoFactory.getUserDAO().getAllUsersByRole(UserRole.USER.getRoleName())).thenThrow(mock(ApplicationException.class));
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        usersSettingsCommand.execute(request,response);
    }

}