/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.uni.sofia.fmi.xml.filesystem2html.model.utils;

import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Node;

import java.io.File;

import static org.junit.Assert.fail;

/**
 * @author Leni Kirilov
 */
@Ignore
public class XmlToolsTest {
    
    public XmlToolsTest() {
    }

    /**
     * Test of convertFileSystemXml2HTML method, of class XmlTools.
     */
    @Test
    public void testConvertFileSystemXml2HTML() {
        System.out.println("convertFileSystemXml2HTML");
        File inputXml = null;
        File resultHtml = null;
        XmlTools.convertFileSystemXml2HTML(inputXml, resultHtml);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateXml method, of class XmlTools.
     */
    @Test
    public void testValidateXml() {
        System.out.println("validateXml");
        File inputXML = null;
        XmlTools.validateXml(inputXML);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of writeFileSystemXmlToFile method, of class XmlTools.
     */
    @Test
    public void testWriteFileSystemXmlToFile() {
        System.out.println("writeFileSystemXmlToFile");
        Node node = null;
        File file = null;
        XmlTools.writeFileSystemXmlToFile(node, file);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
