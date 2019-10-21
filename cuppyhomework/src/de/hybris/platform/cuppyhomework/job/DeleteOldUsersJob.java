package de.hybris.platform.cuppyhomework.job;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.cuppyhomework.facades.impl.DefaultCuppyUserFacade;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class DeleteOldUsersJob extends AbstractJobPerformable<CronJobModel> {

    private DefaultCuppyUserFacade defaultCuppyUserFacade;

    @Resource(name = "defaultConfigurationService")
    private ConfigurationService configurationService;


    @Override
    public PerformResult perform(CronJobModel cronJobModel) {
        int daysLimitToDeleteUser = Integer.parseInt(de.hybris.platform.util.Config.getParameter("daysLimitToDeleteUser"));
        try {
            defaultCuppyUserFacade.deleteUserDaysOld(daysLimitToDeleteUser);
        } catch (Exception e) {
            return new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
        }
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    @Autowired
    public void setDefaultCuppyUserFacade(DefaultCuppyUserFacade defaultCuppyUserFacade) {
        this.defaultCuppyUserFacade = defaultCuppyUserFacade;
    }
}
