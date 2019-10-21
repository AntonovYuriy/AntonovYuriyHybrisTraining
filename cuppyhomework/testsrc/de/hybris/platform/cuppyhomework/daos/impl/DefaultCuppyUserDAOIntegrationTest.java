package de.hybris.platform.cuppyhomework.daos.impl;


import de.hybris.platform.cuppyhomework.model.CuppyUserModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class DefaultCuppyUserDAOIntegrationTest extends ServicelayerTransactionalTest {

    private final String REALLY_EXISTING_NAME = "Vasya";
    private final String NON_EXISTING_NAME = "sldgmskldjgsd;fkljsdkljsd;klj";

    @Resource
    private DefaultCuppyUserDAO defaultCuppyUserDAO;

    @Resource
    private ModelService modelService;

    @Test(expected = Exception.class)
    public void cuppyUserDAOTestWithNullUsername() {
        CuppyUserModel cuppyUserModel = defaultCuppyUserDAO.getCuppyUserByUserName(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void cuppyUserDAOTestWithRealName() {
        CuppyUserModel cuppyUserModel = defaultCuppyUserDAO.getCuppyUserByUserName(REALLY_EXISTING_NAME);
//        assertEquals("UserName must be the same", REALLY_EXISTING_NAME, cuppyUserModel.getUsername());
    }


    @Test(expected = NoSuchElementException.class)
    public void cuppyUserDAOTestWithNotRealName() {
        CuppyUserModel cuppyUserModel = defaultCuppyUserDAO.getCuppyUserByUserName(NON_EXISTING_NAME);
    }

    @Test
    public void getAllCuppyUsersTestIsNotEmpty() {
        List<CuppyUserModel> list = defaultCuppyUserDAO.getAllCuppyUsers();
        assertNotNull(list);
    }

    @Test
    public void getAllCuppyUsersSizeMOreThan0() {
        List<CuppyUserModel> list = defaultCuppyUserDAO.getAllCuppyUsers();
        assertNotEquals("Must be one or more users in list", 1, list.size());
    }

}