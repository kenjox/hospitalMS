/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AddDrugs.java
 *
 * Created on Mar 31, 2011, 7:35:54 PM
 */

package crystal.Pharmacy;

import crystal.Resources.Database;
import crystal.Users.Home;
import crystal.validation.validators;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class AddDrugs extends javax.swing.JDialog {
    Dimension screen = 	Toolkit.getDefaultToolkit().getScreenSize();

    int userID, drugID;
    ListDrugs mzazi;
    boolean isParent = false;
    /** Creates new form AddDrugs */
    public AddDrugs(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public AddDrugs(Home aThis, boolean b, int userID) {
        super(aThis, b);
        this.userID = userID;
        initComponents();
        update.setEnabled(false);
        setLocation((screen.width - getWidth())/2,((screen.height-getHeight())/2)-65);
    }

    AddDrugs(ListDrugs aThis, boolean b) {
        super(aThis, b);
        mzazi = aThis;
        isParent = true;
        initComponents();
        update.setEnabled(false);
        setLocation((screen.width - getWidth())/2,((screen.height-getHeight())/2)-65);
    }

    AddDrugs(ListDrugs aThis, boolean b, int drugID) {
        super(aThis, b);
        mzazi = aThis;
        this.drugID = drugID;
        initComponents();
        save.setEnabled(false);
        setLocation((screen.width - getWidth())/2,((screen.height-getHeight())/2)-65);
        String sqlSelect = "SELECT * FROM `drug` WHERE DrugID = "+drugID;
        try{
            ResultSet rs = new Database().Query(sqlSelect);
            while (rs.next()){
                drugname.setText(rs.getString("Drug_name"));
                quantity.setText(rs.getString("Quantity"));
                price.setText(rs.getString("price"));
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        drugname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        quantity = new javax.swing.JTextField();
        price = new javax.swing.JTextField();
        save = new javax.swing.JButton();
        update = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel1.setText("Drug name:");

        drugname.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel2.setText("Quantity:");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel3.setText("Price:");

        quantity.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        quantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                quantityKeyTyped(evt);
            }
        });

        price.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                priceKeyTyped(evt);
            }
        });

        save.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        update.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jButton3.setText("Cancel");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(update, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(drugname, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(drugname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(save)
                    .addComponent(update)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        boolean x =false;
        if (drugname.getText().equals("") || quantity.getText().equals("") || price.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Please fill all the required fields", null, JOptionPane.ERROR_MESSAGE);
        }
        else{
            String sqlInsert = "INSERT INTO `drug` (`DrugID`, `Drug_name`, `Quantity`, `price`) VALUES (NULL, '"+drugname.getText()
                    +"', "+quantity.getText()+", "+price.getText()+");";
            try{
                new Database().addNew(sqlInsert);
                x=true;
            }catch(Exception err){
                err.printStackTrace();
            }
            if (x == true){
                JOptionPane.showMessageDialog(this, "Drug added", null, JOptionPane.INFORMATION_MESSAGE);
                drugname.setText("");
                quantity.setText("");
                price.setText("");
                if(isParent == true){
                    mzazi.refresh();
                }
            }
        }
    }//GEN-LAST:event_saveActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        boolean x =false;
        if (drugname.getText().equals("") || quantity.getText().equals("") || price.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Please fill all the required fields", null, JOptionPane.ERROR_MESSAGE);
        }else{
            String updateSQL ="UPDATE `drug` SET `Drug_name` = '"+drugname.getText()+"', Quantity = "+quantity.getText()+
                    ", price = "+price.getText()+" WHERE `drug`.`DrugID` = " + drugID;
            try{
                new Database().Update(updateSQL);
                drugname.setText("");
                quantity.setText("");
                price.setText("");
                update.setEnabled(false);
                save.setEnabled(true);
                mzazi.refresh();
            }catch( Exception err){
                err.printStackTrace();
            }
        }
    }//GEN-LAST:event_updateActionPerformed

    private void quantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantityKeyTyped
        validators.validateDigitTextField(evt);
    }//GEN-LAST:event_quantityKeyTyped

    private void priceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_priceKeyTyped
        validators.validateDigitTextField(evt);
    }//GEN-LAST:event_priceKeyTyped

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddDrugs dialog = new AddDrugs(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField drugname;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField price;
    private javax.swing.JTextField quantity;
    private javax.swing.JButton save;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables

}
