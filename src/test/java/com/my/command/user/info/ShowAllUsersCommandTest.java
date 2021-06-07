package com.my.command.user.info;

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
public class ShowAllUsersCommandTest {

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
        ShowAllUsersCommand showAllUsersCommand = new ShowAllUsersCommand(userDAO);

        //when
        when(userDAO.getAllUsersByRole(UserRole.USER.getRoleName())).thenReturn(new ArrayList<>());

        //then
        assertEquals("/WEB-INF/views/user_list.jsp",showAllUsersCommand.execute(request,response));
    }
    @Test(expected = CommandException.class)
    public void executeShouldThrowException() throws CommandException,ApplicationException {
        //given
        ShowAllUsersCommand showAllUsersCommand = new ShowAllUsersCommand(userDAO);

        //when
        when(userDAO.getAllUsersByRole(UserRole.USER.getRoleName())).thenThrow(applicationException);

        //then
        showAllUsersCommand.execute(request,response);
    }

}