# MooRestApi
REST server - Test project for Moo
Use gradle to build the app from the sources using command -
`./gradlew bootJar`
The executable jar is located in the build/libs directory and you can run it by executing the following command:
`java -jar build/libs/MooRestApi-1.0-SNAPSHOT.jar`
---

App resides in `java/main/com.luka.moo` package
**Application** - main spring loader class. Starts the app, sets up Spring boot
and calls initialising of the hashmap representing db with dummy values

`api` package has RestController classes and exception handler for managig error response codes and messages

**HomeController** - Separate controller for home (/) path
**CustomersController** - Main customers endpoint 

All classes that handle model objects and the interfaces that define their actions 
are in the `model` package
**Customer** Main domain model class for representing customer from address book
**DbObject** Base class for domain model classes
**DataAction** Interface for defining possible actions on DbObjects


`services` package and **CustomerService** class represent DAO layer that fetches and persists data

`helpers` package cosists of custom exception class


