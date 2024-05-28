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
 * @author HP
 */
public class ModelTabelDataAdmin extends AbstractTableModel{
    
    List<DataAdmin> da;
    public ModelTabelDataAdmin(List<DataAdmin>da){
        this.da = da;
    }

    @Override
    public int getRowCount() {
        return da.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column){
            case 0:
                return da.get(row).getId();
            case 1:
                return da.get(row).getUsername();
            case 2:
                return da.get(row).getPassword();
            default:
                return null;
        }
    }
    
    public String getColumnName(int column){
        switch(column){
            case 0:
                return "ID_ADMIN";
            case 1:
                return "USERNAME";
            case 2:
                return "PASSWORD";
            default:
                return null;
        }
    }
    
}
