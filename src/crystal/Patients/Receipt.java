package crystal.Patients;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Receipt.java
 *
 * Created on Apr 4, 2011, 3:18:54 PM
 */

import crystal.Resources.Database;
import java.io.InputStream;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JRViewer;
/**
 */
public class Receipt extends javax.swing.JFrame {

    public JRViewer nes;
    String patientID;
    GiveMedication app;
    /** Creates new form Receipt */

    Receipt(String patientID) {
        initComponents();
        this.patientID = patientID;
        JasperTest();
        setExtendedState(MAXIMIZED_BOTH);
    }

    Receipt(GiveMedication aThis, String patientID) {
        initComponents();
        this.patientID = patientID;
        app = aThis;
        JasperTest();
        setExtendedState(MAXIMIZED_BOTH);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
   

    public void JasperTest(){
         //calling  the report
     HashMap hm = new HashMap();
        try{
           InputStream  fileName = getClass().getResourceAsStream("report1.jrxml");
            JasperDesign k = JRXmlLoader.load( getClass().getResourceAsStream("report1.jrxml"));
            JRDesignQuery dQuery = new JRDesignQuery();

            dQuery.setText("SELECT * FROM patient, diagnosis, diagnosis_medication, drug " +
                    "WHERE patient.patient_ID = diagnosis.patient_ID AND diagnosis.diagnosis_ID = diagnosis_medication.diagnosis_ID " +
                    "AND diagnosis_medication.drug_ID = drug.drugID AND patient.patient_ID ="+patientID);
            k.setQuery(dQuery);
            JasperPrint print = JasperFillManager.fillReport(JasperCompileManager.compileReport(k),hm, new Database().getConnection()
            );
         // adding the report on to the frame
             nes = new JRViewer(print);
             setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
             app.closeParent();
             app.dispose();

           nes.setVisible(true);
            setContentPane(nes);

            setContentPane(nes);

        }
        catch (JRException e)
        {
            e.printStackTrace();

        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
