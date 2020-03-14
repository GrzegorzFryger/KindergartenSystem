# Commons module
## Description
This module contains all elements, that are shared with all other modules.  
When you create new module - you should include commons module in it.
## Contents
### Exceptions
This module contains common exceptions, that should be used by all modules.  
Logic responsible for handling them is implemented within RestControllerAdvice in **core module**.

### Constants
This module contains various constants, that are reused withing application.

### Roles
This module contains Strings, which describe all possible authentication roles.
These are kept in `Roles.java` class
