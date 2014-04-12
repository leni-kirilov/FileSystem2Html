package bg.kirilov.filesystem2html.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

//TODO generate Javadoc
//TODO rework in Java7 - better IO, better Exception handling. Some syntactic sugar -> learn Java 7 this way

/**
 * @author Leni Kirilov
 */
public class DirectoryNode extends FileSystemNode {

    public final static String DIRECTORY_NODE = "DirectoryNode";
    public List<FileSystemNode> children = new ArrayList<>();

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
            throw new FileSystemNodeCreationException("", e);
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

                    } else {
                        // if it's not a DirectoryNode, it's a FileNode
                        children.add(new FileNode(currentElement));
                    }
                }
            }
        } catch (Exception e) {
            throw new FileSystemNodeCreationException("Parsing of xml failed." + e.getMessage(), e);
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
            throw new FileSystemNodeCreationException("Isn't a dir or does not exist", new Exception());
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
            throw new FileSystemNodeCreationException("Failed conversion to XML string", ex);
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
            throw new FileSystemNodeCreationException("Creation of " + this + " fails", e);
        }
    }

    @Override
    public void create(File directory) {
        this.create(directory.getAbsolutePath());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(512);

        sb.append("DirectoryNode\nName=");
        sb.append(this.name);

        for (FileSystemNode node : children) {
            sb.append("\n\t");
            sb.append(node.toString());
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
}
