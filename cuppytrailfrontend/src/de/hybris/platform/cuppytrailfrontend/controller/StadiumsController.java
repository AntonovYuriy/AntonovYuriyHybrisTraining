package de.hybris.platform.cuppytrailfrontend.controller;

import de.hybris.platform.cuppytrail.data.StadiumData;
import de.hybris.platform.cuppytrail.facades.StadiumFacade;
import de.hybris.platform.cuppytrailfrontend.StadiumsNameEncoded;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class StadiumsController
{
    private StadiumFacade stadiumFacade;


    @RequestMapping(value = "/stadiums")
    public String showStadiums(final Model model)
    {
        final List<StadiumData> stadiums = stadiumFacade.getStadiums();
        model.addAttribute("stadiums", stadiums);
        return "StadiumListing";
    }



    @RequestMapping(value = "/stadiums/{stadiumName}")
    public String showStadiumDetails(@PathVariable String stadiumName, final Model model) throws UnsupportedEncodingException
    {
        stadiumName = URLDecoder.decode(stadiumName, "UTF-8");
        final StadiumData stadium = stadiumFacade.getStadium(stadiumName);
        stadium.setName(StadiumsNameEncoded.getNameEncoded(stadium.getName()));
        model.addAttribute("stadium", stadium);
        return "StadiumDetails";
    }


    @RequestMapping(value = "/deleteAllStadiums")
    public String deleteAllStadiums()
    {
        stadiumFacade.deleteAllStadiumsInFacade();
        return "redirect:/stadiums";
    }

    @RequestMapping(value = "/deleteStadium/{stadiumName}")
    public String deleteStadium(@PathVariable String stadiumName) throws UnsupportedEncodingException
    {
        stadiumName = URLDecoder.decode(stadiumName, "UTF-8");
        stadiumFacade.deleteOneStadiumByNameInFacade(stadiumName);
        return "redirect:/stadiums";
    }

    @RequestMapping(value = "/addNewStadium")
//    public String addNewStadium(String name, int capacity)
    public String addNewStadium()
    {
//        stadiumFacade.addNewStadium(name, capacity);
        return "AddNewStadium";
    }

    @RequestMapping(value = "/stadiumAddingProcedure")
    public String stadiumAddingProcedure()
    {
        String name = "Torpedo" + LocalDateTime.now().getMinute() + LocalDateTime.now().getSecond();
        int capacity = LocalDateTime.now().getMinute() + LocalDateTime.now().getSecond();
        stadiumFacade.addNewStadium(name, capacity);
        return "redirect:/stadiums";
    }
        @Autowired
    public void setFacade(final StadiumFacade facade)
    {
        this.stadiumFacade = facade;
    }

}