
package Modelo;

import com.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author james
 */
public class AdminDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    Admin a = new Admin();

    public boolean validarAdmin(String correo, String clave) {
        String sql = "SELECT * FROM admin WHERE correo = ? AND clave = ?";
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
    
     public String obtenerNombreAdmin(String correo) {
        String nombreAdmin = null;
        String sql = "SELECT nombre FROM admin WHERE correo = ?";
        try {
            Connection con = cn.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nombreAdmin = rs.getString("nombre");
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nombreAdmin;
    }
}






