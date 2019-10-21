package de.hybris.platform.cuppyhomework.facades.impl;

import de.hybris.platform.cuppy.model.MatchModel;
import de.hybris.platform.cuppy.services.impl.DefaultMatchService;
import de.hybris.platform.cuppy.web.data.MatchData;
import de.hybris.platform.cuppyhomework.services.impl.SpecialMatchService;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.List;

public class SpecialMatchFacade extends DefaultMatchService {

    private SpecialMatchService specialMatchService;

    public List<MatchData> findMatches() {
        List<MatchModel> matchModelList = specialMatchService.findMatches();
        List<MatchData> matchDataList = new ArrayList<MatchData>();
        matchModelList.forEach(matchModel -> {
                    MatchData m = new MatchData();
                    m.setDate(matchModel.getDate());
                    m.setGuestTeam(matchModel.getGuestTeam().getName());
                    m.setHomeTeam(matchModel.getHomeTeam().getName());
                    m.setGuestGoals(matchModel.getGuestGoals());
                    m.setHomeGoals(matchModel.getHomeGoals());
                    m.setId(matchModel.getId());
                    matchDataList.add(m);
                }
        );
        return matchDataList;
    }

    public List<MatchData> findMatchesWithSpecial() {
        List<MatchModel> matchModelList = specialMatchService.findMatchesWithSpecial();
        List<MatchData> matchDataList = new ArrayList<>();
        matchModelList.forEach(matchModel -> {
                    MatchData m = new MatchData();
                    m.setDate(matchModel.getDate());
                    m.setGuestTeam(matchModel.getGuestTeam().getName());
                    m.setHomeTeam(matchModel.getHomeTeam().getName());
                    m.setGuestGoals(matchModel.getGuestGoals());
                    m.setHomeGoals(matchModel.getHomeGoals());
                    m.setId(matchModel.getId());
                    matchDataList.add(m);
                }
        );
        return matchDataList;
    }

    @Required
    public void setSpecialMatchService(final SpecialMatchService specialMatchService) {
        this.specialMatchService = specialMatchService;
    }
}
