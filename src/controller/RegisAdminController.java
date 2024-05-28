/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.util.List;
import DAODataAdmin.DataAdminDAO;
import DAOImplement.DataAdminInterface;
import model.*;
import perpustakaan.RegisterAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
/**
 *
 * @author HP
 */
public class RegisAdminController {
    RegisterAdmin frame;
    DataAdminInterface impldataadmin;
    List<DataAdmin> da;
    
    public RegisAdminController(RegisterAdmin frame){
        this.frame = frame;
        impldataadmin = new DataAdminDAO();
        da = impldataadmin.getAll();
    }
    
    public void isitabel(){
        da = impldataadmin.getAll();
        ModelTabelDataAdmin ma = new ModelTabelDataAdmin(da);
        frame.getTabeltampiladmin().setModel(ma);   
    }
    
    public void insert(){ //disini
        DataAdmin da = new DataAdmin();
        try {
            
            da.setId(frame.getTxtidadmin().getText());
            da.setUsername(frame.getTxtusername().getText());
            da.setPassword(frame.getTxtpassword().getText());
            if(da.getId().isEmpty() || da.getUsername().isEmpty() || da.getPassword().isEmpty()){
                throw new Exception("Data Tidak Boleh Kosong");
            }
            else {
                impldataadmin.insert(da);
                throw new Exception("Data berhasil dimasukkan");   
            }
        } catch(Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }   
    }
    
    public void update(){
        DataAdmin da = new DataAdmin();
        try {
            da.setId(frame.getTxtidadmin().getText());
            da.setUsername(frame.getTxtusername().getText());
            da.setPassword(frame.getTxtpassword().getText());
            impldataadmin.update(da);
            throw new Exception("Data berhasil diupdate");
        } catch(Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }
    public void delete(){
        try{
            String id_admin = frame.getTxtidadmin().getText();
            impldataadmin.delete(id_admin);
            throw new Exception("Data berhasil dihapus");
        }catch(Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }
}
