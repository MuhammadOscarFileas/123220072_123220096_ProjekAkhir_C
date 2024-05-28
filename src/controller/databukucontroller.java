/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.util.List;
import DAOdatabuku.databukuDAO;
import DAOImplement.databukuimplement;
import model.*;
import perpustakaan.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
/**
 *
 * @author Lenovo
 */
public class databukucontroller {
    Halamanutama frame;
    Halamanpeminjaman frame2;
    databukuimplement impldatabuku;
    List<databuku> db;
    
    public databukucontroller(Halamanutama frame){
        this.frame = frame;
        impldatabuku = new databukuDAO();
        db = impldatabuku.getAll();
    }
    
    public databukucontroller(Halamanpeminjaman frame2){
        this.frame2 = frame2;
        impldatabuku = new databukuDAO();
        db = impldatabuku.getAll();
    }
    
    public void isitabel(){
        db = impldatabuku.getAll();
        modeltabeldatabuku mb = new modeltabeldatabuku(db);
        frame.getTabletampilbuku().setModel(mb); 
    }
    
    public void isitabel2(){
        //String judul = frame2.getJjudul().getText();
        db = impldatabuku.getAll();
        modeltabeldatabuku mb = new modeltabeldatabuku(db);
        frame2.getTableviewbuku().setModel(mb);
    }
    
    public void isitabelcari(String cari){
        //String judul = frame2.getJjudul().getText();
        db = impldatabuku.search(cari);
        modeltabeldatabuku mb = new modeltabeldatabuku(db);
        frame2.getTableviewbuku().setModel(mb);
    }
    
    public void hasilsearch(){
        db = impldatabuku.getAll();
        modeltabeldatabuku mb = new modeltabeldatabuku(db);
        frame2.getTableviewbuku().setModel(mb);
    }
    
    public void inputsearch(){
        try{
            String judul = frame2.getJjudul().getText();
            impldatabuku.search(judul);
            //throw new Exception("Data berhasil dihapus");
        }catch(Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }
    
    public void insert(){ //disini
        databuku db = new databuku();
        try {
            db.setId_buku(frame.getJtxtidbuku().getText());
            db.setTitle(frame.getJtxttitle().getText());
            db.setAuthor(frame.getJtxtauthor().getText());
            db.setPublisher(frame.getJtxtpublisher().getText());
            db.setYear(frame.getJtxtyear().getText());
            db.setQty(Integer.parseInt(frame.getJtxtqty().getText()));
            db.setId_admin(frame.getJlabadmin().getText());
            String qty = Integer.toString(db.getQty());
            db.setKeluar(0);
            if(db.getId_buku().isEmpty() || db.getPublisher().isEmpty() || db.getTitle().isEmpty() || db.getYear().isEmpty() || qty.isEmpty()){
                throw new Exception("Data Tidak Boleh Kosong");
            }
//            else if(isInteger(db.getQty()))
            impldatabuku.insert(db);
        } catch(Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        
    }
    
    public void update(){
        databuku db = new databuku();
        try {
            db.setId_buku(frame.getJtxtidbuku().getText());
            db.setTitle(frame.getJtxttitle().getText());
            db.setAuthor(frame.getJtxtauthor().getText());
            db.setPublisher(frame.getJtxtpublisher().getText());
            db.setYear(frame.getJtxtyear().getText());
            db.setQty(Integer.parseInt(frame.getJtxtqty().getText()));
            String qty = Integer.toString(db.getQty());
            //db.setKeluar(0);
            if(db.getId_buku().isEmpty() || db.getPublisher().isEmpty() || db.getTitle().isEmpty() || db.getYear().isEmpty() || qty.isEmpty()){
                throw new Exception("Data Tidak Boleh Kosong");
            }
            impldatabuku.update(db);
            throw new Exception("Data berhasil diupdate");
        } catch(Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }
    
    public void delete(){
        try{
            String id_buku = frame.getJtxtidbuku().getText();
            impldatabuku.delete(id_buku);
            throw new Exception("Data berhasil dihapus");
        }catch(Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        //throw new Exception("Data berhasil dihapus");
    }
    
}
