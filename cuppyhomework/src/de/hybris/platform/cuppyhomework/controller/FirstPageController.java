package de.hybris.platform.cuppyhomework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FirstPageController {

    @RequestMapping(value = "/cuppyUser")
    public String cuppyUserFirstPage(final Model model) {
        String isUserLoggedIn = de.hybris.platform.util.Config.getParameter("isUserLoggedIn");
        if (isUserLoggedIn.equals("true")) {
            return "CuppyUserProfile";
        }
        return "CuppyUserLogin";
    }
}