package de.hybris.platform.cuppytrail.facades;

import de.hybris.platform.core.PK;
import de.hybris.platform.cuppytrail.data.StadiumData;

import java.util.List;


public interface StadiumFacade
{
    StadiumData getStadium(String name);

    StadiumData getStadium(String name, String format);

    List<StadiumData> getStadiums();

    List<StadiumData> getStadiums(String format);

    void deleteAllStadiumsInFacade();

    void deleteOneStadiumByNameInFacade (String name);

    void addNewStadium(String name, int capacity);
}