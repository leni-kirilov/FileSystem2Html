package bg.uni.sofia.fmi.xml.filesystem2html.model;

import static bg.uni.sofia.fmi.xml.filesystem2html.model.XmlExamples.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Test;

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

    public File dirToClean;

    @After
    public void cleanUp() throws FileNotFoundException {
        if (null != dirToClean) {
            IOUtils.deleteRecursively(dirToClean);
        }
    }

    @Test
    public void parsing_Positive_EmptyDir() throws IOException {
        String realDirPath = IOUtils.createTestDir("test_dir_parsing_Positive_SourceSingleDir").getAbsolutePath();
        new DirectoryNode(new File(realDirPath));
    }

    @Test(expected = DirectoriesApplicationException.class)
    public void parsing_Negative_DirDoesNotExist() throws IOException {
        String realDirPath = IOUtils.createTestDir("test_dir_testCreation_Negative_DoesNotExist").getAbsolutePath();
        String fakeDirPath = realDirPath + "\\Inexistent_Dir\\";
        new DirectoryNode(new File(fakeDirPath));
    }

    @Test(expected = DirectoriesApplicationException.class)
    public void parsing_Negative_IsNotDirectory() throws IOException {
        String realFilePath = IOUtils.createTestFile("file_testCreation_Negative_IsNotDirectory.txt").getAbsolutePath();
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
        //Test code
        DirectoryNode dir = new DirectoryNode(STANDARD_DIRECTORY_NODE);

        //Assert
        assertEquals(2, dir.getChildren().size());
        FileNode innerFile = (FileNode) dir.getChildren().get(1);
        assertEquals("NewFile.xml", innerFile.getName());
        assertEquals(256, innerFile.getSize());

        DirectoryNode innerDir = (DirectoryNode) dir.getChildren().get(0);
        assertEquals("EmptyDir", innerDir.getName());

        assertEquals("WithFile", dir.getName());
    }

    @Test(expected = DirectoriesApplicationException.class)
    public void parsingXML_Negative_IsNotDir() {
        String xmlInput = STANDARD_FILE_NODE;
        new DirectoryNode(xmlInput);
    }

    @Test
    public void parsingXML_Positive_ToXML() {
        //Test code
        DirectoryNode dir = new DirectoryNode(new DirectoryNode(STANDARD_DIRECTORY_NODE).toXML());

        //Assert
        assertEquals(2, dir.getChildren().size());
        FileNode innerFile = (FileNode) dir.getChildren().get(1);
        assertEquals("NewFile.xml", innerFile.getName());
        assertEquals(256, innerFile.getSize());

        DirectoryNode innerDir = (DirectoryNode) dir.getChildren().get(0);
        assertEquals("EmptyDir", innerDir.getName());

        assertEquals("WithFile", dir.getName());
    }

    @Test
    public void create_EmptyDir() throws IOException {
        DirectoryNode dir = new DirectoryNode(EMPTY_DIRECTORY_NODE);
        File tempParentDir = IOUtils.createTestDir("create_EmptyDir_ParentDir");

        dir.create(tempParentDir.getAbsolutePath());
        this.dirToClean = new File(tempParentDir.getAbsolutePath() + File.separator + dir.getName());
        assertTrue("Creation of empty dir was unsuccessful", this.dirToClean.exists());
    }

    @Test
    public void create_WithFileAndWithDir() throws IOException {
        DirectoryNode dir = new DirectoryNode(STANDARD_DIRECTORY_NODE);
        File tempParentDir = IOUtils.createTestDir("create_WithFileAndWithDir");

        dir.create(tempParentDir.getAbsolutePath());
        this.dirToClean = new File(tempParentDir.getAbsolutePath() + File.separator + dir.getName());

        assertTrue("Creation of parent dir was unsuccessful", this.dirToClean.exists());
        for (FileSystemNode node : dir.getChildren()) {
            String path = this.dirToClean.getAbsolutePath() + File.separator + node.getName();
            assertTrue("Creation of " + node.getClass().getSimpleName() + " inside dir was unsuccessful", new File(path).exists());
        }
    }

    @Test
    public void create_WithNestedDirs() throws IOException {
        DirectoryNode testDir = new DirectoryNode(STANDARD_DIRECTORY_NODE);
        File tempParentDir = IOUtils.createTestDir("create_WithNestedDirs");

        testDir.create(tempParentDir.getAbsolutePath());
        this.dirToClean = new File(tempParentDir.getAbsolutePath() + File.separator + testDir.getName());

        assertTrue("Creation of parent dir was unsuccessful", this.dirToClean.exists());
        for (FileSystemNode node : testDir.getChildren()) {
            String path = this.dirToClean.getAbsolutePath() + File.separator + node.getName();
            assertTrue("Creation of " + node.getClass().getSimpleName() + " inside dir was unsuccessful", new File(path).exists());
        }
    }
}