package bg.uni.sofia.fmi.xml.filesystem2html.model;

import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * Represents file/dir from the file system. Has convenient methods.
 *
 * @author Leni Kirilov
 */
public abstract class FileSystemNode {

    public static final String NAME = "Name";
    public static final String FILENODE = "FileNode";
    protected String name;

    /**
     * Get file/dir name
     *
     * @return
     */
    public abstract String getName();

    /**
     * Get XML representation
     *
     * @return
     */
    public abstract Element toXML();

    public abstract Element toXML(Document document);

    /**
     * Create files and dirs to a specified path using the data from the object.
     *
     * @param dirPath
     */
    public abstract void create(String dirPath);

    /**
     * Create files and dirs to a specified directory using the data from the object.
     *
     * @param directory
     */
    public abstract void create(File directory);
}
