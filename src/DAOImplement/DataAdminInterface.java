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
public interface DataAdminInterface {

    public boolean cekLogin(DataAdmin a);
    public void insert(DataAdmin a);
    public void update(DataAdmin a);
    public void delete(String id_admin);
    //public List<DataAdmin> getAll(String id);
    public List<DataAdmin> getAll();
}
