package bg.uni.sofia.fmi.xml.filesystem2html.model;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

//TODO rework in Java7 - better IO, better Exception handling. Some syntactic sugar -> learn Java 7 this way
/**
 *
 * @author Leni Kirilov
 * //TODO understand how to set last changed date automatically to each changed file
 */
public class DirectoryNode extends FileSystemNode {

    private final static String DIRECTORY_NODE = "DirectoryNode";
    public List<FileSystemNode> children = new ArrayList<FileSystemNode>();

    public DirectoryNode(File dirPath) {
        buildFromPath(dirPath);
    }

    public DirectoryNode(String inputXML) {
        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(inputXML));
            Document document = db.parse(is);
            if (!document.getDocumentElement().getNodeName().equals(DIRECTORY_NODE)) {
                throw new IllegalArgumentException("A DirectoryNode xml should be the only root element."
                        + document.getDocumentElement().getNodeName() + " = documentElement name");
            }

            if (document.getDocumentElement().getNodeName().equals("FileSystemTree")) {
                parseXml(document.getElementById(DIRECTORY_NODE));
            } else {
                parseXml(document.getDocumentElement());
            }
        } catch (Exception e) {
            throw new DirectoriesApplicationException("", e);
        }
    }

    DirectoryNode(Element directoryNodeElement) {
        parseXml(directoryNodeElement);
    }

    private void parseXml(Element directoryNode) {
        try {
            this.name = directoryNode.getAttribute(NAME);

            NodeList nodes = directoryNode.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                Node currentNode = nodes.item(i);

                if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element currentElement = (Element) currentNode;

                    if (currentElement.getNodeName().equals(DIRECTORY_NODE)) {
                        children.add(new DirectoryNode(currentElement));

                    } else if (currentElement.getNodeName().equals(FILENODE)) {
                        children.add(new FileNode(currentElement));

                        //TODO should I not remove this one here as the XML is already validated with the XSD ? If I have good tests, I can check this quickly
                    } else {
                        throw new IllegalArgumentException("Unknown element found. Only " + FILENODE + " and " + DIRECTORY_NODE + " expected");
                    }
                }
            }
        } catch (Exception e) {
            throw new DirectoriesApplicationException("Parsing of xml failed." + e.getMessage(), e);
        }
    }

    private void buildFromPath(File dir) {
        if (dir.exists() && dir.isDirectory()) {
            this.name = dir.getName();

            for (File innerDir : dir.listFiles()) {
                if (innerDir.isDirectory()) {
                    children.add(new DirectoryNode(innerDir));
                }
            }

            for (File innerFile : dir.listFiles()) {
                if (innerFile.isFile()) {
                    children.add(new FileNode(innerFile));
                }
            }
        } else {
            throw new DirectoriesApplicationException("Isn't a dir or does not exist", new Exception());
        }
    }

    @Override
    public Element toXML() {
        return toXML(null);
    }

    @Override
    public Element toXML(Document document) {
        try {
            if (document == null) {
                document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            }

            Element dirNodeElement = document.createElement(DIRECTORY_NODE);
            dirNodeElement.setAttribute(NAME, name);

            for (FileSystemNode node : children) {
                dirNodeElement.appendChild(node.toXML(document));
            }

            return dirNodeElement;
        } catch (ParserConfigurationException ex) {
            throw new DirectoriesApplicationException("Failed conversion to XML string", ex);
        }
    }

    @Override
    public void create(String dirPath) {
        String pathToThisDir = dirPath + File.separator + this.name;

        try {
            new File(pathToThisDir).mkdirs();
            for (FileSystemNode node : children) {
                node.create(pathToThisDir);
            }
        } catch (Exception e) {
            throw new DirectoriesApplicationException("Creation of " + this + " fails", e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(512);

        sb.append("DirectoryNode\nName=" + this.name);

        for (FileSystemNode node : children) {
            sb.append("\n\t" + node.toString());
        }

        return sb.toString();
    }

    @Override
    public String getName() {
        return this.name;
    }

    public List<FileSystemNode> getChildren() {
        return children;
    }
    //TODO check if the following code has an equivalent test. If yes - delete this commented code. If not - write test and then delete it! :)
//    public static void main(String[] args) throws Exception {
//        String STANDARD_FILE_NODE = "<FileNode Name=\"NewFile.xml\" Size=\"256\" LastDateChanged=\"2010-12-12T06:38:55\"/>";
//        String EMPTY_DIRECTORY_NODE = "<DirectoryNode Name=\"EmptyDir\" />";
//        String STANDARD_DIRECTORY_NODE = "<DirectoryNode Name=\"WithFile\">"
//                + EMPTY_DIRECTORY_NODE
//                + STANDARD_FILE_NODE
//                + "</DirectoryNode>";
//
//        DirectoryNode dir = new DirectoryNode(STANDARD_DIRECTORY_NODE);
//        System.out.println(dir.getChildren().get(0).toString());
//        System.out.println(dir.getChildren().get(1).toString());
//    }
}
