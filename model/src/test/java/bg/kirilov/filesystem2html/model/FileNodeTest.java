package bg.kirilov.filesystem2html.model;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static bg.kirilov.filesystem2html.model.XmlExamples.STANDARD_FILE_NODE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * Tests the basic functions of a FileNode
 * - parsing File from file system
 * - parsing XML
 * - creating a File
 * - correct behaviour with invalid input
 * 
 * @author Leni Kirilov
 */
public class FileNodeTest {

    @Test
    public void parsing_Positive_SourceSingleFile() throws IOException {
        File testFile = IOUtils.createTestFile("file_parsing_Positive_SourceSingleFile.txt");

        FileNode file = new FileNode(testFile);

        assertEquals(testFile.getName(), file.getName());
    }

    @Test(expected = FileSystemNodeCreationException.class)
    public void parsing_Negative_DoesNotExist() throws IOException {
        File testDir = IOUtils.createTestDir("test_dir_parsing_Negative_DoesNotExist");
        String unrealPath = testDir.getAbsolutePath() + File.pathSeparator + "Inexistent_File.txt";

        new FileNode(new File(unrealPath));
    }

    @Test(expected = FileSystemNodeCreationException.class)
    public void parsing_Negative_IsNotFile() throws IOException {
        File testDir = IOUtils.createTestDir("test_dir_parsing_Negative_IsNotFile");
        new FileNode(testDir);
    }

    @Test
    public void parsing_Positive_CorrectXMLInput() {
        FileNode file = new FileNode(STANDARD_FILE_NODE);

        assertEquals("NewFile.bg.kirilov.filesystem2html.utils.xml", file.getName());
        assertEquals(256, file.getSize());
        assertEquals(true, file.isHidden());
        assertEquals(true, file.isReadable());
        assertEquals(true, file.isWritable());
        assertEquals(true, file.isExecutable());
    }

    @Test(expected = FileSystemNodeCreationException.class)
    public void parsing_Negative_DirectoryNodeXML() {
        String inputXML = "<DirectoryNode Name=\"WithFile\">"
                + STANDARD_FILE_NODE
                + "</DirectoryNode>";
        new FileNode(inputXML);
    }

    @Test
    public void parsing_Positive_SourceFileNodeXml() {
        String xmlInput = "<FileNode Name=\"NewFile.bg.kirilov.filesystem2html.utils.xml\" Size=\"256\" LastDateChanged=\"2010-12-12T06:38:55\"/>";
        FileNode file = new FileNode(xmlInput);

        FileNode fileFromFileNode = new FileNode(file.toXML());

        assertEquals(file.getName(), fileFromFileNode.getName());
        assertEquals(file.getSize(), fileFromFileNode.getSize());
        assertEquals(file.isHidden(), fileFromFileNode.isHidden());
        assertEquals(file.isReadable(), fileFromFileNode.isReadable());
        assertEquals(file.isWritable(), fileFromFileNode.isWritable());
        assertEquals(file.isExecutable(), fileFromFileNode.isExecutable());
    }

    @Test
    public void creating_Positive() throws IOException {
        File currentDir = IOUtils.createTestDir("test_dir_creating_Positive");
        try {
            new FileNode(STANDARD_FILE_NODE).create(currentDir.getAbsolutePath());
        } catch (Exception e) {
        } finally {
            File newFile = new File(currentDir + File.separator + "NewFile.bg.kirilov.filesystem2html.utils.xml");
            assertTrue("File couldn't be deleted.", newFile.delete());
        }
    }

    @Test(expected = FileSystemNodeCreationException.class)
    public void parsing_Negative_TwoFileNodesXml() {
        String xmlInput = STANDARD_FILE_NODE + STANDARD_FILE_NODE;
        new FileNode(xmlInput);
    }
}
