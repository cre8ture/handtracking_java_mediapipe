package com.example.handtrack.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Map;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Toolkit;

@RestController
public class MouseController2 {

    private static final int Y_OFFSET = -30; // Adjust this offset based on testing

    @PostMapping("/moveMouse2")
    public void moveMouse2(@RequestBody Map<String, Double> payload) {
        try {
            Robot robot = new Robot();

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            double screenWidth = screenSize.getWidth();
            double screenHeight = screenSize.getHeight();

            Point mousePos = MouseInfo.getPointerInfo().getLocation();

            double sensitivity = 1; // A value less than 1 reduces sensitivity, greater than 1 increases it

            int newX = (int) Math.min(Math.max(mousePos.x + (payload.get("x") * screenWidth * sensitivity), 0),
                    screenWidth - 1);
            int newY = (int) Math.min(Math.max(mousePos.y + (payload.get("y") * screenHeight * sensitivity), 0),
                    screenHeight - 1);

            System.out.println("newX: " + newX + ", newY: " + newY);

            // Move the mouse to the new position
            robot.mouseMove(newX, newY);
        } catch (Exception e) {
            e.printStackTrace(); // Log the full stack trace
            throw new RuntimeException("Failed to control mouse", e);
        }

    }
}