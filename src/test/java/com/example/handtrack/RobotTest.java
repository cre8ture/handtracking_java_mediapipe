package com.example.handtrack;

import org.junit.jupiter.api.Test;
import java.awt.Robot;
import java.awt.Point;
import java.awt.MouseInfo;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest {

    @Test
    void testRobot() {
        try {
            Robot robot = new Robot();
            Point initialPosition = MouseInfo.getPointerInfo().getLocation();

            // Move the mouse
            robot.mouseMove(initialPosition.x + 100, initialPosition.y + 100);
            Point newPosition = MouseInfo.getPointerInfo().getLocation();

            // Check if the mouse moved
            assertNotEquals(initialPosition, newPosition, "The mouse did not move.");
        } catch (Exception e) {
            fail("Failed to create or use Robot: " + e.getMessage());
        }
    }
}
