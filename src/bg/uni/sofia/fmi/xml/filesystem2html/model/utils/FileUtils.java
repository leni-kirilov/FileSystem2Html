package bg.uni.sofia.fmi.xml.filesystem2html.model.utils;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author Leni Kirilov
 */

//TODO move images and helper xml and XSD files to /resources folder; aslo run.bat is not OK
//TODO maven dependencies clear
//TODO create maven master POM project and include modules: model, utils, ui, (and tests for each one)
//TODO cover with tests unless a better IO library is found
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
        } catch (IOException ex) {
            throw new RuntimeException("Copy of files failed", ex); //TODO add better description here. Which files. What exception
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
        reader.close(); //TODO try out the try(closable) Java 7 style here

        //TODO why have I included this hack here? Can I not skip this replace at all?
        return fileData.toString().replace("\n</FileSystemTree>", "");
    }
}