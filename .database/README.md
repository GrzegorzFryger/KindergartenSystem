# 1. General info
![Version](https://img.shields.io/badge/version-1.2-blue?style=for-the-badge)  

Our project is using MySQL Community Server - version 8.0.X 
https://dev.mysql.com/downloads/  

The easiest way to install this database is to use **MySQL Installer for Windows**.  
MySQL server will be installed as Windows Service, which can be easily turned on and off.
# 2. Install procedure
All install scripts are placed in `install` directory.  
You can run all below scripts via MySQL Workbench.  

**Important notice:**  
Updating hibernate_sequence in way it is done in script requires safe mode to be turned off. 
To do so follow below steps:  
a) `Edit -> Preferences -> SQL Editor -> Uncheck "Safe Updates" checkbox`  
b) `Query -> Reconnect to Server` 
## 2.1 Schema
In order to install database: 
* run `install_1_0.sql` script  
This script will create database schemas and users.  

## 2.2 Tables
* run `mvn clean install`  
Hibernate will create all necessary tables.  

## 2.3 Test data
* run `insert_1_0.sql` script  
This script will insert some test data.   

## 2.4 Scheduler
* run `insert_quartz_1_0.sql` script  
This script will initialize all objects, which are required by scheduler in order to work properly

# 3. Uninstall procedure
All uninstall scripts are placed in `uninstall` directory.  
## 3.1 Schema
In order to uninstall database:   
* run `rollback_1_0.sql` script
This script will delete all schemas will all data.  

## 3.2 Test data
In case you only need to reset state of test data:
* run `delete_1_0.sql`  
* run `insert_1_0.sql` (see: 2.2 Tables)  

# 4. Implementation details
Each module has got own DB user, which is granted with all privileges related to his schema.
