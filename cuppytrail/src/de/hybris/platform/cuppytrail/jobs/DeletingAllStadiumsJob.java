package de.hybris.platform.cuppytrail.jobs;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.cuppytrail.StadiumService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

public class DeletingAllStadiumsJob extends AbstractJobPerformable<CronJobModel> {

    private static final Logger LOG = Logger.getLogger(DeletingAllStadiumsJob.class);

    private StadiumService stadiumService;

    @Override
    public PerformResult perform(CronJobModel cronJobModel) {
        stadiumService.deleteAllStadiumsInService();
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    @Required
    public void setStadiumService(final StadiumService stadiumService) {
        this.stadiumService = stadiumService;
    }
}
