import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FindNameInFiles {

    /**
     * Entry point for app execution.
     *
     * @param args Paths to files
     */
    public static void main(String[] args) {
        // Print current directory
        printWorkingDirectory();

        // Handle error cases
        if(errorCheckFileArguments(args)) {
            return;
        }

        // Read files and perform cross search
        String[] names = processNameFile(args[0]);
        if(names == null) {
            return;
        }
        for(int i = 1; i < args.length; i++) {
            searchFileForNames(args[i], names);
        }
        System.out.println("[INFO] File search complete");
    }

    /**
     * Perform checks against the provided files given through execution arguments.
     *
     * @param args -nameFile The file containing names separated by newline, -fullFile Text where names may be found
     * @return True if error encountered
     */
    private static boolean errorCheckFileArguments(String[] args) {
        if(args.length == 0) {
            System.out.println("[ERROR] Need to supply arguments");
            printManPage();
            return true;
        }
        else if(args.length == 1) {
            System.out.println("[ERROR]  Only a single argument is provided.  " +
                    "Provide names file in first argument and additional arguments of files to search in.");
            printManPage();
            return true;
        }

        File file;
        for(String arg : args) {
            file = new File(arg);
            if(!file.exists()) {
                System.out.println("[ERROR] File not found: " + arg);
                return true;
            }
            if(!file.canRead()) {
                System.out.println("[ERROR] File not readable: " + arg);
                return true;
            }
        }
        System.out.println("[INFO] No errors found with the file\n");
        return false;
    }

    private static void printManPage() {
        final String manpage =
                """
                        ================================================================================================
                                                                 User Manuals                                         \s
                        NAME
                           FindNameInFiles nameFile fullFile ...
                        DESCRIPTION
                           Indicate which given files have occurrence of the names given by the nameFile.
                        ================================================================================================""";
        System.out.println(manpage);
    }

    /**
     * Read file and parse into a list of names.  File is expected to organize names as single file (separated by newline characters).
     * @param filePath Path of the file containing names
     * @return Array of names
     */
    private static String[] processNameFile(String filePath) {
        String fileContent = convertFileToString(filePath);
        if(fileContent.isEmpty()) {
            System.out.println("[ERROR] Names file is empty");
            return null;
        }
        return fileContent.split("\\r?\\n|\\r");
    }

    /**
     * Check the contents of the file against the provided list of names.
     * @param filePath Path of the file to search within
     * @param names List of names to search for
     */
    private static void searchFileForNames(String filePath, String[] names) {
        String fileContent = convertFileToString(filePath);
        for (String name : names) {
            assert fileContent != null;
            if(fileContent.contains(name)) {
                System.out.println("Name <" + name + "> in: ");
                System.out.println(filePath);
            }
        }
        System.out.println();
    }

    private static void printWorkingDirectory() {
        System.out.println("[INFO] Current working directory: ");
        System.out.println(Paths.get(".").toAbsolutePath().normalize());
        System.out.println();
    }

    /**
     * Helper method to convert file to String.
     * @param filePath Path the file to parse into String text
     * @return Return the contents of the file as String
     */
    private static String convertFileToString(String filePath) {
        String fileContent = null;
        try {
            Path path = Paths.get(filePath);
            byte[] bytes = Files.readAllBytes(path);
            fileContent = new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("[ERROR] Error converting file to string");
            System.out.println(e.getMessage());
        }

        return fileContent;
    }
}