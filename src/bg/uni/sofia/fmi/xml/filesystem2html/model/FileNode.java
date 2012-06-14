package bg.uni.sofia.fmi.xml.filesystem2html.model;

import java.io.File;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * Represents FileNode
 * 
 * @author Leni Kirilov
 */
public class FileNode extends FileSystemNode {

    private static final String SIZE = "Size";
    private static final String HIDDEN = "Hidden";
    private static final String READABLE = "Readable";
    private static final String WRITABLE = "Writable";
    private static final String EXECUTABLE = "Executable";
    private static final String LAST_DATE_CHANGED = "LastDateChanged";
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'hh:mm:ss";
    //
    private long size;
    private long lastDateChanged;
    private boolean isHidden;
    private boolean isReadable;
    private boolean isWritable;
    private boolean isExecutable;

    public FileNode(File path) {
        validateInput(path);
    }

    public FileNode(Element element) {
        parseXml(element);
    }

    public FileNode(String xml) {
        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            Document document = db.parse(is);

            if (!document.getDocumentElement().getNodeName().equals(FILENODE)) {
                throw new IllegalArgumentException("A FileNode xml should have only 1 FileNode element. "
                        + document.getDocumentElement().getNodeName() + " = documentElement name");
            }

            NodeList nodes = document.getElementsByTagName(FILENODE);

            if (nodes.getLength() != 1) {
                throw new IllegalArgumentException("Too many elements. Only 1 FileNode expected");
            }

            parseXml(nodes.item(0));
        } catch (Exception e) {
            throw new DirectoriesApplicationException("", e);
        }
    }

    private void parseXml(Node fileNode) {
        try {
            Element fileNodeElement = (Element) fileNode;
            parseName(fileNodeElement);
            parseSize(fileNodeElement);
            parseLastDateChanged(fileNodeElement);
            parseHidden(fileNodeElement);
            parseExecutable(fileNodeElement);
            parseWritable(fileNodeElement);
            parseReadable(fileNodeElement);
        } catch (Exception e) {
            throw new DirectoriesApplicationException("Parsing of xml failed." + e.getMessage(), e);
        }
    }

    private void parseName(Element fileNodeElement) {
        this.name = fileNodeElement.getAttribute(NAME);
    }

    private void parseSize(Element fileNodeElement) {
        String sizeRaw = fileNodeElement.getAttribute(SIZE);
        this.size = Integer.parseInt(sizeRaw);
    }

    private void parseLastDateChanged(Element fileNodeElement) throws ParseException {
        String dateRaw = fileNodeElement.getAttribute(LAST_DATE_CHANGED);
        Date date = new SimpleDateFormat(DATE_FORMAT, Locale.US).parse(dateRaw);
        this.lastDateChanged = date.getTime();
    }

    private void parseHidden(Element fileNodeElement) {
        String hiddenRaw = fileNodeElement.getAttribute(HIDDEN);
        this.isHidden = Boolean.parseBoolean(hiddenRaw);
    }

    private void parseExecutable(Element fileNodeElement) {
        String executableRaw = fileNodeElement.getAttribute(EXECUTABLE);
        this.isExecutable = Boolean.parseBoolean(executableRaw);
    }

    private void parseWritable(Element fileNodeElement) {
        String writableRaw = fileNodeElement.getAttribute(WRITABLE);
        this.isWritable = Boolean.parseBoolean(writableRaw);
    }

    private void parseReadable(Element fileNodeElement) {
        String readableRaw = fileNodeElement.getAttribute(READABLE);
        this.isReadable = Boolean.parseBoolean(readableRaw);
    }

    private void validateInput(File file) {
        if (file.exists() && file.isFile()) {
            this.name = file.getName();
            this.size = file.length();
            this.lastDateChanged = file.lastModified();
            this.isHidden = file.isHidden();
            this.isReadable = file.canRead();
            this.isWritable = file.canWrite();
            this.isExecutable = file.canExecute();
        } else {
            throw new DirectoriesApplicationException("File doesn't exist!");
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

            Element fileNodeElement = document.createElement(FILENODE);
            fileNodeElement.setAttribute(EXECUTABLE, Boolean.toString(isExecutable));
            fileNodeElement.setAttribute(HIDDEN, Boolean.toString(isHidden));
            fileNodeElement.setAttribute(LAST_DATE_CHANGED, formatDate());
            fileNodeElement.setAttribute(NAME, name);
            fileNodeElement.setAttribute(READABLE, Boolean.toString(isReadable));
            fileNodeElement.setAttribute(SIZE, "" + size);
            fileNodeElement.setAttribute(WRITABLE, Boolean.toString(isWritable));

            return fileNodeElement;
        } catch (ParserConfigurationException ex) {
            throw new DirectoriesApplicationException("Conversion to XML failed", ex);
        }
    }

    public String formatDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(new Date(lastDateChanged));
    }

    @Override
    public void create(String dirPath) {
        try {
            File file = new File(dirPath + File.separator + this.name);
            file.createNewFile();
            file.setExecutable(isExecutable);
            file.setReadable(isReadable);
            file.setWritable(isWritable);
        } catch (Exception e) {
            throw new DirectoriesApplicationException("Creation of " + this + " fails", e);
        }
    }

    @Override
    public String toString() {
        return "FileNode\n\tName=" + name + "\n\tSize=" + size
                + "\n\tLastDateChange=" + lastDateChanged;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    public boolean isExecutable() {
        return isExecutable;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public boolean isReadable() {
        return isReadable;
    }

    public boolean isWritable() {
        return isWritable;
    }
}
