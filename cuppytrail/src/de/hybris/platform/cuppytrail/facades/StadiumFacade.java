package de.hybris.platform.cuppytrail.facades;

import de.hybris.platform.core.PK;
import de.hybris.platform.cuppytrail.data.StadiumData;

import java.util.List;


public interface StadiumFacade
{
    StadiumData getStadium(String name);

    List<StadiumData> getStadiums();

    Boolean deleteAllStadiumsInFacade();

    Boolean deleteOneStadiumByNameInFacade (String name);

    Boolean deleteOneStadiumByPKInFacade (PK pk);

}