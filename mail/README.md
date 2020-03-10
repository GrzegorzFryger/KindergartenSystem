# Mail module
## Description
This module is responsible for sending emails to all Kindergarten stakeholders.  
This module can be included by any other module in project to be used.

## Getting started
### Sending first email
In order to send email, you need to initialize `BaseMail.java` object, which stores all data that will
be send in email, including:
* `String to` - email recipient **[required]**
* `String subject` - subject of email **[required]**
* `String content` - text, which will be send to recipient **[required] (only in case if variables map is empty)**  
This text can be replaced with variables. Basically it's used for emails, which don't require any special templates.  
* `Map<String, InputStreamSource> attachments` - list of attachments
* `Map<String, Object> variables` - list of variables. These can be used in customized templates to store information
that you want to provide to your recipient. You can add as many of these as you like 
and reference them in .html template.
* `EmailTemplate emailTemplate` - template, which will be used in email.
By default `BASE_TEMPLATE` is used to send email - but you are free to add your own template, that suits your needs.  
In case you add new template - make sure that `BaseMail.java` that you use has this template set.

Then you simply have to use `sendEmail()` method from `MailFacade.java`.  

### Adding new template
1) Create .html file with new template in `resources/email-templates` directory  
2) Add this template in `EmailTemplate.java` enum.  
This enum is used in `BaseMail.java` to pick proper .html template for given email.  
3) Make sure to add code in your module, which sets proper enum in `BaseMail.java` before
sending email.  
4) You can add variables in .html template using below syntax:  
`[[${property_name}]]`  
These variables are taken from `BaseMail.java` - so make sure to use `addVariables(key, value)` method
in order to initialize them.
