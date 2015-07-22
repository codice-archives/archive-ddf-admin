package org.codice.ddf.admin.application.service.impl;

import org.junit.Test;
import static org.junit.Assert.*;

public class ZipFileApplicationDetailsTest {
    /**
     * Tests the {@link ZipFileApplicationDetails#ZipFileApplicationDetails(String, String)} constructor,
     * and the getters related to it
     */
    @Test
    public void testConstructor() {
        ZipFileApplicationDetails testZipFile = new ZipFileApplicationDetails("TestName", "0.0.0");

        assertEquals("TestName", testZipFile.getName());
        assertEquals("0.0.0", testZipFile.getVersion());

        testZipFile.setName("TestName2");
        testZipFile.setVersion("0.0.1");

        assertEquals("TestName2", testZipFile.getName());
        assertEquals("0.0.1", testZipFile.getVersion());
    }
}
