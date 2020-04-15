# Transactional Outbox Pattern
Example implementation in Kotlin with Spring and PostgreSQL.

# Requirements
* PostgreSQL >= 9.5
* schema from `schema.sql` present in the database

# Configuration
Database host, port and credentials in `src/main/resources/application.properties`

# How to run it
Build the app using `mvn clean install` and then run it using `java -jar outbox-0.0.1-SNAPSHOT.jar`.

# How to use it
The app exposes a single HTTP endpoint `POST /accounts`.
Make a request to it with a request body like:
```
{
    "name": "Maciej",
    "email": "maciej@toporowicz.it"
}
```
In up to 5 seconds you should see a log like:
```
Received event: AccountCreatedEvent(id=996b31e5-c8ad-45c0-97d0-744519b8c454, email=maciej@toporowicz.it)
```