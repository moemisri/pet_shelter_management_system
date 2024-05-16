package com.up.demo;

import com.up.pet.run.Main;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {

    public AppTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    public void testApp() {
        assertTrue(true);
    }

    // Add your test for Main.java here
    public void testMainFunctionality() {
        assertTrue(Main.initDB());
        System.out.println("it's all good man!");
    }
}
