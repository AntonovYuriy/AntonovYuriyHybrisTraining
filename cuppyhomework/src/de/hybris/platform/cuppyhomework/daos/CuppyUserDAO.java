package de.hybris.platform.cuppyhomework.daos;

import de.hybris.platform.cuppyhomework.model.CuppyUserModel;

import java.util.List;

public interface CuppyUserDAO {

    CuppyUserModel getCuppyUserByUserName(String userName);

    List<CuppyUserModel> getAllCuppyUsers();
}
