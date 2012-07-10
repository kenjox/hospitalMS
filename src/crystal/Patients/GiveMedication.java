/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GiveMedication.java
 *
 * Created on Mar 31, 2011, 9:37:55 PM
 */

package crystal.Patients;

import crystal.Resources.Database;
import crystal.Resources.Domains;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class GiveMedication extends javax.swing.JDialog {
    DefaultTableModel model = new DefaultTableModel(null, new String [] {
                "Drug ID", "Drug", "Quantity", "Price", "Sub-total"
            });

    String sqlTable = "Select * from drug ORDER BY Drug_name ASC";
    String[] header = {"ID", "Drug Name", "Price"};
    String[] columns = {"DrugID", "Drug_name", "price"};
    String patientID;
    int diagnosisID;

    PharmacyQueue mzazi;
    GiveMedication app;

    Dimension screen = 	Toolkit.getDefaultToolkit().getScreenSize();
    private TableHandler listener;
    /** Creates new form GiveMedication */
    public GiveMedication(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocation((screen.width - getWidth())/2,((screen.height-getHeight())/2)-65);
    }

    GiveMedication(PharmacyQueue aThis, boolean b, String patientID) {
        super(aThis, b);
        this.patientID = patientID;
        mzazi = aThis;
        app = this;
        initComponents();
        receipt.setEnabled(false);
        setPrescription();
        setBillSize();
        setDrugValues();
        setLocation((screen.width - getWidth())/2,((screen.height-getHeight())/2)-65);
        try{
            ResultSet rs = new Database().Query("SELECT * from patient WHERE  Patient_ID = "+patientID);
            while (rs.next()){
                jLabel2.setText(rs.getString("sName")+", "+rs.getString("fName")+" "+rs.getString("otherNames"));
            }
        }catch(Exception err){
            err.printStackTrace();
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        prescription = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        bill = new javax.swing.JTable();
        remove = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        receipt = new javax.swing.JButton();
        checkout = new javax.swing.JButton();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable2);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jTable3);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel1.setText("Search:");

        jTextField1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jButton2.setText("Refresh");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jLabel2.setText("Patient NAme:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButton2)
                    .addComponent(jLabel2))
                .addGap(24, 24, 24))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Doctor's Prescription", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe Print", 0, 10))); // NOI18N

        prescription.setBackground(new java.awt.Color(153, 153, 153));
        prescription.setColumns(20);
        prescription.setEditable(false);
        prescription.setForeground(new java.awt.Color(255, 255, 255));
        prescription.setRows(5);
        jScrollPane1.setViewportView(prescription);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

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
        jScrollPane2.setViewportView(jTable1);
        */
        jTable1 = Domains.getTable(header, columns, sqlTable);
        try{
            jScrollPane2.setViewportView(jTable1);
        }catch(Exception err){
            err.printStackTrace();
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Billing Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Light", 0, 10))); // NOI18N

        bill.setBackground(new java.awt.Color(51, 153, 255));
        bill.setForeground(new java.awt.Color(255, 255, 255));
        bill.setModel(model);
        bill.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bill.setSelectionBackground(new java.awt.Color(102, 102, 102));
        bill.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane5.setViewportView(bill);
        bill.getColumnModel().getColumn(0).setResizable(false);
        bill.getColumnModel().getColumn(1).setResizable(false);
        bill.getColumnModel().getColumn(2).setResizable(false);
        bill.getColumnModel().getColumn(3).setResizable(false);
        bill.getColumnModel().getColumn(4).setResizable(false);

        remove.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        remove.setText("Remove Drug");
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(remove, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(remove)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        receipt.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        receipt.setText("Receipt");
        receipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receiptActionPerformed(evt);
            }
        });

        checkout.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        checkout.setText("Check-out");
        checkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(checkout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(receipt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(receipt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkout)
                        .addGap(116, 116, 116)
                        .addComponent(jButton1)
                        .addGap(22, 22, 22))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try{
            jTable1 = Domains.getTable(header, columns, sqlTable);
            setDrugValues();
            jScrollPane2.getViewport().removeAll();
            jScrollPane2.getViewport().add(jTable1);
            jScrollPane2.repaint();
        }catch(Exception err){
            err.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        if (bill.isRowSelected(bill.getSelectedRow()) == false){
            JOptionPane.showMessageDialog(this, "Please select drug to remove from patients bill", "", JOptionPane.ERROR_MESSAGE);
        }
        else{
            model.removeRow(bill.getSelectedRow());
        }
    }//GEN-LAST:event_removeActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        String sqlSearch = "Select * from drug WHERE drug_name LIKE '%"+jTextField1.getText()+"%' ORDER BY Drug_name ASC";
        try{
            jTable1 = Domains.getTable(header, columns, sqlSearch);
            setDrugValues();
            jScrollPane2.getViewport().removeAll();
            jScrollPane2.getViewport().add(jTable1);
            jScrollPane2.repaint();
        }catch(Exception re){
            re.printStackTrace();
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void checkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkoutActionPerformed
        if(bill.getModel().getRowCount() < 1){
            JOptionPane.showMessageDialog(this, "Please give the patient prescribed medicine before checking out!", "", JOptionPane.ERROR_MESSAGE);
        }else{
            for (int z = 0; z < bill.getModel().getRowCount(); z++){
                int drugID = Integer.parseInt(""+bill.getModel().getValueAt(z, 0));
                int quant = Integer.parseInt(""+bill.getModel().getValueAt(z, 2));
                float total = Float.parseFloat(""+bill.getModel().getValueAt(z, 4));
                String sqlInsert = "INSERT into diagnosis_medication (Diagnosis_Medication_ID, Diagnosis_ID, Drug_ID, Quantity, SubTotal)" +
                    "VALUES(NULL, "+diagnosisID+", "+drugID+", "+quant+", "+total+" )";
                
                String sqlDrug = "SELECT Quantity FROM drug where `drug`.`DrugID` = "+drugID;
                
                try{
                    ResultSet rs = new Database().Query(sqlDrug);
                    rs.next();
                    String qtyFromDb = rs.getString("Quantity");
                    
                    int qtyFromDb2 = Integer.parseInt(qtyFromDb);
                    
                    int finalQty = qtyFromDb2 - quant;
                    
                    String sqlUpdateStock = "UPDATE `drug` SET `Quantity` = '"+finalQty+"' WHERE `drug`.`DrugID` = "+drugID;
                    
                    new Database().Update(sqlUpdateStock);
                    new Database().addNew(sqlInsert);
                }catch(Exception er){
                    er.printStackTrace();
                }
               
            }
            try{
                float total = 0;
                for (int y = 0; y< bill.getModel().getRowCount(); y++ ){
                    total += Float.parseFloat(""+bill.getModel().getValueAt(y, 4));
                }
                String update = "UPDATE `diagnosis` SET `Approved` = 1, Total = "+total+" WHERE `diagnosis`.`Diagnosis_ID` = "+diagnosisID;
                String sql = "DELETE FROM queue WHERE patient_ID = " +patientID;
                new Database().Delete(sql);
                new Database().Update(update);
            }catch(Exception err){
                err.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Patient has been checked out. Proceed to printing receipt", "", JOptionPane.INFORMATION_MESSAGE);
            checkout.setEnabled(false);
            remove.setEnabled(false);
            receipt.setEnabled(true);
            mzazi.refresh();
        }
    }//GEN-LAST:event_checkoutActionPerformed

    private void receiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receiptActionPerformed
       new Receipt(this, patientID).setVisible(true);
    }//GEN-LAST:event_receiptActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GiveMedication dialog = new GiveMedication(new javax.swing.JFrame(), true);
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
    private javax.swing.JTable bill;
    private javax.swing.JButton checkout;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea prescription;
    private javax.swing.JButton receipt;
    private javax.swing.JButton remove;
    // End of variables declaration//GEN-END:variables

    private void setPrescription() {
        String selectSQL = "Select * from Diagnosis where patient_ID = "+patientID+" AND Approved = 0";
        try{
            ResultSet rs = new Database().Query(selectSQL);
            while (rs.next()){
                diagnosisID = rs.getInt("Diagnosis_ID");
                prescription.setText(rs.getString("Prescription"));
            }
        }catch(Exception err){
            err.printStackTrace();
        }
    }

    private void setBillSize() {
        bill.getColumnModel().getColumn(0).setPreferredWidth(60);
        bill.getColumnModel().getColumn(1).setPreferredWidth(205);
        bill.getColumnModel().getColumn(2).setPreferredWidth(90);
        bill.getColumnModel().getColumn(3).setPreferredWidth(90);
        bill.getColumnModel().getColumn(4).setPreferredWidth(90);
    }

    private void setDrugValues() {
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(277);
        listener = new TableHandler(jTable1);
        jTable1.getSelectionModel().addListSelectionListener(listener);
        jTable1.getColumnModel().getSelectionModel().addListSelectionListener(listener);
    }
public class TableHandler implements ListSelectionListener{

    JTable table;
    private int id;

    public TableHandler(JTable table) {
        this.table = table;
    }
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == table.getSelectionModel() && table.getRowSelectionAllowed()) {
            Object x = JOptionPane.showInputDialog(app, "Enter amount of Drug", "",JOptionPane.PLAIN_MESSAGE, null, null,null);
            String drugID = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
            String drugname= (String) jTable1.getValueAt(jTable1.getSelectedRow(), 1);
            String price = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 2);
            if (x !=null ){
                bill.getModel().getRowCount();
                String[] billContent = new String[5];
                billContent[0] = drugID;
                billContent[1] = drugname;
                billContent[2] = (String) x;
                billContent[3] = price;
                float total = Float.parseFloat(String.valueOf(x)) * Float.parseFloat(price);
                billContent[4] = ""+total;

                model.addRow(billContent);
            }
        }
    }

}

 public void closeParent(){
     mzazi.dispose();
 }
}
