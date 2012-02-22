package com.chartis.dvt.gui;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListModel;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class MainFrame extends javax.swing.JFrame {
    private JPanel jPanel1;
    private JButton jButton1;
    private JTextField jTextField1;
    private JButton jButton3;
    private JList jList1;
    private JLabel jLabel1;
    private JButton jButton2;
    private JPanel jPanel2;

    /**
    * Auto-generated main method to display this JFrame
    */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame inst = new MainFrame();
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
            }
        });
    }
    
    public MainFrame() {
        super();
        initGUI();
    }
    
    private void initGUI() {
        try {
            GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
            getContentPane().setLayout(thisLayout);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            {
                jPanel1 = new JPanel();
                GroupLayout jPanel1Layout = new GroupLayout((JComponent)jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                {
                    jLabel1 = new JLabel();
                    jLabel1.setText("Directory/File");
                }
                {
                    ListModel jList1Model = 
                            new DefaultComboBoxModel(
                                    new String[] { "Item One", "Item Two" });
                    jList1 = new JList();
                    jList1.setModel(jList1Model);
                }
                {
                    jButton3 = new JButton();
                    jButton3.setText("jButton3");
                }
                {
                    jTextField1 = new JTextField();
                    jTextField1.setText("jTextField1");
                }
                jPanel1Layout.setHorizontalGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jList1, 0, 344, Short.MAX_VALUE)
                            .addGap(6))
                        .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField1, 0, 143, Short.MAX_VALUE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)))
                    .addGap(6));
                jPanel1Layout.setVerticalGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jList1, 0, 153, Short.MAX_VALUE)
                    .addContainerGap());
            }
            {
                jPanel2 = new JPanel();
                GroupLayout jPanel2Layout = new GroupLayout((JComponent)jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                {
                    jButton1 = new JButton();
                    jButton1.setText("Cancel");
                }
                {
                    jButton2 = new JButton();
                    jButton2.setText("OK");
                }
                    jPanel2Layout.setHorizontalGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap(245, Short.MAX_VALUE)
                    .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap());
                    jPanel2Layout.setVerticalGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap());
            }
            thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, 0, 192, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                .addContainerGap());
            thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(thisLayout.createParallelGroup()
                    .addComponent(jPanel1, GroupLayout.Alignment.LEADING, 0, 368, Short.MAX_VALUE)
                    .addComponent(jPanel2, GroupLayout.Alignment.LEADING, 0, 368, Short.MAX_VALUE))
                .addContainerGap());
            pack();
            setSize(400, 300);
        } catch (Exception e) {
            //add your error handling code here
            e.printStackTrace();
        }
    }

}
