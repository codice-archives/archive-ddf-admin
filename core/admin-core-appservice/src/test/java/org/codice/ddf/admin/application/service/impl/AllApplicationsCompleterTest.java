package org.codice.ddf.admin.application.service.impl;

import static org.mockito.Mockito.*;

import org.codice.ddf.admin.application.service.Application;
import org.codice.ddf.admin.application.service.ApplicationService;
import org.junit.Test;
import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllApplicationsCompleterTest {
    /**
     * Tests the {@link AllApplicationsCompleter#complete(String, int, List)} method,
     * and the {@link AllApplicationsCompleter#AllApplicationsCompleter(ApplicationService)} constructor
     */
    @Test
    public void testAllApplicationsCompleter() {
        Application testApp = mock(ApplicationImpl.class);
        ApplicationService testAppService = mock(ApplicationServiceImpl.class);
        Set<Application> testAppSet = new HashSet<>();
        testAppSet.add(testApp);
        when(testAppService.getApplications()).thenReturn(testAppSet);
        when(testApp.getName()).thenReturn("TestApp");

        AllApplicationsCompleter applicationsCompleter = new AllApplicationsCompleter(testAppService);

        assertNotNull(applicationsCompleter.complete("Tes", 2, new ArrayList()));
    }
}
