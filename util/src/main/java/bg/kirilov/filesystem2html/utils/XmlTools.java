package bg.kirilov.filesystem2html.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

/**
 * @author Leni Kirilov
 */
// TODO write tests for this Class as well - XMLTest of some sort
public class XmlTools {

  //TODO move these xsd, xslt templates to module 'model'
  private final static String FILE_SYSTEM_2_HTML_XSL_LOCATION = "FileSystem2HTML.xsl";
  private final static String FILE_SYSTEM_XSD_LOCATION = "FileSystem.xsd";

  public static void convertFileSystemXml2HTML(File inputXml, File resultHtml) {
    try {
      TransformerFactory transformerFactory = TransformerFactory.newInstance();

      File xslt = FileUtils.locateResource(FILE_SYSTEM_2_HTML_XSL_LOCATION);

      Transformer transformer = transformerFactory.newTransformer(new StreamSource(xslt));
      transformer.transform(new StreamSource(inputXml), new StreamResult(resultHtml));
    } catch (TransformerException ex) {
      throw new RuntimeException("Xslt conversion failed", ex);
    }
  }

  public static void validateXml(File inputXML) {
    File SCHEMA_FILE = FileUtils.locateResource(FILE_SYSTEM_XSD_LOCATION);
    validateXmlWithXsd(inputXML, SCHEMA_FILE);
  }

  private static void validateXmlWithXsd(File inputXML, File schemaXSD) {

    try {
      String schemaLang = "http://www.w3.org/2001/XMLSchema";
      SchemaFactory factory = SchemaFactory.newInstance(schemaLang);
      Schema schema = factory.newSchema(new StreamSource(schemaXSD));
      Validator validator = schema.newValidator();
      validator.validate(new StreamSource(inputXML));

    } catch (IOException | SAXException ex) {
      throw new RuntimeException("Validation of file " + inputXML.getAbsolutePath() + " schema " + schemaXSD.getAbsolutePath() + " failed", ex);
    }
  }

  public static void writeFileSystemXmlToFile(org.w3c.dom.Node node, File xsltFile) {
    try {
      Document document = node.getOwnerDocument();
      Element fileSystem = document.createElement("FileSystemTree");
      fileSystem.appendChild(node);

      Transformer transformer = TransformerFactory.newInstance().newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

      transformer.transform(new DOMSource(fileSystem), new StreamResult(xsltFile));

    } catch (TransformerException e) {
      throw new RuntimeException(e);
    }
  }
}
