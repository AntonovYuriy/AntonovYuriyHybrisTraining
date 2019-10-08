package de.hybris.platform.cuppytrail.facades.impl;

import de.hybris.platform.core.PK;
import de.hybris.platform.cuppy.model.MatchModel;
import de.hybris.platform.cuppytrail.StadiumService;
import de.hybris.platform.cuppytrail.data.MatchSummaryData;
import de.hybris.platform.cuppytrail.data.StadiumData;
import de.hybris.platform.cuppytrail.facades.StadiumFacade;
import de.hybris.platform.cuppytrail.model.StadiumModel;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

public class DefaultStadiumFacade implements StadiumFacade
{
    private StadiumService stadiumService;

    @Override
    public List<StadiumData> getStadiums()
    {
        final List<StadiumModel> stadiumModels = stadiumService.getStadiums();
        final List<StadiumData> stadiumFacadeData = new ArrayList<StadiumData>();
        for (final StadiumModel sm : stadiumModels)
        {
            final StadiumData sfd = new StadiumData();
            sfd.setName(sm.getCode());
            sfd.setCapacity(sm.getCapacity().toString());
            stadiumFacadeData.add(sfd);
        }
        return stadiumFacadeData;
    }

    @Override
    public List<StadiumData> getStadiums(final String format)
    {
        final List<StadiumModel> stadiumModels = stadiumService.getStadiums();
        final List<StadiumData> stadiumFacadeData = new ArrayList<StadiumData>();
        String urlImg;
        for (final StadiumModel sm : stadiumModels)
        {
            try
            {
                urlImg = stadiumService.getImageUrlFromStadium(sm, format);
            }
            catch (final Exception e)
            {
                urlImg = "";
                // something bad happened, possibly no image available
            }

            final StadiumData sfd = new StadiumData();
            sfd.setName(sm.getCode());

            if (sm.getCapacity() != null)
            {
                sfd.setCapacity(sm.getCapacity().toString());
            }

            sfd.setImageUrl(urlImg);
            stadiumFacadeData.add(sfd);
        }
        return stadiumFacadeData;
    }

    @Override
    public StadiumData getStadium(final String name, final String format)
    {
        final StadiumModel stadium = stadiumService.getStadiumForCode(name);
        if (stadium == null)
        {
            return null;
        }
        // Create a list of MatchSummaryData from the matches
        final List<MatchSummaryData> matchSummary = new ArrayList<MatchSummaryData>();

        if (stadium.getMatches() != null)
        {
            final Iterator<MatchModel> matchesIterator = stadium.getMatches().iterator();

            while (matchesIterator.hasNext())
            {
                final MatchModel match = matchesIterator.next();
                final MatchSummaryData summary = new MatchSummaryData();
                final String homeTeam = match.getHomeTeam().getName();
                final String guestTeam = match.getGuestTeam().getName();
                final Date matchDate = match.getDate();
                // If no goals are specified provide a user friendly "-" instead of null
                final String guestGoals = match.getGuestGoals() == null ? "-" : match.getGuestGoals().toString();
                final String homeGoals = match.getHomeGoals() == null ? "-" : match.getHomeGoals().toString();
                summary.setHomeTeam(homeTeam);
                summary.setGuestTeam(guestTeam);
                summary.setDate(matchDate);
                summary.setGuestGoals(guestGoals);
                summary.setHomeGoals(homeGoals);
                final String formatedDate = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(matchDate);
                final String matchSummaryFormatted = homeTeam + ":( " + homeGoals + " ) " + guestTeam + " ( " + guestGoals + " ) "
                        + formatedDate;
                summary.setMatchSummaryFormatted(matchSummaryFormatted);
                matchSummary.add(summary);
            }
        }

        String urlBigImg;
        try
        {
            urlBigImg = stadiumService.getImageUrlFromStadium(stadium, format);
        }
        catch (final Exception e)
        {
            urlBigImg = "";
        }

        // Now we can create the StadiumData transfer object
        final StadiumData stadiumData = new StadiumData();
        stadiumData.setName(stadium.getCode());

        if (stadium.getCapacity() != null)
        {
            stadiumData.setCapacity(stadium.getCapacity().toString());
        }

        stadiumData.setMatches(matchSummary);
        stadiumData.setImageUrl(urlBigImg);
        return stadiumData;
    }

    @Override
    public StadiumData getStadium(final String name)
    {
        StadiumModel stadium = null;
        if (name != null)
        {
            stadium = stadiumService.getStadiumForCode(name);
            if (stadium == null)
            {
                return null;
            }
        }
        else
        {
            throw new IllegalArgumentException("Stadium with name " + name + " not found.");
        }

        // Create a list of MatchSummaryData from the matches
        final List<MatchSummaryData> matchSummary = new ArrayList<MatchSummaryData>();
        if (stadium.getMatches() != null)
        {
            final Iterator<MatchModel> matchesIterator = stadium.getMatches().iterator();

            while (matchesIterator.hasNext())
            {
                final MatchModel match = matchesIterator.next();
                final MatchSummaryData summary = new MatchSummaryData();
                final String homeTeam = match.getHomeTeam().getName();
                final String guestTeam = match.getGuestTeam().getName();
                final Date matchDate = match.getDate();
                // If no goals are specified provide a user-friendly "-" instead of null
                final String guestGoals = match.getGuestGoals() == null ? "-" : match.getGuestGoals().toString();
                final String homeGoals = match.getHomeGoals() == null ? "-" : match.getHomeGoals().toString();
                summary.setHomeTeam(homeTeam);
                summary.setGuestTeam(guestTeam);
                summary.setDate(matchDate);
                summary.setGuestGoals(guestGoals);
                summary.setHomeGoals(homeGoals);
                final String formattedDate = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,  DateFormat.SHORT).format(matchDate);
                final String matchSummaryFormatted = homeTeam + ":( " + homeGoals + " ) - " + guestTeam + " ( " + guestGoals + " ), at "
                        + formattedDate;
                summary.setMatchSummaryFormatted(matchSummaryFormatted);
                matchSummary.add(summary);
            }
        }
        // Now we can create the StadiumData transfer object
        final StadiumData stadiumData = new StadiumData();
        stadiumData.setName(stadium.getCode());
        stadiumData.setCapacity(stadium.getCapacity().toString());
        stadiumData.setMatches(matchSummary);
        return stadiumData;
    }

    @Override
    public void deleteAllStadiumsInFacade() {
        stadiumService.deleteAllStadiumsInService();
    }

    @Override
    public void deleteOneStadiumByNameInFacade(String name) {
         stadiumService.deleteOneStadiumByNameInService(name);
    }

    @Override
    public void addNewStadium(String name, int capacity) {
        stadiumService.addNewStadiumInService(name, capacity);
    }

    @Required
    public void setStadiumService(final StadiumService stadiumService)
    {
        this.stadiumService = stadiumService;
    }
}