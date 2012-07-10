/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RegisterPatients.java
 *
 * Created on Mar 30, 2011, 8:12:36 AM
 */

package crystal.Patients;
import crystal.Resources.Database;
import crystal.validation.validators;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.util.Calendar;
import javax.swing.JOptionPane;
/**
 *
 * @author albert
 */
public class RegisterPatients extends javax.swing.JDialog {

    Dimension screen = 	Toolkit.getDefaultToolkit().getScreenSize();
    private int patientID;
    ViewPatients mzazi;
    boolean isParent = false;
    /** Creates new form RegisterPatients */
    public RegisterPatients(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocation((screen.width - getWidth())/2,((screen.height-getHeight())/2)-65);
        update.setEnabled(false);
    }

    RegisterPatients(ViewPatients aThis, boolean b) {
        super(aThis, b);
        mzazi = aThis;
        initComponents();
        update.setEnabled(false);
        setLocation((screen.width - getWidth())/2,((screen.height-getHeight())/2)-65);
        isParent = true;
        
    }

    RegisterPatients(ViewPatients aThis, boolean b, String patientID) {
        super(aThis, b);
        mzazi = aThis;
        initComponents();
        save.setEnabled(false);
        this.patientID = Integer.parseInt(patientID);
        setLocation((screen.width - getWidth())/2,((screen.height-getHeight())/2)-65);
        isParent = true;

        String sql = "Select * from patient where Patient_ID = "+patientID;
        try{
            ResultSet rs = new Database().Query(sql);
            while(rs.next()){
                fName.setText(rs.getString("fName"));
                sname.setText(rs.getString("sName"));
                oNames.setText(rs.getString("OtherNames"));
                address.setText(rs.getString("address"));
                phone.setText(""+rs.getInt("phoneNumber"));
                idNo.setText(""+rs.getInt("national_ID"));
                gender.setSelectedItem(rs.getString("gender"));
                blood.setSelectedItem(rs.getString("blood_type"));
                
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fName = new javax.swing.JTextField();
        sname = new javax.swing.JTextField();
        oNames = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        gender = new javax.swing.JComboBox();
        blood = new javax.swing.JComboBox();
        address = new javax.swing.JTextField();
        phone = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        idNo = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        save = new javax.swing.JButton();
        update = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registration");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel1.setText("First Name:");

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel2.setText("Surname:");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel3.setText("Other Names:");

        fName.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        fName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fNameKeyTyped(evt);
            }
        });

        sname.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        sname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                snameKeyTyped(evt);
            }
        });

        oNames.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        oNames.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                oNamesKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel5.setText("Gender:");

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel6.setText("Blood Type:");

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel7.setText("Address:");

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel8.setText("Phone Number:");

        gender.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        gender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Male", "Female" }));

        blood.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        blood.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A +ve", "A -ve", "B +ve", "B -ve", "AB +ve", "AB -ve", "O +ve", "O -ve" }));

        address.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N

        phone.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        phone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phoneKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel9.setText("ID Number:");

        idNo.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        idNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                idNoKeyTyped(evt);
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
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(phone, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(address, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(blood, javax.swing.GroupLayout.Alignment.LEADING, 0, 263, Short.MAX_VALUE)
                    .addComponent(oNames, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sname, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fName, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gender, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(idNo))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(sname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(oNames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(blood, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(idNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        save.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        update.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/crystal/img/Update11.png"))); // NOI18N
        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jButton4.setText("Cancel");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
       
        
        String sql = "INSERT INTO `patient` (`Patient_ID`, `fName`, `sName`, `OtherNames`, `national_ID`, `address`, `gender`, `blood_type`, `phoneNumber`) VALUES (NULL, '"+fName.getText()+"', '"+sname.getText()+"', '"+oNames.getText()+"', '"+idNo.getText()+"', '"+address.getText()+"', '"+gender.getSelectedItem()+"', '"+blood.getSelectedItem()+"', '"+phone.getText()+"');";
        
        boolean x = false;

        if (fName.getText().equals("") || sname.getText().equals("") || address.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Ensure you have filled all the required fields!", "", JOptionPane.ERROR_MESSAGE);
        }else{
            try{
                new Database().addNew(sql);
                String getID = "Select Patient_ID from patient";
                ResultSet rs = new Database().Query(getID);
                rs.afterLast();
                rs.previous();
                patientID = rs.getInt("Patient_ID");
                x = true;
                if (isParent == true){
                    mzazi.refresh();
                }
            }catch(Exception err){
                err.printStackTrace();
            }
        }
        if (x==true){
            JOptionPane.showMessageDialog(this, "Patient registered successfully!", "Registered", JOptionPane.INFORMATION_MESSAGE);
            int r = JOptionPane.showConfirmDialog(this, "Do you want to add this patient to the queue?", "", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (r==0){
                sql = "INSERT INTO `queue` (`queue_ID`, `patient_ID`, `seenNurse`, `seenDoctor`,seenLabTech, `seenPharmacy`) " +
                        "VALUES (NULL, "+patientID+", b'0', b'0', b'0',b'0');";
                try{
                    new Database().addNew(sql);
                }catch(Exception err){
                    err.printStackTrace();
                }
            }
            fName.setText("");
            sname.setText("");
            oNames.setText("");
            idNo.setText("");
            address.setText("");
            phone.setText("");
        }
    }//GEN-LAST:event_saveActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        String sql = "UPDATE patient set fName = '"+fName.getText()+"', sName = '"+sname.getText()+"', OtherNames = '"+oNames.getText()+"' , " +
                "national_ID = '"+idNo.getText()+"', address = '"+address.getText()+"', gender = '"+gender.getSelectedItem()+"', " +
                "blood_type = '"+blood.getSelectedItem()+"', phoneNumber = '"+phone.getText()+"' WHERE Patient_ID = "+patientID;
        boolean x = false;
        try{
            new Database().Update(sql);
            x = true;
        }catch (Exception err){
            err.printStackTrace();
        }

        if (x==true){
            JOptionPane.showMessageDialog(this, "Details Updated successfully!", "", JOptionPane.INFORMATION_MESSAGE);
            fName.setText("");
            sname.setText("");
            oNames.setText("");
            idNo.setText("");
            address.setText("");
            phone.setText("");
            save.setEnabled(true);
            update.setEnabled(false);
            mzazi.refresh();
        }
    }//GEN-LAST:event_updateActionPerformed

    private void idNoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idNoKeyTyped
        
        validators.validateDigitTextField(evt);
    }//GEN-LAST:event_idNoKeyTyped

    private void phoneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneKeyTyped
        validators.validateDigitTextField(evt);
    }//GEN-LAST:event_phoneKeyTyped

    private void fNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fNameKeyTyped
        validators.validateNonDigitTextField(evt);
    }//GEN-LAST:event_fNameKeyTyped

    private void snameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_snameKeyTyped
        validators.validateNonDigitTextField(evt);
    }//GEN-LAST:event_snameKeyTyped

    private void oNamesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_oNamesKeyTyped
        validators.validateNonDigitTextField(evt);
    }//GEN-LAST:event_oNamesKeyTyped

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RegisterPatients dialog = new RegisterPatients(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField address;
    private javax.swing.JComboBox blood;
    private javax.swing.JTextField fName;
    private javax.swing.JComboBox gender;
    private javax.swing.JTextField idNo;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField oNames;
    private javax.swing.JTextField phone;
    private javax.swing.JButton save;
    private javax.swing.JTextField sname;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables

}
