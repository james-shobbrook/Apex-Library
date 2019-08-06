/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import bean.RequestNewResourceBean;
import java.util.Vector;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author james
 */
public class RequestNewResourceDaoTest {
    
    public RequestNewResourceDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
        System.out.println("Done");
    }

    /**
     * Test of getAllResourcesBean method, of class RequestNewResourceDao.
     */
    @org.junit.Test
    public void testGetAllResourcesBean() {
        System.out.println("getAllResourcesBean");
        RequestNewResourceDao instance = new RequestNewResourceDao();
        int size = 0;
        Vector<RequestNewResourceBean> result = instance.getAllResourcesBean();
        System.out.println("Size"+result.size());
        assert(size < result.size());
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

//    /**
//     * Test of addNewResource method, of class RequestNewResourceDao.
//     */
//    @org.junit.Test
//    public void testAddNewResource() {
//        System.out.println("addNewResource");
//        RequestNewResourceBean rsrc = null;
//        RequestNewResourceDao instance = new RequestNewResourceDao();
//        boolean expResult = false;
//        boolean result = instance.addNewResource(rsrc);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getByID method, of class RequestNewResourceDao.
//     */
//    @org.junit.Test
//    public void testGetByID() {
//        System.out.println("getByID");
//        int id = 0;
//        RequestNewResourceDao instance = new RequestNewResourceDao();
//        RequestNewResourceBean expResult = null;
//        RequestNewResourceBean result = instance.getByID(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of Purchased method, of class RequestNewResourceDao.
//     */
//    @org.junit.Test
//    public void testPurchased() {
//        System.out.println("Purchased");
//        RequestNewResourceBean rb = null;
//        RequestNewResourceDao instance = new RequestNewResourceDao();
//        instance.Purchased(rb);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
