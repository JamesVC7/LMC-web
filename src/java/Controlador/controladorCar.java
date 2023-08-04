package Controlador;

import Modelo.Plato;
import Modelo.PlatoDAO;
import Modelo.Promocion;
import Modelo.PromocionDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "controladorCar", urlPatterns = {"/controladorCar"})
public class controladorCar extends HttpServlet {

    PlatoDAO pdao = new PlatoDAO();
    PromocionDAO prodao = new PromocionDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion != null) {
            if (accion.equals("agregarCarrito") || accion.equals("agregarPromocion")) {
                int idItem = Integer.parseInt(request.getParameter("id_item"));
                boolean isPlato = accion.equals("agregarCarrito");
                agregarAlCarrito(request, idItem, isPlato);
            } else if (accion.equals("eliminarPlato")) {
                int idPlato = Integer.parseInt(request.getParameter("id_plato"));
                eliminarDelCarrito(request, idPlato);
            } else if (accion.equals("eliminarPromocion")) {
                int idPromocion = Integer.parseInt(request.getParameter("id_promocion"));
                eliminarPromocionDelCarrito(request, idPromocion);
            } else if (accion.equals("desagregarPlato")) {
                int idPlato = Integer.parseInt(request.getParameter("id_plato"));
                desagregarDelCarrito(request, idPlato);
            } else if (accion.equals("desagregarPromocion")) {
                int idPromocion = Integer.parseInt(request.getParameter("id_promocion"));
                desagregarPromocionDelCarrito(request, idPromocion);
            } else if (accion.equals("confirmarCompra")) {
                confirmarCompra(request, response);
                return;
            }
        }

        List<Object> carrito = obtenerCarrito(request);
        request.setAttribute("carrito", carrito);
        request.getRequestDispatcher("carrito.jsp").forward(request, response);
    }

    private void agregarAlCarrito(HttpServletRequest request, int idItem, boolean isPlato) {
        Object item = null;
        if (isPlato) {
            item = pdao.obtenerPorId(idItem);
        } else {
            item = prodao.obtenerPorId(idItem);
        }

        if (item != null) {
            HttpSession session = request.getSession(true);
            List<Object> carrito = (List<Object>) session.getAttribute("carrito");
            if (carrito == null) {
                carrito = new ArrayList<>();
            }
            if (!carrito.contains(item)) {
                setCantidad(item, 1);
                carrito.add(item);
            } else {
                for (Object obj : carrito) {
                    if (obj.equals(item)) {
                        setCantidad(obj, getCantidad(obj) + 1);
                        break;
                    }
                }
            }
            session.setAttribute("carrito", carrito);
        }
    }

    private int getCantidad(Object item) {
        int cantidad = 0;
        if (item instanceof Plato) {
            cantidad = ((Plato) item).getCantidad();
        } else if (item instanceof Promocion) {
            cantidad = ((Promocion) item).getCantidad();
        }
        return cantidad;
    }

    private void setCantidad(Object item, int cantidad) {
        if (item instanceof Plato) {
            ((Plato) item).setCantidad(cantidad);
        } else if (item instanceof Promocion) {
            ((Promocion) item).setCantidad(cantidad);
        }
    }

    private List<Object> obtenerCarrito(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        List<Object> carrito = (List<Object>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
        }
        return carrito;
    }

    private void eliminarDelCarrito(HttpServletRequest request, int idPlato) {
        HttpSession session = request.getSession();
        List<Object> carrito = (List<Object>) session.getAttribute("carrito");
        if (carrito != null) {
            // Iterador para eliminar elementos de forma segura 
            Iterator<Object> iterator = carrito.iterator();
            while (iterator.hasNext()) {
                Object item = iterator.next();
                if (item instanceof Plato && ((Plato) item).getId_plato() == idPlato) {
                    iterator.remove();
                    break;
                }
            }
            // Actualizar el carrito
            session.setAttribute("carrito", carrito);
        }
    }

    private void eliminarPromocionDelCarrito(HttpServletRequest request, int idPromocion) {
        HttpSession session = request.getSession();
        List<Object> carrito = (List<Object>) session.getAttribute("carrito");
        if (carrito != null) {
            Iterator<Object> iterator = carrito.iterator();
            while (iterator.hasNext()) {
                Object item = iterator.next();
                if (item instanceof Promocion && ((Promocion) item).getIdPromocion() == idPromocion) {
                    iterator.remove();
                    break;
                }
            }
            session.setAttribute("carrito", carrito);
        }
    }

    private void desagregarDelCarrito(HttpServletRequest request, int idPlato) {
        HttpSession session = request.getSession();
        List<Object> carrito = (List<Object>) session.getAttribute("carrito");
        if (carrito != null) {
            for (Object item : carrito) {
                if (item instanceof Plato && ((Plato) item).getId_plato() == idPlato) {
                    int cantidad = getCantidad(item);
                    if (cantidad > 1) {
                        setCantidad(item, cantidad - 1);
                    } else {
                        carrito.remove(item);
                    }
                    break;
                }
            }
            session.setAttribute("carrito", carrito);
        }
    }

    private void desagregarPromocionDelCarrito(HttpServletRequest request, int idPromocion) {
        HttpSession session = request.getSession();
        List<Object> carrito = (List<Object>) session.getAttribute("carrito");
        if (carrito != null) {
            for (Object item : carrito) {
                if (item instanceof Promocion && ((Promocion) item).getIdPromocion() == idPromocion) {
                    int cantidad = getCantidad(item);
                    if (cantidad > 1) {
                        setCantidad(item, cantidad - 1);
                    } else {
                        carrito.remove(item);
                    }
                    break;
                }
            }
            session.setAttribute("carrito", carrito);
        }
    }

    private void confirmarCompra(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        List<Object> carrito = (List<Object>) session.getAttribute("carrito");
        if (carrito != null && !carrito.isEmpty()) {
            for (Object item : carrito) {
                if (item instanceof Plato) {
                    Plato plato = (Plato) item;
                    int cantidadComprada = plato.getCantidad();
                    pdao.actualizarCantidad(plato.getId_plato(), cantidadComprada);
                    
                } else if (item instanceof Promocion) {
                    Promocion promocion = (Promocion) item;
                    int cantidadComprada = promocion.getCantidad();
                    prodao.actualizarCantidad(promocion.getIdPromocion(), cantidadComprada);
                }
            }
            session.removeAttribute("carrito");
        }

        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
