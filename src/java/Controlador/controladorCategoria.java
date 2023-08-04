
package Controlador;

import Modelo.Categoria;
import Modelo.CategoriaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author james
 */
@WebServlet(name = "controladorCategoria", urlPatterns = {"/controladorCategoria"})
public class controladorCategoria extends HttpServlet {

    String listar= "dashboard_categoria.jsp";
    String add="agregarCat.jsp";
    String edit="editarCat.jsp";
    Categoria cat = new Categoria();
    CategoriaDAO cdao= new CategoriaDAO();
    int id;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    { response.setContentType("text/html;charset=UTF-8");
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
        String acceso="";
        String action=request.getParameter("accion");
        if (action.equalsIgnoreCase("listar")) {
            acceso=listar;
        }else if(action.equalsIgnoreCase("add")){
            acceso=add;
        } else if(action.equalsIgnoreCase("Agregar")){
            String nombre= request.getParameter("nombre_categoria");
            cat.setNombre_categoria(nombre);
            cdao.agregarCategoria(cat);
            acceso=listar;
        } else if(action.equalsIgnoreCase("editar")){
            request.setAttribute("id", request.getParameter("id"));
            acceso=edit;
        }else if(action.equalsIgnoreCase("Actualizar")){
            id = Integer.parseInt(request.getParameter("id_Categoria"));
            String nombre= request.getParameter("nombre_categoria");
            cat.setId_categoria(id);
            cat.setNombre_categoria(nombre);
            cdao.editarCategoria(cat);
            acceso=listar;
        }else if(action.equalsIgnoreCase("eliminar")){
            id = Integer.parseInt(request.getParameter("id"));
            cat.setId_categoria(id);
            cdao.eliminarCategoria(id);
            acceso=listar;
        }
        
        RequestDispatcher vista=request.getRequestDispatcher(acceso);
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
        processRequest(request, response);
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
