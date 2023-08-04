package Controlador;

import Modelo.AdminDAO;
import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.Detalle;
import Modelo.DetalleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "controladorUsuario", urlPatterns = {"/controladorUsuario"})
public class controladorUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

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
        String accion = request.getParameter("accion");

        if (accion != null && accion.equals("logout")) {
            // Cerrar sesión
            HttpSession session = request.getSession();
            System.out.println("Cerrando sesión");
            session.invalidate();
            response.sendRedirect("index.jsp");
            return;
        } else {
            String correo = request.getParameter("correo");
            String contraseña = request.getParameter("clave");

            // validación del usuario y contraseña en la base de datos
            AdminDAO adminDAO = new AdminDAO();
            boolean isValidAdmin = adminDAO.validarAdmin(correo, contraseña);
            ClienteDAO clienteDAO = new ClienteDAO();
            boolean isValidCliente = clienteDAO.validarCliente(correo, contraseña);

            if (isValidAdmin) {

                String nombre = adminDAO.obtenerNombreAdmin(correo);

                // Almacena el nombre del admin en la sesión
                HttpSession session = request.getSession();
                session.setAttribute("nombre", nombre);

                response.sendRedirect("dashboard.jsp");

            } else if (isValidCliente) {
                Cliente cliente = clienteDAO.obtenerCliente(correo);
                if (cliente != null) {
                    String nombre = cliente.getNombre();
                    String direccion = cliente.getDireccion();
                    String apellido = cliente.getApellido();
                    String correoCliente = cliente.getCorreo();
                    int celular = cliente.getCelular();
                    int id = cliente.getId_cliente();

                    // Almacena los datos del cliente en la sesión
                    HttpSession session = request.getSession();
                    session.setAttribute("nombre", nombre);
                    session.setAttribute("id_clienter", id);
                    session.setAttribute("apellido", apellido);
                    session.setAttribute("celular", celular);
                    session.setAttribute("direccion", direccion);
                    session.setAttribute("correo", correoCliente);

                    // Obtener órdenes asociadas al usuario y guardarlas en la sesión
                    DetalleDAO detalleDAO = new DetalleDAO();
                    List<Detalle> ordenesUsuario = detalleDAO.obtenerOrdenesPorUsuario(id);
                    session.setAttribute("ordenesUsuario", ordenesUsuario);
                            
                    response.sendRedirect("index.jsp");
                }

            }else if (accion != null && accion.equals("actualizarOrdenes")) {
        // Actualizar las órdenes del usuario en la sesión
        HttpSession session = request.getSession();
        int idCliente = (int) session.getAttribute("id_clienter");
        DetalleDAO detalleDAO = new DetalleDAO();
        List<Detalle> ordenesUsuario = detalleDAO.obtenerOrdenesPorUsuario(idCliente);
        session.setAttribute("ordenesUsuario", ordenesUsuario);

        // Redirigir nuevamente a la página usuario.jsp
        response.sendRedirect("usuario.jsp");
    } else {
                String mensaje = "Usuario o contraseña incorrectos";
                String script = "<script type='text/javascript'>alert('" + mensaje + "');window.location.href='iniciarSesion.jsp';</script>";
                response.getWriter().println(script);
            }
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
