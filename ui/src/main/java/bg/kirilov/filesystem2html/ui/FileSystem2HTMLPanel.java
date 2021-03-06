package bg.kirilov.filesystem2html.ui;

import bg.kirilov.filesystem2html.model.FileSystemNode;
import bg.kirilov.filesystem2html.model.FileSystemNodeFactory;
import bg.kirilov.filesystem2html.utils.FileUtils;
import bg.kirilov.filesystem2html.utils.XmlTools;
import org.w3c.dom.Element;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Leni Kirilov
 */
//TODO i18n of messages
//TODO write doc description for this class
//TODO separate the panel's behaviour from the visualization - View-Controller style
//TODO slowly but surely remove the auto-generated UI code.
public class FileSystem2HTMLPanel extends javax.swing.JPanel {

  public static final String FILE_JPEG = "file.jpeg";
  public static final String FOLDER_JPEG = "folder.jpeg";

  private File path;
  private File xmlFile;
  private File htmlFile;
  private File currentBrowsingDirectory;

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
  private javax.swing.JLabel resultXmlPathLabelLabel; //TODO make it scrollable
  private javax.swing.JLabel xmlPathLabel;

  public FileSystem2HTMLPanel() {
    //TODO improve Swing according to Swing best practises, so that Hanging of GUI because of calculations is fixed
    //TODO redo the Swing with code (may be never?)
    initComponents();
    setEnableButtons(false, false);
  }

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
        FileUtils.openFile(htmlFile);
      }
    });

    openXmlButton.setText("Open XML");
    openXmlButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        FileUtils.openFile(xmlFile);
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
  }

  private void createXmlFromPathButtonActionPerformed(java.awt.event.ActionEvent evt){
    try {
      path = choosePath("Choose path", JFileChooser.FILES_AND_DIRECTORIES);
      Element resultXml = createFileSystemNodeXML(path);

      xmlFile = choosePath("Save to xml", JFileChooser.FILES_ONLY, ".xml");
      XmlTools.writeFileSystemXmlToFile(resultXml, xmlFile);

      htmlFile = null;

      setTextToLabels();
      setEnableButtons(true, false);
    } catch (IllegalArgumentException e) {
    }
  }

  private void createHtmlForXmlActionPerformed(java.awt.event.ActionEvent evt) {
    try {
      xmlFile = choosePath("Choose xml input", JFileChooser.FILES_ONLY);
      XmlTools.validateXml(xmlFile);
      htmlFile = choosePath("Save to html", JFileChooser.FILES_ONLY, ".html");

      XmlTools.convertFileSystemXml2HTML(xmlFile, htmlFile);

      path = null;
      setTextToLabels();
      setEnableButtons(true, true);

      copyImageFiles();
    } catch (IllegalArgumentException e) {
    }
  }

  //TODO refactor magic constants
  private void createHtmlForPathActionPerformed(java.awt.event.ActionEvent evt) {
    try {

      path = choosePath("Choose path", JFileChooser.FILES_AND_DIRECTORIES);
      htmlFile = choosePath("Save to html", JFileChooser.FILES_ONLY, ".html");

      Element resultXml = createFileSystemNodeXML(path);

      //TODO overload convertFileSystemXml2HTML to work not only with XmlFile paths but the XML as well. This way we can skip 2 steps: writing to HDD and then reading from it
      xmlFile = new File("temp.xml");
      xmlFile.deleteOnExit();
      XmlTools.writeFileSystemXmlToFile(resultXml, xmlFile);
      XmlTools.convertFileSystemXml2HTML(xmlFile, htmlFile);

      setTextToLabels();
      setEnableButtons(true, true);

      copyImageFiles();
    } catch (IllegalArgumentException e) {
    }
  }

  private void createPathButtonActionPerformed(java.awt.event.ActionEvent evt) {
    try {
      xmlFile = choosePath("Choose xml...", JFileChooser.FILES_ONLY);
      path = choosePath("Choose path...", JFileChooser.DIRECTORIES_ONLY);
      htmlFile = null;

      XmlTools.validateXml(xmlFile);
      String xmlString = FileUtils.readFileAsString(xmlFile);

      FileSystemNode node = FileSystemNodeFactory.createNode(xmlString);
      node.create(path);
      setTextToLabels();
      setEnableButtons(true, false);

    } catch (IllegalArgumentException | IOException e) {
      JOptionPane.showMessageDialog(this, "Incorrect input. Files/dirs could not be created");
    }
  }

  private void setTextToLabels() {
    chosenPathLabel.setText(path != null ? path.getAbsolutePath() : "");
    xmlPathLabel.setText(xmlFile != null ? xmlFile.getAbsolutePath() : "");
    htmlLabel.setText(htmlFile != null ? htmlFile.getAbsolutePath() : "");
  }

  /**
   * This is done so that the resulting html can easily refer the images with simple relative path.
   * better way to do this relative pointings
   */
  private void copyImageFiles() {
    FileUtils.copyFile(FileUtils.locateResource(FILE_JPEG), new File(htmlFile.getParentFile(), FILE_JPEG));
    FileUtils.copyFile(FileUtils.locateResource(FOLDER_JPEG), new File(htmlFile.getParentFile(), FOLDER_JPEG));
  }

  //TODO if a dir is chosen, check if it's a big dir. For example C:\; d:\ or "/" . Display a warning
  private File choosePath(String text, int chooseMode) throws IllegalArgumentException {
    return choosePath(text, chooseMode, null);
  }
  private File choosePath(String text, int chooseMode, String suffix) throws IllegalArgumentException {

    JFileChooser fileChooser = new JFileChooser(this.currentBrowsingDirectory);
    fileChooser.setFileSelectionMode(chooseMode);

    if (fileChooser.showDialog(this, text) != JFileChooser.APPROVE_OPTION) {
      JOptionPane.showMessageDialog(this, "You didn't select any file. Canceling operation...");
      throw new IllegalArgumentException(); //TODO make this operate via a null value instead of exceptions
    }

    this.currentBrowsingDirectory = fileChooser.getCurrentDirectory();
    File selectedFile = fileChooser.getSelectedFile();
    if(suffix != null){
      selectedFile = FileUtils.addSuffix(suffix, selectedFile);
    }

    return selectedFile;
  }

  //TODO separate this complex computation in another thread so that the UI doesn't freeze !
  //TODO redo FileNode and DirectoryNode implementations with JAX-B annotations
  private Element createFileSystemNodeXML(File path) {
    return FileSystemNodeFactory.createNode(path).toXML();
  }

  private void setEnableButtons(boolean xml, boolean html) {
    openXmlButton.setEnabled(xml);
    openHtmlButton.setEnabled(html);
  }
}