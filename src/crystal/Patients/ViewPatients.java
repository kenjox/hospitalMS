/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ViewPatients.java
 *
 * Created on Mar 30, 2011, 9:51:17 AM
 */

package crystal.Patients;

import crystal.Resources.Database;
import crystal.Resources.Domains;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class ViewPatients extends javax.swing.JDialog {
    String sqlTable = "Select * from patient ORDER BY Patient_ID ASC";
    String[] header = {"ID", "First Name", "Surname", "Other names", "Gender"};
    String[] columns = {"Patient_ID", "fName", "sName", "OtherNames", "gender"};

    Dimension screen = 	Toolkit.getDefaultToolkit().getScreenSize();

    /** Creates new form ViewPatients */
    public ViewPatients(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocation((screen.width - getWidth())/2,((screen.height-getHeight())/2)-65);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        addToQueue = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel1.setText("Search:");

        searchField.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchFieldKeyTyped(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jButton2.setText("Add Patient");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jButton3.setText("Edit Patient");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jButton4.setText("Delete Patient");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jButton5.setText("Refresh List");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        addToQueue.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        addToQueue.setText("Add to Queue");
        addToQueue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToQueueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addToQueue, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(169, 169, 169)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchField, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(addToQueue)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        /*
        jScrollPane1.setViewportView(jTable1);
        */
        jTable1 = Domains.getTable(header, columns, sqlTable);
        try{
            jScrollPane1.setViewportView(jTable1);
        }catch(Exception err){
            err.printStackTrace();
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 853, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(700, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new RegisterPatients(this, true).setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        refresh();
        searchField.setText("");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void addToQueueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToQueueActionPerformed
        if (jTable1.isRowSelected(jTable1.getSelectedRow()) == false){
            JOptionPane.showMessageDialog(this, " Select patient to add in the queue", "", JOptionPane.WARNING_MESSAGE);
        }else{
        String patientID =  (String) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
        String sql = "INSERT INTO `queue` (`queue_ID`, `patient_ID`, `seenNurse`, `seenDoctor`,seenLabTech, `seenPharmacy`) " +
                "VALUES (NULL, "+patientID+", b'0', b'0', b'0',b'0');";
        String name = ""+jTable1.getValueAt(jTable1.getSelectedRow(), 1)+" "+jTable1.getValueAt(jTable1.getSelectedRow(), 2);
        try{
            int x = JOptionPane.showConfirmDialog(this, "Are you sure you want to add "+name, "", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (x == 0){
                boolean y = false;
                ResultSet rs = new Database().Query("Select * from queue where patient_ID = "+patientID);
                rs.afterLast();
                rs.previous();
                if (rs.getRow()==0){
                    new Database().addNew(sql);
                    y = true;
                }
                else{
                    JOptionPane.showMessageDialog(this, name+" is already in the queue", "", JOptionPane.WARNING_MESSAGE);
                }
                if (y==true){
                    JOptionPane.showMessageDialog(this, name+" has been added to the queue", "", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }catch(Exception err){
            err.printStackTrace();
        }}
    }//GEN-LAST:event_addToQueueActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (jTable1.isRowSelected(jTable1.getSelectedRow())== false){
            JOptionPane.showMessageDialog(this, "Please select patient to edit Information", "", JOptionPane.WARNING_MESSAGE);
        }
        else{
            String patientID = ""+jTable1.getValueAt(jTable1.getSelectedRow(), 0);
            new RegisterPatients(this, true, patientID).setVisible(true);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void searchFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyTyped
        sqlTable = "Select * from patient where fName LIKE '%"+searchField.getText()+"%' OR sName LIKE '%"+searchField.getText()+"%'" +
                " OR OtherNames LIKE '%"+searchField.getText()+"%' ORDER BY Patient_ID ASC";
        try{
            jTable1 = Domains.getTable(header, columns, sqlTable);
            jScrollPane1.getViewport().removeAll();
            jScrollPane1.getViewport().add(jTable1);
            jScrollPane1.repaint();
        }catch(Exception err){
            err.printStackTrace();
        }
    }//GEN-LAST:event_searchFieldKeyTyped

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (jTable1.isRowSelected(jTable1.getSelectedRow()) == false){
            JOptionPane.showMessageDialog(this, " Select patient to delete", "", JOptionPane.WARNING_MESSAGE);
        }
        else{
            String sql = "DELETE FROM `patient` WHERE `patient`.`Patient_ID` = "
                    +jTable1.getValueAt(jTable1.getSelectedRow(), 0);
            try{
            new Database().Delete(sql);
            JOptionPane.showMessageDialog(this, "Successfully deleted!", "", JOptionPane.INFORMATION_MESSAGE);
            refresh();
            }catch(Exception err){
                err.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ViewPatients dialog = new ViewPatients(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToQueue;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField searchField;
    // End of variables declaration//GEN-END:variables

    public void refresh() {
        sqlTable = "Select * from patient ORDER BY Patient_ID ASC";
        try{
            jTable1 = Domains.getTable(header, columns, sqlTable);
            jScrollPane1.getViewport().removeAll();
            jScrollPane1.getViewport().add(jTable1);
            jScrollPane1.repaint();
        }catch(Exception err){
            err.printStackTrace();
        }
    }
}
