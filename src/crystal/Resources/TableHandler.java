/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crystal.Resources;

import crystal.Patients.Diagnose;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


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
            
            //int col = Integer.parseInt(row);
            //RegisterStudents.display(col);
            //int last = e.getLastIndex();
        }
    }

}
