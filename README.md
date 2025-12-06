""""

# Portfolio project IDATT1003

STUDENT NAME = Kristian Vestbø  
STUDENT ID = 157277

## Project description

This project is a text-based diary application in the terminal. The diary allows for multiple users, with the functionality to create, edit, search and find statistics. The most unique features include tags, which allows for easier management of the multiple entries. Although the application is just a basic diary.

## Project structure

The project consists of three directories. Model, storage and ui. The model directory stores the "groundwork" of the project, and is what makes the entries, users and statistics. In the storage directory you can find the register for the different models, entries and users, and is what saves it so it can be used later. In addition to also having the different search methods, which are in the storage classes. As the ui stands for user input it is self-explanatory. The class takes in input from the user and passes it on to the different classes. It also serves as the flow for the diary application.
Likewise the test classes has two folders. The first one for the model tests. The second one for the storage tests. 

File tree made by AI:
src/
├── main/
│   ├── main.iml
│   └── java/
│       └── edu/
│           └── ntnu/
│               └── iir/
│                   └── bidata/
│                       ├── Main.java
│                       ├── model/
│                       │   ├── Author.java
│                       │   ├── Entry.java
│                       │   └── Statistic.java
│                       ├── storage/
│                       │   ├── AuthorManager.java
│                       │   └── EntryManager.java
│                       ├── ui/
│                       │   ├── IoHandler.java
│                       │   └── Menu.java
│                       └── utils/ (empty)
└── test/
    ├── test.iml
    └── java/
        ├── Test.java
        ├── modelTest/
        │   ├── AuthorTest.java
        │   ├── EntryTest.java
        │   └── StatisticTest.java
        └── storageTest/
            ├── AuthorManagerTest.java
            └── EntryManagerTest.java

## Link to repository

This link will direct you to the repository:
https://github.com/NTNU-IDI/mappe-2025-Kvestboe

## How to run the project

I would recommend running the project in an IDE, like IntelliJ or alike, to run the program:

To run the project you will need to run the Main class, which you can find in the same path as the model, storage and ui folders. 
src\main\java\edu\ntnu\iir\bidata\Main.java

The Main class only runs two methods. One to initialize the program, which assigns objects to the storage variables. Ans the other one to run the flow of the program, as what happends when the user gives certain input. The expected behavior of the program should be that the user would get asked to make an author, and then only have the possibility to make a new entry or author. Search and statistics only appear after making an entry in the diary. 

## How to run the tests

I would recommend to run the program in IntelliJ, as it creates the .iml files necessary to run the tests. The project was made without Maven which leads to less smart IDE's like VSCode to have a hard time getting the right API's.

## References

The project did not rip any code of any place, but multiple sites were used to attain knowledge:

https://www.w3schools.com/
Used to find methods and explanations for methods from API.

https://www.geeksforgeeks.org/
Used to learn how to make UML diagram.

AI was used minimally as to learn more. Although it was used for explaining stringbuilder and learning some methods in Java. Also to make the filetree you see in this readme. The AI model used was ChatGPT 5.


"""
