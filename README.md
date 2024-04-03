# Secure-to-do-list

Secure-to-do-list is a CRUD application with Jwt authentication provided by Spring Security.

## Technologies used

- Spring Security
- Spring Boot
- Spring Data JPA
- MySQL database
- Junit5

## Properties

Features of the application:
- Performing CRUD operations on to do items.
- Registering users.
- Obtaining Jwt Authentication tokens.

## Getting started

To be able to use the app user has to register sending post request on /api/v1/auth/register
providing firstName, lastName, email and password in body of a request.

After successful registration, user will obtain a JWT Token which has to be sent with any other request
as a method of authorization.

Tokens are valid for 24 hours after creation - registered user can obtain a new token sending 
request to /api/v1/auth/authenticate providing email and password in body.
