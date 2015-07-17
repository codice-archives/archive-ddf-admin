package org.codice.ddf.admin.application.service.impl;

import org.codice.ddf.admin.application.service.Application;
import org.codice.ddf.admin.application.service.ApplicationNode;
import org.codice.ddf.admin.application.service.ApplicationService;
import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.tree.Tree;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TreeApplicationCommandTest {
    private Logger logger = LoggerFactory.getLogger(TreeApplicationCommand.class);

    /**
     * Tests the {@link TreeApplicationCommand} class and its associated methods
     */
    @Test
    public void testTreeApplicationCommand() {
        ApplicationService testAppService = mock(ApplicationServiceImpl.class);
        BundleContext bundleContext = mock(BundleContext.class);
        ServiceReference<ApplicationService> mockFeatureRef;
        mockFeatureRef = (ServiceReference<ApplicationService>) mock(ServiceReference.class);

        TreeApplicationCommand treeApplicationCommand = new TreeApplicationCommand();
        treeApplicationCommand.setBundleContext(bundleContext);

        Set<ApplicationNode> treeSet = new TreeSet<>();
        ApplicationNode testNode1 = mock(ApplicationNodeImpl.class);
        ApplicationNode testNode2 = mock(ApplicationNodeImpl.class);
        treeSet.add(testNode1);
        Set<ApplicationNode> childSet = new TreeSet<>();
        childSet.add(testNode2);
        Application testApp = mock(ApplicationImpl.class);

        when(testApp.getName()).thenReturn("TestApp");
        when(testNode1.getApplication()).thenReturn(testApp);
        when(testNode2.getApplication()).thenReturn(testApp);
        when(testNode2.getChildren()).thenReturn(new TreeSet<ApplicationNode>());
        when(testNode1.getChildren()).thenReturn(childSet);
        when(testAppService.getApplicationTree()).thenReturn(treeSet);
        when(bundleContext.getServiceReference(ApplicationService.class)).thenReturn(mockFeatureRef);
        when(bundleContext.getService(mockFeatureRef)).thenReturn(testAppService);

        try{
            treeApplicationCommand.doExecute();
            verify(testAppService).getApplicationTree();
        }catch(Exception e){
            logger.info("Exception: ", e);
            fail();
        }
    }
}
