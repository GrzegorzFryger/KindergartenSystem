# Core module
## Description
This is main module of application - with runnable class `KindergartenSystem.java`.
## PKCS12 certificate generation
Run `generate_PKCS12_certificate.sh` script in `src/resources/keystore` directory.  
Script will automatically update `application-cert.properties` configuration file with generated password.  
Property name: `server.ssl.key-store-password`.  

Generated certificate is valid for 90 days.  

### Issues
#### Google Chrome: connection is unsecured
Google Chrome will complain, that this certificate is not trusted, so in order to fix it,
you can add generated certificate to trusted certificates store.

#### Postman: fails to send request
Self-signed SSL certificates are being blocked:  
Fix this by turning off 'SSL certificate verification' in Settings > General

## Authentication
For Authentication purposes make sure to call /api/authenticate endpoint.  
You can find it in postman collection.  
In order to use users with different roles, make sure to modify request body.  
Possible roles:  

**USER**
```
{
	"username": "{{user_login}}",
	"password": "{{user_pass}}"
}
```
**TEACHER**
```
{
	"username": "{{teacher_login}}",
	"password": "{{teacher_pass}}"
}
```
**ADMIN**
```
{
	"username": "{{admin_login}}",
	"password": "{{admin_pass}}"
}
```



