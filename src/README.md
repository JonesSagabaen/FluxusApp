# Find Names in Files
Java app that takes files as arguments and print occurrences of names found within given files.

## Details
* Company: Fluxus
* Developer: Jones Sagabaen
* Date created: April 9, 2024

## Execution
Compile app using `javac` and execute with arguments of file paths.  The first argument is to take the file-path of the 
file containing a list of names.  One or more arguments after take paths of files of text where the names will be 
searched against.

Sample
```
> javac src/FindNamesInFiles.java
> java -classpath src/ FindNameInFiles resources/Names.txt resources/OneNameMatch.txt
```

Output is to be printed to the console.

## Tests
Unit tests available in `src/FindNameInFilesTest.java`.  Currently executable through Intellij IDE.  Capable of running 
in the command-line provided correct Java jar is provided.

The scope of the tests are only unit testing implementations.  Other areas to consider for testing when applicable are 
integration tests when code becomes complex, performance and load testing to observe limitations of the app, and 
security testing if more external library dependencies get introduced.

## Improvements
### General
Notable improvements that can be added:
* Have unit tests executed through command-line
* Import logging library (i.e. `Log4J`) instead of printing through `System.out`
* Add more methods that perform data validation to files
  * Validate names file uses newline delimiter
  * Validate file input isn't empty
  * Validate reasonable file size
* Concise manual page
### Test Coverage
More tests for better coverage:
* Valid file-paths but files are empty
* Valid file-paths but files contains non-sense (i.e. just special characters)
* Large number of files as arguments
* Large sized files
* Names file not properly formatted using newline delimiter