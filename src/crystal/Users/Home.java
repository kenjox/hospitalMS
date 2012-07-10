/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Home.java
 *
 * Created on Mar 29, 2011, 8:19:13 PM
 */

package crystal.Users;

import crystal.Patients.*;
import crystal.Pharmacy.AddDrugs;
import crystal.Pharmacy.ListDrugs;
import javax.swing.JOptionPane;


public class Home extends javax.swing.JFrame {

    int userID, domain;
    /** Creates new form Home */
    public Home() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
    }

    public Home(int id, int role) {
        userID = id;
        domain = role;
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        validateUser();
    }
    /** This method is called from within the constructor to
     * initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        userMenu = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        patients = new javax.swing.JMenu();
        register = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        nurse = new javax.swing.JMenuItem();
        doctor = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        pharmacy = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hospital management system");
        setBackground(new java.awt.Color(153, 153, 255));

        jPanel1.setForeground(new java.awt.Color(102, 102, 255));

        jLabel1.setForeground(new java.awt.Color(153, 153, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/crystal/img/usagi.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/crystal/img/FileFolder.jpg"))); // NOI18N
        jMenu1.setText("File");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Change Password");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Log out");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Exit");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        userMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/crystal/img/users.png"))); // NOI18N
        userMenu.setText("Users");
        userMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userMenuActionPerformed(evt);
            }
        });

        jMenuItem4.setText("Add Users");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        userMenu.add(jMenuItem4);

        jMenuItem5.setText("View / Edit Users");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        userMenu.add(jMenuItem5);

        jMenuBar1.add(userMenu);

        patients.setIcon(new javax.swing.ImageIcon(getClass().getResource("/crystal/img/patient_256.png"))); // NOI18N
        patients.setText("Patients");
        patients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientsActionPerformed(evt);
            }
        });

        register.setText("Register Patients");
        register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerActionPerformed(evt);
            }
        });
        patients.add(register);

        jMenuItem8.setText("List of all Patients");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        patients.add(jMenuItem8);

        nurse.setText("View Patients Queue");
        nurse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nurseActionPerformed(evt);
            }
        });
        patients.add(nurse);

        jMenuBar1.add(patients);

        doctor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/crystal/img/doctor_256.png"))); // NOI18N
        doctor.setText("Doctor");
        doctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doctorActionPerformed(evt);
            }
        });

        jMenuItem9.setText("Patient Queue");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        doctor.add(jMenuItem9);

        jMenuBar1.add(doctor);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/crystal/img/labTech.jpg"))); // NOI18N
        jMenu2.setText("Laboratory");

        jMenuItem6.setText("View patient list");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        pharmacy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/crystal/img/pharmacy_icon.jpg"))); // NOI18N
        pharmacy.setText("Pharmacy");

        jMenuItem10.setText("Add Stock");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        pharmacy.add(jMenuItem10);

        jMenuItem11.setText("Drug Stock");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        pharmacy.add(jMenuItem11);

        jMenuItem12.setText("Pharmacy Queue");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        pharmacy.add(jMenuItem12);

        jMenuBar1.add(pharmacy);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new Login().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        int x = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (x==0)
            System.exit(0);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new ChangePassword(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        new AddUsers(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        new ViewUsers(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerActionPerformed
        new RegisterPatients(this, true).setVisible(true);
    }//GEN-LAST:event_registerActionPerformed

    private void nurseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nurseActionPerformed
        new NurseQueue(this, true).setVisible(true);
    }//GEN-LAST:event_nurseActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        new ViewPatients(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        new DoctorQueue(this, true, userID).setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        new AddDrugs(this, true, userID).setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        new PharmacyQueue(this, true, userID).setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        new ListDrugs(this, true, userID).setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void userMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userMenuActionPerformed

    private void patientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientsActionPerformed

    private void doctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doctorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_doctorActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        new LabQueue(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu doctor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuItem nurse;
    private javax.swing.JMenu patients;
    private javax.swing.JMenu pharmacy;
    private javax.swing.JMenuItem register;
    private javax.swing.JMenu userMenu;
    // End of variables declaration//GEN-END:variables

    public int getUserID(){
        return userID;
    }

    private void validateUser() {
        if (domain == 1){
            pharmacy.setEnabled(true);
            patients.setEnabled(true);
            nurse.setEnabled(true);
            doctor.setEnabled(true);
            userMenu.setEnabled(true);
        }
        if (domain == 2){
            pharmacy.setEnabled(false);
            patients.setEnabled(false);
            nurse.setEnabled(false);
            doctor.setEnabled(true);
            userMenu.setEnabled(false);
        }
        if (domain == 3){
            pharmacy.setEnabled(false);
            patients.setEnabled(true);
            doctor.setEnabled(false);
            userMenu.setEnabled(false);
        }
        if (domain == 5){
            pharmacy.setEnabled(false);
            patients.setEnabled(true);
            nurse.setEnabled(false);
            doctor.setEnabled(false);
            userMenu.setEnabled(false);
        }
        if (domain == 4){
            pharmacy.setEnabled(true);
            patients.setEnabled(false);
            nurse.setEnabled(false);
            doctor.setEnabled(false);
            userMenu.setEnabled(false);
        }
        if (domain == 6){
            pharmacy.setEnabled(false);
            patients.setEnabled(false);
            nurse.setEnabled(false);
            doctor.setEnabled(false);
            userMenu.setEnabled(false);
        }
    }
}
