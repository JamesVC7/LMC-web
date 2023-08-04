package Modelo;

import com.config.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author james
 */
public class ClienteDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    Cliente cl = new Cliente();

    public boolean validarCliente(String correo, String clave) {
        String sql = "SELECT * FROM clienter WHERE correo = ? AND clave = ?";
        try {
            con = cn.getConnection(); // Establecer conexión a la base de datos
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, clave);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.cerrar(); // Cerrar la conexión después de usarla
        }
        return false;
    }

    public Cliente obtenerCliente(String correo) {
        Cliente cliente = null;
        String sql = "SELECT id_clienter, Nombre, Apellido, celular, direccion, correo FROM clienter WHERE correo = ?";
        try {
            Connection con = cn.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("Nombre");
                String apellido = rs.getString("Apellido");
                String direccion = rs.getString("direccion");
                int celular = rs.getInt("Celular");
                int id = rs.getInt("id_clienter");
                cliente = new Cliente(id, nombre,apellido,celular, direccion, correo);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cliente;
    }

    public List<Cliente> ListarCliente() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clienter";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_cliente(rs.getInt("id_clienter"));
                cliente.setNombre(rs.getString("Nombre"));
                cliente.setApellido(rs.getString("Apellido"));
                cliente.setCelular(rs.getInt("Celular"));
                cliente.setFecha_Nacimiento(rs.getDate("Fecha_Nacimiento"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setClave(rs.getString("clave"));
                clientes.add(cliente);
            }
            cn.cerrar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public void agregarCliente(Cliente cliente) {
        String sql = "INSERT INTO clienter (Nombre, Apellido, Celular, Fecha_Nacimiento, direccion, correo, clave) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getCelular());
            ps.setDate(4, (Date) cliente.getFecha_Nacimiento());
            ps.setString(5, cliente.getDireccion());
            ps.setString(6, cliente.getCorreo());
            ps.setString(7, cliente.getClave());

            ps.executeUpdate();
            cn.cerrar(); // Cerrar la conexión después de usarla
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean editarCliente(Cliente cliente) {
        String sql = "UPDATE clienter SET Nombre = ?, Apellido = ?, Celular = ?, direccion = ?, correo = ?, clave = ? WHERE id_clienter = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getCelular());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getCorreo());
            ps.setString(6, cliente.getClave());
            ps.setInt(7, cliente.getId_cliente());
            int resultado = ps.executeUpdate();
            cn.cerrar(); // Cerrar la conexión después de usarla
            return resultado > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminarCliente(int id) {
        String sql = "DELETE FROM clienter WHERE id_clienter = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Cliente listarPorIdCliente(int id) {
        String sql = "SELECT * FROM clienter WHERE id_clienter = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                cl.setId_cliente(rs.getInt("id_clienter"));
                cl.setNombre(rs.getString("Nombre"));
                cl.setApellido(rs.getString("Apellido"));
                cl.setCelular(rs.getInt("Celular"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setCorreo(rs.getString("correo"));
                cl.setClave(rs.getString("clave"));

            }
            cn.cerrar(); // Cerrar la conexión después de usarla
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cl;
    }
}
