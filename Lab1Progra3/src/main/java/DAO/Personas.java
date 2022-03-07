/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConexionDB.Conexion;
import Entidades.Persona;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author cb272
 */
public class Personas {

    Conexion conexion = new Conexion();
    Connection con = conexion.getConection();

    public ArrayList<Persona> ListadoContactos() {

        ArrayList<Persona> listado = null;

        try {
            listado = new ArrayList<Persona>();
            
            //CallableStatement cb = con.prepareCall("Select * from contactos");
            CallableStatement cb = con.prepareCall("call sp_MostrarContactos()");
            ResultSet resultado = cb.executeQuery();

            while (resultado.next()) {
                Persona persona = new Persona();
                persona.setId(resultado.getInt("Id"));
                persona.setNombre(resultado.getString("Nombre"));
                persona.setEdad(resultado.getInt("Edad"));
                persona.setEmail(resultado.getString("Email"));
                persona.setTelefono(resultado.getString("Telefono"));
                listado.add(persona);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return listado;
    }

    public void AddPersona(Persona persona) {
        try {
//            CallableStatement cb = con.prepareCall("Insert into contactos(Nombre, Edad, Email, Telefono)"
//                    +" Values('"  + persona.getNombre() +
//                    "', '" + persona.getEdad() +
//                    "', '" + persona.getEmail() +
//                    "', '" + persona.getTelefono()+ "')");
            CallableStatement cb = con.prepareCall("call sp_insertarDatos(?,?,?,?)");
            cb.setString(1, persona.getNombre());
            cb.setInt(2, persona.getEdad());
            cb.setString(3, persona.getEmail());
            cb.setString(4, persona.getTelefono());
            cb.execute();

            JOptionPane.showMessageDialog(null, "Contacto agregado");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public void UpdatePersona(Persona persona) {
        try {
            //Update contactos set Nombre= 'prueba', Edad = 5, Email= 'wfwefw', Telefono= 32412342 where id = 3
//            CallableStatement cb = con.prepareCall("Update contactos set Nombre= '"+persona.getNombre()+"' ," +
//                    "Edad='"+ persona.getEdad() +
//                    "', Email='"+ persona.getEmail() +"' , Telefono='"+ persona.getTelefono() +"'"
//                    + "where Id=" + persona.getId());
            CallableStatement cb = con.prepareCall("call sp_ActualizarContacto(?, ?, ?, ?, ?)");
            cb.setInt(1, persona.getId());
            cb.setString(2, persona.getNombre());
            cb.setInt(3, persona.getEdad());
            cb.setString(4, persona.getEmail());
            cb.setString(5, persona.getTelefono());
            cb.execute();

            JOptionPane.showMessageDialog(null, "Contacto Modificado");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public void DeletePersona(int id) {
        try {

//            CallableStatement cb = con.prepareCall("delete from contactos where Id = " + id);
            CallableStatement cb = con.prepareCall("call sp_EliminarContacto(?)");
            cb.setInt(1, id);

            cb.execute();

            JOptionPane.showMessageDialog(null, "Contacto Eliminado");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
}
