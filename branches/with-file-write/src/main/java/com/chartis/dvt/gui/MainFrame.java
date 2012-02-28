package com.chartis.dvt.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileFilter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import com.chartis.dvt.commons.utils.StringUtils;
import com.chartis.dvt.commons.utils.XmlUtils;
import com.chartis.dvt.core.DaoServiceLocator;
import com.chartis.dvt.core.dao.impl.NullDvtLogDao;
import com.chartis.dvt.core.io.DvtLogIoDao;
import com.chartis.dvt.core.io.DvtLogIoDaoImpl;
import com.chartis.dvt.core.model.DocumentComparisonResult;
import com.chartis.dvt.core.model.exception.DvtException;
import com.chartis.dvt.core.service.impl.DocumentComparatorImpl;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
/**
 * 
 * @author CHAMINDA.AMARASINGHE
 * 
 */
@SuppressWarnings("serial")
public class MainFrame extends javax.swing.JFrame {

    private static Logger logger = Logger.getLogger(MainFrame.class.getName());

    private JPanel jPanel1;
    private JButton jButton1;
    private JTextField selectedFileNameText;
    private JButton browseButton;
    private JList jList1;
    private JLabel jLabel1;
    private JButton okButton;
    private JPanel jPanel2;
    private JFileChooser fileChooser;

    /**
     * Auto-generated main method to display this JFrame
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame inst = new MainFrame();
                inst.setTitle("CTom - DVT");
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
            }
        });
    }

    public MainFrame() {
        super();
        initGUI();
        customInitGui();
    }

    private DocumentComparisonResult compare(final File file, final DvtLogIoDao logIoDao)
            throws XPathExpressionException, SQLException, ParserConfigurationException, SAXException, IOException {
        final DocumentComparatorImpl documentComparatorImpl = new DocumentComparatorImpl();
        documentComparatorImpl.setDvtLogIoDao(logIoDao);
        return documentComparatorImpl.compare(XmlUtils.fileAsDocument(file), file.getName());
    }

    private void customInitGui() {
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());
        fileChooser.setFileFilter(new FileFilter() {

            @Override
            public String getDescription() {
                return ".xml";
            }

            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                if (f.getName().toLowerCase().endsWith(".xml")) {
                    return true;
                }
                return false;
            }
        });

        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int returnVal = fileChooser.showOpenDialog(MainFrame.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    final File file = fileChooser.getSelectedFile();
                    selectedFileNameText.setText(file.getAbsolutePath());
                    if (file.isDirectory()) {
                        File[] subFiles = file.listFiles(new FilenameFilter() {

                            public boolean accept(File arg0, String arg1) {
                                return arg1.toLowerCase().endsWith(".xml");
                            }
                        });
                        if (subFiles.length > 0) {
                            NameObject[] nameObjects = new NameObject[subFiles.length];
                            for (int i = 0; i < subFiles.length; i++) {
                                nameObjects[i] = new NameObject(subFiles[i].getName(), subFiles[i]);
                            }
                            jList1.setModel(new DefaultComboBoxModel(nameObjects));
                            okButton.setEnabled(true);
                        } else {
                            jList1.setModel(new DefaultComboBoxModel(new Object[] {}));
                            okButton.setEnabled(false);
                        }

                    } else {
                        jList1.setModel(new DefaultComboBoxModel(new Object[] { new NameObject(file.getName(), file) }));
                        okButton.setEnabled(true);
                    }
                } else {
                    jList1.setModel(new DefaultComboBoxModel(new Object[] {}));
                    okButton.setEnabled(false);
                }

            }
        });

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                final Object[] newModelItems = new Object[jList1.getModel().getSize()];
                DvtLogIoDao logservice;
                try {
                    logservice = new DvtLogIoDaoImpl(".\\result\\"
                            + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".csv");
                } catch (final IOException e1) {
                    logger.log(Level.SEVERE, "Could not create result files. assigning null log service.", e1);
                    logservice = new NullDvtLogDao();
                }
                for (int i = 0; i < jList1.getModel().getSize(); i++) {
                    final NameObject nameObject = (NameObject) jList1.getModel().getElementAt(i);
                    final File file = ((File) nameObject.getObject());
                    try {
                        final DocumentComparisonResult result = compare(file, logservice);
                        final String s = StringUtils.cat(false, file.getName(), "     -     [Matches : (",
                                result.getExactMatch(), "), ", "Mismatches : (", result.getMisMatch(), "), ",
                                "Neglects : (", result.getNiglect(), ") ]");
                        nameObject.setName(s);
                    } catch (final XPathExpressionException e) {
                        logger.log(Level.SEVERE, "Error", e);
                        nameObject.setName(StringUtils.cat(false, file.getName(), " [XPATH ERROR]"));
                    } catch (final SQLException e) {
                        logger.log(Level.SEVERE, "Error", e);
                        nameObject.setName(StringUtils.cat(false, file.getName(), " [DB ERROR]"));
                    } catch (final ParserConfigurationException e) {
                        logger.log(Level.SEVERE, "Error", e);
                        nameObject.setName(StringUtils.cat(false, file.getName(), " [XML PARSE ERROR]"));
                    } catch (final SAXException e) {
                        logger.log(Level.SEVERE, "Error", e);
                        nameObject.setName(StringUtils.cat(false, file.getName(), " [XML PARSE ERROR]"));
                    } catch (final IOException e) {
                        logger.log(Level.SEVERE, "Error", e);
                        nameObject.setName(StringUtils.cat(false, file.getName(), " [FILE ERROR]"));
                    } catch (final DvtException e) {
                        logger.log(Level.SEVERE, "Error", e);
                        nameObject.setName(StringUtils.cat(false, file.getName(),
                                StringUtils.cat(" [", e.getMessage(), "]")));
                    } catch (final Exception e) {
                        logger.log(Level.SEVERE, "Error", e);
                        JOptionPane.showMessageDialog(MainFrame.this, e.getMessage(),
                                StringUtils.cat("Unknow Error for the file", file.getName()), JOptionPane.ERROR_MESSAGE);
                        nameObject.setName(StringUtils.cat(false, file.getName(),
                                StringUtils.cat(" [", e.getMessage(), "]")));
                    }
                    newModelItems[i] = nameObject;
                }
                try {
                    logservice.flush();
                    logservice.close();
                } catch (final IOException e) {
                }
                jList1.setModel(new DefaultComboBoxModel(newModelItems));
            }
        });

    }

    private void initGUI() {
        try {
            GroupLayout thisLayout = new GroupLayout((JComponent) getContentPane());
            getContentPane().setLayout(thisLayout);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            {
                jPanel1 = new JPanel();
                GroupLayout jPanel1Layout = new GroupLayout((JComponent) jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                {
                    jLabel1 = new JLabel();
                    jLabel1.setText("Directory/File");
                }
                {
                    jList1 = new JList();
                    jList1.setEnabled(false);
                }
                {
                    browseButton = new JButton();
                    browseButton.setText("Browse");

                }
                {
                    selectedFileNameText = new JTextField();
                    selectedFileNameText.setEditable(false);
                }
                jPanel1Layout.setHorizontalGroup(jPanel1Layout
                        .createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                jPanel1Layout
                                        .createParallelGroup()
                                        .addGroup(
                                                GroupLayout.Alignment.LEADING,
                                                jPanel1Layout
                                                        .createSequentialGroup()
                                                        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 87,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(selectedFileNameText, 0, 161, Short.MAX_VALUE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(browseButton, GroupLayout.PREFERRED_SIZE, 96,
                                                                GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jList1, GroupLayout.Alignment.LEADING, 0, 362, Short.MAX_VALUE))
                        .addGap(6));
                jPanel1Layout.setVerticalGroup(jPanel1Layout
                        .createSequentialGroup()
                        .addGroup(
                                jPanel1Layout
                                        .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(selectedFileNameText, GroupLayout.Alignment.BASELINE,
                                                GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1, GroupLayout.Alignment.BASELINE,
                                                GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(browseButton, GroupLayout.Alignment.BASELINE,
                                                GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jList1, 0, 153, Short.MAX_VALUE).addContainerGap());
            }
            {
                jPanel2 = new JPanel();
                GroupLayout jPanel2Layout = new GroupLayout((JComponent) jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                {
                    jButton1 = new JButton();
                    jButton1.setText("Cancel");
                }
                {
                    okButton = new JButton();
                    okButton.setText("OK");
                    okButton.setEnabled(false);
                }
                jPanel2Layout.setHorizontalGroup(jPanel2Layout
                        .createSequentialGroup()
                        .addContainerGap(245, Short.MAX_VALUE)
                        .addComponent(okButton, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.PREFERRED_SIZE).addContainerGap());
                jPanel2Layout.setVerticalGroup(jPanel2Layout
                        .createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                jPanel2Layout
                                        .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(okButton, GroupLayout.Alignment.BASELINE,
                                                GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton1, GroupLayout.Alignment.BASELINE,
                                                GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE)).addContainerGap());
            }
            thisLayout.setVerticalGroup(thisLayout.createSequentialGroup().addContainerGap()
                    .addComponent(jPanel1, 0, 192, Short.MAX_VALUE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap());
            thisLayout.setHorizontalGroup(thisLayout
                    .createSequentialGroup()
                    .addContainerGap()
                    .addGroup(
                            thisLayout.createParallelGroup()
                                    .addComponent(jPanel1, GroupLayout.Alignment.LEADING, 0, 716, Short.MAX_VALUE)
                                    .addComponent(jPanel2, GroupLayout.Alignment.LEADING, 0, 716, Short.MAX_VALUE))
                    .addContainerGap());
            pack();
            this.setSize(748, 300);
        } catch (Exception e) {
            // add your error handling code here
            e.printStackTrace();
        }
    }

}
