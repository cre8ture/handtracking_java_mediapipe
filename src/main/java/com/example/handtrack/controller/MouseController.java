// package com.example.handtrack.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import com.example.handtrack.service.MouseService;

// import java.awt.Point;

// @Controller
// public class MouseController {

//     @Autowired
//     private MouseService mouseService;

//     @PostMapping("/moveMouse")
//     public void moveMouse(@RequestBody Point point) {
//         mouseService.moveMouse(point);
//     }

//     @PostMapping("/clickMouse")
//     public void clickMouse() {
//         mouseService.clickMouse();
//     }

//     @GetMapping("/")
//     public String home() {
//         return "index";
//     }
// }

package com.example.handtrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.handtrack.service.MouseService;

import java.awt.Point;

@RestController // Changed from @Controller to @RestController
@RequestMapping("/api") // Optional: add a base path for all APIs
public class MouseController {

    @Autowired
    private MouseService mouseService;

    // DTO class for the point
    public static class PointDTO {
        public int x;
        public int y;

        // Getters and setters (if using Lombok, you can annotate with @Data instead)
    }

    @PostMapping("/moveMouse")
    public ResponseEntity<?> moveMouse(@RequestBody PointDTO pointDTO) {
        Point point = new Point(pointDTO.x, pointDTO.y);
        mouseService.moveMouse(point);
        return ResponseEntity.ok().build(); // Send back a response
    }

    @PostMapping("/clickMouse")
    public ResponseEntity<?> clickMouse() {
        mouseService.clickMouse();
        return ResponseEntity.ok().build(); // Send back a response
    }

    @CrossOrigin // Handle CORS, or configure it globally
    @GetMapping("/")
    public String home() {
        return "index";
    }
}
