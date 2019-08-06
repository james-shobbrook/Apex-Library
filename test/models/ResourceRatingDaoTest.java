/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import bean.ResourceRatingBean;
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
public class ResourceRatingDaoTest {
    
    public ResourceRatingDaoTest() {
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

//    /**
//     * Test of addRating method, of class ResourceRatingDao.
//     */
//    @org.junit.Test
//    public void testAddRating() {
//        System.out.println("addRating");
//        ResourceRatingBean rating = null;
//        ResourceRatingDao instance = new ResourceRatingDao();
//        instance.addRating(rating);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getResourceRating method, of class ResourceRatingDao.
     */
    @org.junit.Test
    public void testGetResourceRating() {
        System.out.println("getResourceRating");
        int resource_id = 1;
        ResourceRatingDao instance = new ResourceRatingDao();
        float expResult = 0.0F;
        float result = instance.getResourceRating(resource_id);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
