//package com.studentsviolation.main.controller;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import com.studentsviolation.main.service.DatabaseService;
//
//@RestController
//@RequestMapping("/api/databases")
//public class DatabaseController {
//    private final DatabaseService databaseService;
//
//    public DatabaseController(DatabaseService databaseService) {
//        this.databaseService = databaseService;
//    }
//
//    @PostMapping("/duplicate")
//    public ResponseEntity<String> duplicateDatabaseSchemaWithUsers(@RequestParam String oldDbName, @RequestParam String newDbName) {
//        try {
//            databaseService.duplicateDatabaseSchema(oldDbName, newDbName);
//            return ResponseEntity.ok("Database schema and users table duplicated successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error duplicating database schema: " + e.getMessage());
//        }
//    }
//    
//}
