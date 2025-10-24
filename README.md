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

