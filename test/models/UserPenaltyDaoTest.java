/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import bean.UserPenaltyBean;
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
public class UserPenaltyDaoTest {
    
    public UserPenaltyDaoTest() {
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
//     * Test of addPenalty method, of class UserPenaltyDao.
//     */
//    @org.junit.Test
//    public void testAddPenalty() {
//        System.out.println("addPenalty");
//        UserPenaltyBean upb = null;
//        UserPenaltyDao instance = new UserPenaltyDao();
//        instance.addPenalty(upb);
//        // TODO review the generated test code and remove the default call to fail.
//
//    }

    /**
     * Test of getPenaltyByResourceID method, of class UserPenaltyDao.
     */
//    @org.junit.Test
//    public void testGetPenaltyByResourceID() {
//        System.out.println("getPenaltyByResourceID");
//        int resource_id = 0;
//        UserPenaltyDao instance = new UserPenaltyDao();
//        UserPenaltyBean expResult = null;
//        UserPenaltyBean result = instance.getPenaltyByResourceID(resource_id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of RemovePenalty method, of class UserPenaltyDao.
     */
//    @org.junit.Test
//    public void testRemovePenalty() {
//        System.out.println("RemovePenalty");
//        UserPenaltyBean upb = null;
//        UserPenaltyDao instance = new UserPenaltyDao();
//        instance.RemovePenalty(upb);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getAllPenalties method, of class UserPenaltyDao.
     */
    @org.junit.Test
    public void testGetAllPenalties() {
        System.out.println("getAllPenalties");
        UserPenaltyDao instance = new UserPenaltyDao();
        int size = 0;
        Vector<UserPenaltyBean> result = instance.getAllPenalties();
        assert(size<result.size());
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
