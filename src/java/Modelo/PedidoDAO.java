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
public class PedidoDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public void agregarPedido(Pedido pedido) {
        String sql = "INSERT INTO pedidos (id_detalle, id_plato, id_promo) VALUES (?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, pedido.getId_detalle());

            // Verificar si el pedido es un plato o una promoción
            if (pedido.getId_plato() != null) {
                ps.setInt(2, pedido.getId_plato());
            } else {
                ps.setNull(2, java.sql.Types.INTEGER); // Establecer el ID del plato como nulo
            }

            // Verificar si el pedido es una promoción o un plato
            if (pedido.getId_promo() != null) {
                ps.setInt(3, pedido.getId_promo());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER); // Establecer el ID de la promoción como nulo
            }

            ps.executeUpdate();
            cn.cerrar(); // Cerrar la conexión después de usarla
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Pedido> listarDetallesPedidoPorIdDetalle(int idDetalle) {
        String sql = "SELECT  p.id_plato, p.id_promo, pl.nombre_plato, pr.nombre_promocion " +
                     "FROM pedidos p " +
                     "LEFT JOIN platos pl ON p.id_plato = pl.id_plato " +
                     "LEFT JOIN promocion pr ON p.id_promo = pr.id_promocion " +
                     "WHERE p.id_detalle = ?";

        List<Pedido> detallesPedido = new ArrayList<>();

        try (Connection con = cn.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idDetalle);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Integer idPlato = rs.getInt("id_plato");
                Integer idPromo = rs.getInt("id_promo");
                String nombrePlato = rs.getString("nombre_plato");
                String nombrePromo = rs.getString("nombre_promocion");

                Pedido pedido = new Pedido( idDetalle, idPlato, idPromo, nombrePlato, nombrePromo);
                detallesPedido.add(pedido);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return detallesPedido;
    }

}
