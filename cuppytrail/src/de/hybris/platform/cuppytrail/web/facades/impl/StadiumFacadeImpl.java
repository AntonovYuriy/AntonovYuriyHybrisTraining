package de.hybris.platform.cuppytrail.web.facades.impl;

import de.hybris.platform.cuppytrail.web.data.StadiumDataTO;
import de.hybris.platform.cuppytrail.web.facades.StadiumFacade;

import java.util.ArrayList;
import java.util.List;


public class StadiumFacadeImpl implements StadiumFacade
{

    @Override
    public StadiumDataTO getStadiumDetails(final String name)
    {
        return new StadiumDataTO("Wembley", Integer.valueOf(12345));
    }

    @Override
    public List<StadiumDataTO> getAllStadium()
    {
        final List<StadiumDataTO> dataTOs = new ArrayList<StadiumDataTO>();
        dataTOs.add(new StadiumDataTO("Wembley", Integer.valueOf(12345)));
        return dataTOs;
    }

}