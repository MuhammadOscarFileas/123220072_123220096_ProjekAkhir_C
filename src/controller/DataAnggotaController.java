/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import DAOdataAnggota.DataAnggotaDAO;
import DAOImplement.DataAnggotaInterface;
import model.*;
import perpustakaan.HalamanAnggota;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class DataAnggotaController {

    HalamanAnggota frame;
    DataAnggotaInterface impldataanggota;
    List<DataAnggota> dang;

    public DataAnggotaController(HalamanAnggota frame) {
        this.frame = frame;
        impldataanggota = new DataAnggotaDAO();
        dang = impldataanggota.getAll();
    }

    public void isitabel() {
        dang = impldataanggota.getAll();
        ModelTabelDataAnggota mang = new ModelTabelDataAnggota(dang);
        frame.getTabeltampilanggota().setModel(mang);

    }

    public void insert() { //disini
        DataAnggota dang = new DataAnggota();
        try {
            dang.setId_anggota(frame.getJtxtidanggota().getText());
            dang.setFull_name(frame.getJtxtnama().getText());
            dang.setTmp_lahir(frame.getJtxttempatlahir().getText());
            dang.setTgl_lahir(frame.getJtxttanggallahir().getText());
            dang.setAlamat(frame.getJtxtalamat().getText());
            dang.setGender(frame.getJtxtgender().getText());
            dang.setTelepon(frame.getJtxttelepon().getText());
            dang.setId_admin(frame.getJlabadmin().getText());
            if (dang.getFull_name().isEmpty() || dang.getId_anggota().isEmpty() || dang.getTmp_lahir().isEmpty() || dang.getTgl_lahir().isEmpty() || dang.getAlamat().isEmpty() || dang.getGender().isEmpty() || dang.getTelepon().isEmpty()) {
                throw new Exception("Data Tidak Boleh Kosong");
            }
            impldataanggota.insert(dang);
            throw new Exception("Data berhasil dimasukkan");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }

    public void update() {
        DataAnggota dang = new DataAnggota();
        try {
            dang.setId_anggota(frame.getJtxtidanggota().getText());
            dang.setFull_name(frame.getJtxtnama().getText());
            dang.setTmp_lahir(frame.getJtxttempatlahir().getText());
            dang.setTgl_lahir(frame.getJtxttanggallahir().getText());
            dang.setAlamat(frame.getJtxtalamat().getText());
            dang.setGender(frame.getJtxtgender().getText());
            dang.setTelepon(frame.getJtxttelepon().getText());
            dang.setId_admin(frame.getJlabadmin().getText());
            if (dang.getFull_name().isEmpty() || dang.getId_anggota().isEmpty() || dang.getTmp_lahir().isEmpty() || dang.getTgl_lahir().isEmpty() || dang.getAlamat().isEmpty() || dang.getGender().isEmpty() || dang.getTelepon().isEmpty()) {
                throw new Exception("Data Tidak Boleh Kosong");
            }
            impldataanggota.update(dang);
            throw new Exception("Data berhasil diupdate");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }

    public void delete() {
        try {
            String id_anggota = frame.getJtxtidanggota().getText();
            impldataanggota.delete(id_anggota);
            throw new Exception("Data berhasil dihapus");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }
}
