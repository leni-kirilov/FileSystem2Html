package bg.uni.sofia.fmi.xml.filesystem2html.model;

/**
 *
 * Contains some XML examples of FileNodes and DirectoryNodes
 * 
 * @author Leni
 */
public class XmlExamples {

    public final static String STANDARD_FILE_NODE = "<FileNode Name=\"NewFile.xml\" Size=\"256\" "
            + "LastDateChanged=\"2010-12-12T06:38:55\" "
            + "Hidden=\"true\" Readable=\"true\" Writable=\"true\" Executable=\"true\" />";
    
    public final static String EMPTY_DIRECTORY_NODE = "<DirectoryNode Name=\"EmptyDir\" />";
    
    public final static String STANDARD_DIRECTORY_NODE = "<DirectoryNode Name=\"WithFile\">"
            + EMPTY_DIRECTORY_NODE
            + STANDARD_FILE_NODE
            + "</DirectoryNode>";
}
