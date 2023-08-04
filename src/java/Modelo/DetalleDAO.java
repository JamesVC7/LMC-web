package Modelo;

import com.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DetalleDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    Detalle d = new Detalle();

    public void agregarDetalle(Detalle detalle) {
        String sql = "INSERT INTO detalle_pedido (id_clienter, tipo_entrega, total) VALUES (?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, detalle.getId_cliente());
            ps.setString(2, "delivery"); 
            ps.setDouble(3, detalle.getTotal()); 
            ps.executeUpdate();
            cn.cerrar(); // Cerrar la conexión después de usarla
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Detalle> ListarDetalles() {
        List<Detalle> detalles = new ArrayList<>();
        String sql = "SELECT * FROM detalle_pedido";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Detalle detalle = new Detalle();
                detalle.setId_detalle(rs.getInt("id_detalle"));
                detalle.setId_cliente(rs.getInt("id_clienter"));
                detalle.setTipo_entrega(rs.getString("tipo_entrega"));
                detalle.setFecha(rs.getDate("fecha"));
                detalle.setTotal(rs.getDouble("total"));
                detalles.add(detalle);
            }
            cn.cerrar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detalles;
    }

    public List<Detalle> obtenerUltimasCincoOrdenes() {
        List<Detalle> ultimasOrdenes = new ArrayList<>();
        String sql = "SELECT * FROM detalle_pedido ORDER BY fecha DESC LIMIT 5";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Detalle detalle = new Detalle();
                detalle.setId_detalle(rs.getInt("id_detalle"));
                detalle.setTipo_entrega(rs.getString("tipo_entrega"));
                detalle.setFecha(rs.getDate("fecha"));
                detalle.setTotal(rs.getDouble("total"));
                
                ultimasOrdenes.add(detalle);
            }
            cn.cerrar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ultimasOrdenes;
    }
    
    public List<Detalle> obtenerOrdenesPorUsuario(int idCliente) {
        List<Detalle> ordenesUsuario = new ArrayList<>();
        String sql = "SELECT * FROM detalle_pedido WHERE id_clienter = ? ORDER BY fecha DESC";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();

            while (rs.next()) {
                Detalle detalle = new Detalle();
                detalle.setId_detalle(rs.getInt("id_detalle"));
                detalle.setId_cliente(rs.getInt("id_clienter"));
                detalle.setTipo_entrega(rs.getString("tipo_entrega"));
                detalle.setFecha(rs.getDate("fecha"));
                detalle.setTotal(rs.getDouble("total"));

                ordenesUsuario.add(detalle);
            }

            cn.cerrar();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ordenesUsuario;
    }
    
    public int obtenerUltimoIdDetalleGenerado() {
    String sql = "SELECT MAX(id_detalle) AS ultimo_id_detalle FROM detalle_pedido";
    int ultimoIdDetalle = 0;

    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        if (rs.next()) {
            ultimoIdDetalle = rs.getInt("ultimo_id_detalle");
        }

        cn.cerrar();
    } catch (Exception e) {
        e.printStackTrace();
    }

    return ultimoIdDetalle;
}


}
