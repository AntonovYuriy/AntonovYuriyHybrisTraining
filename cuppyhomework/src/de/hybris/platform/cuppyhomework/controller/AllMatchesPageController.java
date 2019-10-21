package de.hybris.platform.cuppyhomework.controller;

import de.hybris.platform.cuppy.web.data.MatchData;
import de.hybris.platform.cuppyhomework.facades.impl.SpecialMatchFacade;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AllMatchesPageController {

    private SpecialMatchFacade specialMatchFacade;

    @RequestMapping(value = "/allMatchesShow")
    public String showAllMatches(final Model model) {
        List<MatchData> matchesData = specialMatchFacade.findMatches();
        if (matchesData == null || CollectionUtils.isEmpty(matchesData)) {
            matchesData = new ArrayList<MatchData>();
        }
        model.addAttribute("allMatches", matchesData);
        return "AllMatcesShow";
    }

    @Autowired
    public void specialMatchFacadeFacade(final SpecialMatchFacade specialMatchFacade) {
        this.specialMatchFacade = specialMatchFacade;
    }
}
