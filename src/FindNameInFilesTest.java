import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class FindNameInFilesTest {
    private final PrintStream standardOut = System.out;
    private static final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeAll
    public static void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void matchingSingleFile() {
        String expected = "Name <Adams> in: \n" +
                "resources/OneNameMatch.txt";

        String namesFile = "resources/Names.txt";
        String searchFile = "resources/OneNameMatch.txt";
        String[] arguments = {namesFile, searchFile};
        FindNameInFiles.main(arguments);
        String actual = outputStreamCaptor.toString();
        assertTrue(actual.contains(expected));
    }

    @Test
    void matchingMultipleFiles() {
        String expected = """
                Name <Adams> in:\s
                resources/OneNameMatch.txt

                Name <Adams> in:\s
                resources/MultipleNameMatch.txt
                Name <Baker> in:\s
                resources/MultipleNameMatch.txt
                Name <Jones> in:\s
                resources/MultipleNameMatch.txt""";

        String namesFile = "resources/Names.txt";
        String searchFile1 = "resources/OneNameMatch.txt";
        String searchFile2 = "resources/MultipleNameMatch.txt";
        String[] arguments = {namesFile, searchFile1, searchFile2};
        FindNameInFiles.main(arguments);
        String actual = outputStreamCaptor.toString();
        assertTrue(actual.contains(expected));
    }

    @Test
    void noNameMatch() {
        String expected = """
                [INFO] No errors found with the file


                [INFO] File search complete""";

        String namesFile = "resources/Names.txt";
        String searchFile = "resources/NoNameMatch.txt";
        String[] arguments = {namesFile, searchFile};
        FindNameInFiles.main(arguments);
        String actual = outputStreamCaptor.toString();
        assertTrue(actual.contains(expected));
    }

    @Test
    void noArgumentsProvided() {
        String expected = "[ERROR] Need to supply arguments";

        String[] arguments = {};
        FindNameInFiles.main(arguments);
        String actual = outputStreamCaptor.toString();
        assertTrue(actual.contains(expected));
    }

    @Test
    void noPermissionsError() {
        File file = new File("resources/NoPermissions.txt");
        assertTrue(file.setReadable(false));
        String expected = "[ERROR] File not readable: resources/NoPermissions.txt";

        String namesFile = "resources/Names.txt";
        String searchFile = "resources/NoPermissions.txt";
        String[] arguments = {namesFile, searchFile};
        FindNameInFiles.main(arguments);
        String actual = outputStreamCaptor.toString();
        assertTrue(actual.contains(expected));
    }

    @Test
    void emptyNamesFile() {
        String expected = "[ERROR] Names file is empty";

        String namesFile = "resources/EmptyNames.txt";
        String searchFile = "resources/OneNameMatch.txt";
        String[] arguments = {namesFile, searchFile};
        FindNameInFiles.main(arguments);
        String actual = outputStreamCaptor.toString();
        assertTrue(actual.contains(expected));
    }

    @Test
    void duplicateFilePair() {
        String expected = "[ERROR] Duplicate files found: [DuplicateA.txt, DuplicateB.txt]";

        String namesFile = "resources/Names.txt";
        String searchFile1 = "resources/DuplicateA.txt";
        String searchFile2 = "resources/DuplicateB.txt";
        String[] arguments = {namesFile, searchFile1, searchFile2};
        FindNameInFiles.main(arguments);
        String actual = outputStreamCaptor.toString();
        assertTrue(actual.contains(expected));
    }

    @Test
    void duplicateAmongFiles() {
        String expected = "[ERROR] Duplicate files found: [DuplicateA.txt, DuplicateB.txt]";

        String namesFile = "resources/Names.txt";
        String searchFile1 = "resources/OneNameMatch.txt";
        String searchFile2 = "resources/DuplicateA.txt";
        String searchFile3 = "resources/NoNameMatch.txt";
        String searchFile4 = "resources/DuplicateB.txt";
        String[] arguments = {namesFile, searchFile1, searchFile2, searchFile3, searchFile4};
        FindNameInFiles.main(arguments);
        String actual = outputStreamCaptor.toString();
        assertTrue(actual.contains(expected));
    }

    @Test
    void mulitpleDuplicateFiles() {
        String expected = "[ERROR] Duplicate files found: [DuplicateA.txt, DuplicateB.txt, DuplicateC.txt]";

        String namesFile = "resources/Names.txt";
        String searchFile1 = "resources/OneNameMatch.txt";
        String searchFile2 = "resources/DuplicateA.txt";
        String searchFile3 = "resources/NoNameMatch.txt";
        String searchFile4 = "resources/DuplicateB.txt";
        String searchFile5 = "resources/DuplicateC.txt";
        String[] arguments = {namesFile, searchFile1, searchFile2, searchFile3, searchFile4, searchFile5};
        FindNameInFiles.main(arguments);
        String actual = outputStreamCaptor.toString();
        assertTrue(actual.contains(expected));
    }
}