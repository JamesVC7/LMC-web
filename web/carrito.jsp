<%-- 
    Document   : carrito
    Created on : 17/05/2023, 01:31:22 PM
    Author     : james
--%>

<%@page import="Modelo.Promocion"%>
<%@page import="java.util.Base64"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Plato"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%
    List<Object> carrito = (List<Object>) request.getSession().getAttribute("carrito");
%>
<%
    double total = 0.0;
    if (carrito != null && !carrito.isEmpty()) {
        for (Object item : carrito) {
            double subtotal = 0.0;
            if (item instanceof Plato) {
                Plato plato = (Plato) item;
                subtotal = plato.getPrecio() * plato.getCantidad();
            } else if (item instanceof Promocion) {
                Promocion pro = (Promocion) item;
                subtotal = pro.getPrecio() * pro.getCantidad();
            }
            total += subtotal;
        }
    }
%>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>La Mesa del Chef</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/styles.css"/>
        <link rel="stylesheet" type="text/css" href="css/carrito.css"/>
        <link rel="stylesheet" type="text/css" href="css/alert.css"/>
        <!--Iconos-->
        <script src="https://kit.fontawesome.com/eb496ab1a0.js" crossorigin="anonymous"></script>
        <!--Fuente de letras-->
        <link href="https://fonts.cdnfonts.com/css/redslit" rel="stylesheet">
        <link href="https://fonts.cdnfonts.com/css/spanish-faith" rel="stylesheet">
        <link href="https://fonts.cdnfonts.com/css/rosintha" rel="stylesheet">

    </head>

    <body>
        <div class='titulo'>
            <a href="index.jsp"><img src="img/logo1-blanco.png" class="logo"><h1>LA MESA DEL CHEF</h1></a>
            <div class='contNombre' >
                <%
                    String nombre = (String) session.getAttribute("nombre");
                    if (nombre != null) {
                %>
                <a href="usuario.jsp"><p id="texto">Hola <%= nombre%></p></a>
                <% } %>

                <a href="iniciarSesion.jsp"><button>Iniciar sesión</button></a>
            </div>
        </div>
        <!-- BARRA DE NAVEGACION-->
        <nav class="navbar navbar-expand-sm navbar-dark bg-dark sticky-top">
            <div class="container-fluid">
                <a class="navbar-brand" href="index.jsp">LA MESA DEL CHEF</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav mx-auto">
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="index.jsp">Inicio</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="Nosotros.jsp">Nosotros</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="carta.jsp">Carta</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="promociones.jsp">Promociones</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link " href="contacto.jsp">Contacto</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="carta.jsp"><i class="fas fa-solid fa-plus"></i>Seguir Comprando</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- TABLA-->
        <div class="contenedor-carrito">
            <div class="contenedor-tabla">
                <h1>CARRITO</h1>
                <div class="table-responsive">
                    <table class="table ">
                        <thead class="table-dark">
                            <tr>
                                <th scope="col">Item</th>
                                <th scope="col">Articulo</th>
                                <th scope="col">Nombre</th>
                                <th scope="col">Precio</th>
                                <th scope="col">Cantidad</th>
                                <th scope="col">Total</th>
                                <th scope="col">Acciones</th>
                            </tr>
                        </thead>

                        <tbody>
                            <% if (carrito != null && !carrito.isEmpty()) { %>
                            <% int itemIndex = 1; %>
                            <% double subtotalTotal = 0.0; %>
                            <% for (Object item : carrito) {%>
                            <tr class="fila-carrito <%= "fila-" + itemIndex%>">
                                <th scope="row"><%= itemIndex%></th>
                                    <% if (item instanceof Plato) {
                                            Plato plato = (Plato) item;
                                            double subtotalPlato = plato.getPrecio() * plato.getCantidad();
                                            subtotalTotal += subtotalPlato;
                                    %>
                                <td><img src="data:image/jpeg;base64, <%= Base64.getEncoder().encodeToString(plato.getImagen())%>"
                                         width="100" height="100"></td>
                                <td><%= plato.getNombre_plato()%></td>
                                <td class="precio">S/. <%= plato.getPrecio()%></td>
                                <td>
                                    <div class="controlCantidad">
                                        <form method="POST" action="controladorCar" id="cantidad">
                                            <input type="hidden" name="accion" value="desagregarPlato">
                                            <input type="hidden" name="id_plato" value="<%= plato.getId_plato()%>">
                                            <button type="submit" class="mas"><i class="fas fa-minus"></i></button>
                                        </form>
                                        <input type="number" name="cantidad" value="<%= plato.getCantidad()%>" min="1" max="20"  readonly>
                                        <form action="controladorCar" method="GET">
                                            <input type="hidden" name="accion" value="agregarCarrito">
                                            <input type="hidden" name="id_item" value="<%= plato.getId_plato()%>">
                                            <button type="submit" class="mas"><i class="fas fa-plus"></i></button>
                                        </form>
                                    </div>
                                </td>

                                <td class="subtotal">S/. <%= subtotalPlato%></td>
                                <td>
                                    <form method="POST" action="controladorCar" id="cantidad">
                                        <input type="hidden" name="accion" value="eliminarPlato">
                                        <input type="hidden" name="id_plato" value="<%= plato.getId_plato()%>">
                                        <button type="submit" class="btn btn-danger">Eliminar</button>
                                    </form>
                                </td>
                                <% } else if (item instanceof Promocion) {
                                    Promocion pro = (Promocion) item;
                                    double subtotalPromocion = pro.getPrecio() * pro.getCantidad();
                                    subtotalTotal += subtotalPromocion;
                                %>
                                <td><img src="data:image/jpeg;base64, <%= Base64.getEncoder().encodeToString(pro.getImagen())%>"
                                         width="100" height="100"></td>

                                <td><%= pro.getNombre_promocion()%></td>
                                <td class="precio">S/. <%= pro.getPrecio()%></td>
                                <td>
                                    <div class="controlCantidad">
                                        <form method="POST" action="controladorCar"  id="cantidad">
                                            <input type="hidden" name="accion" value="desagregarPromocion">
                                            <input type="hidden" name="id_promocion" value="<%= pro.getIdPromocion()%>">
                                            <button type="submit" class="mas"><i class="fas fa-minus"></i></button>
                                        </form>
                                        <input type="number" name="cantidad" value="<%= pro.getCantidad()%>" min="1" max="20" readonly>
                                        <form action="controladorCar" method="GET">
                                            <input type="hidden" name="accion" value="agregarPromocion">
                                            <input type="hidden" name="id_item" value="<%= pro.getIdPromocion()%>">
                                            <button type="submit" class="mas"><i class="fas fa-plus"></i></button>
                                        </form>
                                    </div>
                                </td>
                                <td class="subtotal">S/. <%= subtotalPromocion%></td>
                                <td>
                                    <form method="POST" action="controladorCar">
                                        <input type="hidden" name="accion" value="eliminarPromocion">
                                        <input type="hidden" name="id_promocion" value="<%= pro.getIdPromocion()%>">
                                        <button type="submit" class="btn btn-danger">Eliminar</button>
                                    </form>
                                </td>
                                <% } %>
                            </tr>
                            <% itemIndex++; %>
                            <% } %>
                            <% }%>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="contenedor-compra">
                <div>
                    <div class="card">
                        <div class="card-header">
                            <h3>Generar Compra</h3>
                        </div>
                        <div class="card-body">
                            <label>Subtotal:</label>
                            <input type="text" readonly class="form-control" name="subtotal" placeholder="S/. <%= total%>">
                            <label>Descuento:</label>
                            <input type="text" readonly class="form-control" placeholder="S/. 0.0">
                            <label>Total:</label>
                            <input type="text" readonly class="form-control" name="total" placeholder="S/. <%= total%>">
                        </div>
                        <div class="cont_Footer">
                            <%
                                if (nombre != null) {
                            %>

                            <a href="detalleVenta.jsp" class="btn btn-info btn-block" id="pago">Generar Compra</a>
                            <% } else {%> 
                            <a class="btn btn-info btn-block" id="pago" onclick="mostrarAlerta()" disabled>Generar Compra</a>
                            <% }%> 
                        </div>
                    </div>
                </div>
            </div>
        </div>
                        
        <script>
            function mostrarAlerta() {
                // Crea el elemento del fondo oscurecido
                var overlay = document.createElement('div');
                overlay.className = 'alert-overlay';

                // Crea el elemento de la alerta
                var alertBox = document.createElement('div');
                alertBox.className = 'alert-box';

                // Crea el contenido de la alerta
                var mensaje = 'Debes iniciar sesión para generar la compra.';
                var contenido = document.createElement('p');
                contenido.innerHTML = mensaje;

                // Crea el botón "Aceptar"
                var botonAceptar = document.createElement('button');
                botonAceptar.textContent = 'Aceptar';
                botonAceptar.className = 'boton-box';
                botonAceptar.addEventListener('click', function () {
                    // Cierra la alerta al hacer clic en el botón "Aceptar"
                    overlay.remove();
                });

                // Agrega el contenido y el botón a la alerta
                alertBox.appendChild(contenido);
                alertBox.appendChild(botonAceptar);

                // Agrega la alerta al fondo oscurecido
                overlay.appendChild(alertBox);

                // Agrega el fondo oscurecido a la página
                document.body.appendChild(overlay);
            }
        </script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
    </body>
</html>
