package org.codice.ddf.admin.application.service.impl;

import org.codice.ddf.admin.application.service.Application;
import org.codice.ddf.admin.application.service.ApplicationService;
import org.codice.ddf.admin.application.service.ApplicationStatus;
import org.codice.ddf.admin.application.service.ApplicationStatus.*;
import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class StopApplicationCommandTest {
    private Logger logger = LoggerFactory.getLogger(AddApplicationCommand.class);
    /**
     * Tests the {@link StopApplicationCommand} class and its associated methods
     */
    @Test
    public void testStopApplicationCommandTest() {
        ApplicationService testAppService = mock(ApplicationServiceImpl.class);
        BundleContext bundleContext = mock(BundleContext.class);
        ServiceReference<ApplicationService> mockFeatureRef;
        mockFeatureRef = (ServiceReference<ApplicationService>) mock(ServiceReference.class);

        Application testApp = mock(ApplicationImpl.class);
        ApplicationStatus testStatus = mock(ApplicationStatus.class);

        StopApplicationCommand stopApplicationCommand = new StopApplicationCommand();
        stopApplicationCommand.appName = "TestApp";
        stopApplicationCommand.setBundleContext(bundleContext);

        when(testStatus.getState()).thenReturn(ApplicationState.ACTIVE);
        when(testAppService.getApplicationStatus(testApp)).thenReturn(testStatus);
        when(testAppService.getApplication("TestApp")).thenReturn(testApp);
        when(bundleContext.getServiceReference(ApplicationService.class)).thenReturn(mockFeatureRef);
        when(bundleContext.getService(mockFeatureRef)).thenReturn(testAppService);

        try{
            stopApplicationCommand.doExecute();
            //verify stuff
        }catch(Exception e){
            logger.info("Exception", e);
            fail();
        }
    }
}
