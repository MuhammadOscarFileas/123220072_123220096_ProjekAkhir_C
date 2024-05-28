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
public class ModelTabelDataAnggota extends AbstractTableModel{
    
    List<DataAnggota> dang;
    public ModelTabelDataAnggota(List<DataAnggota> dang){
        this.dang = dang;
    }
    
    @Override
    public int getRowCount() {
        return dang.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }
    
    public String getColumnName(int column){
        switch(column){
            case 0:
                return "ID_Anggota";
            case 1:
                return "ID_Admin";
            case 2:
                return "Nama Lengkap";
            case 3:
                return "Tempat Lahir";
            case 4:
                return "Tanggal Lahir";
            case 5:
                return "Alamat";
            case 6:
                return "Gender";
            case 7:
                return "Telepon";
            default:
                return null;
        }
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        switch(column){
            case 0:
                return dang.get(row).getId_anggota();
            case 1:
                return dang.get(row).getId_admin();
            case 2:
                return dang.get(row).getFull_name();
            case 3:
                return dang.get(row).getTmp_lahir();
            case 4:
                return dang.get(row).getTgl_lahir();
            case 5:
                return dang.get(row).getAlamat();
            case 6:
                return dang.get(row).getGender();
            case 7:
                return dang.get(row).getTelepon();
            default:
                return null;
        }
    }
    
}
