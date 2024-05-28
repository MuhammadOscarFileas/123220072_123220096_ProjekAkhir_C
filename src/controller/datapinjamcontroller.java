/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.util.List;
import DAOdatapinjam.datapinjamDAO;
import DAOdatariwayat.datariwayatdao;
import DAOImplement.datapinjamimplement;
import DAOImplement.datariwayatimplement;
import model.*;
import perpustakaan.Halamanpeminjaman;
import perpustakaan.Halamanriwayat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
/**
 *
 * @author Lenovo
 */
public class datapinjamcontroller {
    Halamanpeminjaman frame;
    Halamanriwayat frame2;
    datapinjamimplement impldatapinjam;
    datariwayatimplement impldatariwayat;
    List<datariwayat> dl;
    List<datapinjam> dp;
    
    public datapinjamcontroller(Halamanpeminjaman frame){
        this.frame = frame;
        impldatapinjam = new datapinjamDAO();
        dp = impldatapinjam.getAll();
    }
    
    public datapinjamcontroller(Halamanriwayat frame2){
        this.frame2 = frame2;
        impldatariwayat = new datariwayatdao();
        impldatapinjam = new datapinjamDAO();
        dl = impldatariwayat.getAll();
    }
    
//    public void isitabel(){
//        dp = impldatapinjam.getAll();
//        modeltabeldatapinjam mb = new modeltabeldatapinjam(db);
//        frame.getTabletampilpinjam().setModel(mb);
//    } TABLE MASIH MATI SOALNYA
    
    public void insert(){
        datapinjam dp = new datapinjam();
        try {
            //dp.setId_pinjam(Integer.parseInt(frame.getLabelid().getText()));
            dp.setId_anggota(frame.getJnamalengkap().getText());
            dp.setId_admin(frame.getJlabadmin().getText());
            dp.setId_buku(frame.getLabelid().getText());
            dp.setTgl_pinjam(frame.getJtglpinjam().getText());
            dp.setTgl_kembali(frame.getJtxtkembali().getText());
            dp.setJumlah(Integer.parseInt(frame.getJqty().getText()));
            String qty = Integer.toString(dp.getJumlah());
            dp.setStatus("Belum Kembali");
            if (dp.getId_anggota().isEmpty() || dp.getId_buku().isEmpty() || dp.getTgl_kembali().isEmpty() ||dp.getTgl_pinjam().isEmpty() || qty.isEmpty()){
                throw new Exception("Data Tidak Boleh Kosong");
            }
            impldatapinjam.insert(dp);
            throw new Exception("Data berhasil diupdate");
        } catch(Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        
    }
    
    public void update(){
        //datapinjam dp = new datapinjam();
        System.out.println("Sudah di controller");
        try {
            System.out.println("1");
            int idpinjam = Integer.parseInt(frame2.getLabelidpinjam().getText());
            System.out.println("2");
            String tglkembali = frame2.getLabeltglkembali().getText();
            System.out.println("3");
            String statusTerpilih = (String) frame2.getJinputstatus().getSelectedItem();
            System.out.println("4");
            //dp.setStatus(statusTerpilih);
            //db.setKeluar(0);
            System.out.println("Mengupdate");
            System.out.println(idpinjam+ " " + tglkembali + " " + statusTerpilih);
            impldatapinjam.update(idpinjam, tglkembali, statusTerpilih);
            throw new Exception("Data berhasil diupdate");
        } catch(Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }
    
    public void update2(){
        try {
            
            String idbuku = frame2.getLabelidbuku().getText();
            //System.out.println("2");
            int qty = Integer.parseInt(frame2.getLabelqty().getText());
            //System.out.println("3");
            String statusTerpilih = (String) frame2.getJinputstatus().getSelectedItem();
           
            impldatapinjam.update2(idbuku, statusTerpilih, qty);
       
            
                //impldatapinjam.update(idbuku, statusTerpilih, qty);
            
            //System.out.println("4");
            //dp.setStatus(statusTerpilih);
            //db.setKeluar(0);
//            System.out.println("Mengupdate");
//            System.out.println(idpinjam+ " " + tglkembali + " " + statusTerpilih);
            //impldatapinjam.update(idpinjam, tglkembali, statusTerpilih);
            //throw new Exception("Data berhasil diupdate");
        } catch(Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }
}
