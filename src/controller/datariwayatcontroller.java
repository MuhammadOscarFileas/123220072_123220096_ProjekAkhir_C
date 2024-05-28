/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.util.List;
import DAOdatariwayat.datariwayatdao;
import DAOImplement.datariwayatimplement;
import model.*;
import perpustakaan.Halamanriwayat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
/**
 *
 * @author Lenovo
 */
public class datariwayatcontroller {
    Halamanriwayat frame;
    datariwayatimplement impldatarwyt;
    List<datariwayat> dl;
    
    public datariwayatcontroller(Halamanriwayat frame){
        this.frame = frame;
        impldatarwyt = new datariwayatdao();
        dl = impldatarwyt.getAll();
    }
    
    public void isitabel(){
        dl = impldatarwyt.getAll();
        modeltabeldatariwayat mb = new modeltabeldatariwayat(dl);
        frame.getTableriwayat().setModel(mb); 
    }
    
    
}
