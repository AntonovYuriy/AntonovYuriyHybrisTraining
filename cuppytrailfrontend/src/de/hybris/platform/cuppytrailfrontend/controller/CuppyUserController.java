package de.hybris.platform.cuppytrailfrontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CuppyUserController {

    @RequestMapping(value = "/cuppyUser")
    public String cuppyUserFirstPage(final Model model) {

        return "CuppyUserFirstPage";
    }

    @RequestMapping(value = "/cuppyUser/login", method = RequestMethod.GET)
    public String cuppyUserLoginPage(final Model model) {

        return "CuppyUserLogin";
    }

    @RequestMapping(value = "/cuppyUser/login", method = RequestMethod.POST)
    public String cuppyUserShowLoginPage(final Model model) {

        return "CuppyUserLogin";
    }

    @RequestMapping(value = "/cuppyUser/profile")
    public String cuppyUserProfile(final Model model) {

        return "CuppyUserProfile";
    }

    @RequestMapping(value = "/cuppyUser/register")
    public String cuppyUserRegisterPage(final Model model) {

        return "CuppyUserRegister";
    }
}