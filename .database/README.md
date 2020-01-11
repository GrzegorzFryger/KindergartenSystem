# 1. General info
![Version](https://img.shields.io/badge/version-1.1-blue?style=for-the-badge)  

Our project is using MySQL Community Server - version 8.0.X 
https://dev.mysql.com/downloads/  

The easiest way to install this database is to use **MySQL Installer for Windows**.  
MySQL server will be installed as Windows Service, which can be easily turned on and off.
# 2. Install procedure
In order to install database: 
* run `install_1_0.sql` script  
This script will create database schemas and users.
* run KindergartenSystem application  
Hibernate will create all necessary tables
* run `insert_1_0.sql` script  
This script will insert some test data  

You can run these scripts via MySQL Workbench.  

**Important notice:**  
Updating hibernate_sequence in way it is done in script requires safe mode to be turned off. 
To do so follow below steps:  
a) `Edit -> Preferences -> SQL Editor -> Uncheck "Safe Updates" checkbox`  
b) `Query -> Reconnect to Server`  
# 3. Uninstall procedure
In order to uninstall database:   
* run `rollback_1_0.sql` script

You can run these scripts via MySQL Workbench.
# 4. Implementation details
Each module has got own DB user, which is granted with all privileges related to his schema.