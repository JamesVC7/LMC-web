package Modelo;

import com.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PromocionDAO {

    Connection con;
    Conexion cn = new Conexion();
    Statement stm = null;
    PreparedStatement ps;
    ResultSet rs;
    Promocion pro = new Promocion();

    public List<Promocion> listar() {
        List<Promocion> promocion = new ArrayList<>();
        String sql = "SELECT * FROM promocion ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Promocion pro = new Promocion();
                pro.setIdPromocion(rs.getInt(1));
                pro.setNombre_promocion(rs.getString(2));
                pro.setDescripcion(rs.getString(3));
                pro.setPrecio(rs.getFloat(4));
                pro.setCantidad(rs.getInt(5));
                byte[] imagen = rs.getBytes(6);
                pro.setImagen(imagen);

                promocion.add(pro);
            }
            cn.cerrar();// Cerrar la conexión después de usarla

        } catch (Exception e) {
            e.printStackTrace();
        }
        return promocion;
    }

    public Promocion obtenerPorId(int idPromocion) {
        Promocion promocion = null;
        String sql = "SELECT * FROM promocion WHERE id_promocion = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idPromocion);
            rs = ps.executeQuery();
            if (rs.next()) {
                promocion = new Promocion();
                promocion.setIdPromocion(rs.getInt(1));
                promocion.setNombre_promocion(rs.getString(2));
                promocion.setDescripcion(rs.getString(3));
                promocion.setPrecio(rs.getFloat(4));
                promocion.setCantidad(rs.getInt(5));
                byte[] imagen = rs.getBytes(6);
                promocion.setImagen(imagen);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return promocion;
    }

    public Promocion ListarPorId(int id) {
        String sql = "SELECT * FROM promocion WHERE id_promocion = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                pro.setIdPromocion(rs.getInt(1));
                pro.setNombre_promocion(rs.getString(2));
                pro.setDescripcion(rs.getString(3));
                pro.setPrecio(rs.getFloat(4));
                pro.setCantidad(rs.getInt(5));
                byte[] imagen = rs.getBytes(6);
                pro.setImagen(imagen);
            }
            cn.cerrar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pro;
    }

    public boolean editarPromocion(Promocion promocion) {
        String sql = "UPDATE promocion SET nombre_promocion=?, descripcion=?, precio=?, cantidad=?,  imagen=? WHERE id_promocion=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, promocion.getNombre_promocion());
            ps.setString(2, promocion.getDescripcion());
            ps.setFloat(3, promocion.getPrecio());
            ps.setInt(4, promocion.getCantidad());
            ps.setBytes(5, promocion.getImagen());
            ps.setInt(6, promocion.getIdPromocion());
            int resultado = ps.executeUpdate();
            cn.cerrar(); // Cerrar la conexión después de usarla
            return resultado > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM promocion WHERE id_promocion=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean agregarProm(String nombre_promocion, String descripcion, float precio, int cantidad, byte[] imagen) {
        int resultUpdate = 0;
        try {
            con = cn.getConnection();
            String query = "INSERT INTO promocion (nombre_promocion, descripcion, precio, cantidad, imagen) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, nombre_promocion);
            pstmt.setString(2, descripcion);
            pstmt.setFloat(3, precio);
            pstmt.setInt(4, cantidad);
            pstmt.setBytes(5, imagen);

            resultUpdate = pstmt.executeUpdate();

            if (resultUpdate != 0) {
                cn.cerrar();
                return true;
            } else {
                cn.cerrar();
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error, no se pudo guardar");
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarCantidad(int idPromocion, int cantidadComprada) {
        String sql = "UPDATE promocion SET cantidad = cantidad - ? WHERE id_promocion = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cantidadComprada);
            ps.setInt(2, idPromocion);
            int resultado = ps.executeUpdate();
            cn.cerrar(); // Cerrar la conexión después de usarla
            return resultado > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Promocion> listarCantidad() {
        List<Promocion> promocion = new ArrayList<>();
        String sql = "SELECT * FROM promocion ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Promocion pro = new Promocion();
                pro.setIdPromocion(rs.getInt(1));
                pro.setCantidad(rs.getInt(5));

                promocion.add(pro);
            }
            cn.cerrar();// Cerrar la conexión después de usarla

        } catch (Exception e) {
            e.printStackTrace();
        }
        return promocion;
    }
    
}
