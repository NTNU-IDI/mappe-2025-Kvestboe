""""

[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/NzRaDbQp)
# Portfolio project IDATT1003
This file uses Mark Down syntax. For more information see [here](https://www.markdownguide.org/basic-syntax/).

[//]: # (TODO: Fill inn your name and student ID)

STUDENT NAME = Kristian Vestbø  
STUDENT ID = 157277

## Project description

[//]: # (TODO: Write a short description of your project/product here.)
This project is a text-based diary application in the terminal. The diary allows for multiple users, with the functionality to create, edit, search and find statistics. The most unique features include tags, which allows for easier management of the multiple entries. Although the application is just a basic diary.

## Project structure

[//]: # (TODO: Describe the structure of your project here. How have you used packages in your structure. Where are all sourcefiles stored. Where are all JUnit-test classes stored. etc.)
The project consists of three directories. Model, storage and ui. The model directory stores the "groundwork" of the project, and is what makes the entries, users and statistics. In the storage directory you can find the register for the different models, entries and users, and is what saves it so it can be used later. In addition to also having the different search methods, which are in the storage classes. As the ui stands for user input it is self-explanatory. The class takes in input from the user and passes it on to the different classes. It also serves as the flow for the diary application.

## Link to repository

[//]: # (TODO: Include a link to your GitHub repository here.)
This link will direct you to the repository:
https://github.com/NTNU-IDI/mappe-2025-Kvestboe

## How to run the project

[//]: # (TODO: Describe how to run your project here. What is the main class? What is the main method?)
I would recommend running the project in an IDE, like IntelliJ or alike, to run the program:

To run the project you will need to run the Main class, which you can find in the same path as the model, storage and ui folders. 
src\main\java\edu\ntnu\iir\bidata\Main.java

The Main class only runs two methods. One to initialize the program, which assigns objects to the storage variables. Ans the other one to run the flow of the program, as what happends when the user gives certain input. The expected behavior of the program should be that the user would get asked to make an author, and then only have the possibility to make a new entry or author. Search and statistics only appear after making an entry in the diary. 

## How to run the tests

[//]: # (TODO: Describe how to run the tests here.)
I would recommend to run the program in IntelliJ, as it creates the .iml files necessary to run the tests. The project was made without Maven which leads to less smart IDE's like VSCode to have a hard time getting the right API's.

## References

[//]: # (TODO: Include references here, if any. For example, if you have used code from the course book, include a reference to the chapter.
Or if you have used code from a website or other source, include a link to the source.)

"""
