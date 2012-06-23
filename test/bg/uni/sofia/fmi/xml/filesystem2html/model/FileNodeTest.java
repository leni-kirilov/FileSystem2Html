package bg.uni.sofia.fmi.xml.filesystem2html.model;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

//TODO make the tests working again!
//TODO convert project to Java7. Use Java7 features

/**
 *
 * @author Leni Kirilov
 */
public class FileNodeTest {

    public static String STANDARD_FILE_NODE = "<FileNode Name=\"NewFile.xml\" Size=\"256\" "
            + "LastDateChanged=\"2010-12-12T06:38:55\" "
            + "Hidden=\"true\" Readable=\"true\" Writable=\"true\" Executable=\"true\" />";

    @Test
    public void testCreation_Positive() {
        //TODO fix tests to be executable everywhere! No absolute paths. Only relative ones
        String realPath = "D:/Coding/DOT NET Projects/Projects/WPFHomeWorks/FileTree2XMLVisualizer/NodesHelper.cs";
        FileNode file = new FileNode(new File(realPath));

        assertEquals("NodesHelper.cs", file.getName());
    }

    public void testCreation_Negative_DoesNotExist() {
        String unrealPath = "D:/Coding/DOT NET Projects/Projects/WPFHomeWorks/FileTree2XMLVisualizer/NodesHelper.csssss";
        new FileNode(new File(unrealPath));
    }

    @Test(expected = DirectoriesApplicationException.class)
    public void testCreation_Negative_IsNotFile() {
        String unrealPath = "D:/Coding/DOT NET Projects/Projects/WPFHomeWorks/FileTree2XMLVisualizer/";
        new FileNode(new File(unrealPath));
    }

//    @Test
//    public void testCorrectXMLOutput() {
//        String xmlInput = "<FileNode Name=\"NewFile.xml\" Size=\"256\" LastDateChanged=\"2010-12-12T06:38:55\" />";
//        String output = new FileNode(xmlInput).toXML();
//        assertEquals(true, output.Contains(xmlInput), "/n" + output + "/n" + xmlInput);
//    }
    @Test
    public void testCorrectXMLInput() {
        FileNode file = new FileNode(STANDARD_FILE_NODE);

        assertEquals("NewFile.xml", file.getName());
        assertEquals(256, file.getSize());
        assertEquals(true, file.isHidden());
        assertEquals(true, file.isReadable());
        assertEquals(true, file.isWritable());
        assertEquals(true, file.isExecutable());
    }

    @Test(expected = DirectoriesApplicationException.class)
    public void testIncorrectXMLInput_DirectoryNodeXML() {
        String inputXML = "<DirectoryNode Name=\"WithFile\">"
                + STANDARD_FILE_NODE
                + "</DirectoryNode>";
        new FileNode(inputXML);
    }

    @Test
    public void testCreateFileNodeFromFileNodeToXML() {
        String xmlInput = "<FileNode Name=\"NewFile.xml\" Size=\"256\" LastDateChanged=\"2010-12-12T06:38:55\"/>";
        FileNode file = new FileNode(xmlInput);
        new FileNode(file.toXML());
    }

    @Test
    public void testCreate_Positive() {
        String currentDir = "c:/";
        try {
            new FileNode(STANDARD_FILE_NODE).create(currentDir);
        } catch (Exception e) {
        } finally {
            new File(currentDir + File.separator + "NewFile.xml").delete();
        }
    }

    @Test(expected = DirectoriesApplicationException.class)
    public void testCreation_Negative_TwoFileNodes() {
        String xmlInput = STANDARD_FILE_NODE + STANDARD_FILE_NODE;
        new FileNode(xmlInput);
    }
}
