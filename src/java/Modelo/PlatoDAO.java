package Modelo;

import com.config.Conexion;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

public class PlatoDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    Plato p = new Plato();

    public List<Plato> listar() {
        List<Plato> platos = new ArrayList<>();
        String sql = "SELECT * FROM platos";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Plato p = new Plato();
                p.setId_plato(rs.getInt(1));
                p.setId_categoria(rs.getInt(2));
                p.setNombre_plato(rs.getString(3));
                p.setPrecio(rs.getFloat(4));
                p.setCantidad(rs.getInt(5));
                p.setDescripcion(rs.getString(6));

                platos.add(p);
            }
            cn.cerrar();// Cerrar la conexión después de usarla

        } catch (Exception e) {
            e.printStackTrace();
        }
        return platos;
    }

    public List<Plato> listar(int idCategoria) {
        List<Plato> platos = new ArrayList<>();
        String sql = "SELECT * FROM platos WHERE id_categoria = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCategoria);
            rs = ps.executeQuery();
            while (rs.next()) {
                Plato p = new Plato();
                p.setId_plato(rs.getInt(1));
                p.setId_categoria(rs.getInt(2));
                p.setNombre_plato(rs.getString(3));
                p.setPrecio(rs.getFloat(4));
                p.setCantidad(rs.getInt(5));
                p.setDescripcion(rs.getString(6));
                byte[] imagen = rs.getBytes(7);
                p.setImagen(imagen);

                platos.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return platos;
    }

    public List<Plato> listarPorPagina(int pageNumber, int pageSize) {
        List<Plato> platos = new ArrayList<>();
        int offset = (pageNumber - 1) * pageSize;
        String sql = "SELECT * FROM platos LIMIT ? OFFSET ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pageSize);
            ps.setInt(2, offset);
            rs = ps.executeQuery();
            while (rs.next()) {
                Plato p = new Plato();
                p.setId_plato(rs.getInt(1));
                p.setId_categoria(rs.getInt(2));
                p.setNombre_plato(rs.getString(3));
                p.setPrecio(rs.getFloat(4));
                p.setCantidad(rs.getInt(5));
                p.setDescripcion(rs.getString(6));

                platos.add(p);
            }
            cn.cerrar(); // Cerrar la conexión después de usarla
        } catch (Exception e) {
            e.printStackTrace();
        }
        return platos;
    }

    public int getTotalPages(int pageSize) {
        int totalRows = 0;
        int totalPages = 0;
        String sql = "SELECT COUNT(*) FROM platos";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                totalRows = rs.getInt(1);
            }
            totalPages = (int) Math.ceil((double) totalRows / pageSize);
            cn.cerrar(); // Cerrar la conexión después de usarla
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalPages;
    }

    public Plato obtenerPorCategoria(int idCategoria) {
        Plato plato = null;
        String sql = "SELECT * FROM platos WHERE id_categoria = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCategoria);
            rs = ps.executeQuery();
            if (rs.next()) {
                plato = new Plato();
                plato.setId_plato(rs.getInt(1));
                plato.setId_categoria(rs.getInt(2));
                plato.setNombre_plato(rs.getString(3));
                plato.setPrecio(rs.getFloat(4));
                plato.setCantidad(rs.getInt(5));
                plato.setDescripcion(rs.getString(6));
                byte[] imagen = rs.getBytes(7);
                plato.setImagen(imagen);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plato;
    }

    public Plato obtenerPorId(int idPlato) {
        Plato plato = null;
        String sql = "SELECT * FROM platos WHERE id_plato = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idPlato);
            rs = ps.executeQuery();
            if (rs.next()) {
                plato = new Plato();
                plato.setId_plato(rs.getInt(1));
                plato.setId_categoria(rs.getInt(2));
                plato.setNombre_plato(rs.getString(3));
                plato.setPrecio(rs.getFloat(4));
                plato.setCantidad(rs.getInt(5));
                plato.setDescripcion(rs.getString(6));
                byte[] imagen = rs.getBytes(7);
                plato.setImagen(imagen);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plato;
    }

    public Plato ListarPorId(int id) {
        String sql = "SELECT * FROM platos WHERE id_plato = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                p.setId_plato(rs.getInt(1));
                p.setId_categoria(rs.getInt(2));
                p.setNombre_plato(rs.getString(3));
                p.setPrecio(rs.getFloat(4));
                p.setCantidad(rs.getInt(5));
                p.setDescripcion(rs.getString(6));
                byte[] imagen = rs.getBytes(7);
                p.setImagen(imagen);
            }
            cn.cerrar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    public List<Plato> listar(int numeroPagina, int filasPorPagina) {
        List<Plato> platos = new ArrayList<>();
        String sql = "SELECT * FROM platos LIMIT ?, ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            // Calcular el índice de inicio para la consulta LIMIT
            int indiceInicio = (numeroPagina - 1) * filasPorPagina;
            ps.setInt(1, indiceInicio);
            ps.setInt(2, filasPorPagina);
            rs = ps.executeQuery();
            while (rs.next()) {
                Plato p = new Plato();
                p.setId_plato(rs.getInt(1));
                p.setId_categoria(rs.getInt(2));
                p.setNombre_plato(rs.getString(3));
                p.setPrecio(rs.getFloat(4));
                p.setCantidad(rs.getInt(5));
                p.setDescripcion(rs.getString(6));
                byte[] imagen = rs.getBytes(7);
                p.setImagen(imagen);

                platos.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return platos;
    }

    public int obtenerNumeroFilasTotal() {
        int numeroFilasTotal = 0;
        String sql = "SELECT COUNT(*) FROM platos";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                numeroFilasTotal = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numeroFilasTotal;
    }

    public boolean editarPlato(Plato plato) {
        String sql = "UPDATE platos SET id_categoria=?, nombre_plato=?, precio=?, cantidad=?, descripcion=?, imagen=? WHERE id_plato=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, plato.getId_categoria());
            ps.setString(2, plato.getNombre_plato());
            ps.setFloat(3, plato.getPrecio());
            ps.setInt(4, plato.getCantidad());
            ps.setString(5, plato.getDescripcion());
            ps.setBytes(6, plato.getImagen()); // Agregar el campo de imagen
            ps.setInt(7, plato.getId_plato());
            int resultado = ps.executeUpdate();
            cn.cerrar(); // Cerrar la conexión después de usarla
            return resultado > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM platos WHERE id_plato=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void agregarPlato(Plato nuevoPlato) {
        String sql = "INSERT INTO platos (id_categoria, nombre_plato, precio, cantidad, descripcion, imagen) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, nuevoPlato.getId_categoria());
            ps.setString(2, nuevoPlato.getNombre_plato());
            ps.setFloat(3, nuevoPlato.getPrecio());
            ps.setInt(4, nuevoPlato.getCantidad());
            ps.setString(5, nuevoPlato.getDescripcion());
            ps.setBytes(6, nuevoPlato.getImagen()); // Guardar arreglo de bytes de la imagen
            ps.executeUpdate();
            cn.cerrar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean actualizarCantidad(int idPlato, int cantidadComprada) {
        String sql = "UPDATE platos SET cantidad = cantidad - ? WHERE id_plato = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cantidadComprada);
            ps.setInt(2, idPlato);
            int resultado = ps.executeUpdate();
            cn.cerrar(); // Cerrar la conexión después de usarla
            return resultado > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Integer> obtenerCantidadPlatosPorCategoria() {
    List<Integer> cantidadPlatosPorCategoria = new ArrayList<>();
    String sql = "SELECT id_categoria, COUNT(*) AS cantidad_platos FROM platos GROUP BY id_categoria";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            int cantidadPlatos = rs.getInt("cantidad_platos");
            cantidadPlatosPorCategoria.add(cantidadPlatos);
        }
        cn.cerrar(); // Cerrar la conexión después de usarla
    } catch (Exception e) {
        e.printStackTrace();
    }
    return cantidadPlatosPorCategoria;
}




}
