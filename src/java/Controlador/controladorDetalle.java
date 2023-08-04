package Controlador;

import Modelo.Detalle;
import Modelo.DetalleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author james
 */
@WebServlet(name = "controladorDetalle", urlPatterns = {"/controladorDetalle"})
public class controladorDetalle extends HttpServlet {

    String listarOrdenes = "dashboard_ordenes.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        int idCliente = (int) session.getAttribute("id_clienter");
        DetalleDAO detalleDAO = new DetalleDAO();
        List<Detalle> detalles = detalleDAO.ListarDetalles();

        String action = request.getParameter("accion");

        if (action != null && action.equalsIgnoreCase("regresar")) {
            // Eliminar el carrito del usuario de la sesión
            
            session.removeAttribute("carrito");

            response.sendRedirect("index.jsp");
        } else if (action != null && action.equalsIgnoreCase("listar")) {
            
            List<Detalle> ultimasOrdenes = detalleDAO.obtenerUltimasCincoOrdenes();

            request.setAttribute("ultimasOrdenes", ultimasOrdenes);
            
            List<Detalle> ordenesUsuario = detalleDAO.obtenerOrdenesPorUsuario(idCliente);
            request.setAttribute("ordenesUsuario", ordenesUsuario);
            
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        }
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
        String idCliente = request.getParameter("id_cliente");
        String tipoEntrega = "delivery";
        String totalCompra = request.getParameter("total_compra");

        // Crea el objeto Detalle con los datos obtenidos
        Detalle detalle = new Detalle();
        detalle.setId_cliente(Integer.parseInt(idCliente));
        detalle.setTipo_entrega(tipoEntrega);
        detalle.setTotal(Double.parseDouble(totalCompra));

        // Guardar el detalle en la base de datos
        DetalleDAO detalleDAO = new DetalleDAO();
        detalleDAO.agregarDetalle(detalle);

        response.sendRedirect("confirmacion.jsp");
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
