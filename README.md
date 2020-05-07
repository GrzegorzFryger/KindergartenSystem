[![Build Status](https://travis-ci.com/GrzegorzFryger/KindergartenSystem.svg?token=A8Ubwyy4DyMdGiKbN6WU&branch=develop)](https://travis-ci.com/GrzegorzFryger/KindergartenSystem)

[![CodeFactor](https://www.codefactor.io/repository/github/grzegorzfryger/kindergartensystem/badge?s=043349d4c2f39571807d34741268318a323ecb25)](https://www.codefactor.io/repository/github/grzegorzfryger/kindergartensystem)

# KindergartenSystem
## Getting started
### 1. Initialize database
Make sure to initialize database from 
[.database](https://github.com/GrzegorzFryger/KindergartenSystem/tree/develop/.database) directory.  

### 2. Build application
Run `mvn clean install`

### 3. Setup SSL
Make sure to follow all instructions from
[core](https://github.com/GrzegorzFryger/KindergartenSystem/tree/develop/core) module.

### 4. Run application
#### Using IntelliJ IDEA
Simply run `KindergartenSystem.java` from core module (configuration should be loaded automatically)

#### From console - using java -jar
Run `java -jar core/target/core-1.0-SNAPSHOT.jar` from root directory.  
Notice: make sure that core jar version is correct.

## Authentication
For Authentication purposes make sure to call /api/authenticate endpoint.  
You can find it in postman collection.  
There are 3 predefined requests, which contains proper credentials in body:


**USER**  
Request: `api/authenticate [USER]`
```
{
	"username": "{{user_login}}",
	"password": "{{user_pass}}"
}
```
**TEACHER**  
Request: `api/authenticate [TEACHER]`
```
{
	"username": "{{teacher_login}}",
	"password": "{{teacher_pass}}"
}
```
**ADMIN**  
Request: `api/authenticate [ADMIN]`
```
{
	"username": "{{admin_login}}",
	"password": "{{admin_pass}}"
}
```
