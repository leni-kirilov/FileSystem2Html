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
        String tempDirPath = System.getProperty("java.io.tmpdir");
        File testFile = new File(tempDirPath, fileName);
        testFile.createNewFile();
        testFile.deleteOnExit();
        
        Assert.assertTrue(testFile.isFile());
        return testFile;
    }
}
