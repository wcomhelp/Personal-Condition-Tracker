package ca.ualberta.cs.personal_condition_tracker;

import org.junit.Test;
import static org.junit.Assert.*;
import junit.framework.TestCase;

public class BodyLocationListTest extends TestCase{


    public void testHasBodyLocation() {
        BodyLocationList bodyLocationList = new BodyLocationList();
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocationList.addBodyLocation(bodyLocation);
        assertTrue(bodyLocationList.hasBodyLocation(bodyLocation));
    }

    public void testGetBodyLocation() {
        BodyLocationList bodyLocationList = new BodyLocationList();
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocationList.addBodyLocation(bodyLocation);
        assertTrue(bodyLocationList.getBodyLocation(0).equals(bodyLocation));
    }

    public void testAddBodyLocation() {
        BodyLocationList bodyLocationList = new BodyLocationList();
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocationList.addBodyLocation(bodyLocation);
        assertTrue(bodyLocationList.hasBodyLocation(bodyLocation));
    }

    public void testDeleteBodyLocation() {
        BodyLocationList bodyLocationList = new BodyLocationList();
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocationList.addBodyLocation(bodyLocation);
        assertTrue(bodyLocationList.hasBodyLocation(bodyLocation));
        bodyLocationList.deleteBodyLocation(bodyLocation);
        assertFalse(bodyLocationList.hasBodyLocation(bodyLocation));
    }

    public void testEditBodyLocation() {
        BodyLocationList bodyLocationList = new BodyLocationList();
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocationList.addBodyLocation(bodyLocation);
        BodyLocation newBodyLocation = new BodyLocation();
        bodyLocationList.editBodyLocation(0, newBodyLocation);
        assertTrue(bodyLocationList.getBodyLocation(0).equals(newBodyLocation));
    }
}