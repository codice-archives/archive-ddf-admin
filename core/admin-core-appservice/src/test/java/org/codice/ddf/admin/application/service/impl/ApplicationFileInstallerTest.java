package org.codice.ddf.admin.application.service.impl;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.io.File;
import java.util.zip.ZipException;


public class ApplicationFileInstallerTest {

    private Logger logger = LoggerFactory.getLogger(ApplicationFileInstaller.class);

    /**
     * Tests the {@link ApplicationFileInstaller#install(File)} method
     */
    @Test
    public void testInstall() {
        ApplicationFileInstaller testInstaller = new ApplicationFileInstaller();
        File testFile = new File(File.class.getResource("/test-kar.zip").getPath());

        try{
            assertNotNull(testInstaller.install(testFile));
        }catch(Exception e){
            logger.info("Exception: ", e);
            fail();
        }
    }

    /**
     * Tests the {@link ApplicationFileInstaller#getAppDetails(File)} method
     */
    @Test
    public void testGetAppDetails() {
        ApplicationFileInstaller testInstaller = new ApplicationFileInstaller();
        File testFile = new File(File.class.getResource("/test-kar.zip").getPath());
        ZipFileApplicationDetails testFileDetails;
        try{
            testFileDetails = testInstaller.getAppDetails(testFile);
            assertNotNull(testFileDetails);
            //Verify other stuff
            assertEquals("main-feature", testFileDetails.getName());
            assertEquals("1.0.1", testFileDetails.getVersion());
        }catch(Exception e){
            logger.info("Exception: ", e);
            fail();
        }
    }
}
