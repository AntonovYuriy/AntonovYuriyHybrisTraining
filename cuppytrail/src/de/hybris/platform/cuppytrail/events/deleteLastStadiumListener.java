package de.hybris.platform.cuppytrail.events;

import de.hybris.platform.cuppy.model.NewsModel;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;


public class DeleteLastStadiumListener extends AbstractEventListener<DeleteStadiumEvent> {

    private Logger LOG = Logger.getLogger(this.getClass());

    @Autowired
    ModelService modelService;

    @Override
    public void onEvent(final DeleteStadiumEvent event) {
        try {
            LOG.info("### Entering event handler ###");
            Thread.sleep(2000);

            final NewsModel news = modelService.create(NewsModel.class);
            final String content = "CANNOT DELETE LAST STADIUM == " + event.getCode();
            news.setContent(content, Locale.ENGLISH);
            modelService.save(news);
            LOG.info("### News created: " + content + " ###");
        } catch (final InterruptedException e) {
            Log.info("Error during event processing in listener");
        }
    }
}