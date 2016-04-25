package bg.kirilov.filesystem2html.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author Leni Kirilov
 */

//TODO move images and helper bg.kirilov.filesystem2html.utils.xml and XSD files to /resources folder; aslo run.bat is not OK
//TODO maven dependencies clear
//TODO cover with tests unless a better IO library is found
public class FileUtils {

  public static final String OS_NAME_SYSTEM_PROPERTY_NAME = "os.name";
  public static final String WINDOWS = "Windows";
  public static final String MAC_OS_X = "Mac";

  public static File locateResource(String name) {
    try {
      URL url = ClassLoader.getSystemResource(name);
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

    //skips first 2 rows of a bg.kirilov.filesystem2html.utils.xml
    //TODO uglyyyy
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

  //TODO cover in tests
  public static void openFile(File file) {
    String osName = System.getProperty(OS_NAME_SYSTEM_PROPERTY_NAME);
    if (osName.contains(WINDOWS)) {
      openWindowsStyle(file);

    } else if (osName.contains(MAC_OS_X)) {
      openLinuxStyle(file);
    }
  }

  private static void openLinuxStyle(File file) {
    try {
      Runtime.getRuntime().exec("open " + file.toString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static void openWindowsStyle(File file) {
    try {
      Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler \"" + file.toString() + "\"");
      p.waitFor();
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}