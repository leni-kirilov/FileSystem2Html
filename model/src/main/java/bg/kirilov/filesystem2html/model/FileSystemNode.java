package bg.kirilov.filesystem2html.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;

/**
 * Represents file/dir from the file system. Has convenient methods.
 *
 * @author Leni Kirilov
 */
public abstract class FileSystemNode {

    public static final String NAME = "Name";
    public static final String FILENODE = "FileNode";
    protected String name;

    public abstract String getName();

    /**
     * Get XML representation
     * @return XML Element instance, representing the FileNode
     */
    public abstract Element toXML();

    public abstract Element toXML(Document document);

    /**
     * Create files and dirs to a specified path using the data from the object.
     *
     * @param dirPath path to a directory in the File System
     */
    public abstract void create(String dirPath);

    /**
     * Create files and dirs to a specified directory using the data from the object.
     *
     * @param directory
     */
    public abstract void create(File directory);
}
