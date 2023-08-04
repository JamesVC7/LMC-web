/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Modelo.Promocion;
import Modelo.PromocionDAO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author james
 */
@MultipartConfig
@WebServlet(name = "controladorPromociones", urlPatterns = {"/controladorPromociones"})
public class controladorPromociones extends HttpServlet {

    String listarPromocion = "dashboard_promociones.jsp";
    String addPromocion = "agregarPro.jsp";
    String editPromocion = "editarPro.jsp";
    Promocion pro = new Promocion();
    PromocionDAO prodao = new PromocionDAO();
    
    int id;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out= response.getWriter();
                try{
            
            String nombre = request.getParameter("nombre_promo");
            System.out.println("Nombre: " + nombre);
            String descripcion = request.getParameter("descripcion_prom");
            float precio = Float.parseFloat(request.getParameter("precio_prom"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad_prom"));
            // Obtener la parte de la imagen
        Part imagenPart = request.getPart("imagen_prom");
        InputStream imagenStream = imagenPart.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = imagenStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }
        byte[] imagenBytes = byteArrayOutputStream.toByteArray();
            if(prodao.agregarProm(nombre, descripcion, precio, cantidad, imagenBytes)){
                request.getRequestDispatcher("dashboard_promociones.jsp").forward(request, response);
            }       
            
        }catch(Exception e){
            out.close();
            e.printStackTrace();
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        PromocionDAO promocionDAO = new PromocionDAO();
        List<Promocion> promociones = promocionDAO.listar();
        
        String acceso = "";
        String action = request.getParameter("accion");
        if (action.equalsIgnoreCase("listar")) {
            acceso = listarPromocion;
        } else if (action.equalsIgnoreCase("addPromocion")) {
            acceso = addPromocion;
            request.getRequestDispatcher(acceso).forward(request, response);
        } else if (action.equalsIgnoreCase("editarPromocion")) {
            request.setAttribute("id", request.getParameter("id"));
            acceso = editPromocion;
            request.getRequestDispatcher(acceso).forward(request, response);
            
        } else if (action.equalsIgnoreCase("eliminarPromocion")) {
            id = Integer.parseInt(request.getParameter("id"));
            pro.setIdPromocion(id);
            prodao.eliminar(id);
            acceso = listarPromocion;
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
        int idPromocion = Integer.parseInt(request.getParameter("id_promo"));
        String nombre = request.getParameter("nombre_promo");
        String descripcion = request.getParameter("descripcion_prom");
        float precio = Float.parseFloat(request.getParameter("precio_prom"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad_prom"));

        // Obtener la imagen enviada desde el formulario
        Part imagenPart = request.getPart("imagen_prom");
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
            // Conservar la imagen existente en la promoción
            Promocion promocionExistente = prodao.ListarPorId(idPromocion);
            imagenBytes = promocionExistente.getImagen();
        }

        pro.setIdPromocion(idPromocion);
        pro.setNombre_promocion(nombre);
        pro.setDescripcion(descripcion);
        pro.setPrecio(precio);
        pro.setCantidad(cantidad);
        pro.setImagen(imagenBytes);

        prodao.editarPromocion(pro);

        response.sendRedirect("dashboard_promociones.jsp");
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
