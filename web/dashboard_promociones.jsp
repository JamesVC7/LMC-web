<%-- 
    Document   : dashboard_categoria
    Created on : 22/05/2023, 07:11:52 PM
    Author     : james
--%>

<%@page import="Modelo.Promocion"%>
<%@page import="Modelo.PromocionDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/stylesDash.css">
        <!--Iconos-->
        <script src="https://kit.fontawesome.com/eb496ab1a0.js" crossorigin="anonymous"></script>
        <!--Fuente de letras-->
        <link href="https://fonts.cdnfonts.com/css/redslit" rel="stylesheet">
        <title>Dashboard</title>
    </head>
    <body>
        <!-- Navbar -->
        <nav id="main-navbar" class="navbar navbar-expand-lg navbar-light bg-black fixed-top">

            <div class="container-fluid">

                <button
                    class="navbar-toggler"
                    id="sidebarToggle"
                    type="button"
                    data-mdb-toggle="collapse"
                    data-mdb-target="#sidebarMenu"
                    aria-controls="sidebarMenu"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
                    >
                    <i class=" fas fa-solid fa-bars"></i>
                </button>

                <a class="navbar-brand" href="#">
                    <h1>LA MESA DEL CHEF</h1>
                </a>
                <ul class="navbar-nav ms-auto d-flex flex-row">
                    <div class="divAvatar">
                        <form action="controladorUsuario" method="post" id="formCerrarSesion">
                            <input type="hidden" name="accion" value="logout">
                            <button type="submit" id="botoncerrar">Cerrar Sesión</button>
                        </form>
                        <img
                            src="img/avatar.jpg"
                            class="rounded-circle"
                            height="50px"
                            alt="Avatar"
                            loading="lazy"
                            />
                    </div>
                </ul>
            </div>

        </nav>
        <!-- Sidebar -->
        <div class="sidebar_cont">
            <nav id="sidebarMenu" class="collapse d-lg-block sidebar collapse bg-black fixed-top">
                <div class="position-sticky">
                    <div class="list-group list-group-flush mx-3 mt-4">
                        <a
                            href="dashboard.jsp"
                            class="list-group-item list-group-item-action py-2 ripple "
                            aria-current="true"
                            >
                            <i class="fas fa-tachometer-alt fa-fw me-3"></i><span>Inicio</span>
                        </a>
                        <a href="dashboard_platos.jsp" class="list-group-item list-group-item-action py-2 ripple">
                            <i class="fas fa-utensils fa-fw me-3"></i><span>Platos</span>
                        </a>
                        <a href="controladorCategoria?accion=listar" class="list-group-item list-group-item-action py-2 ripple">
                            <i class="fas fa-th fa-fw me-3"></i><span>Categoria</span>
                        </a>
                        <a href="dashboard_promociones.jsp" class="list-group-item list-group-item-action py-2 ripple active"
                           ><i class="fas fa-sharp fa-solid fa-tags fa-fw me-3"></i><span>Promociones</span></a
                        >
                        <a href="dashboard_clientes.jsp" class="list-group-item list-group-item-action py-2 ripple"
                           ><i class="fas fa-users fa-fw me-3"></i><span>Clientes</span></a
                        >
                        <a href="dashboard_ordenes.jsp" class="list-group-item list-group-item-action py-2 ripple">
                            <i class="fas fa-shopping-cart fa-fw me-3"></i><span>Ordenes</span>
                        </a>
                        <a href="dashboard_comentarios.jsp" class="list-group-item list-group-item-action py-2 ripple">
                            <i class="fas fa-comment fa-fw me-3"></i><span>Comentarios</span>
                        </a>
                    </div>

                </div>
            </nav>
            <div class="divCont">
                <div class="div_contenido">
                    <h1>Promociones</h1>
                    <a href="controladorPromociones?accion=addPromocion" class="botnAgregar">Agregar Promociones</a>

                    <div class="container mt-5">
                        <div class="table-responsive">  
                            <table class="table table-dark table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">ID</th>
                                        <th scope="col">Nombre</th>
                                        <th scope="col">Descripcion</th>
                                        <th scope="col">Precio</th>
                                        <th scope="col">Cantidad</th>
                                        <th scope="col">Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        PromocionDAO prodao = new PromocionDAO();
                                        List<Promocion> list = prodao.listar();
                                        Iterator<Promocion> iter = list.iterator();
                                        Promocion pro = null;
                                        while (iter.hasNext()) {
                                            pro = iter.next();
                                    %>
                                    <tr>
                                        <th scope="row"><%= pro.getIdPromocion()%></th>
                                        <td><%= pro.getNombre_promocion()%></td>
                                        <td><%= pro.getDescripcion()%></td>
                                        <td><%= pro.getPrecio()%></td>
                                        <td><%= pro.getCantidad()%></td>
                                        <td>
                                            <a href="controladorPromociones?accion=editarPromocion&id=<%= pro.getIdPromocion()%>" class="btn btn-primary">
                                                <i class="fas fa-edit"></i>
                                            </a>
                                            <a  href="controladorPromociones?accion=eliminarPromocion&id=<%= pro.getIdPromocion()%>" class="btn btn-danger">
                                                <i class="fas fa-solid fa-trash"></i>
                                            </a>
                                        </td>
                                    </tr>
                                    <% }%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- MDB -->
        <script type="text/javascript" src="js/mdb.min.js"></script>

        <script type="text/javascript" src="script.js"></script>

    </body>
</html>
