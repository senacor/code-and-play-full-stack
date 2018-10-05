
# Chat application

## Structure

 - frontend 	- contains an Angular frontend
 - services 	- contains the REST services to be used by the frontend.
 - tasks 		- contains the task about the implementation fo the services and the frontend.

## Build an run

Frontend and services can be build together by executing a maven install in the root folder
	
	mvn clean install
	
To run it together just start the service jar file in services/target

	java -jar services/target/chat-app-services-0.0.1-SNAPSHOT.jar

## Development

For development it's useful to start the frontend and the services seperatly

### Frontend

Use the Angular CLI to start the frontend.

    cd frontend
    ng serve
    
The service calls are going through a proxy to service (see frontend/proxy.conf.js)
    
### Services

The Spring Boot application can be started directly in your IDE by running Application class.

Or if preferred you can start it view maven

    cd services
    mvn spring-boot:run

