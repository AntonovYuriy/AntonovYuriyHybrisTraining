package de.hybris.platform.cuppytrail.jobs;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.cuppytrail.StadiumService;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.impex.jalo.imp.ImpExImportReader;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import de.hybris.platform.util.CSVReader;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import java.io.File;
import java.io.IOException;

public class AddStadiumsFromImpexFile extends AbstractJobPerformable<CronJobModel> {

    private static final Logger LOG = Logger.getLogger(AddStadiumsFromImpexFile.class);

    private StadiumService stadiumService;

    @Override
    public PerformResult perform(CronJobModel cronJobModel) {

        File file = new File("C:\\work\\hybris\\bin\\custom\\cuppytrail\\resources\\impex\\projectdataStadium.impex");

        try {
            if (CollectionUtils.isEmpty(stadiumService.getStadiums())) {
                ImpExImportReader reader = new ImpExImportReader(new CSVReader(file, "UTF-8"));
                reader.readAll();
                reader.close();
                LOG.info("======================================================");
                LOG.info("Adding stadiums has been successfully done");
                LOG.info("======================================================");
            }
        } catch (ImpExException e) {
            LOG.info("======================================================");
            LOG.info("Error in reading all lines from file");
            LOG.info("======================================================");
            e.printStackTrace();
        } catch (IOException e) {
            LOG.info("======================================================");
            LOG.info("Error in closing file");
            LOG.info("======================================================");
            e.printStackTrace();
        }
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);

    }

    @Required
    public void setStadiumService(final StadiumService stadiumService) {
        this.stadiumService = stadiumService;
    }
}
