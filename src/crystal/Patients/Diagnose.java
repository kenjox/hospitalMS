/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Diagnose.java
 *
 * Created on Mar 30, 2011, 2:22:08 PM
 */

package crystal.Patients;

import crystal.Resources.Database;
import crystal.Resources.Domains;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


 
public class Diagnose extends javax.swing.JDialog {
    Dimension screen = 	Toolkit.getDefaultToolkit().getScreenSize();

    DefaultTableModel tablemodel = new DefaultTableModel(null, new String [] {"Date", "Diagnosis", "Prescription"});
    DefaultTableModel tablemodelLAB = new DefaultTableModel(null, new String [] {"Test Name", "Test Results"});

    DefaultListModel model = new DefaultListModel();

    String sqlTable = "Select * from drug ORDER BY Drug_name ASC";
    String[] header = {"ID", "Drug Name"};
    String[] columns = {"DrugID", "Drug_name"};

    String patientID;
    String queueID;
    Diagnose app;
    DoctorQueue mzazi;

    TableHandler listener;
    String date = String.valueOf(DateFormat.getDateInstance(DateFormat.LONG).format(new Date()));
    /** Creates new form Diagnose */
    public Diagnose(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocation((screen.width - getWidth())/2,((screen.height-getHeight())/2)-65);
    }

    Diagnose(DoctorQueue aThis, boolean b, String id) {
        super(aThis, b);
        patientID = id;
        try{
            ResultSet rs = new Database().Query("Select * from queue where patient_id = "+patientID+" ORDER BY queue_ID DESC ");
            rs.next();
            queueID = ""+rs.getInt("queue_ID");
        }catch(Exception e){
            e.printStackTrace();
        }
        //queueID
        initComponents();
        mzazi = aThis;
        setLocation((screen.width - getWidth())/2,((screen.height-getHeight())/2)-65);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(55);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(290);
        listener = new TableHandler(jTable1);
        jTable1.getSelectionModel().addListSelectionListener(listener);
        jTable1.getColumnModel().getSelectionModel().addListSelectionListener(listener);
        setHistory();
        setLabResults();

        try{
            ResultSet rs = new Database().Query("SELECT * from patient WHERE  Patient_ID = "+id);
            while (rs.next()){
                jLabel4.setText(rs.getString("sName")+", "+rs.getString("fName")+" "+rs.getString("otherNames"));
            }
        }catch(Exception err){
            err.printStackTrace();
        }
        setTests();
        app = this;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        pharmacy = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        sendToLab = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        diagnosis = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        prescription = new javax.swing.JList();
        jButton3 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        pressure = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        weight = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        temperature = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        pulse = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        history = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        labResults = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        jLabel4.setText("Patient Name:");

        pharmacy.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        pharmacy.setText("Send To Pharmacy");
        pharmacy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pharmacyActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jButton4.setText("Check-Out Patient");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        sendToLab.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        sendToLab.setText("Send To Lab");
        sendToLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendToLabActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sendToLab)
                .addGap(34, 34, 34)
                .addComponent(pharmacy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(pharmacy, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sendToLab, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

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

        jTextField2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel3.setText("Search for drug:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel1.setText("Diagnosis Notes:");

        diagnosis.setColumns(20);
        diagnosis.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        diagnosis.setRows(5);
        jScrollPane1.setViewportView(diagnosis);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(317, 317, 317))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel2.setText("Prescription:");

        prescription.setBackground(new java.awt.Color(153, 153, 153));
        prescription.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        prescription.setForeground(new java.awt.Color(255, 255, 255));
        prescription.setModel(model);
        prescription.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(prescription);

        jButton3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jButton3.setText("Delete Drug");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addGap(189, 189, 189))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Diagnosis", jPanel5);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Nurse's Results"));

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel5.setText("Blood Pressure:");

        pressure.setEditable(false);
        pressure.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        pressure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pressureActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel6.setText("Weight:");

        weight.setEditable(false);
        weight.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel7.setText("Temperature:");

        temperature.setEditable(false);
        temperature.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel8.setText("Pulse rate:");

        pulse.setEditable(false);
        pulse.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)
                        .addGap(49, 49, 49)
                        .addComponent(temperature))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(0, 10, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(66, 66, 66)
                        .addComponent(pulse, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel5))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel6)))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(weight)
                            .addComponent(pressure))))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(pressure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(weight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(temperature, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(pulse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Laboratory Results"));

        jLabel11.setText("Specimen");

        jTextField3.setEditable(false);
        jTextField3.setText(" ");

        jLabel12.setText("Results");

        jTextArea2.setColumns(20);
        jTextArea2.setEditable(false);
        jTextArea2.setRows(5);
        jScrollPane6.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField3)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE))
                .addGap(0, 49, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(298, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Test Results", jPanel6);

        history.setModel(tablemodel);
        jScrollPane4.setViewportView(history);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(166, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Patient History", jPanel7);

        labResults.setModel(tablemodelLAB);
        jScrollPane7.setViewportView(labResults);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(166, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("View Lab Results", jPanel8);

        jButton1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 777, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        String sqlSearch = "Select * from drug where Drug_name LIKE '%"+jTextField2.getText()+"%' ORDER BY Drug_name ASC";
        try{
            jTable1 = Domains.getTable(header, columns, sqlSearch);
            listener = new TableHandler(jTable1);
            jTable1.getSelectionModel().addListSelectionListener(listener);
            jTable1.getColumnModel().getSelectionModel().addListSelectionListener(listener);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(55);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(290);
            jScrollPane2.getViewport().removeAll();
            jScrollPane2.getViewport().add(jTable1);
            jScrollPane2.repaint();
        }catch(Exception err){
            err.printStackTrace();
        }
    }//GEN-LAST:event_jTextField2KeyTyped

    private void pharmacyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pharmacyActionPerformed
        if (diagnosis.getText().equals("")){
            JOptionPane.showMessageDialog(app, "Please type the diagnosis notes", "", JOptionPane.WARNING_MESSAGE);
        }
        else if (prescription.getModel().getSize() == 0){
            JOptionPane.showMessageDialog(app, "You need to have a prescription to send a patient to the pharmacy", "", JOptionPane.WARNING_MESSAGE);
        }
        else{
            String sql = "UPDATE `queue` SET `seenNurse` = 1,`seenDoctor` = 1,`seenPharmacy` = 0, seenLabTech=2 " +
                    "WHERE patient_ID = " +patientID;
            String date = String.valueOf(DateFormat.getDateInstance(DateFormat.LONG).format(new Date()));
            
            String drugs = "";
            for(int x = 0; x< prescription.getModel().getSize(); x++){
                drugs  =  drugs+"-"+prescription.getModel().getElementAt(x)+"\\n";
            }
            String sqlDiagnosis = "INSERT INTO `diagnosis` (`Diagnosis_ID`, `patient_ID`, `user_ID`, `Date_Of_Diagnosis`, " +
                    "`Diagnosis`, `Prescription`, Total, Approved) " +
                    "VALUES (NULL, "+patientID+", "+mzazi.getUserID()+", '"+date+"', '"+diagnosis.getText()+"', '"+drugs+"',0,0);";
            try{
                new Database().Update(sql);
                new Database().addNew(sqlDiagnosis);
                app.dispose();
                mzazi.refresh();
            }catch(Exception err){
                err.printStackTrace();
            }
        }
    }//GEN-LAST:event_pharmacyActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (prescription.isSelectionEmpty()){
            JOptionPane.showMessageDialog(app, "Select drug to remove", "", JOptionPane.WARNING_MESSAGE);
        }
        else{
            model.remove(prescription.getSelectedIndex());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        boolean y = true;
        if (prescription.getModel().getSize() > 0){
            int x = JOptionPane.showConfirmDialog(app, "Are you sure you want to check out this patient?\n" +
                    "You have set the patients prescription and she/he won't be able to pick the drugs at the pharmacy if you say yes. " +
                    "Proceed to check-out?", "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (x!=0)
                y = false;
        }
        if (y==true){
            String sqlDiagnosis = "INSERT INTO `diagnosis` (`Diagnosis_ID`, `patient_ID`, `user_ID`, `Date_Of_Diagnosis`, " +
                    "`Diagnosis`, `Prescription`) " +
                    "VALUES (NULL, "+patientID+", "+mzazi.getUserID()+", '"+date+"', '"+diagnosis.getText()+"', 'NA');";
            
            String sql = "DELETE FROM queue WHERE patient_ID = " +patientID;
            try{
                new Database().addNew(sql);
                new Database().Delete(sqlDiagnosis);
                JOptionPane.showMessageDialog(this, "Patient successfully checked out", "", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                mzazi.refresh();
            }catch(Exception err){
                err.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void pressureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pressureActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pressureActionPerformed

    private void sendToLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendToLabActionPerformed
        new TestSelector(this, true).setVisible(true);
    }//GEN-LAST:event_sendToLabActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Diagnose dialog = new Diagnose(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextArea diagnosis;
    private javax.swing.JTable history;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTable labResults;
    private javax.swing.JButton pharmacy;
    private javax.swing.JList prescription;
    private javax.swing.JTextField pressure;
    private javax.swing.JTextField pulse;
    private javax.swing.JButton sendToLab;
    private javax.swing.JTextField temperature;
    private javax.swing.JTextField weight;
    // End of variables declaration//GEN-END:variables

    private void setTests() {
        
        String sqlTest = "Select * from check_up where patient_ID = "+patientID;
        String sql = "select * from labqueue where isTested = 0 AND patient_ID = "+patientID;
        
        try{
            
            ResultSet rs = new Database().Query(sqlTest);
            rs.afterLast();
            rs.previous();
            pressure.setText(rs.getString("pressure"));
            weight.setText(rs.getString("weight"));
            temperature.setText(rs.getString("temperature"));
            pulse.setText(rs.getString("pulse"));
            
        }catch(Exception err){
            err.printStackTrace();
        }
        
       
    }

    private void setHistory() {
        history.getColumnModel().getColumn(0).setPreferredWidth(150);
        history.getColumnModel().getColumn(1).setPreferredWidth(310);
        history.getColumnModel().getColumn(2).setPreferredWidth(310);
        try{
            String SelectSQL = "SELECT * FROM `diagnosis` WHERE patient_ID = "+patientID+" ORDER BY Diagnosis_ID DESC";
            ResultSet rs = new Database().Query(SelectSQL);
            rs.afterLast();
            rs.previous();
            int numOfRows = 0;
            Object[] row = new Object[3];
            if (rs.getRow() > 0 ) {
                rs.beforeFirst();
                numOfRows = rs.getRow();
                history.setRowHeight(75);
                while (rs.next()){
                    row[0] = rs.getString("Date_Of_Diagnosis");
                    row[1] = rs.getString("Diagnosis");
                    row[2] = rs.getString("Prescription");
                    tablemodel.addRow(row);
                }
            }else{
                row = new String[3];
                row[0] = "NO";
                row[1] = "PATIENT";
                row[2] = "HISTORY";

                tablemodel.addRow(row);
            }
        }catch(Exception err){
            err.printStackTrace();
        }
    }
    // End of variables declaration

    void disableLabButton() {
        sendToLab.setEnabled(false);
    }

    private void setLabResults() {
        labResults.getColumnModel().getColumn(0).setPreferredWidth(310);
        labResults.getColumnModel().getColumn(1).setPreferredWidth(310);
        try{
            String SelectSQL = "SELECT * FROM `lab_test` WHERE queue_ID = "+queueID+"";
            ResultSet rs = new Database().Query(SelectSQL);
            rs.afterLast();
            rs.previous();
            int numOfRows = 0;
            Object[] row = new Object[2];
            if (rs.getRow() > 0 ) {
                rs.beforeFirst();
                numOfRows = rs.getRow();
                history.setRowHeight(75);
                while (rs.next()){
                    row[0] = rs.getString("Test_Name");
                    row[1] = rs.getString("Test_Results");
                    tablemodelLAB.addRow(row);
                }
            }else{
                row = new String[2];
                row[0] = "NO LAB";
                row[1] = "RESULTS";

                tablemodelLAB.addRow(row);
            }
        }catch(Exception err){
            err.printStackTrace();
        }
    }

public class TableHandler implements ListSelectionListener{

    JTable table;
    private int id;
    Object[] content;

    public TableHandler(JTable table) {
        this.table = table;
        content = new Object[3];
    }
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == table.getSelectionModel() && table.getRowSelectionAllowed()) {
            //int first = e.getFirstIndex();
            //String row = String.valueOf(table.getValueAt(table.getSelectedRow(), 1));
            System.out.println(table.getValueAt(table.getSelectedRow(), 0));

            int pos = prescription.getModel().getSize();

            String prescription = (String) table.getValueAt(table.getSelectedRow(), 1);
            Object x = JOptionPane.showInputDialog(app, "Enter prescription details", "",
                    JOptionPane.INFORMATION_MESSAGE, null, null,null);
            String directions = " "+ x+"\n";

            if (x != null){
                model.add(pos, prescription+directions);
            }
        }
    }

}
}
