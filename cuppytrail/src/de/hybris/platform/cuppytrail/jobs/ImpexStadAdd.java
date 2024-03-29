package de.hybris.platform.cuppytrail.jobs;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import java.util.List;

public class ImpexStadAdd extends AbstractJobPerformable<CronJobModel>
{

    @Override
    public PerformResult perform(final CronJobModel cronJob)
    {


        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }
}