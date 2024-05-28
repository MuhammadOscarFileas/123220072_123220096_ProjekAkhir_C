/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImplement;
import java.util.List;
import model.*;
/**
 *
 * @author Lenovo
 */
public interface databukuimplement {
    public void insert(databuku b);
    public void update(databuku b);
    public void delete(String id_buku);
    public List<databuku> getAll();
    public List<databuku> search(String cari);
}
