package org.codice.ddf.admin.application.service.impl;

import java.net.URI;
import org.apache.karaf.features.FeaturesService;
import org.codice.ddf.admin.application.service.ApplicationService;
import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AddApplicationCommandTest {

    private Logger logger = LoggerFactory.getLogger(AddApplicationCommand.class);

    /**
     * Tests the {@link AddApplicationCommand} class and its contained methods
     */
    @Test
    public void testAddApplicationCommand() {
        ApplicationService testAppService = mock(ApplicationServiceImpl.class);
        BundleContext bundleContext = mock(BundleContext.class);
        ServiceReference<ApplicationService> mockFeatureRef;
        mockFeatureRef = (ServiceReference<ApplicationService>) mock(ServiceReference.class);

        AddApplicationCommand addApplicationCommand = new AddApplicationCommand();
        addApplicationCommand.appName = "TestApp";
        addApplicationCommand.setBundleContext(bundleContext);

        when(bundleContext.getServiceReference(ApplicationService.class)).thenReturn(mockFeatureRef);
        when(bundleContext.getService(mockFeatureRef)).thenReturn(testAppService);

        try {
            addApplicationCommand.doExecute();
            verify(testAppService).addApplication(any(URI.class));
        }catch(Exception e){
            logger.info("Exception: ", e);
            fail();
        }
    }
}
