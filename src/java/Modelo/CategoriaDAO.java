package Modelo;

import com.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author james
 */
public class CategoriaDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    Categoria c =new Categoria();

    public List<Categoria> listar() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId_categoria(rs.getInt(1));
                c.setNombre_categoria(rs.getString(2));

                categorias.add(c); // Agregar el objeto Categoria a la lista
            }
            cn.cerrar();// Cerrar la conexión después de usarla

        } catch (Exception e) {
            e.printStackTrace();
        }
        return categorias;
    }

    public void agregarCategoria(Categoria categoria) {
        String sql = "INSERT INTO categoria (nombre_categoria) VALUES (?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, categoria.getNombre_categoria());
            ps.executeUpdate();
            cn.cerrar(); // Cerrar la conexión después de usarla
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Categoria listarPorIdCategoria(int id) {
        String sql = "SELECT * FROM categoria WHERE id_categoria = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                c.setId_categoria(rs.getInt("id_categoria"));
                c.setNombre_categoria(rs.getString("nombre_categoria"));
            }
            cn.cerrar(); // Cerrar la conexión después de usarla
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public boolean editarCategoria(Categoria categoria) {
        String sql = "UPDATE categoria SET nombre_categoria = ? WHERE id_categoria = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, categoria.getNombre_categoria());
            ps.setInt(2, categoria.getId_categoria());
            int resultado = ps.executeUpdate();
            cn.cerrar(); // Cerrar la conexión después de usarla
            return resultado > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean eliminarCategoria(int id) {
        String sql = "DELETE FROM categoria WHERE id_categoria = ?";
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

}
