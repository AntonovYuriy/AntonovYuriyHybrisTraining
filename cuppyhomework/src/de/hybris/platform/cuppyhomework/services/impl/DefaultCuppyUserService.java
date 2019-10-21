package de.hybris.platform.cuppyhomework.services.impl;

import de.hybris.platform.cuppyhomework.daos.impl.DefaultCuppyUserDAO;
import de.hybris.platform.cuppyhomework.model.CuppyUserModel;
import de.hybris.platform.cuppyhomework.services.CuppyUserService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.internal.model.impl.DefaultModelService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;
import java.util.Objects;

public class DefaultCuppyUserService implements CuppyUserService {

    private ModelService modelService;

    private DefaultCuppyUserDAO defaultCuppyUserDAO;

    @Override
    public CuppyUserModel getCuppyUserByUserName(String userName) {

        try {
            CuppyUserModel result = defaultCuppyUserDAO.getCuppyUserByUserName(userName);
            return result;
        } catch (Exception e) {
            throw new UnknownIdentifierException("Cuppy User with name '" + userName + "' not found!");
        }
    }

    @Override
    public List<CuppyUserModel> getAllCuppyUsers() {
        return defaultCuppyUserDAO.getAllCuppyUsers();
    }

    @Override
    public void addNewUser(String name, String username, String phone, String password) {
        if ((Objects.nonNull(name)) && (Objects.nonNull(username)) && (Objects.nonNull(phone)) && (Objects.nonNull(password))) {
            CuppyUserModel cuppyUserModel = new CuppyUserModel();
            cuppyUserModel.setUsername(username);
            cuppyUserModel.setName(name);
            cuppyUserModel.setPassword(password);
            cuppyUserModel.setPhone(phone);
            cuppyUserModel.setLogIn(new java.util.Date());
            modelService.save(cuppyUserModel);
        } else {
            throw new NullPointerException("Error in received from reg form data");
        }
    }

    public void deleteUserDaysOld(int daysLimitToDeleteUser) {
        defaultCuppyUserDAO.getAllCuppyUsers().forEach(cuppyUserModel -> {
            long diff = cuppyUserModel.getLogIn().getTime() - new java.util.Date().getTime();
            int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
            if (diffDays > daysLimitToDeleteUser) {
                modelService.remove(cuppyUserModel);
            }
        });
    }

    @Required
    public void setDefaultCuppyUserDAO(final DefaultCuppyUserDAO defaultCuppyUserDAO) {
        this.defaultCuppyUserDAO = defaultCuppyUserDAO;
    }

    public void setModelService(DefaultModelService modelService) {
        this.modelService = modelService;
    }
}
