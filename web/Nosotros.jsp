<%-- 
    Document   : Nosotros
    Created on : 2/05/2023, 01:53:21 PM
    Author     : james
--%>

<%@page import="java.util.List"%>
<%@page import="Modelo.Plato"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>La Mesa del Chef</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/styles.css"/>
        <link rel="stylesheet" type="text/css" href="css/nosotros.css"/>
        <!--Iconos-->
        <script src="https://kit.fontawesome.com/eb496ab1a0.js" crossorigin="anonymous"></script>
        <!--Fuente de letras-->
        <link href="https://fonts.cdnfonts.com/css/redslit" rel="stylesheet">
        <link href="https://fonts.cdnfonts.com/css/milkipa" rel="stylesheet">
        <link href="https://fonts.cdnfonts.com/css/gowun-batang" rel="stylesheet">
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
                            <a class="nav-link active" aria-current="page" href="Nosotros.jsp">Nosotros</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link " href="carta.jsp">Carta</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="promociones.jsp">Promociones</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link " href="contacto.jsp">Contacto</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="carrito.jsp">
                                <i class="fas fa-cart-plus"></i>Carrito
                                <% List<Plato> carrito = (List<Plato>) request.getSession().getAttribute("carrito"); %>
                                <% if (carrito != null && !carrito.isEmpty()) {%>
                                <span id="num_car"><%= carrito.size()%></span>
                                <% }%>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- TOP-->
        <div class="topNosotros">
            <div class="contTop">
                <h2>Acerca de La Mesa del Chef</h2>
            </div>
        </div>

        <!-- CONTENIDO-->
        <div class="contDescrip">
            <p>
                Bienvenido al restaurante La Mesa del Chef, donde la experiencia culinaria se combina con 
                la elegancia y el buen gusto.<br><br>

                Nuestro restaurante cuenta con un ambiente acogedor y sofisticado que te transportará a un lugar de
                tranquilidad y bienestar.<br><br>

                Nos enorgullece utilizar ingredientes frescos y de alta calidad en cada uno de nuestros platillos, 
                preparados con técnicas culinarias innovadoras y presentados con una estética exquisita. Desde entradas 
                hasta postres, cada bocado será una experiencia única y deliciosa.<br><br>

                En La mesa del Chef, nuestro objetivo es ofrecerte una experiencia gastronómica memorable y satisfactoria. 
                Por eso, nuestro personal altamente capacitado y atento te brindará un servicio excepcional para garantizar 
                que tu visita sea inolvidable.<br><br>

                ¡Ven y disfruta de una experiencia extraordinaria en nuestro restaurante!
            </p>

            <div class="flip-card">
                <div class="flip-card-inner">
                    <div class="flip-card-front">
                        <!-- Contenido de la cara frontal -->
                        <img src="img/contDesc.jpg">
                    </div>
                    <div class="flip-card-back">
                        <!-- Contenido de la cara trasera -->
                        <img src="img/contDesc2.jpg">
                    </div>
                </div>

            </div>
        </div>

        <!-- FOOTER-->
        <footer class="pie-pagina">
            <div class="grupo-1">
                <div class="box">
                    <figure>
                        <a href="#">
                            <img src="img/logo2.png" class="card-img-top" alt="...">
                        </a>
                    </figure>
                </div>
                <div class="box" id="c-box">
                    <h2>SOBRE NOSOTROS</h2>
                    <p>En La Mesa del Chef, nos apasiona la comida. Creemos que la buena comida es algo 
                        que todos merecemos y estamos comprometidos a proporcionar productos frescos y 
                        de alta calidad a nuestros clientes</p>
                </div>
                <div class="box">
                    <h2>SIGUENOS</h2>
                    <div class="red-social" id="c-box"> 
                        <a href="#" class="fa fa-facebook"></a>
                        <a href="#" class="fa fa-instagram"></a>
                        <a href="#" class="fa fa-twitter"></a>
                        <a href="#" class="fa fa-youtube"></a>
                    </div>
                </div>
            </div>
            <div class="grupo-2">
                <small>&copy; 2023 <b>GRUPO 4</b> - Todos los Derechos Reservados.</small>
            </div>
        </footer>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
    </body>
</html>
