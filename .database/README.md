# 1. General info
Our project is using MySQL Community Server - version 8.0.X 
https://dev.mysql.com/downloads/  

The easiest way to install this database is to use **MySQL Installer for Windows**.  
MySQL server will be installed as Windows Service, which can be easily turned on and off.
# 2. Install procedure
In order to install database run below scripts:   
* `install_1_0.sql`  

You can run these scripts via MySQL Workbench.
# 3. Uninstall procedure
In order to uninstall database run below scripts:   
* `rollback_1_0.sql`  

You can run these scripts via MySQL Workbench.
# 4. Implementation details
Each module has got own DB user, which is granted with all privileges related to his schema.