/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOdataAnggota;

import java.sql.*;
import java.util.*;
import koneksi.connector;
import model.*;
import DAOImplement.DataAnggotaInterface;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class DataAnggotaDAO implements DataAnggotaInterface {

    Connection connection;
    final String select = "SELECT * FROM anggota";
    final String insert = "INSERT INTO anggota (ID_ANGGOTA, ID_ADMIN, FULL_NAME, TMP_LAHIR, TGL_LAHIR, ALAMAT, GENDER, TELP) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    final String update = "UPDATE anggota set FULL_NAME=?, TMP_LAHIR=?, TGL_LAHIR=?, ALAMAT=?, GENDER=?, TELP=? WHERE ID_ANGGOTA=?";
    final String delete = "DELETE FROM anggota where ID_ANGGOTA=?";

    public DataAnggotaDAO() {
        connection = connector.connection();
    }

    @Override
    public void insert(DataAnggota ang) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, ang.getId_anggota());
            statement.setString(2, ang.getId_admin());
            statement.setString(3, ang.getFull_name());
            statement.setString(4, ang.getTmp_lahir());
            statement.setString(5, ang.getTgl_lahir());
            statement.setString(6, ang.getAlamat());
            statement.setString(7, ang.getGender());
            statement.setString(8, ang.getTelepon());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();

            while (rs.next()) {
                ang.setId_anggota(rs.getString(0));
            }
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
    public void update(DataAnggota ang) {
        PreparedStatement statement = null;
        
        try{
            statement = connection.prepareStatement(update);
            statement.setString(1, ang.getFull_name());
            statement.setString(2, ang.getTmp_lahir());
            statement.setString(3, ang.getTgl_lahir());
            statement.setString(4, ang.getAlamat());
            statement.setString(5, ang.getGender());
            statement.setString(6, ang.getTelepon());
            statement.setString(7, ang.getId_anggota());
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
    public void delete(String id_anggota) {
        PreparedStatement statement = null;
        
        try{
            statement = connection.prepareStatement(delete);
            statement.setString(1, id_anggota);
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

    @Override
    public List<DataAnggota> getAll() {
        List<DataAnggota> dang = null;
        try{
            dang = new ArrayList<DataAnggota>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                DataAnggota anggota = new DataAnggota();
                //
                anggota.setId_anggota(rs.getString("ID_ANGGOTA"));
                anggota.setId_admin(rs.getString("ID_ADMIN"));
                anggota.setFull_name(rs.getString("FULL_NAME"));
                anggota.setTmp_lahir(rs.getString("TMP_LAHIR"));
                anggota.setTgl_lahir(rs.getString("TGL_LAHIR"));
                anggota.setAlamat(rs.getString("ALAMAT"));
                anggota.setGender(rs.getString("GENDER"));
                anggota.setTelepon(rs.getString("TELP"));
                dang.add(anggota);
            }
        }catch(SQLException ex){
            Logger.getLogger(DataAnggotaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dang;
    }

}
