package de.hybris.platform.cuppyhomework.daos.impl;

import de.hybris.platform.cuppy.daos.impl.DefaultMatchDao;
import de.hybris.platform.cuppy.model.MatchModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SpecialMatchDAO extends DefaultMatchDao {

    @Autowired
    private FlexibleSearchService flexibleSearchService;


    public List<MatchModel> findMatchesWithSpecial() {
        final String queryString = //
                "SELECT {p:" + MatchModel.PK + "} "//
                        + "FROM {" + MatchModel._TYPECODE + " AS p} "
                        + "WHERE {" + MatchModel.SPECIAL + "} = true";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        return flexibleSearchService.<MatchModel>search(query).getResult();
    }

    public List<MatchModel> findMatches() {
        final String queryString = //
                "SELECT {" + MatchModel.PK + "}"
                        + "FROM {" + MatchModel._TYPECODE + "}";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        return flexibleSearchService.<MatchModel>search(query).getResult();
    }
}
