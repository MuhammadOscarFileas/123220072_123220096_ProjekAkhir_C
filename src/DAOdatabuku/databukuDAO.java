/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOdatabuku;
import java.sql.*;
import java.util.*;
import koneksi.connector;
import model.*;
import DAOImplement.databukuimplement;
import java.util.logging.Level;
import java.util.logging.Logger;
import perpustakaan.*;
import javax.swing.JComboBox;
/**
 *
 * @author Lenovo
 */
public class databukuDAO implements databukuimplement{
    Connection connection;
    final String select = "SELECT * FROM buku";
    final String insert = "INSERT INTO buku (ID_BUKU, ID_ADMIN, TITLE, AUTHOR, PUBLISHER, YEAR, QTY, KELUAR) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    final String update = "UPDATE buku set TITLE=?, AUTHOR=?, PUBLISHER=?, YEAR=?, QTY=? where ID_BUKU=?";
    final String delete = "DELETE FROM buku where ID_BUKU=?";
    final String search = "SELECT * FROM buku WHERE TITLE LIKE ?";
    
    public databukuDAO(){
        connection = connector.connection();
    }

    @Override
    public void insert(databuku b) {
        PreparedStatement statement = null;
        
        try{
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, b.getId_buku());
            statement.setString(2, b.getId_admin());
            statement.setString(3, b.getTitle());
            statement.setString(4, b.getAuthor());
            statement.setString(5, b.getPublisher());
            statement.setString(6, b.getYear());
            statement.setInt(7, b.getQty());
            statement.setInt(8, b.getKeluar());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            
            while(rs.next()){
                b.setId_buku(rs.getString(0));
            }
            throw new SQLException("Data telah ditambahkan");
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
    public void update(databuku b) {
       PreparedStatement statement = null;
        
        try{
            statement = connection.prepareStatement(update);
            statement.setString(1, b.getTitle());
            statement.setString(2, b.getAuthor());
            statement.setString(3, b.getPublisher());
            statement.setString(4, b.getYear());
            statement.setInt(5, b.getQty());
            statement.setString(6, b.getId_buku());
            statement.executeUpdate();
       
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
    public void delete(String id_buku) {
        PreparedStatement statement = null;
        
        try{
            statement = connection.prepareStatement(delete);
            statement.setString(1, id_buku);
            statement.executeUpdate();    
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
    
    public List<databuku> search(String cari){
        List<databuku> db = null;
        try{
            db = new ArrayList<databuku>();
            PreparedStatement st = connection.prepareStatement(search);
            st.setString(1,"%" + cari + "%");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                databuku buku = new databuku();
                //
                buku.setId_buku(rs.getString("ID_BUKU"));
                buku.setId_admin(rs.getString("ID_ADMIN"));
                buku.setTitle(rs.getString("TITLE"));
                buku.setAuthor(rs.getString("AUTHOR"));
                buku.setPublisher(rs.getString("PUBLISHER"));
                buku.setYear(rs.getString("YEAR"));
                buku.setQty(rs.getInt("QTY"));
                buku.setKeluar(rs.getInt("KELUAR"));
                db.add(buku);
            }
        }catch(SQLException ex){
            Logger.getLogger(databukuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<databuku> getAll() {
        List<databuku> db = null;
        try{
            db = new ArrayList<databuku>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                databuku buku = new databuku();
                //
                buku.setId_buku(rs.getString("ID_BUKU"));
                buku.setId_admin(rs.getString("ID_ADMIN"));
                buku.setTitle(rs.getString("TITLE"));
                buku.setAuthor(rs.getString("AUTHOR"));
                buku.setPublisher(rs.getString("PUBLISHER"));
                buku.setYear(rs.getString("YEAR"));
                buku.setQty(rs.getInt("QTY"));
                buku.setKeluar(rs.getInt("KELUAR"));
                db.add(buku);
            }
        }catch(SQLException ex){
            Logger.getLogger(databukuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    
