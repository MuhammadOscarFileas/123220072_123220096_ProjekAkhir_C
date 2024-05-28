/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.util.List;
import DAODataAdmin.DataAdminDAO;
import DAOImplement.DataAdminInterface;
import javax.swing.JOptionPane;
import model.*;
import perpustakaan.MainView;
import perpustakaan.RegisterAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author HP
 */
public class DataAdminController {
    MainView frame;
    DataAdminInterface ImplDataAdmin;
    List<DataAdmin> da;
    public boolean isAuthenticated;

    public DataAdminController(MainView frame) {
        this.frame = frame;
        ImplDataAdmin = new DataAdminDAO();
    }
    
    public boolean cekLogin() {
        DataAdmin ds = new DataAdmin();
        ds.setUsername(frame.getTxtusername().getText());  
        ds.setPassword(frame.getTxtpassword().getText());
        isAuthenticated = ImplDataAdmin.cekLogin(ds);
        return isAuthenticated;
    }
}
