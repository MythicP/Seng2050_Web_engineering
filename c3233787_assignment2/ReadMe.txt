To Run this program copy the "c3233787_assignment2" folder into tomcat/webapps
Start up tomcat and navigate to "http://localhost:8080/c3233787_assignment2/HomePage" to run the page

This program is made of 4 java files(1 servlet, 2 java beans, 1 standard), a jsp file, a css file and a java script

HomePage.java:
This is the main program that creates a servlet to run the homepage
This hompage lets the user start a new game or load an exsisting game given a username
uses a session object to create a SaveData and 
a GameSave bean to maintain the list of saves

GamePage.jsp:
Runs the entirety of the deal or no-deal game
Creates a SaveData and a GameSave bean to maintain the list of saves

HtmlGen.java:
Is called by HomePage and BookingPage to generate html code based on passed infomation

GameSave.java:
Maintains all deatails of the game state and is defined by a user id 

SaveData.java:
Maintains all saved games(GameSave) as a list to be refrenced

validate.js:
used to check username exsist
called from homepage