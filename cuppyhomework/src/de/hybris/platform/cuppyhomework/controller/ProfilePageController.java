package de.hybris.platform.cuppyhomework.controller;

import de.hybris.platform.cuppyhomework.facades.impl.SpecialMatchFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfilePageController {

    private SpecialMatchFacade specialMatchFacade;

    @RequestMapping(value = "/cuppyUser/profile")
    public String cuppyUserProfile(final Model model) {
        if ((de.hybris.platform.util.Config.getParameter("isUserLoggedIn").equals("true")) &&
                ((de.hybris.platform.util.Config.getParameter("isUserLoggedIn").equals(model.asMap().get("loggedUserName").toString())))){
            model.addAttribute("isUserLoggedIn", "true");
            model.addAttribute("loggedUserName", model.asMap().get("loggedUserName").toString());
            model.addAttribute("matchesSelected", specialMatchFacade.findMatchesWithSpecial());
            return "CuppyUserProfile";
        } else {
            de.hybris.platform.util.Config.setParameter("isUserLoggedIn","false");
            de.hybris.platform.util.Config.setParameter("loggedUserName","");
            model.addAttribute("loggedUserName", "");
            model.addAttribute("matchesSelected", "");
            return "CuppyUserLogin";
        }
    }

    @Autowired
    public void setSpecialMatchFacade(final SpecialMatchFacade specialMatchFacade) {
        this.specialMatchFacade = specialMatchFacade;
    }
}
