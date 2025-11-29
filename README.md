# Mock Data Generator 

## What will the application do? ##

This application will allow users to design and generate collections of mock data. This can include, but is not limited to, fields like names, emails, dates, and numbers, allowing users to export them in common formats such as JSON or CSV. The project will begin as a *console-based application*, later expanding into a *graphical user interface*, exclusively maintaining a separation between the data model and the user interface. By phase 2, I will implement the ability to *save and restore* the full state of the application. Additionally, the application will illuminate *non-trivial model classes* with *branching* and *looping* to generate data patterns, which meets the rubric and applies the course concepts directly. 

## Who will use it? ##

This project will be directed towards **web developers** and **software engineers** who need access to realistic, customizable datasets for testing. This application saves time as generating datasets on demand and reusing them across various projects is faster than manually writing placeholder data. 

## Why is this project of interest to me?

I often test many of my personal projects in web development with sample data. By building this tool, this will not only help me practice good software design principles in java, but will also give me a practical tool that I can reuse in the future. Additionally, this will expand my knowledge as a software engineer and allow me to solve problems I encounter in real-world workflows. 

## Key Features ##

- Creating and managing several different fields
- Generate randomized values with user (e.g., user, product, etc.)specified constraints (e.g., unique emails)
- Save and load datasets for reuse
- Export results in common formats (CSV or JSON)

## User Stories ##

- As a user, I want to be able to **create a new data field** and **add it to a dataset**

- As a user, I want to be able to **view a list of all data fields in my dataset**, allowing me to see what will be generated

- As a user, I want to be able to **remove a data field from a dataset**, to adjust the structure of the mock data

- As a user, I want to be able to **see a preview of the generated mock data**, to confirm the data before exporting to CSV or JSON

- As a user, I want to have the **option to save my dataset to a file**, so I can preserve my work and reuse it later

- As a user, I want to have the **option to load a saved dataset from a file**, so I can continue editing or generating data from where I left off

# Instructions for End User

- You can view the panel that displays the data fields that have already been added to the data set by looking at the center panel of the window

- You can generate the second required action related to the user story "remove a data field from a data set" by selecting an existing data field and clicking the **"Remove Selected Field"** button

- You can generate the first required action related to the user story "see a preview of the generated mock data" by clicking the **"Generate Data Row"** button near the bottom of the window. 

- You can locate my visual component by looking at the top left corner of the window

- You can save the state of my application by clicking the **"Save"** button near the bottom of the window

- You can reload the state of my application by clicking the **"Load"** button near the bottom of the window

# Phase 4: Task 2
Fri Nov 28 14:35:15 PST 2025
Added field "A" (String) to dataset "My dataset"
Fri Nov 28 14:35:22 PST 2025
Added field "B" (Integer) to dataset "My dataset"
Fri Nov 28 14:35:23 PST 2025
Removed field "A" (String) from dataset "My dataset"
Fri Nov 28 14:35:27 PST 2025
Generated mock data row for dataset "My dataset" with 1 fields.

# Phase 4: Task 3
If given the opportunity to refactor my project, reducing coupling between the UI and model components would be helpful. For example, a dedicated DataSetController class would be a nice improvement as it would handle all the interactions between the GUI and the DataSet model. Looking at the project now, the GUI couples the UI and model more tightly than necessary by calling the model for adding and removing fields, and generating the data. 

Another refactoring that could improve my app would be a DataField enum of field-type classes. An example of this would be a STRING_FIELD or a INTEGER_field to make my code more robust and easier to extend. Overall, if I were to implement these refactorings, they would improve maintainability, reduce direct dependencies, and make my project more extensible, all while preserving the project's current functionality. 

