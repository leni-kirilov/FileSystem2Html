package bg.uni.sofia.fmi.xml.filesystem2html.model;

import java.io.File;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
import static bg.uni.sofia.fmi.xml.filesystem2html.model.XmlExamples.*;

/**
 *
 * Tests the basic functions of a DirectoryNode
 * - parsing a Directory from file system
 * - parsing XML
 * - creating a Directory
 * - correct behaviour with invalid input
 * 
 * @author Leni Kirilov
 */
public class DirectoryNodesTest {

    @Test
    public void parsing_Positive_EmptyDir() throws IOException {
        String realDirPath = Utils.createTestDir("test_dir_parsing_Positive_SourceSingleDir").getAbsolutePath();
        new DirectoryNode(new File(realDirPath));
    }

    @Test(expected = DirectoriesApplicationException.class)
    public void parsing_Negative_DirDoesNotExist() throws IOException {
        String realDirPath = Utils.createTestDir("test_dir_testCreation_Negative_DoesNotExist").getAbsolutePath();
        String fakeDirPath = realDirPath + "\\Inexistent_Dir\\";
        new DirectoryNode(new File(fakeDirPath));
    }

    @Test(expected = DirectoriesApplicationException.class)
    public void parsing_Negative_IsNotDirectory() throws IOException {
        String realFilePath = Utils.createTestFile("file_testCreation_Negative_IsNotDirectory.txt").getAbsolutePath();
        new DirectoryNode(new File(realFilePath));
    }

    @Test
    public void parsingXML_Positive_EmptyDir() {
        DirectoryNode dir = new DirectoryNode(EMPTY_DIRECTORY_NODE);
        assertEquals(0, dir.getChildren().size());
        assertEquals("EmptyDir", dir.getName());
    }

    @Test
    public void parsingXML_Positive_NestedDirs() {
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
    public void parsingXML_Positive_DirWithFile() {
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
    public void parsingXML_Positive_WithFileAndWithDir() {
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
    public void parsingXML_Negative_IsNotDir() {
        String xmlInput = STANDARD_FILE_NODE;
        new DirectoryNode(xmlInput);
    }

    @Test
    public void parsingXML_Positive_ToXML() {
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
    
    //TODO add create dir tests, dir + file, dir in dir
    //TODO parsing file system dir + file, dir in dir
}
