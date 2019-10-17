package de.hybris.platform.cuppyhomework.facades;

import de.hybris.platform.cuppyhomework.data.CuppyUserData;

import java.util.List;

public interface CuppyUserFacade {

    CuppyUserData getCuppyUserByUserName (String userName);

    List<CuppyUserData> getAllCuppyUsers();
}
