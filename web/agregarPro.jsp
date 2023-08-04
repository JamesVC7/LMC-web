<%-- 
    Document   : agregar
    Created on : 23/05/2023, 03:25:05 PM
    Author     : james
--%>

<%@page import="Modelo.Promocion"%>
<%@page import="java.util.List"%>
<%@page import="Controlador.controladorPromociones" %>
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
                            class="list-group-item list-group-item-action py-2 ripple"
                            aria-current="true"
                            >
                            <i class="fas fa-tachometer-alt fa-fw me-3"></i><span>Inicio</span>
                        </a>
                        <a href="dashboard_platos.jsp" class="list-group-item list-group-item-action py-2 ripple">
                            <i class="fas fa-utensils fa-fw me-3"></i><span>Platos</span>
                        </a>
                        <a href="controladorCategoria?accion=listar" class="list-group-item list-group-item-action py-2 ripple ">
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

            <script>
                function valida(form) {
                    if (document.formulario.nombre_promo.value === "") {
                        alert("Falta introducir el nombre");
                    } else {
                        if (document.formulario.descripcion_prom.value === "") {
                            alert("Falta introducir la descripcion");
                        } else {
                            if (document.formulario.precio_prom.value === "") {
                                alert("Falta introducir el precio");
                            } else {
                                if (document.formulario.cantidad_prom.value === "") {
                                    alert("Falta introducir la cantidad");
                                } else {
                                    if (document.formulario.imagen_prom.value === "") {
                                        alert("Falta introducir la imagen");
                                    } else {
                                        form.submit();
                                    }
                                }
                            }
                        }
                    }
                }
            </script>
            <div class="div_contenido">
                <h1>Agregar Promociones</h1><br>
                <form name="formulario" class="form_agregar" action="controladorPromociones" method="POST" enctype="multipart/form-data">
                    <label>Nombre</label>
                    <input type="text" name="nombre_promo">
                    <label>Descripcion</label>
                    <textarea type="text" name="descripcion_prom"></textarea>
                    <label>Precio</label>
                    <input type="text" name="precio_prom">
                    <label>Cantidad</label>
                    <input type="text" name="cantidad_prom">                    
                    <label>Imagen</label>
                    <input type="file" name="imagen_prom">
                    <input type="button"  value="Agregar" onClick="valida(this.form)">
                    <a href="dashboard_promociones.jsp" id="return">Regresar</a>
                </form>
            </div>
            <!-- MDB -->
            <script type="text/javascript" src="js/mdb.min.js"></script>
            <script type="text/javascript" src="script.js"></script>
    </body>
</html>
