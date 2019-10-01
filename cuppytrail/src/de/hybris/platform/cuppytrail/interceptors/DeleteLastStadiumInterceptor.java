package de.hybris.platform.cuppytrail.interceptors;

import de.hybris.platform.cuppytrail.StadiumService;
import de.hybris.platform.cuppytrail.events.DeleteStadiumEvent;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.RemoveInterceptor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class DeleteLastStadiumInterceptor implements RemoveInterceptor {

    private static final int minimalSizeOFStadiumList = 1;
    Logger LOG = Logger.getLogger(this.getClass());

    @Autowired
    private EventService eventService;

    @Autowired
    private StadiumService st;

    @Override
    public void onRemove(Object model, InterceptorContext interceptorContext) throws InterceptorException {
        if (model instanceof StadiumModel && IsItLastStadium()) {
            final StadiumModel stadium = (StadiumModel) model;
            LOG.info("==========================================================");
            LOG.info("PUBLISHING EVENT FROM INTERCEPTOR");
            eventService.publishEvent(new DeleteStadiumEvent(stadium.getCode()));
        }
    }

    private boolean IsItLastStadium() throws InterceptorException {
        final Integer QuantityOfStadiumsAtTheBeginning = st.getStadiums().size();
        LOG.info("==========================================================");
        LOG.info("QuantityOfStadiumsAtTheBeginning = " + QuantityOfStadiumsAtTheBeginning);
        LOG.info("==========================================================");
        if (QuantityOfStadiumsAtTheBeginning <= minimalSizeOFStadiumList) {
            throw new InterceptorException("This is the last stadium");
        }
        return false;
    }
}
