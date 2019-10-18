package de.hybris.platform.cuppyhomework.controller;

import de.hybris.platform.cuppyhomework.facades.impl.DefaultCuppyUserFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterPageController {

    private final static Logger LOG = Logger.getLogger(RegisterPageController.class);

    private DefaultCuppyUserFacade cuppyUserFacade;

    @RequestMapping(value = "/cuppyUser/register", method = RequestMethod.POST)
    public String cuppyUserRegisterPageAction(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "password", required = false) String password,
            final Model model) {
        LOG.info("=============================================");
        LOG.info("CONTROLLER REGISTER POST");
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