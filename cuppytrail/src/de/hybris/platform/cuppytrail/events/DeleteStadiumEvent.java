package de.hybris.platform.cuppytrail.events;

import de.hybris.platform.servicelayer.event.events.AbstractEvent;

public class DeleteStadiumEvent extends AbstractEvent
{
    private final String code;

    public DeleteStadiumEvent(final String code)
    {
        super();
        this.code = code;
    }

    public String getCode()
    {
        return code;
    }

    @Override
    public String toString()
    {

        return "Trying delete stadium - " + this.code + " EVENT";
    }
}