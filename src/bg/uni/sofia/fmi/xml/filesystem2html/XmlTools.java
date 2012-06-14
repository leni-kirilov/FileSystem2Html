package bg.uni.sofia.fmi.xml.filesystem2html;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
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
public class XmlTools {

    public static void convertFileSystemXml2HTML(File inputXml, File resultHtml) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            File xslt = FileUtils.locateResource("xml/FileSystem2HTML.xsl");

            Transformer transformer = transformerFactory.newTransformer(new StreamSource(xslt));
            transformer.transform(new StreamSource(inputXml), new StreamResult(resultHtml));
        } catch (TransformerException ex) {
            throw new RuntimeException("Xslt convertion failed", ex);
        }
    }

    public static void validateWithJAVAX(File inputXML, File schemaXSD) {
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

//    public static String xmlToString(org.w3c.dom.Node node) {
//        try {
//            StringWriter stringWriter = new StringWriter();
//
//            Transformer transformer = TransformerFactory.newInstance().newTransformer();
//            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//            transformer.transform(new DOMSource(node), new StreamResult(stringWriter));
//
//            return stringWriter.getBuffer().toString();
//        } catch (TransformerConfigurationException e) {
//            e.printStackTrace();
//        } catch (TransformerException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static void xmlToFile(org.w3c.dom.Node node, File file) {
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
