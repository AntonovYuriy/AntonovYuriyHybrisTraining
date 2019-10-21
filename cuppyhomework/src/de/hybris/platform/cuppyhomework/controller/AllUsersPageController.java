package de.hybris.platform.cuppyhomework.controller;

import de.hybris.platform.cuppyhomework.data.CuppyUserData;
import de.hybris.platform.cuppyhomework.facades.impl.DefaultCuppyUserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AllUsersPageController {

    private DefaultCuppyUserFacade cuppyUserFacade;

    @RequestMapping(value = "/allUsersShow")
    public String showAllCuppyUsers(final Model model) {
        final List<CuppyUserData> allCuppyUsers = cuppyUserFacade.getAllCuppyUsers();
        model.addAttribute("allCuppyUsers", allCuppyUsers);
        return "AllCuppyUsersListing";
    }

    @Autowired
    public void setFacade(final DefaultCuppyUserFacade cuppyUserFacade) {
        this.cuppyUserFacade = cuppyUserFacade;
    }
}
