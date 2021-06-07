package com.my.command.user.info;

import com.my.dao.DAOFactory;
import com.my.dao.user.impl.UserDAOImpl;
import com.my.enums.UserRole;
import com.my.exception.ApplicationException;
import com.my.exception.CommandException;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShowAllUsersCommandTest {

    @Test
    public void executeShouldReturnPath() throws CommandException,ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        ShowAllUsersCommand showAllUsersCommand = new ShowAllUsersCommand(daoFactory.getUserDAO());
        when(daoFactory.getUserDAO()).thenReturn(mock(UserDAOImpl.class));
        when(daoFactory.getUserDAO().getAllUsersByRole(UserRole.USER.getRoleName())).thenReturn(new ArrayList<>());
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Assert.assertEquals("/WEB-INF/views/user_list.jsp",showAllUsersCommand.execute(request,response));
    }
    @Test(expected = ApplicationException.class)
    public void executeShouldThrowException() throws CommandException,ApplicationException {
        DAOFactory daoFactory = mock(DAOFactory.class);
        ShowAllUsersCommand showAllUsersCommand = new ShowAllUsersCommand(daoFactory.getUserDAO());
        when(daoFactory.getUserDAO()).thenReturn(mock(UserDAOImpl.class));
        when(daoFactory.getUserDAO().getAllUsersByRole(UserRole.USER.getRoleName())).thenThrow(ApplicationException.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        showAllUsersCommand.execute(request,response);
    }

}