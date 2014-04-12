package bg.kirilov.filesystem2html.utils;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Leni
 */
@Ignore
public class FileUtilsTest {

    /**
     * Test of locateResource method, of class FileUtils.
     */
    @Test
    public void testLocateResource() {
        System.out.println("locateResource");
        String name = "";
        File expResult = null;
        File result = FileUtils.locateResource(name);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of copyFile method, of class FileUtils.
     */
    @Test
    public void testCopyFile_File_File() {
        System.out.println("copyFile");
        File sourceFile = null;
        File destinationFile = null;
        FileUtils.copyFile(sourceFile, destinationFile);
        fail("The test case is a prototype.");
    }

    /**
     * Test of copyFile method, of class FileUtils.
     */
    @Test
    public void testCopyFile_String_String() {
        System.out.println("copyFile");
        String sourceFilePath = "";
        String destinationFilePath = "";
        FileUtils.copyFile(sourceFilePath, destinationFilePath);
        fail("The test case is a prototype.");
    }

    /**
     * Test of readFileAsString method, of class FileUtils.
     */
    @Test
    public void testReadFileAsString() throws Exception {
        System.out.println("readFileAsString");
        File filePath = null;
        String expResult = "";
        String result = FileUtils.readFileAsString(filePath);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
}
