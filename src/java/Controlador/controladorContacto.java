package Controlador;

import Modelo.Contacto;
import Modelo.ContactoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author julio
 */
@WebServlet(name = "controladorContacto", urlPatterns = {"/controladorContacto"})
public class controladorContacto extends HttpServlet {

    String listarContacto = "dashboard_comentarios.jsp";
    
    int id;
    Contacto contact = new Contacto();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ContactoDAO contactoDAO = new ContactoDAO();

        // Obtener el listado completo de comentarios
        List<Contacto> contactos = contactoDAO.ListarContacto();

        // Obtener los últimos dos comentarios
        List<Contacto> ultimosDosComentarios = contactoDAO.obtenerUltimosDosComentarios();

        String acceso = "";
        String action = request.getParameter("accion");

        if (action != null && action.equalsIgnoreCase("listar")) {
            acceso = listarContacto;
        }else if (action.equalsIgnoreCase("eliminar")) {
            id = Integer.parseInt(request.getParameter("id"));
            contact.setId_contacto(id);
            contactoDAO.eliminar(id);
            acceso = listarContacto;
        }

        contactos.addAll(ultimosDosComentarios);

        request.setAttribute("contactos", contactos);
        
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los datos enviados por el formulario
        String nombre = request.getParameter("Nombre");
        String apellido = request.getParameter("Apellido");
        String email = request.getParameter("email");
        String telefonoStr = request.getParameter("telefono");
        String mensaje = request.getParameter("mensaje");
        int telefono = 0;

        try {
            telefono = Integer.parseInt(telefonoStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        // Crear un objeto Contacto con los datos recibidos
        Contacto contacto = new Contacto();
        contacto.setNombre(nombre);
        contacto.setApellido(apellido);
        contacto.setCorreo(email);
        contacto.setCelular(telefono);
        contacto.setMensaje(mensaje);

        ContactoDAO contactoDAO = new ContactoDAO();
        contactoDAO.agregarContacto(contacto);

        response.sendRedirect("contacto.jsp");

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
