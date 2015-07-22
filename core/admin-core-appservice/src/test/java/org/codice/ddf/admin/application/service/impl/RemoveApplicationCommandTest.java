package org.codice.ddf.admin.application.service.impl;

import org.codice.ddf.admin.application.service.ApplicationService;
import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RemoveApplicationCommandTest {
    private Logger logger = LoggerFactory.getLogger(RemoveApplicationCommand.class);
    /**
     * Tests the {@link RemoveApplicationCommand} class
     */
    @Test
    public void testRemoveApplicationCommand() {
        ApplicationService testAppService = mock(ApplicationServiceImpl.class);
        BundleContext bundleContext = mock(BundleContext.class);
        ServiceReference<ApplicationService> mockFeatureRef;
        mockFeatureRef = (ServiceReference<ApplicationService>) mock(ServiceReference.class);

        RemoveApplicationCommand removeApplicationCommand = new RemoveApplicationCommand();
        removeApplicationCommand.appName = "TestApp";
        removeApplicationCommand.setBundleContext(bundleContext);

        when(bundleContext.getServiceReference(ApplicationService.class)).thenReturn(mockFeatureRef);
        when(bundleContext.getService(mockFeatureRef)).thenReturn(testAppService);

        try {
            removeApplicationCommand.doExecute();
            verify(testAppService).removeApplication("TestApp");
        }catch(Exception e) {
            logger.info("Exception: ", e);
            fail();
        }
    }
}
