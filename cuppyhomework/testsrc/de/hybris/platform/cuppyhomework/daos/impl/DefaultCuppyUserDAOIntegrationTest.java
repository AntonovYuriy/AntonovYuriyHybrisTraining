package de.hybris.platform.cuppyhomework.daos.impl;


import de.hybris.platform.cuppyhomework.daos.CuppyUserDAO;
import de.hybris.platform.cuppyhomework.model.CuppyUserModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.assertTrue;

public class DefaultCuppyUserDAOIntegrationTest extends ServicelayerTransactionalTest {

    private final String REALLY_EXISTING_NAME = "Vasya";
    private final String NON_EXISTING_NAME = "sldgmskldjgsd;fkljsdkljsd;klj";


    @Resource
    private CuppyUserDAO cuppyUserDAO;

    @Resource
    private ModelService modelService;

    @Test(expected = Exception.class)
    public void cuppyUserDAOTestWithNullUsername() {
        CuppyUserModel cuppyUserModel = cuppyUserDAO.getCuppyUserByUserName(null);
    }

    @Test
    public void cuppyUserDAOTestWithRealName() {
        CuppyUserModel cuppyUserModel = cuppyUserDAO.getCuppyUserByUserName(REALLY_EXISTING_NAME);
        assertTrue("Name of user have to return model with the same name", cuppyUserModel.getName().equals(REALLY_EXISTING_NAME));
    }

    @Test
    public void cuppyUserDAOTestWithNotRealName() {
        CuppyUserModel cuppyUserModel = cuppyUserDAO.getCuppyUserByUserName(NON_EXISTING_NAME);
        assertTrue("Name of user have to return model with the same name", cuppyUserModel==null);
    }
}
