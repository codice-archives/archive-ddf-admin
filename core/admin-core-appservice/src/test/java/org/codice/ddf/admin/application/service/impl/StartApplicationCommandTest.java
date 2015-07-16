package org.codice.ddf.admin.application.service.impl;

import org.codice.ddf.admin.application.service.ApplicationService;
import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class StartApplicationCommandTest {
    private Logger logger = LoggerFactory.getLogger(AddApplicationCommand.class);
    /**
     * Tests the {@link StartApplicationCommand} class and its associated methods
     */
    @Test
    public void testStartApplicationCommand() {
        ApplicationService testAppService = mock(ApplicationServiceImpl.class);
        BundleContext bundleContext = mock(BundleContext.class);
        ServiceReference<ApplicationService> mockFeatureRef;
        mockFeatureRef = (ServiceReference<ApplicationService>) mock(ServiceReference.class);

        StartApplicationCommand startApplicationCommand = new StartApplicationCommand();
        startApplicationCommand.appName = "TestApp";
        startApplicationCommand.setBundleContext(bundleContext);

        when(bundleContext.getServiceReference(ApplicationService.class)).thenReturn(mockFeatureRef);
        when(bundleContext.getService(mockFeatureRef)).thenReturn(testAppService);

        try{
            startApplicationCommand.doExecute();
            verify(testAppService).startApplication("TestApp");
        }catch(Exception e){
            logger.info("Exception: ", e);
            fail();
        }
    }
}
