package com.my.command.user.settings;


import com.my.dao.user.UserDAO;
import com.my.enums.UserRole;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UsersSettingsCommandTest {

    @Mock
    UserDAO userDAO;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    ApplicationException applicationException;


    @Test
    public void executeShouldReturnPath() throws CommandException,ApplicationException {
        //given
        UsersSettingsCommand usersSettingsCommand = new UsersSettingsCommand(userDAO);

        //when
        when(userDAO.getAllUsersByRole(UserRole.USER.getRoleName())).thenReturn(new ArrayList<>());

        //then
        assertEquals("/WEB-INF/views/users_settings.jsp",usersSettingsCommand.execute(request,response));
    }

    @Test(expected = CommandException.class)
    public void executeShouldThrowException() throws CommandException,ApplicationException {
        //given
        UsersSettingsCommand usersSettingsCommand = new UsersSettingsCommand(userDAO);

        //when
        when(userDAO.getAllUsersByRole(UserRole.USER.getRoleName())).thenThrow(applicationException);

        //then
        usersSettingsCommand.execute(request,response);
    }

}