/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOdatapinjam;
import java.sql.*;
import java.util.*;
import koneksi.connector;
import model.*;
import DAOImplement.datapinjamimplement;
import DAOdatabuku.databukuDAO;
import java.util.logging.Level;
import perpustakaan.*;
import java.util.logging.Logger;
/**
 *
 * @author Lenovo
 */
public class datapinjamDAO implements datapinjamimplement{
    Connection connection;
    final String select = "SELECT * FROM peminjaman";
    final String insert = "INSERT INTO peminjaman (ID_ANGGOTA, ID_ADMIN, ID_BUKU, TGL_PINJAM, TGL_KEMBALI, JML_BUKU, STATS) VALUES (?, ?, ?, ?, ?, ?, ?)";
    final String update = "UPDATE peminjaman set TGL_KEMBALI=?, STATS=? where ID_PINJAM=?";
    final String update2 = "UPDATE buku set";
    //final String updatebuku = "UPDATE buku set QTY=? WHERE ID_BUKU=?";

    public datapinjamDAO(){
        connection = connector.connection();
    }
    
    @Override
    public void insert(datapinjam b) {
        PreparedStatement statement = null;
        //String id_buku = null;
        //int jumlah = 0;
        
        try{
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            //statement.setInt(1, b.getId_pinjam());
            statement.setString(1, b.getId_anggota());
            statement.setString(2, b.getId_admin());
            statement.setString(3, b.getId_buku());
            statement.setString(4, b.getTgl_pinjam());
            statement.setString(5, b.getTgl_kembali());
            statement.setInt(6, b.getJumlah());
            statement.setString(7, b.getStatus());
            //statement.executeUpdate();
            int rowsAffected = statement.executeUpdate();
        
        if (rowsAffected == 1) {
            // Jika operasi INSERT berhasil, ambil nilai ID yang dihasilkan
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idPinjam = generatedKeys.getInt(1);
                
                // Lakukan pengurangan jumlah buku dan update ke database
                updateJumlahBuku(b.getId_buku(), b.getJumlah());
            }
        }
//            ResultSet rs = statement.getGeneratedKeys();
//            while(rs.next()){
//                b.setId_pinjam(rs.getInt(0));
//            }
            //throw new SQLException("Data telah ditambahkan");
        }catch(SQLException ex){
            ex.printStackTrace();
            
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }
    
    private void updateJumlahBuku(String idBuku, int jumlah) {
    PreparedStatement statement = null;
    try {
        // Lakukan kueri untuk mengurangi jumlah buku
        String query = "UPDATE buku SET QTY = QTY - ?, KELUAR = KELUAR + ? WHERE ID_BUKU = ?";
        statement = connection.prepareStatement(query);
        
        statement.setInt(1, jumlah);
        statement.setInt(2, jumlah);
        statement.setString(3, idBuku);
        
        // Eksekusi query UPDATE
        statement.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

    @Override
    public List<datapinjam> getAll() {
        List<datapinjam> dp = null;
        try{
            dp = new ArrayList<datapinjam>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                datapinjam pinjam = new datapinjam();
                //
                pinjam.setId_pinjam(rs.getInt("ID_PINJAM"));
                pinjam.setId_admin(rs.getString("ID_ANGGOTA"));
                pinjam.setId_admin(rs.getString("ID_ADMIN"));
                pinjam.setId_buku(rs.getString("ID_BUKU"));
                pinjam.setTgl_pinjam(rs.getString("TGL_PINJAM"));
                pinjam.setTgl_kembali(rs.getString("TGL_KEMBALI"));
                pinjam.setJumlah(rs.getInt("JML_BUKU"));
                pinjam.setStatus(rs.getString("STATS"));
                dp.add(pinjam);
            }
        }catch(SQLException ex){
            Logger.getLogger(datapinjamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dp;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void update(int idpinjam, String tglkembali, String status) {
        PreparedStatement statement = null;
        System.out.println("Pertama dao");
        try{
            System.out.println("Masuk DAO");
            statement = connection.prepareStatement(update);
            statement.setString(1, tglkembali);
            statement.setString(2, status);
            statement.setInt(3, idpinjam);
            statement.executeUpdate();
            System.out.println("Selesai di DAO");
            System.out.println(status+ "ini");
            //throw new SQLException("Data telah diupdate");
        }catch(SQLException ex){
            ex.printStackTrace();
            
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void update2(String idbuku, String status, int qty) {
        PreparedStatement statement = null;
    try {
        if(status == "Sudah Kembali"){
         String query = "UPDATE buku SET QTY = QTY + ?, KELUAR = KELUAR - ? WHERE ID_BUKU = ?";
         statement = connection.prepareStatement(query);
         statement.setInt(1, qty);
         statement.setInt(2, qty);
         statement.setString(3, idbuku);   
        }
        else if(status == "Hilang"){
            String query = "UPDATE buku SET KELUAR = KELUAR - ? WHERE ID_BUKU = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, qty);
            statement.setString(2, idbuku);   
        }else if(status == "Belum Kembali"){
            String query = "UPDATE buku SET QTY = QTY - ? KELUAR = KELUAR + ? WHERE ID_BUKU = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, qty);
            statement.setInt(2, qty);
            statement.setString(3, idbuku);
        }
        
        // Eksekusi query UPDATE
        statement.executeUpdate();
        //throw new SQLException("Data Buku Telah Diupdate");
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    }
}
