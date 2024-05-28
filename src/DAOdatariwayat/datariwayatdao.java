/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOdatariwayat;
import java.sql.*;
import java.util.*;
import koneksi.connector;
import model.*;
import DAOImplement.datariwayatimplement;
import DAOdatabuku.databukuDAO;
import java.util.logging.Level;
import perpustakaan.*;
import java.util.logging.Logger;
/**
 *
 * @author Lenovo
 */
public class datariwayatdao implements datariwayatimplement{
    Connection connection;
    final String select = "SELECT peminjaman.ID_PINJAM, peminjaman.ID_ANGGOTA, anggota.FULL_NAME, peminjaman.ID_ADMIN, peminjaman.ID_BUKU, buku.TITLE, peminjaman.TGL_PINJAM, peminjaman.TGL_KEMBALI, peminjaman.JML_BUKU, peminjaman.STATS FROM peminjaman INNER JOIN buku ON peminjaman.ID_BUKU = buku.ID_BUKU INNER JOIN anggota ON peminjaman.ID_ANGGOTA = anggota.ID_ANGGOTA";
    
    public datariwayatdao(){
        connection = connector.connection();
    }

    @Override
    public List<datariwayat> getAll() {
        List<datariwayat> dl = null;
        try{
            dl = new ArrayList<datariwayat>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                datariwayat rwyt = new datariwayat();
                //
                rwyt.setId_pinjam(rs.getInt("ID_PINJAM"));
                rwyt.setId_anggota(rs.getString("ID_ANGGOTA"));
                rwyt.setNama_lengkap(rs.getString("FULL_NAME"));
                rwyt.setId_admin(rs.getString("ID_ADMIN"));
                rwyt.setId_buku(rs.getString("ID_BUKU"));
                rwyt.setNama_buku(rs.getString("TITLE"));
                rwyt.setTgl_pinjam(rs.getString("TGL_PINJAM"));
                rwyt.setTgl_kembali(rs.getString("TGL_KEMBALI"));
                rwyt.setJumlah(rs.getInt("JML_BUKU"));
                rwyt.setStatus(rs.getString("STATS"));
                dl.add(rwyt);
            }
        }catch(SQLException ex){
            Logger.getLogger(datariwayatdao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dl;
    }
    
}
