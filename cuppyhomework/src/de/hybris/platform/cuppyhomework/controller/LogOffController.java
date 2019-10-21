package de.hybris.platform.cuppyhomework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogOffController {

    @RequestMapping(value = "/cuppyUser/logOff")
    public String cuppyUserLogOff(final Model model) {
        de.hybris.platform.util.Config.setParameter("isUserLoggedIn","false");
        de.hybris.platform.util.Config.setParameter("loggedUserName","");
        return "CuppyUserLogin";
    }

}
