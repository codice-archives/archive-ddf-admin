package org.codice.ddf.admin.application.service.impl;

import org.codice.ddf.admin.application.service.Application;
import org.codice.ddf.admin.application.service.ApplicationService;
import org.codice.ddf.admin.application.service.ApplicationStatus;
import org.codice.ddf.admin.application.service.ApplicationStatus.ApplicationState;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ActiveApplicationsCompleterTest {
    /**
     * Tests the {@link ActiveApplicationsCompleter#complete(String, int, List)} method,
     * as well as the associated constructor
     */
    @Test
    public void testActiveApplicationsCompleter() {
        ApplicationService testAppService = mock(ApplicationServiceImpl.class);
        Application testApp = mock(ApplicationImpl.class);
        Set<Application> appSet = new HashSet<>();
        appSet.add(testApp);
        ApplicationStatus testStatus = mock(ApplicationStatusImpl.class);
        ApplicationState testState = ApplicationState.ACTIVE;

        when(testAppService.getApplications()).thenReturn(appSet);
        when(testAppService.getApplicationStatus(testApp)).thenReturn(testStatus);
        when(testStatus.getState()).thenReturn(testState);
        when(testApp.getName()).thenReturn("TestApp");

        ActiveApplicationsCompleter activeApplicationsCompleter = new ActiveApplicationsCompleter(testAppService);

        assertNotNull(activeApplicationsCompleter.complete("Tes", 2, new ArrayList()));
    }
}
