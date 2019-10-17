package de.hybris.platform.cuppyhomework.services;

import de.hybris.platform.cuppyhomework.model.CuppyUserModel;

import java.util.List;

public interface CuppyUserService {

    CuppyUserModel getCuppyUserByUserName (String userName);

    List<CuppyUserModel> getAllCuppyUsers ();

    void addNewUser(String name, String username, String phone, String password);
}
