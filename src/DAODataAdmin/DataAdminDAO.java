/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAODataAdmin;

import java.sql.*;
import java.util.*;
import koneksi.connector;
import model.*;
import DAOImplement.DataAdminInterface;
import perpustakaan.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class DataAdminDAO implements DataAdminInterface {

    Connection connection;
    public boolean isAuthenticated = false;

    final String cek = "SELECT * FROM admin WHERE USERNAME=? AND PASSWORD=?";
    final String select = "SELECT * FROM admin";
    //final String select2 = "SELECT * FROM admin";
    final String insert = "INSERT INTO admin (ID_ADMIN, USERNAME, PASSWORD) VALUES (?, ?, ?)";
    String update = "UPDATE admin set USERNAME=?, PASSWORD=? WHERE ID_ADMIN=?";
    final String delete = "DELETE FROM admin where ID_ADMIN=?";

    public DataAdminDAO() {
        connection = connector.connection();
    }

    @Override
    public boolean cekLogin(DataAdmin a) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(cek);
            statement.setString(1, a.getUsername());
            statement.setString(2, a.getPassword());
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                isAuthenticated = true;
                String adminId = rs.getString("ID_ADMIN");
                System.out.println(adminId);
                Halamanutama v = new Halamanutama(adminId);
                v.setVisible(true);
                v.setLocationRelativeTo(null);   
                throw new SQLException("Berhasil Login");
            } else {
                throw new SQLException("Gagal Login");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return isAuthenticated;
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return isAuthenticated;
        }
        
    }

    public void insert(DataAdmin a) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, a.getId());
            statement.setString(2, a.getUsername());
            statement.setString(3, a.getPassword());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            throw new SQLException("Data telah ditambahkan");
        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void update(DataAdmin a) {
        PreparedStatement statement = null;
        
        try{
            statement = connection.prepareStatement(update);
            statement.setString(1, a.getUsername());
            statement.setString(2, a.getPassword());
            statement.setString(3, a.getId());
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
    public void delete(String id_admin) {
        PreparedStatement statement = null;
        
        try{
            statement = connection.prepareStatement(delete);
            statement.setString(1, id_admin);
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
    
    
    public List<DataAdmin> getAll() {
        List<DataAdmin> da = null;
        try{
            da = new ArrayList<DataAdmin>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                DataAdmin admin = new DataAdmin();
                //
                admin.setId(rs.getString("ID_ADMIN"));
                admin.setUsername(rs.getString("USERNAME"));
                admin.setPassword(rs.getString("PASSWORD"));
                da.add(admin);
            }
        }catch(SQLException ex){
            Logger.getLogger(DataAdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return da;
    }
}
