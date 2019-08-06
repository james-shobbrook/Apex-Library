/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import bean.ResourceBean;
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
public class ResourceDaoTest {
    
    public ResourceDaoTest() {
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
    }

    /**
     * Test of getAllResourcesBean method, of class ResourceDao.
     */
    @org.junit.Test
    public void testGetAllResourcesBean() {
        System.out.println("getAllResourcesBean");
        ResourceDao instance = new ResourceDao();
        int SIZE = 0;
        Vector<ResourceBean> result = instance.getAllResourcesBean();
        assert(result.size() > SIZE);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

//    /**
//     * Test of addNewResource method, of class ResourceDao.
//     */
//    @org.junit.Test
//    public void testAddNewResource() {
//        System.out.println("addNewResource");
//        ResourceBean rsrc = null;
//        ResourceDao instance = new ResourceDao();
//        boolean expResult = false;
//        boolean result = instance.addNewResource(rsrc);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getByID method, of class ResourceDao.
     */
//    @org.junit.Test
//    public void testGetByID() {
//        System.out.println("getByID");
//        int id = 0;
//        ResourceDao instance = new ResourceDao();
//        ResourceBean expResult = null;
//        ResourceBean result = instance.getByID(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of addRating method, of class ResourceDao.
     */
//    @org.junit.Test
//    public void testAddRating() {
//        System.out.println("addRating");
//        float rating = 0.0F;
//        int resource_id = 0;
//        ResourceDao instance = new ResourceDao();
//        instance.addRating(rating, resource_id);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
