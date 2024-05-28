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
public class modeltabeldatariwayat extends AbstractTableModel{
    List<datariwayat> dl;

    public modeltabeldatariwayat(List<datariwayat> dl){
         this.dl = dl;
     }
    
    @Override
    public int getRowCount() {
       return dl.size();
    }

    @Override
    public int getColumnCount() {
        return 10;
    }
    
    public String getColumnName(int column){
        switch(column){
            case 0:
                return "ID_PINJAM";
            case 1:
                return "ID_ANGGOTA";
            case 2:
                return "FULL_NAME";
            case 3:
                return "ID_BUKU";
            case 4:
                return "TITLE";
            case 5:
                return "ID_ADMIN";
            case 6:
                return "TGL_PINJAM";
            case 7:
                return "TGL_KEMBALI";
            case 8:
                return "JML_BUKU";
            case 9:
                return "STATS";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch(column){
            case 0:
                return dl.get(row).getId_pinjam();
            case 1:
                return dl.get(row).getId_anggota();
            case 2:
                return dl.get(row).getNama_lengkap();
            case 3:
                return dl.get(row).getId_buku();
            case 4:
                return dl.get(row).getNama_buku();
            case 5:
                return dl.get(row).getId_admin();
            case 6:
                return dl.get(row).getTgl_pinjam();
            case 7:
                return dl.get(row).getTgl_kembali();
            case 8:
                return dl.get(row).getJumlah();
            case 9:
                return dl.get(row).getStatus();
            default:
                return null;
        }
    }
    
}
