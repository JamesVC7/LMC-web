package Modelo;

import com.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author julio
 */
public class ContactoDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    Contacto ct = new Contacto();
    
    public void agregarContacto(Contacto contacto) {
        String sql = "INSERT INTO comentarios (Nombre, Apellido, Correo, Celular, Mensaje) VALUES (?, ?, ?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, contacto.getNombre()); 
            ps.setString(2, contacto.getApellido()); 
            ps.setString(3, contacto.getCorreo());
            ps.setInt(4, contacto.getCelular()); 
            ps.setString(5, contacto.getMensaje());
            ps.executeUpdate();
            cn.cerrar(); // Cerrar la conexión después de usarla
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     public List<Contacto> ListarContacto() {
        List<Contacto> contactos = new ArrayList<>();
        String sql = "SELECT * FROM comentarios";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Contacto contacto = new Contacto();
                contacto.setId_contacto(rs.getInt("id_comentario"));
                contacto.setNombre(rs.getString("Nombre"));
                contacto.setApellido(rs.getString("Apellido"));
                contacto.setCorreo(rs.getString("Correo"));
                contacto.setCelular(rs.getInt("Celular"));
                contacto.setMensaje(rs.getString("Mensaje"));
                contactos.add(contacto);
            }
            cn.cerrar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contactos;
    }
     
     public List<Contacto> obtenerUltimosDosComentarios() {
    List<Contacto> ultimosDosComentarios = new ArrayList<>();
    String sql = "SELECT * FROM comentarios ORDER BY id_comentario DESC LIMIT 2";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            Contacto contacto = new Contacto();
            contacto.setId_contacto(rs.getInt("id_comentario"));
            contacto.setNombre(rs.getString("Nombre"));
            contacto.setApellido(rs.getString("Apellido"));
            contacto.setCorreo(rs.getString("Correo"));
            contacto.setCelular(rs.getInt("Celular"));
            contacto.setMensaje(rs.getString("Mensaje"));
            ultimosDosComentarios.add(contacto);
        }
        cn.cerrar();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return ultimosDosComentarios;
}
     
     public void eliminar(int id) {
        String sql = "DELETE FROM comentarios WHERE id_comentario=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

