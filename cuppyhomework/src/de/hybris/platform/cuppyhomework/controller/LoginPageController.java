package de.hybris.platform.cuppyhomework.controller;

import de.hybris.platform.cuppyhomework.data.CuppyUserData;
import de.hybris.platform.cuppyhomework.facades.impl.DefaultCuppyUserFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginPageController {

    private final static Logger LOG = Logger.getLogger(LoginPageController.class);

    private DefaultCuppyUserFacade defaultCuppyUserFacade;

    @RequestMapping(value = "/cuppyUser/login", method = RequestMethod.POST)
    public String cuppyUserLoginAction(
            @RequestParam(value = "login", required = false) String login,
            @RequestParam(value = "password", required = false) String password,
            final Model model) {
        LOG.info("=============================================");
        LOG.info("login = " + login + " AND  password = " + password);
        if ((!login.isEmpty()) && (!password.isEmpty())) {
            try {
                CuppyUserData cuppyUserData = defaultCuppyUserFacade.getCuppyUserByUserName(login);
                LOG.info("=============================================");
                LOG.info("PASSED GETTING DATA FROM FACADE");
                if ((cuppyUserData.getUsername().equals(login)) && (cuppyUserData.getPassword().equals(password))) {
                    model.addAttribute("loginConfirmator", "LoginConfirmed");
                    model.addAttribute("registeredUserName", cuppyUserData.getName());
                    LOG.info("=============================================");
                    LOG.info("INFO FROM FACADE IS GOOD");
                    LOG.info("GET FROM FACADE = " + cuppyUserData);
                    return "CuppyUserProfile";
                } else {
                    LOG.info("=============================================");
                    LOG.info("INFO FROM FACADE IS BAD");
                    LOG.info("login = " + login + " , from FACADE = " + cuppyUserData.getUsername());
                    LOG.info("password = " + password + " , from FACADE = " + cuppyUserData.getPassword());
                    LOG.info("GET FROM FACADE = " + cuppyUserData);
                    return "CuppyUserLogin";
                }
            } catch (Exception e) {
                LOG.info("=============================================");
                LOG.info("CANNOT GET THE DATA FROM FACADE - ERROR");
                return "CuppyUserLogin";
            }
        } else {
            return "CuppyUserLogin";
        }
    }

    @RequestMapping(value = "/cuppyUser/login", method = RequestMethod.GET)
    public String cuppyUserShowLoginPage(final Model model) {
        LOG.info("=============================================");
        LOG.info("CONTROLLER LOGIN GET");
        return "CuppyUserLogin";
    }

    @Autowired
    public void setDefaultCuppyUserFacade (DefaultCuppyUserFacade defaultCuppyUserFacade) {
        this.defaultCuppyUserFacade=defaultCuppyUserFacade;
    }
}