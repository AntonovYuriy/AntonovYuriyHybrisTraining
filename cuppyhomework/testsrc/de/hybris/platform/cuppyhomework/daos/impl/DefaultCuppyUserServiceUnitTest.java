package de.hybris.platform.cuppyhomework.daos.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.cuppyhomework.model.CuppyUserModel;
import de.hybris.platform.cuppyhomework.services.impl.DefaultCuppyUserService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@UnitTest
public class DefaultCuppyUserServiceUnitTest {

    private static final String CUPPY_USERNAME = "Vasya";

    private DefaultCuppyUserService defaultCuppyUserService;
    private DefaultCuppyUserDAO defaultCuppyUserDAO;
    private CuppyUserModel cuppyUserModel;


    @Before
    public void setUp() {
        defaultCuppyUserService = new DefaultCuppyUserService();
        defaultCuppyUserDAO = mock(DefaultCuppyUserDAO.class);
        defaultCuppyUserService.setDefaultCuppyUserDAO(defaultCuppyUserDAO);

        cuppyUserModel = new CuppyUserModel();
        cuppyUserModel.setName("Oleg");
        cuppyUserModel.setUsername("Vasya");
    }

    @Test
    public void testGetCuppyUserByUserName() {
        when(defaultCuppyUserDAO.getCuppyUserByUserName(CUPPY_USERNAME)).thenReturn(cuppyUserModel);
        final CuppyUserModel result = defaultCuppyUserService.getCuppyUserByUserName(CUPPY_USERNAME);
        assertEquals("We have to get the same mocked object", result, cuppyUserModel);
    }

    @Test
    public void testGetAllCuppyUsers() {
        final List<CuppyUserModel> cuppyUserModels = Arrays.asList(cuppyUserModel);
        when(defaultCuppyUserDAO.getAllCuppyUsers()).thenReturn(cuppyUserModels);
        final List<CuppyUserModel> result = defaultCuppyUserService.getAllCuppyUsers();
        assertEquals("There must be only 1 DAO", 1, result.size());
        assertEquals("The first one has to be the one we have", cuppyUserModel, result.get(0));
    }
}