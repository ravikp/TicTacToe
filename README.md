TicTacToe
=========

Pre-requisites
--------------
- Connection to internet to download junit libraries for testing
- Gradle 3.0
- JDK 1.8.0_45 update or higher

Steps to build the codebase
---------------------------

Run the below commands after downloading the codebase

- To compile the codebase run below command at bash prompt
  - ``` ./gradlew clean build ```
- To run the application
  - ``` java -jar ./build/libs/TicTacToe-1.0.jar ```

Additional Notes
----------------
Application works for tictactoe boards that are greater than 3. 
But the UI formatting for pretty printing the table is not done
because time was spent on core logic of the application. 