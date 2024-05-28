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
public interface datapinjamimplement {
    public void insert(datapinjam b);
    public List<datapinjam> getAll();
    public void update(int idpinjam, String tglkembali, String status);
    public void update2(String idbuku, String status, int qty);
}
