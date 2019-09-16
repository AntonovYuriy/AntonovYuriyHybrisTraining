/**
 * This class belongs to the Source Code Trail documented at https://wiki.hybris.com/display/pm/Source+Code+Tutorial
 */
package de.hybris.platform.cuppytrail.mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import de.hybris.platform.cuppytrail.web.data.StadiumDataTO;
import de.hybris.platform.cuppytrail.web.facades.StadiumFacade;

import java.util.List;

import de.hybris.platform.cuppytrail.web.facades.impl.StadiumFacadeImpl;
import org.junit.Before;
import org.junit.Test;


public class FacadeTDDWithMockito
{
    private StadiumFacade stadiumFacade;

    @Before
    public void setUp() {
        stadiumFacade = new StadiumFacadeImpl();
    }

    @Test
    public void testFacade1()
    {
        final List<StadiumDataTO> stadiumDataTOs = stadiumFacade.getAllStadium();
        assertEquals(1, stadiumDataTOs.size());
        final StadiumDataTO stadiumDataTO = stadiumFacade.getStadiumDetails("Wembley");
        assertNotNull(stadiumDataTO);
    }
}