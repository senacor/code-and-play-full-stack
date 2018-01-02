
# Tasks


## Task 1 - Creation of a Spring Boot application

### Theory
 - Spring / Spring Boot basics

### 1.1 - Create an empty Spring Boot application
 - Go to https://start.spring.io or use Spring Initializr integrated into IDEA
 - Choose Spring Boot Version 2.0.0 (SNAPSHOT)
 - Add the following dependencies:
	- web
	- jpa
	
### 1.2 - Run the application 
 - Run the Spring Boot application 
 - Open http://localhost:8080/	
		
INFO: When running with Java 9 the warning about "illegal reflective access operation" can be ignored.
It's just because there are plans to remove reflective access and the used libs are not yet adapted.

## Task 2 - Create REST resource for channels and messages

### Theory
 - Define a Spring bean
 - Dependency Injection
 - REST / How to create a REST endpoint

### 2.0 Checkout "chat-app" master branch 
 - Checkout "chat-app" master branch.
 - It contains an empty Spring Boot application (as created in task 1.1) with the integration of an Angular frontend.

### 2.1 Channel Service
 - A channel can be represented by a class with just one property "name". We suggest to create all model classes in a subpackage called "domain".
 - Create a service	to load all existing channels. For now just return a static list.

### 2.2 Messages Service
- A message can be represented by a class with the following properties:
    - id - A unique id for this message
    - channel - The communication channel this message was posted in.
    - sender - The name of the sender of the message.
    - message - A String that contains the message text.
    - creationTimestamp - A timestamp to indicate message creation / sent time.
 - Create a service	to load all existing messages for a channel.
 - For now just return a static list.
	
### 2.3 Create REST endpoints
 - Create a REST endpoint for both resources
    - /api/v1/channels
    - /api/v1/channels/{channel}/messages
 - For now we only support GET 
 - Use the services to fetch the data

### 2.4 Integration test the REST endpoints	
 - Create an integration test with Spring mock mvc.
	
	
## Task 3 - Create an Angular frontend	

### Theory
 - Angular basics
 - Angular CLI
    - https://github.com/angular/angular-cli
	- https://www.npmjs.com/package/@angular/cli
	
### 3.0 - Setup
 - Install Angular CLI
	
			
	# if old versions exist, uninstall first 
	npm uninstall -g angular-cli
	npm uninstall -g @angular/cli
	# clean cache - if npm version is > 5 then use `npm cache verify` to avoid errors (or to avoid using --force) 
	npm cache clean
	# install latest Anular CLI	
	npm install -g @angular/cli@latest


### 3.1 - Create a frontend
- Use the CLI to create a new frontend


    ng new my-ng-fe
    cd my-ng-fe
    ng serve --open

The '--open' option opens the browser on http://localhost:4200/

### 3.2 Inspect the created files 
 - Look into the folder "my-ng-fe" and inspect the created files
 - Especially look at:
    - package.json
    - tsconfig.json
    - src/main.ts
    - src/index.html
    - src/app/app.module.ts 
    - src/app/app.component.ts
    - src/app/app.component.html


## Task 4 - Modify the frontend

### Theory

### 4.1 Add Bootstrap for styling

Install bootstrap as npm dependency

    npm install bootstrap@3 jquery --save
    
Add bootstrap files path to angular-cli.json:

    "styles": [
                 "styles.css",
                 "../node_modules/bootstrap/dist/css/bootstrap.min.css"
             ],
             "scripts": [
                 "../node_modules/jquery/dist/jquery.min.js",
                 "../node_modules/bootstrap/dist/js/bootstrap.min.js"
             ]    


Bootstrap documentation > http://getbootstrap.com/docs/3.3/css/'

Use Bootstrap 'container', 'row' 'col-md-2' and 'text-center' CSS classes to improve the markup of 'app.component.html'.

e.g.:
<div class="container">
  <div class="row">
    <div class="col-md-4 text-center">...</div>
    <div class="col-md-4 text-center">...</div>
    <div class="col-md-4 text-center">...</div>
  </div>
</div>

### 4.? Change title

### 4.? Add menu



## Task 5 - Showing Messages

### 5.1 A component to show messages

Create a component to show chat messages

	ng generate component messages

Remove the logo and show the new component instead of the links

	replace img tag with <app-messages></app-messages>
	
	
### 5.2 Show some messages

Show simple mock messages. 

Create a class to represent messages.

    export class ChatMessage {
      message: string;
    }    
    const MESSAGES : ChatMessage[] = [
      {message: "foo"},
      {message: "bar"}
    ];

### 5.3 Add more attributes to a message

Extend the message model and create a component to show a message.
Use a Bootstrap panel (https://www.w3schools.com/bootstrap/bootstrap_panels.asp) to layout a message.
Use @Input to pass the message to the message component.

    ng generate component messages/message

Extended class:

    export class ChatMessage {
      message: string;
      sender: string;
      created: Date;
    }
    const MESSAGES : ChatMessage[] = [
      {message: "Hi Maria", sender: "Hans", created: new Date()},
      {message: "Hi Hans", sender: "Maria", created: new Date()}
    ];



## Task 6 - Call the Service

### Theory
 - Injectables
 - Http
 
### 6.1 Create channels service
 - Create a service class
 - Call the REST service via http
 - Use the service in the component

 
    ng generate service services/channels

### 6.2 Create messages service
 - Do the same for messages


    ng generate service services/messages


## Task 7 - Form to enter messages

### Theory
 - Angular Forms

### 7.1  Sent message component

Create a component to send chat message.

This component must contain a from with two input fields "sender" (email) and "message" 
and a button to send the message.

## Task 8 - Extend messages service to send messages

Extend the message service to send new messages. This should be done with a POST request.
Trigger the new service by pressing the "send"-button.

## Task 9 - Extend REST endpoint for writing message

### Theory
- JPA
- Write to Mongo DB
 
## Task 10 - Read messages from DB

## Task 11 - Validation 

### 11.1 validation frontend
 - validate message length and sender email

### 11.2 bean validation backend

### 11.3 frontend error handling 
 -  general error handling
    for return code >= 4xx
 
