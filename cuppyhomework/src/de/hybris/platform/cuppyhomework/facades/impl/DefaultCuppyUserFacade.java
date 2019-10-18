package de.hybris.platform.cuppyhomework.facades.impl;

import de.hybris.platform.cuppyhomework.data.CuppyUserData;
import de.hybris.platform.cuppyhomework.facades.CuppyUserFacade;
import de.hybris.platform.cuppyhomework.model.CuppyUserModel;
import de.hybris.platform.cuppyhomework.services.impl.DefaultCuppyUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.List;

public class DefaultCuppyUserFacade implements CuppyUserFacade {

    private DefaultCuppyUserService defaultCuppyUserService;
    private static final Logger LOG = Logger.getLogger(DefaultCuppyUserFacade.class);

    @Override
    public CuppyUserData getCuppyUserByUserName(String userName) {
        CuppyUserModel cuppyUserModel = null;
        if (userName != null) {
            cuppyUserModel = defaultCuppyUserService.getCuppyUserByUserName(userName);
            if (cuppyUserModel == null) {
                return null;
            }
        } else {
            throw new IllegalArgumentException("Cuppy user with name " + userName + " not found.");
        }

        final CuppyUserData cuppyUserData = new CuppyUserData();
        cuppyUserData.setName(cuppyUserModel.getName());
        cuppyUserData.setUsername(cuppyUserModel.getUsername());
        cuppyUserData.setPhone(cuppyUserModel.getPhone());
        cuppyUserData.setLogIn(cuppyUserModel.getLogIn());
        cuppyUserData.setPassword(cuppyUserModel.getPassword());
        return cuppyUserData;
    }

    @Override
    public List<CuppyUserData> getAllCuppyUsers() {
        final List<CuppyUserModel> cuppyUserModels = defaultCuppyUserService.getAllCuppyUsers();
        final List<CuppyUserData> cuppyUserFacadeData = new ArrayList<CuppyUserData>();
        for (final CuppyUserModel cumo : cuppyUserModels) {
            final CuppyUserData cuppyUserData = new CuppyUserData();
            cuppyUserData.setName(cumo.getName());
            cuppyUserData.setUsername(cumo.getUsername());
            cuppyUserData.setLogIn(cumo.getLogIn());
            cuppyUserData.setPhone(cumo.getPhone());
            cuppyUserData.setPassword(cumo.getPassword());
            cuppyUserFacadeData.add(cuppyUserData);
        }
        return cuppyUserFacadeData;
    }

    public void addNewUser(String name, String username, String phone, String password) {
        defaultCuppyUserService.addNewUser(name, username, phone, password);
    }

    public void deleteUserDaysOld(int daysLimitToDeleteUser) {
        defaultCuppyUserService.deleteUserDaysOld(daysLimitToDeleteUser);
    }

    @Required
    public void setDefaultCuppyUserService(final DefaultCuppyUserService defaultCuppyUserService) {
        this.defaultCuppyUserService = defaultCuppyUserService;
    }
}
