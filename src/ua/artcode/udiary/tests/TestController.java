package ua.artcode.udiary.tests;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * checking classes_methods of controller package
 */

public class TestController {

    @BeforeClass
    public static void setUp() {
        System.out.println("Before");
    }

    @Test
    public void addRecordCheck() {
        System.out.println("TestController_addRecord");
    }

    @Test
    public void getRecordById() {
        System.out.println("TestController_getRecordById");
    }

}
