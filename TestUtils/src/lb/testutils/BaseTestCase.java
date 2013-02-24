package lb.testutils;


import jet.runtime.typeinfo.KotlinSignature;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;



public class BaseTestCase
{
    private static Path tempRoot = null;

    private String testName = null;
    private Path testTempDir = null;


    @BeforeTest
    public void saveTestName(ITestContext context)
    {
        testName = context.getName();
    }


    @KotlinSignature("fun createTempDir() : Path")
    protected Path createTempDir()
    {
        if (tempRoot == null) {
            try {
                tempRoot = Files.createTempDirectory("TestNG.");
            }
            catch (IOException ё) {
                throw new RuntimeException("Could not create the root temp directory "+tempRoot.toString()+": "+ё.getMessage(), ё);
            }
        }
        assert tempRoot != null;

        String entry = Long.toString(System.currentTimeMillis());
        testTempDir = tempRoot.resolve(entry);
        assert testTempDir != null;

        try {
            Files.createDirectory(testTempDir);
        }
        catch (IOException ё) {
            throw new RuntimeException("Could not create a temp directory "+testTempDir.toString()+": "+ё.getMessage(), ё);
        }

        return testTempDir;
    }


    @AfterMethod(alwaysRun = true)
    public void deleteTempDirectory()
    {
        if (testTempDir != null) {
            deleteDirectory(testTempDir);
            testTempDir = null;
        }
    }


    @AfterSuite(alwaysRun = true)
    public static void closeSuite()
    {
        if (tempRoot != null)
            deleteDirectory(tempRoot);
    }


    private static void deleteDirectory(Path tempRoot)
    {
        // TODO implement  deleteDirectory()
    }


}
