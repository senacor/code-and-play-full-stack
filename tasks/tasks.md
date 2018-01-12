
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
    - REST verbs: http://www.restapitutorial.com/lessons/httpmethods.html

### 2.0 Checkout "chat-app" master branch 
 - Checkout "chat-app" master branch.
 - It contains an empty Spring Boot application (as created in task 1.1) with the integration of an Angular frontend.

### 2.1 Channel Service
 - A channel can be represented by a class with just one property "name". We suggest to create all model classes in a subpackage called "domain".
 - Create a service	"loadChannels" to load all existing channels. For now just return a static list.
 - Create a service "existsChannel" to check if a channel exists.

### 2.2 Messages Service
A message can be represented by a class with the following properties:
- id (String) - A unique id for this message.
- channel (String) - The communication channel this message was posted in.
- sender (String) - The name of the sender of the message.
- message (String) - A String that contains the message text.
- creationTimestamp (Instant) - A timestamp to indicate message creation / sent time.

Initialize the creationTimestamp to now in the constructor of the ChatMessage.

Create a service to load all existing messages for a channel. For now just return a static list.

Only return messages for exiting channels. Use the channel service to check if a channel exits and throw a "ChannelNotFoundException" for not exiting channels.
	
### 2.3 Create REST endpoints
 - Create a REST endpoint for both resources
    - /api/v1/channels
    - /api/v1/channels/{channel}/messages
 - For now we only support GET.
 - Use the services to fetch the data.
 - The messages endpoint should return the messages for one channel and response with 404 for all other channels.

### 2.4 Integration test the REST endpoints	
Create an integration test with Spring mock mvc.
 
Information about how to write test with Mock Mvc
    - http://www.baeldung.com/integration-testing-in-spring
    
You need JsonPath for to do assertions about the returned JSON.
    - https://github.com/json-path/JsonPath
	
## Task 3 - Create an Angular frontend	

### Theory
- Angular basics
- Angular CLI
    - https://github.com/angular/angular-cli
	- https://www.npmjs.com/package/@angular/cli
	
### 3.0 - Setup
Install Angular CLI
	
	# if old versions exist, uninstall first 
	npm uninstall -g angular-cli
	npm uninstall -g @angular/cli
	# clean cache - if npm version is > 5 then use `npm cache verify` to avoid errors (or to avoid using --force) 
	npm cache clean
	# install latest Anular CLI	
	npm install -g @angular/cli@latest


### 3.1 - Create a frontend
Use the CLI to create a new frontend

    ng new my-ng-fe
    cd my-ng-fe
    ng serve --proxy proxy.conf.js --open

The '--open' option opens the browser on http://localhost:4200/

The '--proxy proxy.conf.js' option enables the proxy configured in the file. Our Angular application is running on a dev server with port 4200 but our Spring application with the API services is running on port 8080.
In the proxy conf file is configured to send request for http://localhost:4200/api/* to http://localhost:8080/api/*. 

If `ng serve` complains about that some files are "not part of the compilation", this could help:

    ng serve --proxy proxy.conf.js --preserve-symlinks

### 3.2 Inspect the created files 
Look into the folder "my-ng-fe" and inspect the created files.
 
Especially look at:
- package.json
- tsconfig.json
- src/main.ts
- src/index.html
- src/app/app.module.ts 
- src/app/app.component.ts
- src/app/app.component.html
- src/app/app.component.spec.ts

### 3.3 Run tests

You can run the unit and e2e tests with:

 ng test
 
 ng e2e
 

## Task 4 - Modify the frontend

### Theory
 - [Bootstrap documentation](http://getbootstrap.com/docs/3.3/css/)
 - [Type Script Classes](https://www.typescriptlang.org/docs/handbook/classes.html)
 - [Angular Template Syntax](https://angular.io/guide/template-syntax)

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

Use Bootstrap 'container', 'row' 'col-md-2' and 'text-center' CSS classes to improve the markup of 'app.component.html'. e.g.:

    <div class="container">
      <div class="row">
        <div class="col-md-4 text-center hidden-sm hidden-xs">
			<img width="100" alt="Angular Logo" ...
		</div>
        <div class="col-md-4 text-center">
			<h1>Welcome to {{ title }}!</h1>
		</div>
        <div class="col-md-4 text-center user">
			<span>Hello User</span>
		</div>
      </div>
    </div>

Costume CSS can be added per component. Add the followin css class to "app.component.css"

	.user {
		margin-top: 25px;
		text-align: center;
	}

UI Example:
![UI Task 4.1](https://raw.githubusercontent.com/senacor/code-and-play-full-stack/master/tasks/task-4.1/task-4.1-ui.png)

### 4.2 Show the current user
Change the headline "Welcome to {{ title }}!" into "Chat", or whatever you like.

We like to show the current user (with name and email) on the right side. For this we create a model class to represent the user.

	ng generate class shared/user --type=model
	
Finish the created model class and replace the property title in app.component.ts with an instance of the user model and and change the html to show it.

Adjust and rerun also the unit and  e2e test.

UI Example:
![UI Task 4.2](https://raw.githubusercontent.com/senacor/code-and-play-full-stack/master/tasks/task-4.2/task-4.2-ui.png)

## Task 5 - Showing Messages

### Theory
 - [Angular Display Data](https://angular.io/guide/displaying-data)
 - [Angular Pipe](https://angular.io/guide/pipes)
 - [Angular Cheat Sheet](https://angular.io/guide/cheatsheet)

### 5.1 Show some simple messages
First create a model class ChatMessage. For now we only add a single property "message".

	ng generate class shared/chat-message --type=model

Create a component to show the chat messages.

	ng generate component messages

Display the new messages component in a second row and remove the h2 and the list of links.
Define an array of ChatMessages in the message component e.g.

    const MESSAGES : ChatMessage[] = [
	  new ChatMessage("Hello World!"),
	  new ChatMessage("This is another message.")
	];

We will later replace this static array with by calling our REST endpoint.

To layout each message you can use a [Bootstrap panel](https://www.w3schools.com/bootstrap/bootstrap_panels.asp).

UI Example:
![UI Task 5.1](https://raw.githubusercontent.com/senacor/code-and-play-full-stack/master/tasks/task-5.1/task-5.1-ui.png)

### 5.2 Add more attributes to a message
Extend the message model to include sender and creationTimestamp and extend the mock data, e.g.

    const MESSAGES : ChatMessage[] = [
	  new ChatMessage("Hello World!", "sender@test.de", new Date()),
	  new ChatMessage("This is another message.", "sender@test.de", new Date())
	];

Show the sender and the creation time in the header of the Bootstrap panel.

You can use a [Angular pipe](https://angular.io/guide/pipes) to format the date object.

UI Example:
![UI Task 5.2](https://raw.githubusercontent.com/senacor/code-and-play-full-stack/master/tasks/task-5.2/task-5.2-ui.png)

## Task 6 - Form to enter messages

### Theory
 - [Angular template-driven Forms](https://angular.io/guide/forms)
 - [Angular Component Interaction](https://angular.io/guide/component-interaction)
  
### 6.1 Select a channel
Let's create the UI to select the current communication channel.

For this we extend our data model with a channel class that hat one property "name" for the channel's name.

	ng generate class shared/channel --type=model
	
And create a component for the ui.

	ng generate component channel-selector

Define a static array of channels and use a form select input field to enable the user to pick a channel.

Use `(ngModelChange)="onChannelSelected()"` at the `select` element to get notified when the user selected a new channel.
	
UI Example:
![UI Task 6.1](https://raw.githubusercontent.com/senacor/code-and-play-full-stack/master/tasks/task-6.1/task-6.1.png)

###	6.2 Component Interaction
We need the selected channel to load the messages. Therefore we need to pass the selected channel from the channel selector component to the messages component.

We do this via the parent by using the @Input and @Output decorations as described in [Angular Component Interaction](https://angular.io/guide/component-interaction).

In the messages component you can use a setter for the current channel to have a hook to do something when the channel changes.

### 6.3 Form to post a message

Create a component to send a chat message and add it into the message component above the messages and use @Output to pass new messages to the message list.

	ng generate component channel-form
	
This component must contain a from with one input field for the "message" and a button to submit the form. 
By now we just hard code the sender of the messages.


## Task 7 - Call the Service

### Theory
 - [Angular Dependency Injection](https://angular.io/guide/dependency-injection)
 - [Angular Http Client](https://angular.io/guide/http)
 
 - [RxJS Observable](http://reactivex.io/rxjs/class/es6/Observable.js~Observable.html)
 - [RxJS Observable map](http://reactivex.io/rxjs/class/es6/Observable.js~Observable.html#instance-method-map)
 
### 7.1 Get channels via service call
Create a service class,
call the REST service via http using the Angular Http Client 
and use the service in the channel selector component to replace the static channel array.

    ng generate service services/channels
	
Hints:  

To cast the http call result to the expected type you can use the map operator of the RxJS Observable returned by the http.get call.
	
	.map(data => data as Channel[])
	
To use the map operator you have to import it in the service class.

	import 'rxjs/add/operator/map'
	
An Observable can be converted into a Promis with:

	observable.toPromise()
	

### 7.2 Get messages via service call
Do the same for messages.

    ng generate service services/messages

And use the service in the messages component to replace the static message array.

	
## Task 8 - Extend messages service to send messages

Extend the message service to send new messages. This should be done with a POST request.
Trigger the new service by pressing the "send"-button.


## Task 9 - Add a database

### Theory
- Mongo DB
- Spring Data
    - https://spring.io/guides/gs/accessing-data-mongodb/
    - https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#repositories.core-concepts
 
### 9.1 Setup MongoDB
Add dependencies for Spring Data and embedded Mongo DB:

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-mongodb</artifactId>
    </dependency>
    <dependency>
        <groupId>de.flapdoodle.embed</groupId>
        <artifactId>de.flapdoodle.embed.mongo</artifactId>
    </dependency>

Start your application an check the log for the Mongo DB startup.
 
### 9.2 Spring Data Repository for ChatMessages
Follow https://spring.io/guides/gs/accessing-data-mongodb/
 - Annotate the ChatMessage entity to store it into the MongoDB
 - Create a ChatMessageRepository
 - Save some ChatMessages for channel "dev" on application startup.
 - And write a simple test for the repository that saves and loads some ChatMessages.

### 9.3 Read messages from the database
Load messages by channel and ordered by creation time. 
    
    List<ChatMessage> findAllByChannelOrderByCreationTimestampAsc(String channel)

Add this method to the ChatMessageRepository and use it in the ChatMessageService.
 
## Task 10 - Writing of chat messages

### Theory
 - REST verbs: http://www.restapitutorial.com/lessons/httpmethods.html
 
### 10.1 A service to store messages
Extend chat message service with a create method that should take three String parameters (channel, sender, message)
and it should return the saved ChatMessage. 

Use the ChatMessage repository to save the message into the database.

As in the load method throw also a "ChannelNotFoundException" if the channel does not exist.

### 10.2 A POST endpoint to save new chat message
Allow POST for the resource /channels/{channel}/messages.

Hint: Use the UriComponentsBuilder to build the location URI returned by this endpoint.
 
## Task 11 - Validation 

### 11.1 validation frontend
 - validate message length and sender email

### 11.2 bean validation backend

### 11.3 frontend error handling 
 -  general error handling
    for return code >= 4xx
 
