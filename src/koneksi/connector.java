/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class connector {
    static Connection conn;
    
    public static Connection connection(){
        if (conn==null){
            MysqlDataSource data = new MysqlDataSource();
            data.setDatabaseName("perpus");
            data.setUser("root");
            data.setPassword("");
            try{
                conn = data.getConnection();
                System.out.println("Koneksi Berhasil");
            }catch(SQLException ex){
                ex.printStackTrace();
                System.out.println("Koneksi Gagal");
            }
        }
        return conn;
    }
}

