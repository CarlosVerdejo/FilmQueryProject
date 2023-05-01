# FilmQueryProject

# Description
This project displays a menu that allows you to search for films on a database based on 
keywords and a film's id. Through the use of "PreparedStatements", when a film is searched,
the app executes a search through the database and displays a film's title, release year, 
description, language, rating and cast. 

"FilmQueryApp.java" is the user interface. It displays a menu and prompts the user to select
from the given menu options. A switch is then used to execute the selected option. 

"DatabaseAccessorObject.java" contains the methods that execute the searches on the database.
The searches are made by first connecting to the SQL database, declaring a string that
contains the SQL search statement, pre-compiling the search statement and executing the statement. The results are then used to create either "Film" or "Actor" Object and stored into an ArrayList as needed. 

# Technologies Used
Java: DatabaseAccessor, PreparedStatements, Connection/DriverManager, ResultSet, Try Catch blocks, encapsulation, ArrayList, switches. 

SQL

# Lessons Learned
The main lessons learned in this project were learning how to construct search statements in order to retrieve the correct data from an SQL database.
As well as learning how to connect to the database using Java and executing those statements.