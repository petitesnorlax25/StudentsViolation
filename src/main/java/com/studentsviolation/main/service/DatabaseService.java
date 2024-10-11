//package com.studentsviolation.main.service;
//
//import java.util.List;
//import java.util.Map;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class DatabaseService {
//    private final JdbcTemplate jdbcTemplate;
//
//    public DatabaseService(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public void duplicateDatabaseSchema(String oldDbName, String newDbName) {
//        try {
//            // Create the new database if it doesn't exist
//            String createDbSql = "CREATE DATABASE IF NOT EXISTS " + newDbName;
//            jdbcTemplate.execute(createDbSql);
//
//            // Retrieve the list of tables from the old database
//            String listTablesSql = "SHOW TABLES FROM " + oldDbName;
//            List<Map<String, Object>> tables = jdbcTemplate.queryForList(listTablesSql);
//
//            // Duplicate the table structures to the new database
//            for (Map<String, Object> table : tables) {
//                String tableName = (String) table.get("Tables_in_" + oldDbName);
//                if (tableName != null) {
//                    // Retrieve the create table statement
//                    String createTableSql = "SHOW CREATE TABLE " + oldDbName + "." + tableName;
//                    Map<String, Object> createTableResult = jdbcTemplate.queryForMap(createTableSql);
//                    String createTableStatement = (String) createTableResult.get("Create Table");
//
//                    // Adjust the database name in the create table statement
//                    createTableStatement = createTableStatement.replaceFirst("`" + oldDbName + "`.", "`" + newDbName + "`.");
//
//                    // Execute the create table statement in the new database
//                    jdbcTemplate.execute("USE " + newDbName);
//                    jdbcTemplate.execute(createTableStatement);
//                }
//            }
//
//            // Duplicate the data of 'admin' table from the old database to the new database
//            String copyDataSql = "INSERT INTO " + newDbName + ".admin SELECT * FROM " + oldDbName + ".admin";
//            jdbcTemplate.execute(copyDataSql);
//
//        } catch (Exception e) {
//            // Log the error for debugging purposes
//            System.err.println("Error duplicating database schema: " + e.getMessage());
//            e.printStackTrace();
//            throw e;
//        }
//    }
//}
