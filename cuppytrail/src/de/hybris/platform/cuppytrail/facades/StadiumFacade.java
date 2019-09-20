package de.hybris.platform.cuppytrail.facades;

import de.hybris.platform.core.PK;
import de.hybris.platform.cuppytrail.data.StadiumData;

import java.util.List;


public interface StadiumFacade
{
    StadiumData getStadium(String name);

    List<StadiumData> getStadiums();

    void deleteAllStadiumsInFacade();

    void deleteOneStadiumByNameInFacade (String name);
}