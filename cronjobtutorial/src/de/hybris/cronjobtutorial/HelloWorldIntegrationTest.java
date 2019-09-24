package de.hybris.cronjobtutorial;

import de.hybris.cronjobtutorial.model.HelloWorldCronJobModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.model.TriggerModel;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.cronjob.CronJobService;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.internal.model.ServicelayerJobModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.junit.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import static junit.framework.TestCase.*;

public class HelloWorldIntegrationTest extends ServicelayerTest
{

    private static final Logger LOG = Logger.getLogger(HelloWorldIntegrationTest.class.getName());

    @Resource
    CronJobService cronJobService;

    @Resource
    ModelService modelService;

    @Resource
    FlexibleSearchService flexibleSearchService;

    List<ServicelayerJobModel> servicelayerJobModelList = Collections.EMPTY_LIST;
    ServicelayerJobModel servicelayerJobModel = null;
    HelloWorldCronJobModel helloWorldCJ = null;

    @Before
    public void setUp()
    {
        //The update of the JUnit tenant creates automatically an instance of the defined MyJobPerformable job
        //Search for it
        ServicelayerJobModel sjm = new ServicelayerJobModel();
        sjm.setSpringId("myJobPerformable");
        try
        {
            servicelayerJobModel = flexibleSearchService.getModelByExample(sjm); //searching by example
        }
        catch(ModelNotFoundException e)
        {
            //The cronjob functionality in the processing extension creates for each JobPerformable a ServicelayerJob where the springID is equal to the job bean id
            //You just create a job here
            servicelayerJobModel = modelService.create(ServicelayerJobModel.class);
            servicelayerJobModel.setSpringId("myJobPerformable");
            servicelayerJobModel.setCode("myJobPerformable");
            modelService.save(servicelayerJobModel);
            //Keep in mind that creating models in the catch clause is bad style
        }

        // Create a CronJob and set the servicelayerJob
        helloWorldCJ = modelService.create(HelloWorldCronJobModel.class);
        helloWorldCJ.setActive(Boolean.TRUE);
        helloWorldCJ.setJob(servicelayerJobModel);
        modelService.save(helloWorldCJ);

        //Below is just to show how to create a trigger but not really necessary for this JUnit test
        createTrigger(helloWorldCJ);
    }

    @Test
    public void testIfThePerformableExist()
    {
        //Check if there is an instance of myJobPerformable
        assertNotNull("***************No performable with springID *myJobPerformable* found perhaps you have to "
                + "Update your JunitTenant to let create an instance!", servicelayerJobModel);
    }

    @Test
    public void testExecuteThePerformable()
    {
        //Check if setup works correctly
        assertNotNull("***************The in set upcreated CronJob is null?", helloWorldCJ);

        //Perform the CronJob once for the test
        cronJobService.performCronJob(helloWorldCJ);

        //Wait for the result to be written
        try
        {
            Thread.sleep(2000);
        }
        catch (final InterruptedException e)
        {
            e.printStackTrace();
        }

        LOG.info("*************** lets wait 2 seconds for the result  ***************");

        //Test if the job was executed successfully, if it fails here then try to extend the time
        assertEquals("*************** The perfromable has not finished successfull or more wait is required on this mashine!",
                CronJobResult.SUCCESS, helloWorldCJ.getResult());

    }

    //Create a trigger just to show how to implement it
    public void createTrigger(final HelloWorldCronJobModel helloWorldCJ)
    {
        final TriggerModel triggerModel = modelService.create(TriggerModel.class);
        triggerModel.setActive(Boolean.TRUE);
        triggerModel.setMinute(new Integer(1));
        triggerModel.setCronJob(helloWorldCJ);
        modelService.save(triggerModel);
    }

}