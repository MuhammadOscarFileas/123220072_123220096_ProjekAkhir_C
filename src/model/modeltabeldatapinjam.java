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
public class modeltabeldatapinjam extends AbstractTableModel{
    List<datapinjam> dp;
     public modeltabeldatapinjam(List<datapinjam> dp){
         this.dp = dp;
     }

    @Override
    public int getRowCount() {
        return dp.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }
    
    public String getColumnName(int column){
        switch(column){
            case 0:
                return "ID_PINJAM";
            case 1:
                return "ID_ANGGOTA";
            case 2:
                return "ID_ADMIN";
            case 3:
                return "ID_BUKU";
            case 4:
                return "TGL_PINJAM";
            case 5:
                return "TGL_KEMBALI";
            case 6:
                return "JUMLAH_BUKU";
            case 7:
                return "STATS";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch(column){
            case 0:
                return dp.get(row).getId_pinjam();
            case 1:
                return dp.get(row).getId_anggota();
            case 2:
                return dp.get(row).getId_admin();
            case 3:
                return dp.get(row).getId_buku();
            case 4:
                return dp.get(row).getTgl_pinjam();
            case 5:
                return dp.get(row).getTgl_kembali();
            case 6:
                return dp.get(row).getJumlah();
            case 7:
                return dp.get(row).getStatus();
            default:
                return null;
        }
    }
    
}
