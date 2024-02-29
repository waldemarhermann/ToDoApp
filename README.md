# About

This project is a ToDo application that allows users to manage their daily tasks effectively. Built with Java Swing for the graphical user interface, it incorporates several components to create, display, and manage tasks. 
The main features include adding new tasks, marking tasks as completed, filtering tasks, and persisting tasks between sessions by saving to and loading from a file. The application is structured around a central ToDoManager 
class, which manages the tasks list, and a ToDoWindow class that renders the GUI and handles user interaction. Additional functionalities are provided through custom classes such as TimedToDo for tasks with deadlines and 
CustomWindowAdapter for saving tasks upon application closure.

## What I have learned

– __Java Swing for Building GUIs:__ How to use Java Swing components to build a user-friendly interface for desktop applications.

– __Managing Application State:__ Techniques for managing the state of an application, including task lists and UI state, in a centralized manner with Java.

– __Event Handling in Java:__ Implementing action listeners to handle user interactions such as button clicks and checkbox changes.

– __File I/O in Java:__ Reading from and writing to text files in Java for data persistence, allowing tasks to be saved between application runs.

– __Error Handling:__ Implementing error handling in file operations and other critical sections of the application to ensure stability.

– __Component Reusability and Composition:__ Designing the application in a modular way, allowing for the reuse and composition of components such as the task display and input sections.

– __Lifecycle Management:__ Managing the lifecycle of a GUI application, including initializing the state, updating the UI in response to state changes, and performing cleanup actions like saving tasks to a file upon exiting.

## Starting the Application

The executable JAR file for the ToDoApp can be downloaded from the project's [Releases](https://github.com/YOUR_USERNAME/YOUR_REPOSITORY/releases) section. Look for the latest version `ToDoApp_v1.0.jar` to run the application locally.
