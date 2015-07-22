package org.codice.ddf.admin.application.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class ApplicationServiceExceptionTest {
    /**
     * Tests the {@link ApplicationServiceException#ApplicationServiceException(String)} constructor
     */
    @Test
    public void testApplicationServiceExceptionStringParam() {
        try{
            throw new ApplicationServiceException("TestMessage");
        }catch(Exception e){
            assertEquals("TestMessage", e.getMessage());
        }
    }

    /**
     * Tests the {@link ApplicationServiceException#ApplicationServiceException(String, Throwable)} constructor
     */
    @Test
    public void testApplicationServiceExceptionStringThrowableParams() {
        try {
            Throwable testThrowable = new Throwable("ThrowableMessage");
            throw new ApplicationServiceException("TestMessage", testThrowable);
        }catch(Exception e) {
            assertEquals("TestMessage", e.getMessage());
            assertEquals("ThrowableMessage", e.getCause().getMessage());
        }
    }
    /**
     * Tests the {@link ApplicationServiceException#ApplicationServiceException(Throwable)} constructor
     */
    @Test
    public void testApplicationServiceExceptionThrowableParam() {
        try{
            Throwable testThrowable = new Throwable("ThrowableMessage");
            throw new ApplicationServiceException(testThrowable);
        }catch(Exception e){
            assertEquals("ThrowableMessage", e.getCause().getMessage());
        }
    }
}
