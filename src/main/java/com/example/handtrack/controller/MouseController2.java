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

    @PostMapping("/moveMouse2")
    public void moveMouse2(@RequestBody Map<String, Double> payload) {
        try {
            Robot robot = new Robot();

            // Get the screen dimensions
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            double screenWidth = screenSize.getWidth();
            double screenHeight = screenSize.getHeight();

            // Get the current mouse position
            Point mousePos = MouseInfo.getPointerInfo().getLocation();

            // Apply deltas to the current mouse position, scaled to screen size
            int newX = (int) Math.min(Math.max(mousePos.x + payload.get("x") * screenWidth, 0), screenWidth);
            int newY = (int) Math.min(Math.max(mousePos.y + payload.get("y") * screenHeight, 0), screenHeight);

            System.out.println("newX: " + newX + ", newY: " + newY);

            // Move the mouse to the new position
            robot.mouseMove(newX, newY);
        } catch (Exception e) {
            e.printStackTrace(); // Log the full stack trace
            throw new RuntimeException("Failed to control mouse", e);
        }

    }
}