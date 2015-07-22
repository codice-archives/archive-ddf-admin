package org.codice.ddf.admin.application.rest;

import org.apache.cxf.jaxrs.ext.multipart.*;
import org.codice.ddf.admin.application.service.ApplicationService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.junit.Test.*;

public class ApplicationUploadEndpointTest {
    private Logger logger = LoggerFactory.getLogger(ApplicationUploadEndpoint.class);

    /**
     * Tests the {@link ApplicationUploadEndpoint#update(MultipartBody, UriInfo)} method
     *
     * TODO: This is creating junk in the file system, shouldn't be
     */
    @Test
    public void testApplicationUploadEndpointUpdate() {
        ApplicationService testAppService = mock(ApplicationService.class);
        MultipartBody testMultipartBody = mock(MultipartBody.class);
        UriInfo testUriInfo = mock(UriInfo.class);
        List<Attachment> attachmentList = new ArrayList<>();
        Attachment testAttach1 = mock(Attachment.class);
        attachmentList.add(testAttach1);
        ContentDisposition testDisp = mock(ContentDisposition.class);
        DataHandler testDataHandler = mock(DataHandler.class);

        when(testAttach1.getDataHandler()).thenReturn(testDataHandler);
        when(testAttach1.getContentDisposition()).thenReturn(testDisp);
        when(testMultipartBody.getAllAttachments()).thenReturn(attachmentList);

        try {
            File testFile = new File(File.class.getResource("/test-kar.zip").getPath());
            InputStream testIS = new FileInputStream(testFile);
            when(testDataHandler.getInputStream()).thenReturn(testIS);

            ApplicationUploadEndpoint applicationUploadEndpoint = new ApplicationUploadEndpoint(testAppService);

            assertNotNull(applicationUploadEndpoint.update(testMultipartBody, testUriInfo));
        }catch(Exception e){
            logger.info("Exception: ", e);
            fail();
        }
    }

    /**
     * Tests the {@link ApplicationUploadEndpoint#create(MultipartBody, UriInfo)} method
     */
    @Test
    public void testApplicationUploadEndpointCreate() {
        ApplicationService testAppService = mock(ApplicationService.class);
        MultipartBody testMultipartBody = mock(MultipartBody.class);
        UriInfo testUriInfo = mock(UriInfo.class);
        List<Attachment> attachmentList = new ArrayList<>();
        Attachment testAttach1 = mock(Attachment.class);
        attachmentList.add(testAttach1);
        ContentDisposition testDisp = mock(ContentDisposition.class);
        DataHandler testDataHandler = mock(DataHandler.class);

        when(testAttach1.getDataHandler()).thenReturn(testDataHandler);
        when(testAttach1.getContentDisposition()).thenReturn(testDisp);
        when(testMultipartBody.getAllAttachments()).thenReturn(attachmentList);

        try{
            File testFile = new File(File.class.getResource("/test-kar.zip").getPath());
            InputStream testIS = new FileInputStream(testFile);
            when(testDataHandler.getInputStream()).thenReturn(testIS);

            ApplicationUploadEndpoint applicationUploadEndpoint = new ApplicationUploadEndpoint(testAppService);

            assertNotNull(applicationUploadEndpoint.create(testMultipartBody, testUriInfo));
            verify(testAppService).addApplication(any(URI.class));
        }catch(Exception e){
            logger.info("Exception: ", e);
            fail();
        }
    }
}
