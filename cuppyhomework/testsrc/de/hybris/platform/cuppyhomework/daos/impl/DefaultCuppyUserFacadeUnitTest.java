package de.hybris.platform.cuppyhomework.daos.impl;


import de.hybris.platform.cuppyhomework.data.CuppyUserData;
import de.hybris.platform.cuppyhomework.facades.impl.DefaultCuppyUserFacade;
import de.hybris.platform.cuppyhomework.model.CuppyUserModel;
import de.hybris.platform.cuppyhomework.services.impl.DefaultCuppyUserService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class DefaultCuppyUserFacadeUnitTest {


    private static final String CUPPY_USER_NAME = "Vasya";
    private static final String CUPPY_USER_PASSWORD = "1234556789";

    private DefaultCuppyUserService defaulCuppyUserService;
    private DefaultCuppyUserFacade defaultCuppyUserFacade;
    private CuppyUserData cuppyUserData;

    private List<CuppyUserModel> modelList() {
        final CuppyUserModel model = new CuppyUserModel();
        model.setUsername(CUPPY_USER_NAME);
        model.setPassword(CUPPY_USER_PASSWORD);
        final List<CuppyUserModel> listForResult = new ArrayList<CuppyUserModel>();
        listForResult.add(model);
        return listForResult;
    }

    @Before
    public void setUp() {
        DefaultCuppyUserFacade defaultCuppyUserFacade = new DefaultCuppyUserFacade();
        defaulCuppyUserService = mock(DefaultCuppyUserService.class);
        defaultCuppyUserFacade.setDefaultCuppyUserService(defaulCuppyUserService);

        cuppyUserData = new CuppyUserData();
        cuppyUserData.setPassword(CUPPY_USER_PASSWORD);
        cuppyUserData.setUsername(CUPPY_USER_NAME);
    }

    @Test
    public void TestGettingAllCuppyUsers() {
        final List<CuppyUserModel> userModels = modelList();
        when(defaulCuppyUserService.getAllCuppyUsers()).thenReturn(userModels);
        final List<CuppyUserData> testingResult = defaultCuppyUserFacade.getAllCuppyUsers();
        assertNotNull(testingResult);
        assertEquals(testingResult.size(), userModels.size());
        assertEquals(testingResult.get(0).getUsername(), userModels.get(0).getUsername());
        assertEquals(testingResult.get(0).getPassword(), userModels.get(0).getPassword());
    }

    @Test
    public void TestGetCuppyUserByName() {
        final List<CuppyUserModel> userModels = modelList();
        when(defaultCuppyUserFacade.getCuppyUserByUserName(CUPPY_USER_NAME)).thenReturn(cuppyUserData);
        assertEquals(CUPPY_USER_NAME, userModels.get(0).getUsername());
        assertEquals(CUPPY_USER_PASSWORD, userModels.get(0).getPassword());
    }

}
