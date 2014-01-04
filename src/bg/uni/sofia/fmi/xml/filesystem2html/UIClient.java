package bg.uni.sofia.fmi.xml.filesystem2html;

import bg.uni.sofia.fmi.xml.filesystem2html.gui.FileSystem2HTMLPanel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * The test execution of the program starts here.
 *
 * @author Leni Kirilov
 */
public class UIClient {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                JFrame frame = new JFrame();
                frame.setTitle("FileSystem2HTML");
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500, 200);
                frame.add(new FileSystem2HTMLPanel());
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });
    }
}