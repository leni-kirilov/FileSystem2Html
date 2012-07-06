package bg.uni.sofia.fmi.xml.filesystem2html.model.utils;

import java.io.File;
import java.io.IOException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * @author Leni Kirilov
 */
//TODO run CheckStyle and FindBugs and resolve the most critical errors
public class XmlTools {

    private final static String FILE_SYSTEM_2_HTML_XSL_LOCATION = "xml/FileSystem2HTML.xsl";
    private final static String FILE_SYSTEM_XSD_LOCATION = "xml/FileSystem.xsd";

//TODO write tests for this Class as well - XMLTest of some sort
    public static void convertFileSystemXml2HTML(File inputXml, File resultHtml) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            File xslt = FileUtils.locateResource(FILE_SYSTEM_2_HTML_XSL_LOCATION);

            Transformer transformer = transformerFactory.newTransformer(new StreamSource(xslt));
            transformer.transform(new StreamSource(inputXml), new StreamResult(resultHtml));
        } catch (TransformerException ex) {
            throw new RuntimeException("Xslt convertion failed", ex);
        }
    }

    public static void validateXml(File inputXML) {
        File SCHEMA_FILE = FileUtils.locateResource(FILE_SYSTEM_XSD_LOCATION);
        validateXmlWithXsd2(inputXML, SCHEMA_FILE);
    }

    private static void validateXmlWithXsd2(File inputXML, File schemaXSD) {

        try {
            String schemaLang = "http://www.w3.org/2001/XMLSchema";
            SchemaFactory factory = SchemaFactory.newInstance(schemaLang);
            Schema schema = factory.newSchema(new StreamSource(schemaXSD));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(inputXML));

        } catch (IOException ex) {
            throw new RuntimeException("Validation of FileSystem xml failed", ex);
        } catch (SAXException ex) {
            throw new RuntimeException("Validation of FileSystem xml failed", ex);
        }
    }

    public static void writeFileSystemXmlToFile(org.w3c.dom.Node node, File file) {
        try {
            Document document = node.getOwnerDocument();
            Element fileSystem = document.createElement("FileSystemTree");
            fileSystem.appendChild(node);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            transformer.transform(new DOMSource(fileSystem), new StreamResult(file));

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
