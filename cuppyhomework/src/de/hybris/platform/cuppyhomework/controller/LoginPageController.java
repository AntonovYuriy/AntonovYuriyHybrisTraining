package de.hybris.platform.cuppyhomework.controller;

import de.hybris.platform.cuppyhomework.data.CuppyUserData;
import de.hybris.platform.cuppyhomework.facades.impl.DefaultCuppyUserFacade;
import de.hybris.platform.cuppyhomework.facades.impl.SpecialMatchFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginPageController {

    private DefaultCuppyUserFacade defaultCuppyUserFacade;
    private SpecialMatchFacade specialMatchFacade;

    @RequestMapping(value = "/cuppyUser/login", method = RequestMethod.POST)
    public String cuppyUserLoginAction(
            @RequestParam(value = "login", required = false) String login,
            @RequestParam(value = "password", required = false) String password,
            final Model model) {
        if ((!login.isEmpty()) && (!password.isEmpty())) {
            try {
                CuppyUserData cuppyUserData = defaultCuppyUserFacade.getCuppyUserByUserName(login);
                if ((cuppyUserData.getUsername().equals(login)) && (cuppyUserData.getPassword().equals(password))) {
                    model.addAttribute("loggedUserName", cuppyUserData.getName());
                    model.addAttribute("matchesSelected", specialMatchFacade.findMatchesWithSpecial());
                    de.hybris.platform.util.Config.setParameter("isUserLoggedIn","true");
                    de.hybris.platform.util.Config.setParameter("loggedUserName",cuppyUserData.getName());
                    return "CuppyUserProfile";
                } else {
                    return "CuppyUserLogin";
                }
            } catch (Exception e) {
                return "CuppyUserLogin";
            }
        } else {
            return "CuppyUserLogin";
        }
    }

    @RequestMapping(value = "/cuppyUser/login", method = RequestMethod.GET)
    public String cuppyUserShowLoginPage(final Model model) {
        return "CuppyUserLogin";
    }

    @Autowired
    public void setDefaultCuppyUserFacade(DefaultCuppyUserFacade defaultCuppyUserFacade) {
        this.defaultCuppyUserFacade = defaultCuppyUserFacade;
    }

    @Autowired
    public void setSpecialMatchFacade(final SpecialMatchFacade specialMatchFacade) {
        this.specialMatchFacade = specialMatchFacade;
    }
}