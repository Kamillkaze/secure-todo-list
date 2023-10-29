# Boring-to-do-list

Boring-to-do-list is a simple application - backend for program
which can help with organising day and setting up schedules.

## Technologies used

- Spring Boot
- Spring Data JPA
- MySQL database
- Junit5

## Properties

Features of the application:
- Adding new to-do item to database.
- Deleting to-do item from the database.
- Updating to-do item records in the database.
- Getting all records from the database.

### To-do item fields

- Short description (String) - stores name of the task.
- Details (String) - stores detailed description of the task.
- Deadline (java.sql.Date) - specifies the day on which the task
  should be performed.