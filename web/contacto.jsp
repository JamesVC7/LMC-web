<%@page import="java.util.List"%>
<%@page import="Modelo.Plato"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>La Mesa del Chef</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/styles.css"/>
        <link rel="stylesheet" type="text/css" href="css/contacto.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
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
                            <a class="nav-link" aria-current="page" href="Nosotros.jsp">Nosotros</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="carta.jsp">Carta</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="promociones.jsp">Promociones</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="contacto.jsp">Contacto</a>
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
        <div class="container">
            <div class=" text-center mt-5 ">

                <!-- Form Contacto-->
                <h1 >Contactanos</h1>        
            </div>
            <div class="row ">
                <div class="col-lg-7 mx-auto">

                    <div class="card mt-2 mx-auto p-4 bg-light">

                        <div class="card-body bg-light">

                            <div class = "container">
                                <form id="contact-form" role="form" method="post" action="controladorContacto" >
                                    <div class="controls">

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="form_name">Nombre *</label>
                                                    <input id="form_name" type="text" name="Nombre" class="form-control" placeholder="Nombre *" required="required" data-error="El nombre es requerido.">

                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="form_apellido">Apellido *</label>
                                                    <input id="form_apellido" type="text" name="Apellido" class="form-control" placeholder="Ingresa tu apellido *" required="required" data-error="El apellido es requerido.">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="form_email">Email *</label>
                                                    <input id="form_email" type="email" name="email" class="form-control" placeholder="Ingresa tu email *" required="required" data-error="El email es requerido.">

                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="form_email">Telefono *</label>
                                                    <input id="form_email" type="text" name="telefono" class="form-control" placeholder="Ingresa tu telefono *" required="required" data-error="El telefono es requerido.">

                                                </div>
                                            </div>

                                        </div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group">
                                                    <label for="form_message">Mensaje *</label>
                                                    <textarea id="form_mensaje" name="mensaje" class="form-control" placeholder="Mensaje *" rows="4" required="required" data-error="Escribe tu mensaje aqui."></textarea
                                                    >
                                                </div>
                                            </div>
                                            <div class="col-md-12">

                                                <input type="submit" class="btn btn-success btn-send  pt-2 btn-block
                                                       " value="Enviar Mensaje" >            
                                            </div>  
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-7 mx-auto">
                    <div class="card mt-2 mx-auto p-4 bg-light">
                        <div class="map-area"><iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3306.759099389987!2d-77.12635304520043!3d-11.896020100496276!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x9105d3b88e797ef7%3A0xb59d983f5ad6d5d4!2s4V3F%2BC98%2C%20Ventanilla%2007051!5e0!3m2!1ses!2spe!4v1685760720704!5m2!1ses!2spe" width="90%" height="450" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
                        </div> </div>  
                </div>
            </div>
        </div>
        <br>
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

