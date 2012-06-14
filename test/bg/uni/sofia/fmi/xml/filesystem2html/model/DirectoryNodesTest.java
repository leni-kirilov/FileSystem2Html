package bg.uni.sofia.fmi.xml.filesystem2html.model;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;
import static bg.uni.sofia.fmi.xml.filesystem2html.model.FileNodeTest.STANDARD_FILE_NODE;

/**
 *
 * @author Leni Kirilov
 */
public class DirectoryNodesTest {

    private final static String EMPTY_DIRECTORY_NODE = "<DirectoryNode Name=\"EmptyDir\" />";
    private final static String STANDARD_DIRECTORY_NODE = "<DirectoryNode Name=\"WithFile\">"
            + EMPTY_DIRECTORY_NODE
            + STANDARD_FILE_NODE
            + "</DirectoryNode>";

    @Test
    public void testCreation_Positive() {
        String realPath = "D:/Coding/DOT NET Projects/Projects/WPFHomeWorks/FileTree2XMLVisualizer/";
        new DirectoryNode(new File(realPath));
    }

    @Test(expected = DirectoriesApplicationException.class)
    public void testCreation_Negative_DoesNotExist() {
        String realPath = "D:/Coding/DOT NET Projects/Projects/WPFHomeWorks/FileTree2XMLVisualizer_I_AM_AN_UNEXISTING_DIRECTORY/";
        new DirectoryNode(new File(realPath));
    }

    @Test(expected = DirectoriesApplicationException.class)
    public void testCreation_Negative_IsNotDirectory() {
        String realFilePath = "D:/Coding/DOT NET Projects/Projects/WPFHomeWorks/FileTree2XMLVisualizer/NodesHelper.cs";
        new DirectoryNode(new File(realFilePath));
    }

    @Test
    public void testInputXML_EmptyDir() {
        DirectoryNode dir = new DirectoryNode(EMPTY_DIRECTORY_NODE);
        assertEquals(0, dir.getChildren().size());
        assertEquals("EmptyDir", dir.getName());
    }

    @Test
    public void testInputXML_WithDir() {
        String inputXML = "<DirectoryNode Name=\"WithDir\">"
                + EMPTY_DIRECTORY_NODE
                + "</DirectoryNode>";

        DirectoryNode dir = new DirectoryNode(inputXML);

        assertEquals(1, dir.getChildren().size());

        DirectoryNode innerDir = (DirectoryNode) dir.getChildren().get(0);
        assertEquals("EmptyDir", innerDir.getName());
        assertEquals("WithDir", dir.getName());
    }

    @Test
    public void testInputXML_WithFile() {
        String inputXML = "<DirectoryNode Name=\"WithFile\">"
                + STANDARD_FILE_NODE
                + "</DirectoryNode>";
        DirectoryNode dir = new DirectoryNode(inputXML);

        assertEquals(1, dir.getChildren().size());
        FileNode innerFile = (FileNode) dir.getChildren().get(0);
        assertEquals("NewFile.xml", innerFile.getName());
        assertEquals(256, innerFile.getSize());
        assertEquals("WithFile", dir.getName());
    }

    @Test
    public void testInputXML_WithFileAndWithDir() {
        String inputXML = "<DirectoryNode Name=\"WithFile\">"
                + EMPTY_DIRECTORY_NODE
                + STANDARD_FILE_NODE
                + "</DirectoryNode>";

        DirectoryNode dir = new DirectoryNode(inputXML);
        assertEquals(2, dir.getChildren().size());
        FileNode innerFile = (FileNode) dir.getChildren().get(1);
        assertEquals("NewFile.xml", innerFile.getName());
        assertEquals(256, innerFile.getSize());

        DirectoryNode innerDir = (DirectoryNode) dir.getChildren().get(0);
        assertEquals("EmptyDir", innerDir.getName());

        assertEquals("WithFile", dir.getName());
    }

//        @Test
//        public void testOutputXML() {
//             String inputXML = "<DirectoryNode Name=\"WithFile\">"
//                + EMPTY_DIRECTORY_NODE
//                + STANDARD_FILE_NODE
//                + "</DirectoryNode>";
//
//            DirectoryNode dir = new DirectoryNode(inputXML);
//            String output = dir.toXML();
//            assertEquals(true, output.Contains(inputXML), "/n" + output + "/n" + inputXML);
//        }
    @Test(expected = DirectoriesApplicationException.class)
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void testInputXML_Negative_FileNodeInput() {
        String xmlInput = STANDARD_FILE_NODE;
        new DirectoryNode(xmlInput);
    }

    @Test
    public void testCreateDirectoryNodeFromDirectoryNodeToXML() {
        String inputXML = "<DirectoryNode Name=\"WithFile\">"
                + EMPTY_DIRECTORY_NODE
                + STANDARD_FILE_NODE
                + "</DirectoryNode>";

        DirectoryNode dir = new DirectoryNode(new DirectoryNode(inputXML).toXML());
        assertEquals(2, dir.getChildren().size());
        FileNode innerFile = (FileNode) dir.getChildren().get(1);
        assertEquals("NewFile.xml", innerFile.getName());
        assertEquals(256, innerFile.getSize());

        DirectoryNode innerDir = (DirectoryNode) dir.getChildren().get(0);
        assertEquals("EmptyDir", innerDir.getName());

        assertEquals("WithFile", dir.getName());
    }
}
