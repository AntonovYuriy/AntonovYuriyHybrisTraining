package de.hybris.platform.cuppyhomework.daos.impl;

import de.hybris.platform.cuppy.model.MatchModel;
import de.hybris.platform.cuppy.web.data.MatchData;
import de.hybris.platform.cuppyhomework.facades.impl.SpecialMatchFacade;
import de.hybris.platform.cuppyhomework.services.impl.SpecialMatchService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class SpecialMatchFacadeUnitTest {

    private SpecialMatchFacade specialMatchFacade;
    private SpecialMatchService specialMatchService;

    private static final int MATCHDAY = 12345;
    private static final boolean MATCHSPECIAL = true;

    private List<MatchModel> temporaryModels() {
        final MatchModel model = new MatchModel();
        model.setSpecial(MATCHSPECIAL);
        model.setMatchday(MATCHDAY);
        model.setId(123);
        final List<MatchModel> madelist = new ArrayList<MatchModel>();
        madelist.add(model);
        return madelist;
    }

    @Test
    public void testGettingAllMatches() {
        final List<MatchModel> matchModels = temporaryModels();
        when(specialMatchService.findMatches()).thenReturn(matchModels);
        final List<MatchData> resultingData = specialMatchFacade.findMatches();
        assertNotNull(resultingData);
        assertEquals(resultingData.size(), matchModels.size());
        assertNotEquals(resultingData.get(0).getMatchday(), matchModels.get(0).getMatchday());
    }
}
