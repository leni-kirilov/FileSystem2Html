package bg.uni.sofia.fmi.xml.filesystem2html.model;

import java.io.File;
import java.io.IOException;
import junit.framework.Assert;

/**
 *
 * @author Leni Kirilov
 */
public class Utils {

    /**
     * Creates temp file in the User temp dir.
     * @param fileName
     * @return
     * @throws IOException 
     */
    public static File createTestFile(String fileName) throws IOException {
        String tempDirPath = getTempDir().getAbsolutePath();
        File testFile = new File(tempDirPath, fileName);
        testFile.deleteOnExit();
        Assert.assertTrue("File was not created successfully", testFile.createNewFile());
        
        return testFile;
    }

    public static File createTestDir(String dirName) throws IOException {
        String tempDirPath = getTempDir().getAbsolutePath();
        File testDir = new File(tempDirPath, dirName);
        testDir.deleteOnExit();
        Assert.assertTrue("Directory was not created successfully", testDir.mkdirs());

        return testDir;
    }

    // Private methods
    private static File getTempDir() {
        String tempDirPath = System.getProperty("java.io.tmpdir");
        return new File(tempDirPath);
    }
}
