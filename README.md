# Babysitter Kata: Andrew Antes

## Build Instructions

1. Clone this repository and navigate to its root directory.
2. Run `gradle build` at the command line.
    - This will create a build directory in the project root.
    - To view test results, navigate to `./build/reports/tests/test` and open `index.html` in the browser
    - To run the application driver, navigate to `./build/classes/java/main` and run
        `java Driver`.
        
        NOTE: This is a command-line application. The on-screen instructions should
        be sufficient.
        
        NOTE: The start/end dates require a specific format (indicated by the 
        command-line interface). The times are in military format (00:00 - 24:00)
        
        Example datetime inputs:
        
        ```
        2019.05.07 at 17:00
        2019.05.08 at 4:00
        2019.05.07 at 23:00
        2019.05.08 at 1:00
        ```
        
        