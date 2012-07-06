package bg.uni.sofia.fmi.xml.filesystem2html.model;

/**
 *
 * Factory for creating Nodes
 *
 * @author Leni Kirilov
 */
public class FileSystemNodeFactory {

//    public static FileSystemNode createNode(File xmlFile) throws IOException {
//        String xmlString = FileUtils.readFileAsString(xmlFile);
//        return createNode(xmlString);
//    }
    public static FileSystemNode createNode(String xmlFile) {

        FileSystemNode node = null;

        if (xmlFile.contains("DirectoryNode")) {
            node = new DirectoryNode(xmlFile);
        } else {
            // it is not a directory -> it's a file
            node = new FileNode(xmlFile);
        }

        return node;
    }
}
