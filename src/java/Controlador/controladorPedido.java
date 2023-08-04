
package Controlador;

import Modelo.DetalleDAO;
import Modelo.Pedido;
import Modelo.PedidoDAO;
import Modelo.Plato;
import Modelo.Promocion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author james
 */
@WebServlet(name = "controladorPedido", urlPatterns = {"/controladorPedido"})
public class controladorPedido extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controladorPedido</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet controladorPedido at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion"); // Obtener el valor del campo "accion"

        if (accion != null && accion.equals("agregarPedido")) {
            DetalleDAO detalleDAO = new DetalleDAO();
            
            int ultimoIdDetalle = detalleDAO.obtenerUltimoIdDetalleGenerado();
            // Guardar los platos y promociones seleccionados en la tabla "Pedidos"
            List<Object> carrito = (List<Object>) request.getSession().getAttribute("carrito");
            PedidoDAO pedidoDAO = new PedidoDAO();

            if (carrito != null && !carrito.isEmpty()) {
            for (Object item : carrito) {
            if (item instanceof Plato) {
                Plato plato = (Plato) item;
                
                Pedido pedidoPlato = new Pedido();
                pedidoPlato.setId_detalle(ultimoIdDetalle);
                pedidoPlato.setId_plato(plato.getId_plato());
                pedidoPlato.setId_promo(null); // Puedes establecerlo como nulo ya que es un plato
                pedidoDAO.agregarPedido(pedidoPlato);
            } else if (item instanceof Promocion) {
                Promocion promocion = (Promocion) item;
                
                Pedido pedidoPromocion = new Pedido();
                pedidoPromocion.setId_detalle(ultimoIdDetalle);
                pedidoPromocion.setId_plato(null); // Puedes establecerlo como nulo ya que es una promoción
                pedidoPromocion.setId_promo(promocion.getIdPromocion());
                pedidoDAO.agregarPedido(pedidoPromocion);
            }
        }
    }

    response.sendRedirect("confirmacion.jsp");
        }
    }



    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
