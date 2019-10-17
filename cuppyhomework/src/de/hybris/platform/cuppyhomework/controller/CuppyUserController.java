package de.hybris.platform.cuppyhomework.controller;

import de.hybris.platform.cuppy.web.data.MatchData;
import de.hybris.platform.cuppy.web.facades.impl.DefaultMatchFacade;
import de.hybris.platform.cuppyhomework.data.CuppyUserData;
import de.hybris.platform.cuppyhomework.facades.impl.DefaultCuppyUserFacade;
import de.hybris.platform.cuppyhomework.model.CuppyUserModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CuppyUserController {

    private final static Logger LOG = Logger.getLogger(CuppyUserController.class);

    private DefaultCuppyUserFacade cuppyUserFacade;

    private DefaultMatchFacade matchFacade;

    @RequestMapping(value = "/allUsersShow")
    public String showAllCuppyUsers(final Model model) {
        LOG.info("=============================================");
        LOG.info("CONTROLLER SHOW ALL UISERS");
        final List<CuppyUserData> allCuppyUsers = cuppyUserFacade.getAllCuppyUsers();
        model.addAttribute("allCuppyUsers", allCuppyUsers);
        return "AllCuppyUsersListing";
    }

    @RequestMapping(value = "/allMatchesShow")
    public String showAllMatches(final Model model) {
        LOG.info("=============================================");
        LOG.info("CONTROLLER SHOW ALL MATCHES");
        final List<MatchData> matchesData = matchFacade.getMatches();
        model.addAttribute("allMatches", matchesData);
        return "AllMatcesShow";
    }

    @RequestMapping(value = "/cuppyUser")
    public String cuppyUserFirstPage(final Model model) {
        LOG.info("=============================================");
        LOG.info("CONTROLLER CUPPY USER");
        return "CuppyUserLogin";
    }

    @RequestMapping(value = "/cuppyUser/login", method = RequestMethod.POST)
    public String cuppyUserLoginAction(
            @RequestParam(value = "login", required = false) String login,
            @RequestParam(value = "password", required = false) String password,
            final Model model) {
        LOG.info("=============================================");
        LOG.info("CONTROLLER LOGIN POST");
        LOG.info("login = " + login + " AND  password = " + password);
        if ((!login.isEmpty()) && (!password.isEmpty())) {
            try {
                CuppyUserData cuppyUserData = cuppyUserFacade.getCuppyUserByUserName(login);
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

    @RequestMapping(value = "/cuppyUser/profile")
    public String cuppyUserProfile(final Model model) {
        LOG.info("=============================================");
        LOG.info("CONTROLLER PROFILE");
        if (model.asMap().get("loginConfirmator").equals("LoginConfirmed")) {

//            ПОМЕНЯТЬ НА ИЗБРАННЫЕ МАТЧИ

            model.addAttribute("matchesSelected", matchFacade.getMatches());
        } else {
            model.addAttribute("matchesSelected", new ArrayList<>());
            model.addAttribute("registeredUserName", "");
        }
        return "CuppyUserProfile";
    }

    @RequestMapping(value = "/cuppyUser/logOff")
    public String cuppyUserLogOff(final Model model) {
        LOG.info("=============================================");
        LOG.info("CONTROLLER LOGOFF");
        model.addAttribute("loginConfirmator", "loginNOTconfirmed");
        return "CuppyUserLogin";
    }


    @RequestMapping(value = "/cuppyUser/register", method = RequestMethod.POST)
    public String cuppyUserRegisterPageAction(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "password", required = false) String password,
            final Model model) {
        LOG.info("=============================================");
        LOG.info("CONTROLLER REGISTER POST");
        CuppyUserModel cuppyUserModel = new CuppyUserModel();

        try {
            LOG.info("TRY to save to Facade");
            cuppyUserFacade.addNewUser(name, username, phone, password);
            LOG.info("SAVE to Facade COMPLETE");
        } catch (Exception e) {
            LOG.info("ERROR DURING SAVE TO ModelService");
            return "CuppyUserRegister";
        }
        return "CuppyUserLogin";
    }

    @RequestMapping(value = "/cuppyUser/register", method = RequestMethod.GET)
    public String cuppyUserRegisterPage(final Model model) {
        LOG.info("=============================================");
        LOG.info("CONTROLLER REGISTER GET");

        return "CuppyUserRegister";
    }

    @Autowired
    public void setFacade(final DefaultCuppyUserFacade cuppyUserFacade) {
        this.cuppyUserFacade = cuppyUserFacade;
    }
}