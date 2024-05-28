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
 * @author HP
 */
public interface DataAnggotaInterface {
    public void insert(DataAnggota ang);
    public void update(DataAnggota ang);
    public void delete(String id_anggota);
    public List<DataAnggota> getAll();
}
