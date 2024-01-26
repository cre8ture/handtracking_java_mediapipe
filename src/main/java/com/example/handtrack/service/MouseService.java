// package com.example.handtrack.service;

// import org.springframework.stereotype.Service;

// import java.awt.AWTException;
// import java.awt.Point;
// import java.awt.Robot;
// import java.awt.event.InputEvent;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// @Service
// public class MouseService {

//     private static final Logger logger = LoggerFactory.getLogger(MouseService.class);

//     private Robot robot;

//     public MouseService() {
//         try {
//             this.robot = new Robot();
//         } catch (AWTException e) {
//             logger.error("Failed to create Robot instance", e);
//         }
//     }

//     public void moveMouse(Point point) {
//         if (robot != null) {
//             robot.mouseMove(point.x, point.y);
//         } else {
//             logger.error("Robot instance is null");
//         }
//     }

//     public void clickMouse() {
//         if (robot != null) {
//             robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//             robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//         } else {
//             logger.error("Robot instance is null");
//         }
//     }
// }

package com.example.handtrack.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.awt.GraphicsEnvironment;
import java.awt.*;
import java.awt.event.InputEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MouseService {

    private static final Logger logger = LoggerFactory.getLogger(MouseService.class);

    private Robot robot;

    @Value("${mouse.movement.scale:1.0}") // Default scale factor
    private double scale;

    public MouseService() {
        if (!GraphicsEnvironment.isHeadless()) {
            try {
                this.robot = new Robot();
            } catch (AWTException e) {
                throw new IllegalStateException("Failed to create Robot instance for controlling mouse", e);
            }
        } else {
            logger.warn("Running in a headless environment. Robot functionality will be disabled.");
        }
    }

    public void moveMouse(Point point) {
        if (robot != null) {
            // Scale the coordinates based on the screen size
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int scaledX = (int) (point.x * screenSize.getWidth() * scale);
            int scaledY = (int) (point.y * screenSize.getHeight() * scale);
            robot.mouseMove(scaledX, scaledY);
        } else {
            logger.error("Robot instance is null");
        }
    }

    public void clickMouse() {
        if (robot != null) {
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            // Optional: Add a small delay for a more 'natural' click
            robot.delay(100); // Delay in milliseconds
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } else {
            logger.error("Robot instance is null");
        }
    }
}
