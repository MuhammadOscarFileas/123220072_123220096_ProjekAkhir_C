/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Lenovo
 */
public class modeltabeldatabuku extends AbstractTableModel{
    List<databuku> db;
     public modeltabeldatabuku(List<databuku> db){
         this.db = db;
     }

    @Override
    public int getRowCount() {
        return db.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }
    
    public String getColumnName(int column){
        switch(column){
            case 0:
                return "ID_Buku";
            case 1:
                return "ID_Admin";
            case 2:
                return "Title";
            case 3:
                return "Author";
            case 4:
                return "Publisher";
            case 5:
                return "YEAR";
            case 6:
                return "QTY";
            case 7:
                return "Keluar";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch(column){
            case 0:
                return db.get(row).getId_buku();
            case 1:
                return db.get(row).getId_admin();
            case 2:
                return db.get(row).getTitle();
            case 3:
                return db.get(row).getAuthor();
            case 4:
                return db.get(row).getPublisher();
            case 5:
                return db.get(row).getYear();
            case 6:
                return db.get(row).getQty();
            case 7:
                return db.get(row).getKeluar();
            default:
                return null;
        }
    }
    
}
