package org.codice.ddf.admin.application.rest.model;

import org.apache.karaf.features.Feature;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class FeatureDetailsTest {
    /**
     * Tests the {@link FeatureDetails#FeatureDetails(Feature, String, String)} constructor,
     * and all associated getters
     */
    @Test
    public void testFeatureDetails() {
        Feature testFeature = mock(Feature.class);
        when(testFeature.getName()).thenReturn("TestFeature");
        when(testFeature.getId()).thenReturn("001");
        when(testFeature.getVersion()).thenReturn("0.0.0");
        when(testFeature.getInstall()).thenReturn("TestInstallString");
        when(testFeature.getDescription()).thenReturn("Feature for testing FeatureDetails");
        when(testFeature.getDetails()).thenReturn("TestDetails");
        when(testFeature.getResolver()).thenReturn("TestResolver");

        FeatureDetails testDetails = new FeatureDetails(testFeature, "TestStatus", "TestRepo");

        assertEquals("TestFeature", testDetails.getName());
        assertEquals("001", testDetails.getId());
        assertEquals("0.0.0", testDetails.getVersion());
        assertEquals("TestInstallString", testDetails.getInstall());
        assertEquals("Feature for testing FeatureDetails", testDetails.getDescription());
        assertEquals("TestDetails", testDetails.getDetails());
        assertEquals("TestResolver", testDetails.getResolver());
        assertEquals("TestRepo", testDetails.getRepository());
        assertEquals("TestStatus", testDetails.getStatus());
    }
}
