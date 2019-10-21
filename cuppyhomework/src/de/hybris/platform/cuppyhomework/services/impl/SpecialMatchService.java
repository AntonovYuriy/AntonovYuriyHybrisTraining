package de.hybris.platform.cuppyhomework.services.impl;

import de.hybris.platform.cuppy.model.MatchModel;
import de.hybris.platform.cuppy.services.impl.DefaultMatchService;
import de.hybris.platform.cuppyhomework.daos.impl.SpecialMatchDAO;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

public class SpecialMatchService extends DefaultMatchService {

    private SpecialMatchDAO specialMatchDAO;

    public List<MatchModel> findMatchesWithSpecial() {
        return specialMatchDAO.findMatchesWithSpecial();
    }

    public List<MatchModel> findMatches() {
        return specialMatchDAO.findMatches();
    }

    @Required
    public void setSpecialMatchDAO(final SpecialMatchDAO specialMatchDAO) {
        this.specialMatchDAO = specialMatchDAO;
    }
}
