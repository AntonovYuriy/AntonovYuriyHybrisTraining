package de.hybris.platform.cuppyhomework.daos.impl;

import de.hybris.platform.cuppyhomework.model.CuppyUserModel;
import de.hybris.platform.cuppyhomework.daos.CuppyUserDAO;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "cuppyUserDAO")
public class DefaultCuppyUserDAO implements CuppyUserDAO {

    private final static Logger LOG = Logger.getLogger(DefaultCuppyUserDAO.class);

    @Autowired
    private FlexibleSearchService flexibleSearchService;

    @Override
    public CuppyUserModel getCuppyUserByUserName(String userName) {
        final String queryString = //
                "SELECT {p:" + CuppyUserModel.PK + "}"
                        + "FROM {" + CuppyUserModel._TYPECODE + " AS p} "
                        + "WHERE " + "{p:" + CuppyUserModel.USERNAME + "}=?userName";

        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        query.addQueryParameter("userName", userName);
        SearchResult<CuppyUserModel> searchResult = flexibleSearchService.<CuppyUserModel>search(query);
        LOG.info("GETTING EXACT USER BY NAME IN DAO");
        return searchResult.getResult().iterator().next();
    }

    @Override
    public List<CuppyUserModel> getAllCuppyUsers() {
        final String queryString = "SELECT {" + CuppyUserModel.PK + "} FROM {" + CuppyUserModel._TYPECODE + "}";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        LOG.info("GETTING LIST OF ALL USERS IN DAO");
        return flexibleSearchService.<CuppyUserModel>search(query).getResult();
    }


}
