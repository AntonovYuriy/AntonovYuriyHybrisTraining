package de.hybris.cronjobtutorial;

import de.hybris.cronjobtutorial.model.HelloWorldCronJobModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import java.util.logging.Logger;

public class MyJobPerformable extends AbstractJobPerformable<HelloWorldCronJobModel>
{
    private static final Logger LOG = Logger.getLogger(MyJobPerformable.class.getName());

    @Override
    public PerformResult perform(final HelloWorldCronJobModel cronJobModel)
    {
        LOG.info("**********************************");
        LOG.info("Greeting from MyJobPerformable!!!");
        LOG.info("**********************************");

        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }
}