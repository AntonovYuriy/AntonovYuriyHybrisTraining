package de.hybris.platform.cuppyhomework.controller;

import de.hybris.platform.cuppyhomework.facades.impl.SpecialMatchFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfilePageController {

    private final static Logger LOG = Logger.getLogger(ProfilePageController.class);

    private SpecialMatchFacade specialMatchFacade;

    @RequestMapping(value = "/cuppyUser/profile")
    public String cuppyUserProfile(final Model model) {
        LOG.info("=============================================");
        LOG.info("CONTROLLER PROFILE GO TO PROFILE PAGE");
        model.addAttribute("matchesSelected", specialMatchFacade.findMatchesWithSpecial());
        return "CuppyUserProfile";
    }

    @Autowired
    public void setSpecialMatchFacade(final SpecialMatchFacade specialMatchFacade) {
        this.specialMatchFacade = specialMatchFacade;
    }
}
