package bg.uni.sofia.fmi.xml.filesystem2html.model;

import java.io.File;

/**
 *
 * Factory for creating Nodes
 *
 * @author Leni Kirilov
 */
public class FileSystemNodeFactory {

    /**
     * Parses the string and returns the correct Node type.
     *
     * @param xmlString - string holding FileSystemNode xml
     * @return node of correct type
     */
    public static FileSystemNode createNode(String xmlString) {

        FileSystemNode node = null;

        //TODO extract magic string
        if (xmlString.contains("DirectoryNode")) {
            node = new DirectoryNode(xmlString);
        } else {
            // it is not a directory -> it's a file
            node = new FileNode(xmlString);
        }

        return node;
    }

    /**
     * Creates an XML representation of a file/directory pointed by the path parameter
     *
     * @param path - File object pointing to a place in the file system
     * @return
     */
    public static FileSystemNode createNode(File path) {
        FileSystemNode node = null;
        
        if (path.isDirectory()) {
            node = new DirectoryNode(path);
        } else {
            node = new FileNode(path);
        }

        return node;
    }
}
