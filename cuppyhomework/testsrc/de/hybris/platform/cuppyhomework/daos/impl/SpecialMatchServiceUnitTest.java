package de.hybris.platform.cuppyhomework.daos.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.cuppy.model.MatchModel;
import de.hybris.platform.cuppyhomework.services.impl.SpecialMatchService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@UnitTest
public class SpecialMatchServiceUnitTest {

    private SpecialMatchService specialMatchService;
    private SpecialMatchDAO specialMatchDAO;
    private MatchModel matchModel;


    @Before
    public void setUp() {
        specialMatchService = new SpecialMatchService();
        specialMatchDAO = mock(SpecialMatchDAO.class);
        specialMatchService.setSpecialMatchDAO(specialMatchDAO);
    }

    @Test
    public void testGetMatches() {
        final List<MatchModel> matchModels = Arrays.asList(matchModel);
        when(specialMatchService.findMatches()).thenReturn(matchModels);
        final List<MatchModel> result = specialMatchService.findMatches();
        assertEquals("We have to get the same mocked object", result.get(0), matchModel);
    }

    @Test
    public void testGetMatchesMoreThan0() {
        final List<MatchModel> matchModels = Arrays.asList(matchModel);
        when(specialMatchService.findMatches()).thenReturn(matchModels);
        final List<MatchModel> result = specialMatchService.findMatches();
        assertNotEquals("We have to get the same mocked object", result.size(), 0);
    }

    @Test
    public void testGetSpecialMatches() {
        final List<MatchModel> matchModels = Arrays.asList(matchModel);
        when(specialMatchService.findMatchesWithSpecial()).thenReturn(matchModels);
        final List<MatchModel> result = specialMatchService.findMatchesWithSpecial();
        assertEquals("We have to get the same mocked object", result.get(0), matchModel);
    }

}
