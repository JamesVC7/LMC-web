package Controlador;

import Modelo.Plato;
import Modelo.PlatoDAO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.tomcat.util.http.fileupload.IOUtils;

/**
 *
 * @author james
 */
@MultipartConfig
@WebServlet(name = "controladorPlato", urlPatterns = {"/controladorPlato"})
public class controladorPlato extends HttpServlet {

    String listarPlato = "dashboard_platos.jsp";
    String addPlato = "agregar.jsp";
    String editPlato = "editar.jsp";
    Plato p = new Plato();
    PlatoDAO pdao = new PlatoDAO();
    int id;

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
        String acceso = "";
        String action = request.getParameter("accion");
        if (action.equalsIgnoreCase("listar")) {
            acceso = listarPlato;
        } else if (action.equalsIgnoreCase("addPlato")) {
            acceso = addPlato;

        } else if (action.equalsIgnoreCase("editarPlato")) {
            request.setAttribute("id", request.getParameter("id"));
            acceso = editPlato;
        } else if (action.equalsIgnoreCase("eliminarPlato")) {
            id = Integer.parseInt(request.getParameter("id"));
            p.setId_plato(id);
            pdao.eliminar(id);
            acceso = listarPlato;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("accion");
        if (action.equalsIgnoreCase("Agregar")) {
            int idCategoria = Integer.parseInt(request.getParameter("id_cat"));
            String nombre = request.getParameter("nombre_plato");
            float precio = Float.parseFloat(request.getParameter("precio"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            String descripcion = request.getParameter("descripcion");

            // Obtener la parte de la imagen
            Part imagenPart = request.getPart("imagen");
            InputStream imagenStream = imagenPart.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = imagenStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            byte[] imagenBytes = byteArrayOutputStream.toByteArray();

            // Crear objeto Plato con los datos y la imagen
            Plato nuevoPlato = new Plato();
            nuevoPlato.setId_categoria(idCategoria);
            nuevoPlato.setNombre_plato(nombre);
            nuevoPlato.setPrecio(precio);
            nuevoPlato.setCantidad(cantidad);
            nuevoPlato.setDescripcion(descripcion);
            nuevoPlato.setImagen(imagenBytes);
            
            pdao.agregarPlato(nuevoPlato);
            
            response.sendRedirect("dashboard_platos.jsp");

        } else  if (action.equalsIgnoreCase("Actualizar")) {
        int idPlato = Integer.parseInt(request.getParameter("id_plato"));
        int idCategoria = Integer.parseInt(request.getParameter("id_categoria"));
        String nombre = request.getParameter("nombre_plato");
        float precio = Float.parseFloat(request.getParameter("precio"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        String descripcion = request.getParameter("descripcion");

        // Obtener la imagen enviada desde el formulario
        Part imagenPart = request.getPart("imagen");
        byte[] imagenBytes = null;
        if (imagenPart != null && imagenPart.getSize() > 0) {
            // Se ha seleccionado un archivo nuevo
            InputStream imagenStream = imagenPart.getInputStream();
            ByteArrayOutputStream imagenBytesStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = imagenStream.read(buffer)) != -1) {
                imagenBytesStream.write(buffer, 0, bytesRead);
            }
            imagenBytes = imagenBytesStream.toByteArray();
            imagenStream.close();
            imagenBytesStream.close();
        } else {
            //conservar la imagen existente en el plato
            PlatoDAO pdao = new PlatoDAO();
            Plato platoExistente = pdao.ListarPorId(idPlato);
            imagenBytes = platoExistente.getImagen();
        }

        Plato p = new Plato();
        p.setId_plato(idPlato);
        p.setId_categoria(idCategoria);
        p.setNombre_plato(nombre);
        p.setPrecio(precio);
        p.setCantidad(cantidad);
        p.setDescripcion(descripcion);
        p.setImagen(imagenBytes);

        PlatoDAO pdao = new PlatoDAO();
        pdao.editarPlato(p);

        String listarPlato = "listar.jsp";
        response.sendRedirect("dashboard_platos.jsp");
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
