/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author cb272
 */
public class Conexion {
    
    private static Connection con = null;
    
    public Connection getConection(){
        try {
            String url = "jdbc:mysql://localhost:3306/Lab";
            String user = "root";
            String password = "root";
            
            con = DriverManager.getConnection(url, user, password);
            //System.out.println("Conexion exitosa");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error" + ex.toString());
        }
            return con;
    }
}
