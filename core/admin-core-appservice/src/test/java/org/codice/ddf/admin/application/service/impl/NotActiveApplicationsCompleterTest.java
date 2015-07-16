package org.codice.ddf.admin.application.service.impl;

import org.codice.ddf.admin.application.service.Application;
import org.codice.ddf.admin.application.service.ApplicationService;
import org.codice.ddf.admin.application.service.ApplicationStatus;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class NotActiveApplicationsCompleterTest {
    /**
     * Tests the {@link NotActiveApplicationsCompleter} class
     */
    @Test
    public void testNotActiveApplicationsCompleter() {
        Application testApp = mock(ApplicationImpl.class);
        ApplicationService testAppService = mock(ApplicationServiceImpl.class);
        Set<Application> appSet = new HashSet<>();
        appSet.add(testApp);
        ApplicationStatus testStatus = mock(ApplicationStatusImpl.class);
        ApplicationStatus.ApplicationState testState = ApplicationStatus.ApplicationState.INACTIVE;

        when(testAppService.getApplications()).thenReturn(appSet);
        when(testAppService.getApplicationStatus(testApp)).thenReturn(testStatus);
        when(testStatus.getState()).thenReturn(testState);
        when(testApp.getName()).thenReturn("TestApp");

        NotActiveApplicationsCompleter activeApplicationsCompleter = new NotActiveApplicationsCompleter(testAppService);

        assertNotNull(activeApplicationsCompleter.complete("Tes", 2, new ArrayList()));
    }
}
