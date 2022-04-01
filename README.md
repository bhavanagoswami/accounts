# Current Account API

Developed Apis to create Account , get account details and Get All Customer details.
Used Spring Boot framework with in-memory  H2 DB.Maven is used as build tool.

Create Account:

Create Account api uses a DTO ,AccountCreateRequest, to create an account.
This DTO has customerId ,AccountName and initialCredit parameters.
If initialCredit field is provided in request then a transaction service get called.
And add a transaction for newly created account.
If initialCredit field is 0 or null in request then no transaction has been created for Account.

Get Customer Details:
This API return all the customer details with related accounts and transactions. 
Added a dataLoader component to prepopulate some customer details.
that can be seen after starting this spring boot application.
and using:
http://localhost:8080/h2-console/
Username : sa
Password : password

Swagger:
To check the API specification, added Swagger-UI.
All the specification can be seen using below URL.
http://localhost:8080/v2/api-docs

Exception:
Add a controller advice for Exception handling that can we extended and used for multiple 
Exception handlers.

How to run This Application:
1) Clone this git Url https://github.com/bhavanagoswami/accounts.git in your IDE.
2) Run Maven build and Install : 
   mvn clean install
3) Run this http://localhost:8080/v2/api-docs in postman or run http://localhost:8080/swagger-ui/index.html#/
on any browser.
4) Also attaching postman collection with this code.




