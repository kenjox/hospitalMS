/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crystal.Resources;

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import javax.swing.JTable;


public class Domains {
    public static String[] getDomains(){
        String[] domains=null;
        try{
            ResultSet rs = new Database().Query("Select * From roles");
            rs.afterLast();
            rs.previous();
            int x = rs.getRow();
            int y = 0;
            rs.beforeFirst();
            domains = new String[x];
            while (rs.next()){
                domains[y] = rs.getString("Role_name");
                y++;
            }
        }catch(Exception err){
            err.printStackTrace();
        }
        return domains;
    }

    public static JTable getTable(String[] header, String[] columns, String sql){
        ResultSet rsTable;
        String[][] Content = null;
        try{
            rsTable = new Database().Query(sql);
            int total = 0;
            rsTable.afterLast();
            if(rsTable.previous())total = rsTable.getRow();
            rsTable.beforeFirst();
            rsTable.next();
            Content = new String[total][header.length];
            //while(rsTable.next()){
                for (int x = 0; x < total; x++){
                    for (int y = 0; y < header.length; y++){
                        Content[x][y] = String.valueOf(rsTable.getObject(columns[y]));
                    }
                    rsTable.next();
                }
            //}

        }catch(Exception err){
            err.printStackTrace();
        }

        JTable NewTable = new JTable (Content,header){
        @Override
                    public boolean isCellEditable (int iRows, int iCols) {
                            return false;
                    }
            };

            NewTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
            //NewTable.setPreferredScrollableViewportSize(new Dimension(727, 320));
            //NewTable.getModel().ge
            NewTable.setBackground(Color.BLACK);
            NewTable.setForeground(Color.WHITE);
            NewTable.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            NewTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));

            return NewTable;
    }
}
