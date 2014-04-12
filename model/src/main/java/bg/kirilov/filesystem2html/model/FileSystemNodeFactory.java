package bg.kirilov.filesystem2html.model;

import java.io.File;

/**
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

        FileSystemNode node;

        if (xmlString.contains(DirectoryNode.DIRECTORY_NODE)) {
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
     * @return a FileSystemNode instance
     */
    public static FileSystemNode createNode(File path) {
        FileSystemNode node;

        if (path.isDirectory()) {
            node = new DirectoryNode(path);
        } else {
            node = new FileNode(path);
        }

        return node;
    }
}
