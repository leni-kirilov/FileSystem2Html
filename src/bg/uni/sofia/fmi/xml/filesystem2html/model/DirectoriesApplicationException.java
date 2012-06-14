package bg.uni.sofia.fmi.xml.filesystem2html.model;

/**
 * Exception thrown when incorrect situation happens during FileSystemNode
 * or one of its decendants are processing.
 *
 * @author Leni Kirilov
 */
public class DirectoriesApplicationException extends RuntimeException {

    public DirectoriesApplicationException(String message) {
        this(message, null);
    }

    public DirectoriesApplicationException(String message, Throwable t) {
        super(message, t);
    }
}
