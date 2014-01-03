package bg.uni.sofia.fmi.xml.filesystem2html.model.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;

/**
 *
 * @author Leni Kirilov
 */
//TODO cover with tests inless a better IO library is found
public class FileUtils {

    private static String MAIN_PACKAGE;

    static {
        String s = FileUtils.class.getCanonicalName().replace(".", "/");
        int len = FileUtils.class.getSimpleName().length();
        MAIN_PACKAGE = s.substring(0, s.length() - len);
    }

    public static File locateResource(String name) {
        try {
            URL url = ClassLoader.getSystemResource(MAIN_PACKAGE + name);
            return new File(url.toURI());
        } catch (URISyntaxException ex) {
            throw new RuntimeException("failed lookup", ex);
        }
    }

    //TODO search of a better IO library to include in the project.
    public static void copyFile(File sourceFile, File destinationFile) {
        try {
            System.out.println("sourceFile= " + sourceFile.getAbsolutePath());
            System.out.println("destinationFile= " + destinationFile.getAbsolutePath());

            InputStream in = new FileInputStream(sourceFile);
            OutputStream out = new FileOutputStream(destinationFile);
            
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Copy of files failed", ex);
        } catch (IOException e) {
            throw new RuntimeException("Copy of files failed", e);
        }
    }

    public static void copyFile(String sourceFilePath, String destinationFilePath) {
        copyFile(new File(sourceFilePath), new File(destinationFilePath));
    }

    public static String readFileAsString(File filePath) throws IOException {
        StringBuilder fileData = new StringBuilder(1000);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead = 0;

        //skips first 2 rows of a xml
        reader.readLine();
        reader.readLine();
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();

        //TODO why have I included this hack here? Can I not skip this replace at all?
        return fileData.toString().replace("\n</FileSystemTree>", "");
    }
}