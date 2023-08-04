package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author julio
 */
@WebServlet(name = "controladorCliente", urlPatterns = {"/controladorCliente"})
public class controladorCliente extends HttpServlet {

    String listarClientes = "dashboard_clientes.jsp";
    String edit = "editarClient.jsp";
    Cliente client = new Cliente();
    ClienteDAO clientdao = new ClienteDAO();
    int id;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> clientes = clienteDAO.ListarCliente();

        String acceso = "";
        String action = request.getParameter("accion");

        if (action != null && action.equalsIgnoreCase("listar")) {
            acceso = listarClientes;
        } else if (action.equalsIgnoreCase("editar")) {
            request.setAttribute("id", request.getParameter("id"));
            acceso = edit;
        } else if (action.equalsIgnoreCase("eliminar")) {
            id = Integer.parseInt(request.getParameter("id"));
            client.setId_cliente(id);
            clientdao.eliminarCliente(id);
            acceso = listarClientes;
        }

        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
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

        String action = request.getParameter("accion");
        if (action.equalsIgnoreCase("Actualizar")) {
            int idCliente = Integer.parseInt(request.getParameter("id_clienter"));
            String nombre = request.getParameter("Nombre");
            String apellido = request.getParameter("Apellido");
            int celular = Integer.parseInt(request.getParameter("Celular"));
            String direccion = request.getParameter("direccion");
            String correo = request.getParameter("correo");
            String clave = request.getParameter("clave");

            client.setId_cliente(idCliente);
            client.setNombre(nombre);
            client.setApellido(apellido);
            client.setCelular(celular);
            client.setDireccion(direccion);
            client.setCorreo(correo);
            client.setClave(clave);

            clientdao.editarCliente(client);

            response.sendRedirect("dashboard_clientes.jsp");

        } else if (action.equalsIgnoreCase("Registrar")) {
            // Código para registrar un nuevo cliente en la base de datos
            String nombre = request.getParameter("nombres");
            String apellido = request.getParameter("apellidos");
            int celular = Integer.parseInt(request.getParameter("numero"));
            String fechaStr = request.getParameter("fecha");
            Date fechaNacimiento = null;

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                fechaNacimiento = sdf.parse(fechaStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String direccion = request.getParameter("direccion");
            String correo = request.getParameter("correo");
            String clave = request.getParameter("clave");

            Cliente nuevoCliente = new Cliente();
            nuevoCliente.setNombre(nombre);
            nuevoCliente.setApellido(apellido);
            nuevoCliente.setCelular(celular);
            nuevoCliente.setFecha_Nacimiento(new java.sql.Date(fechaNacimiento.getTime()));
            nuevoCliente.setDireccion(direccion);
            nuevoCliente.setCorreo(correo);
            nuevoCliente.setClave(clave);

            clientdao.agregarCliente(nuevoCliente);

            response.sendRedirect("iniciarSesion.jsp");
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
