package bg.uni.sofia.fmi.xml.filesystem2html.gui;

import bg.uni.sofia.fmi.xml.filesystem2html.FileUtils;
import bg.uni.sofia.fmi.xml.filesystem2html.model.DirectoryNode;
import bg.uni.sofia.fmi.xml.filesystem2html.model.FileNode;
import bg.uni.sofia.fmi.xml.filesystem2html.model.FileSystemNode;
import bg.uni.sofia.fmi.xml.filesystem2html.XmlTools;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.w3c.dom.Element;

/**
 *
 * @author Leni Kirilov
 */
public class FileSystem2HTMLPanel extends javax.swing.JPanel {

    private File path;
    private File xmlFile;
    private File htmlFile;
    private final File SCHEMA_FILE;

    public FileSystem2HTMLPanel() {
        initComponents();
        setEnableButtons(false, false);
        SCHEMA_FILE = FileUtils.locateResource("xml/FileSystem.xsd");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        createXmlFromPathButton = new javax.swing.JButton();
        createHtmlForXml = new javax.swing.JButton();
        chosenPathLabelLabel = new javax.swing.JLabel();
        createHtmlForPath = new javax.swing.JButton();
        chosenPathLabel = new javax.swing.JLabel();
        resultXmlPathLabelLabel = new javax.swing.JLabel();
        xmlPathLabel = new javax.swing.JLabel();
        resultHtmlLabelLabel = new javax.swing.JLabel();
        htmlLabel = new javax.swing.JLabel();
        openHtmlButton = new javax.swing.JButton();
        openXmlButton = new javax.swing.JButton();
        createPathButton = new javax.swing.JButton();

        createXmlFromPathButton.setText("Create XML for path...");
        createXmlFromPathButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createXmlFromPathButtonActionPerformed(evt);
            }
        });

        createHtmlForXml.setText("Create HTML for xml...");
        createHtmlForXml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createHtmlForXmlActionPerformed(evt);
            }
        });

        chosenPathLabelLabel.setForeground(new java.awt.Color(255, 0, 0));
        chosenPathLabelLabel.setText("Chosen path:");

        createHtmlForPath.setText("Create HTML for path...");
        createHtmlForPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createHtmlForPathActionPerformed(evt);
            }
        });

        chosenPathLabel.setText(" ");

        resultXmlPathLabelLabel.setForeground(new java.awt.Color(255, 0, 0));
        resultXmlPathLabelLabel.setText("Result xml:");

        xmlPathLabel.setText(" ");

        resultHtmlLabelLabel.setForeground(new java.awt.Color(255, 0, 0));
        resultHtmlLabelLabel.setText("Result html:");

        htmlLabel.setText(" ");

        openHtmlButton.setText("Open HTML");
        openHtmlButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openHtmlButtonActionPerformed(evt);
            }
        });

        openXmlButton.setText("Open XML");
        openXmlButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openXmlButtonActionPerformed(evt);
            }
        });

        createPathButton.setText("Create path from XML to path...");
        createPathButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createPathButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(createPathButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(createXmlFromPathButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(createHtmlForXml, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(createHtmlForPath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(openHtmlButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(openXmlButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(xmlPathLabel)
                    .addComponent(chosenPathLabelLabel)
                    .addComponent(chosenPathLabel)
                    .addComponent(resultXmlPathLabelLabel)
                    .addComponent(resultHtmlLabelLabel)
                    .addComponent(htmlLabel))
                .addContainerGap(126, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chosenPathLabelLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chosenPathLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resultXmlPathLabelLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(xmlPathLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resultHtmlLabelLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(htmlLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(createXmlFromPathButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(createHtmlForXml)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(createHtmlForPath)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(openHtmlButton)
                            .addComponent(openXmlButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(createPathButton)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void createXmlFromPathButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createXmlFromPathButtonActionPerformed
        try {
            path = choosePath("Choose path", JFileChooser.FILES_AND_DIRECTORIES);
            Element resultXml = createFileSystemXML(path);

            xmlFile = choosePath("Save to xml", JFileChooser.FILES_ONLY);
            xmlFile = addSuffix(".xml", xmlFile);
            XmlTools.xmlToFile(resultXml, xmlFile);

            htmlFile = null;

            setTextToLabels();
            setEnableButtons(true, false);
        } catch (IllegalArgumentException e) {
            return;
        }
    }//GEN-LAST:event_createXmlFromPathButtonActionPerformed

    private void createHtmlForXmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createHtmlForXmlActionPerformed
        try {
            xmlFile = choosePath("Choose xml input", JFileChooser.FILES_ONLY);
            XmlTools.validateWithJAVAX(xmlFile, SCHEMA_FILE);
            htmlFile = choosePath("Save to html", JFileChooser.FILES_ONLY);
            htmlFile = addSuffix(".html", htmlFile);

            XmlTools.convertFileSystemXml2HTML(xmlFile, htmlFile);

            path = null;
            setTextToLabels();
            setEnableButtons(true, true);

            copyImageFiles();
        } catch (IllegalArgumentException e) {
            return;
        }
    }//GEN-LAST:event_createHtmlForXmlActionPerformed

    private void createHtmlForPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createHtmlForPathActionPerformed
        try {
            path = choosePath("Choose path", JFileChooser.FILES_AND_DIRECTORIES);

            htmlFile = choosePath("Save to html", JFileChooser.FILES_ONLY);
            htmlFile = addSuffix(".html", htmlFile);

            Element resultXml = createFileSystemXML(path);
            xmlFile = new File("temp.xml");
            xmlFile.deleteOnExit();

            XmlTools.xmlToFile(resultXml, xmlFile);
            XmlTools.convertFileSystemXml2HTML(xmlFile, htmlFile);

            setTextToLabels();
            setEnableButtons(true, true);

            copyImageFiles();
        } catch (IllegalArgumentException e) {
            return;
        }
    }//GEN-LAST:event_createHtmlForPathActionPerformed

    private void openHtmlButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openHtmlButtonActionPerformed
        openFile(htmlFile);
    }//GEN-LAST:event_openHtmlButtonActionPerformed

    private void openXmlButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openXmlButtonActionPerformed
        openFile(xmlFile);
    }//GEN-LAST:event_openXmlButtonActionPerformed

    private void createPathButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createPathButtonActionPerformed
        try {
            xmlFile = choosePath("Choose xml...", JFileChooser.FILES_ONLY);
            path = choosePath("Choose path...", JFileChooser.DIRECTORIES_ONLY);
            htmlFile = null;

            XmlTools.validateWithJAVAX(xmlFile, SCHEMA_FILE);
            String xmlString = FileUtils.readFileAsString(xmlFile);

            try {
                new DirectoryNode(xmlString).create(path.getAbsolutePath());
                setTextToLabels();
                setEnableButtons(true, false);
                return;
            } catch (Throwable e) {
            }

            try {
                new FileNode(xmlString).create(path.getAbsolutePath());
                setTextToLabels();
                setEnableButtons(true, false);
                return;
            } catch (Throwable e) {
            }

            throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Incorrect input. Files/dirs could not be created");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Incorrect input. Files/dirs could not be created");
        }
    }//GEN-LAST:event_createPathButtonActionPerformed

    private void setTextToLabels() {
        chosenPathLabel.setText(path != null ? path.getAbsolutePath() : "");
        xmlPathLabel.setText(xmlFile != null ? xmlFile.getAbsolutePath() : "");
        htmlLabel.setText(htmlFile != null ? htmlFile.getAbsolutePath() : "");
    }

    private File addSuffix(String suffix, File file) {
        String path = file.getAbsolutePath();
        if (!path.endsWith(suffix)) {
            path += suffix;
        }
        return new File(path);
    }

    private void copyImageFiles() {
        String resultHtmlFolder = getDirectory(htmlFile);
        FileUtils.copyFile(FileUtils.locateResource("images/file.jpeg"), new File(resultHtmlFolder + "file.jpeg"));
        FileUtils.copyFile(FileUtils.locateResource("images/folder.jpeg"), new File(resultHtmlFolder + "folder.jpeg"));
    }

    private String getDirectory(File file) {
        String s = file.getAbsolutePath();
        int index = s.lastIndexOf("\\");
        String dir = s.substring(0, index) + "\\";
        return dir;
    }

    private void openFile(File file) {
        String osName = System.getProperty("os.name");
        if (osName.contains("Windows")) {
            openInWindowsOS(file);
        } else if (osName.contains("Mac")) {
            openInMacOS(file);
        }
    }

    private void openInWindowsOS(File file) {
        try {
            Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler \"" + file.toString() + "\"");
            p.waitFor();
        } catch (IOException e) {
        } catch (InterruptedException e) {
        }
    }

    private void openInMacOS(File file) {
        try {
            Runtime.getRuntime().exec("open " + file.toString());
        } catch (IOException e) {
        }
    }

    private File choosePath(String text, int chooseMode) throws IllegalArgumentException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(chooseMode);

        int result = fileChooser.showDialog(this, text);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        } else {
            JOptionPane.showMessageDialog(this, "You didn't select any file. Canceling operation...");
            throw new IllegalArgumentException();
        }
    }

    private Element createFileSystemXML(File path) {
        if (path.isFile()) {
            return new FileNode(path).toXML();
        } else if (path.isDirectory()) {
            return new DirectoryNode(path).toXML();
        } else {
            throw new RuntimeException("invalid file chosen");
        }
    }

    private void setEnableButtons(boolean xml, boolean html) {
        openXmlButton.setEnabled(xml);
        openHtmlButton.setEnabled(html);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel chosenPathLabel;
    private javax.swing.JLabel chosenPathLabelLabel;
    private javax.swing.JButton createHtmlForPath;
    private javax.swing.JButton createHtmlForXml;
    private javax.swing.JButton createPathButton;
    private javax.swing.JButton createXmlFromPathButton;
    private javax.swing.JLabel htmlLabel;
    private javax.swing.JButton openHtmlButton;
    private javax.swing.JButton openXmlButton;
    private javax.swing.JLabel resultHtmlLabelLabel;
    private javax.swing.JLabel resultXmlPathLabelLabel;
    private javax.swing.JLabel xmlPathLabel;
    // End of variables declaration//GEN-END:variables
}
