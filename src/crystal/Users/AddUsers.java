/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AddUsers.java
 *
 * Created on Mar 29, 2011, 11:17:48 PM
 */

package crystal.Users;

import crystal.Resources.Database;
import crystal.Resources.Domains;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class AddUsers extends javax.swing.JDialog {

    Dimension screen = 	Toolkit.getDefaultToolkit().getScreenSize();
    boolean isParent = false;
    ViewUsers mzazi;
    String userID;
    /** Creates new form AddUsers */
    public AddUsers(Home parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocation((screen.width - getWidth())/2,((screen.height-getHeight())/2)-65);
        setResizable(false);
        update.setEnabled(false);
    }

    public AddUsers(ViewUsers parent, boolean modal) {
        super(parent, modal);
        mzazi = parent;
        isParent = true;
        initComponents();
        setLocation((screen.width - getWidth())/2,((screen.height-getHeight())/2)-65);
        setResizable(false);
        update.setEnabled(false);
    }

    public AddUsers(ViewUsers parent, boolean modal, String id) {
        super(parent, modal);
        mzazi = parent;
        isParent = true;
        userID = id;
        initComponents();
        setLocation((screen.width - getWidth())/2,((screen.height-getHeight())/2)-65);
        setResizable(false);

        save.setEnabled(false);
        try{
            ResultSet rsEdit = new Database().Query("Select * from users where user_ID = "+userID);
            while (rsEdit.next()){
                fName.setText(rsEdit.getString("fName"));
                surname.setText(rsEdit.getString("sName"));
                oNames.setText(rsEdit.getString("otherNames"));
                role.setSelectedIndex((rsEdit.getInt("role_ID")-1));
            }
        }catch(Exception err){
            err.printStackTrace();
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        fName = new javax.swing.JTextField();
        surname = new javax.swing.JTextField();
        oNames = new javax.swing.JTextField();
        role = new javax.swing.JComboBox();
        save = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        update = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel1.setText("First Name:");

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel2.setText("Surname:");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel3.setText("Other Names:");

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel4.setText("Role:");

        fName.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N

        surname.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N

        oNames.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N

        role.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        role.setModel(new javax.swing.DefaultComboBoxModel(Domains.getDomains()));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(oNames, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addComponent(surname, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addComponent(fName, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addComponent(role, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(surname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(oNames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(role, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        save.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        update.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(save)
                    .addComponent(jButton2)
                    .addComponent(update))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        String sql = "INSERT INTO `users` (`user_ID` ,`fName` ,`sName` ,`otherNames` ,`password` ,`role_ID`) "+
                "VALUES(NULL, '"+fName.getText()+"', '"+surname.getText()+"', '"+oNames.getText()+"', '123456', "
                +(role.getSelectedIndex()+1)+")";
        if (fName.getText().equals("") || surname.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Fill the required fields", "", JOptionPane.ERROR_MESSAGE);
        }
        else{
            boolean x = false;
            try{
                new Database().addNew(sql);
                x = true;
            }catch(Exception err){
                err.printStackTrace();
            }
            if (x==true){
                JOptionPane.showMessageDialog(this, ""+fName.getText()+" has been succesfully added as a user.", "Success", JOptionPane.INFORMATION_MESSAGE);
                fName.setText(null);
                surname.setText("");
                oNames.setText("");
                if (isParent == true){
                    mzazi.refresh();
                }
                
            }
        }
    }//GEN-LAST:event_saveActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        String sql = "UPDATE users SET fName = '"+fName.getText()+"', `sName` = '"+surname.getText()+"', otherNames = '"+oNames.getText()+
                "', role_ID = "+(role.getSelectedIndex()+1)+" WHERE `user_ID` = "+userID;
        boolean s = false;
        try{
            new Database().Update(sql);
            s = true;
        }catch(Exception err){
            err.printStackTrace();
        }
        if (s==true){
            JOptionPane.showMessageDialog(this, fName.getText()+"'s details successfully updated", "", JOptionPane.INFORMATION_MESSAGE);
            fName.setText(null);
            surname.setText("");
            oNames.setText("");
            if (isParent == true){
                mzazi.refresh();
            }
            update.setEnabled(false);
            save.setEnabled(true);
        }
    }//GEN-LAST:event_updateActionPerformed

    /**
    * @param args the command line arguments
    */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField fName;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField oNames;
    private javax.swing.JComboBox role;
    private javax.swing.JButton save;
    private javax.swing.JTextField surname;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables

}
