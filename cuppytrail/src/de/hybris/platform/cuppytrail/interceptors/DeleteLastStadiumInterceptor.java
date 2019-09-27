package de.hybris.platform.cuppytrail.interceptors;

import de.hybris.platform.cuppytrail.StadiumService;
import de.hybris.platform.cuppytrail.events.DeleteStadiumEvent;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import static de.hybris.platform.servicelayer.model.ModelContextUtils.getItemModelContext;

public class DeleteLastStadiumInterceptor implements ValidateInterceptor {

    private static final int minimalSizeOFStadiumList = 1;
    Logger LOG = Logger.getLogger(this.getClass());

    @Autowired
    private EventService eventService;
//    private DefaultStadiumService StService;


    @Override
    public void onValidate(Object model, InterceptorContext interceptorContext) throws InterceptorException {
        if (model instanceof StadiumModel && IsItLastStadium()) {
            final StadiumModel stadium = (StadiumModel) model;
            eventService.publishEvent(new DeleteStadiumEvent(stadium.getCode()));
        }
    }

    private boolean IsItLastStadium() {
        StadiumService st = new StadiumService();
        final Integer QuantityOfStadiumsAtTheBeginning = st.getStadiums().size();
        LOG.info ("==========================================================");
        LOG.info ("QuantityOfStadiumsAtTheBeginning = " + QuantityOfStadiumsAtTheBeginning);
        LOG.info ("==========================================================");
        if (QuantityOfStadiumsAtTheBeginning < 0) {

//            CHANGE CHECKING TO ==1
            return true;
        }
        return false;
    }
}
