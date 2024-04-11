# Find Names in Files
Java app that takes files as arguments and print occurrences of names found within given files.

## Details
* Company: Fluxus
* Developer: Jones Sagabaen
* Date created: April 9, 2024

## Execution
```
> java -classpath src/ FindNameInFiles <FileContainingNames> <TextFileToSearchIn>
```

From the root of the project, compile app using `javac` and execute with arguments of file paths.  The first argument 
is to take the file-path of the file containing a list of names.  One or more arguments after take paths of files of 
text where the names will be searched against.

Full Sample Commands
```
> javac src/FindNamesInFiles.java
> java -classpath src/ FindNameInFiles resources/Names.txt resources/OneNameMatch.txt
```

Output is to be printed to the console.

## Tests
Test class, `FindNameInFilesTests.java`, executable in the command-line from the root of the project.
```
> javac --class-path lib/junit-jupiter-api-5.8.1.jar:lib/apiguardian-api-1.1.2.jar --source-path src/ src/FindNameInFilesTest.java 
> java -jar lib/junit-platform-console-standalone-1.10.2.jar --class-path src/ --scan-classpath
```

The scope of the tests are only unit testing implementations.  Other areas to consider for testing when applicable are 
integration tests when code becomes complex, performance and load testing to observe limitations of the app, and 
security testing if more external library dependencies get introduced.

## Improvements
### General
Notable improvements that can be added:
* ~~Have unit tests executed through command-line~~
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