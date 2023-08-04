<%-- 
    Document   : index
    Created on : 22/04/2023, 02:45:10 PM
    Author     : james
--%>

<%@page import="java.util.List"%>
<%@page import="Modelo.Plato"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>La Mesa del Chef</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/styles.css"/>
        <!--Iconos-->
        <script src="https://kit.fontawesome.com/eb496ab1a0.js" crossorigin="anonymous"></script>
        <!--Fuente de letras-->
        <link href="https://fonts.cdnfonts.com/css/boba-cups" rel="stylesheet">
        <link href="https://fonts.cdnfonts.com/css/karethtrial" rel="stylesheet">
        <link href="https://fonts.cdnfonts.com/css/redslit" rel="stylesheet">
        <link href="https://fonts.cdnfonts.com/css/agefin-regad-trial" rel="stylesheet">
        <link href="https://fonts.cdnfonts.com/css/qielftan" rel="stylesheet">
        <link href="https://fonts.cdnfonts.com/css/garunda" rel="stylesheet">
        <link href="https://fonts.cdnfonts.com/css/el-glance" rel="stylesheet">
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
        <!-- CARRUSEL-->
        <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel" data-bs-interval="3000">
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
            </div>
            <div class="carousel-inner">
                <div class="carousel-item active" >
                    <img src="img/step1.jpeg" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                        <h1 id='text-slide1'>Degusta algo especial</h1><br>
                        <h3>La comida es el ingrediente que une a la familia y a los amigos.</h3>
                    </div>
                </div>
                <div class="carousel-item">
                    <img src="img/step2.jpg" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                        <h1 id='text-slide2'>Disfrute de la experiencia culinaria</h1><br>
                        <h3>La comida es la materia prima que nutre nuestro cuerpo y alma.</h3>
                    </div>
                </div>
                <div class="carousel-item">
                    <img src="img/step3.jpeg" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                        <h1 id='text-slide3'>Ambiente agradable y satisfactorio para todos</h1><br>
                        <h3>No hay amor más sincero que el amor por la comida.</h3>
                    </div>
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
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
                            <a class="nav-link active" aria-current="page" href="index.jsp">Inicio</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="Nosotros.jsp">Nosotros</a>
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
        <!-- DIV ESLOGAN-->
        <div class="Eslogan">
            <h1>¡Descubre el sabor de la excelencia en cada bocado!</h1>
        </div>
        <h1 id="text">Nuestros Platos Recomendados</h1>
        <!-- CARTAS-->
        <div class="contenedor-cartas">
            <div class="card" style="width: 18rem;">
                <div class="i1">
                    <img src="img/a0.jpg" class="card-img-top" alt="...">
                </div>
                <div class="card-body">
                    <h5 class="card-title">Ceviche</h5>
                    <p class="card-text">El ceviche es un plato fresco, saludable y lleno de sabor, 
                        catalogandose como uno de los mejores en la cocina latinoamericana.</p><br><br>
                    <a href="segundos.jsp?id_categoria=3" class="btn boton fourth">Ver detalles</a>
                </div>
            </div>
            <div class="card" style="width: 18rem;">
                <div class="i1">
                    <img src="img/p2.jpg" class="card-img-top" alt="...">
                </div>
                <div class="card-body">
                    <h5 class="card-title">Pollo a la Brasa</h5>
                    <p class="card-text">Prueba nuestro delicioso pollo a la brasa, preparado con la receta auténtica
                        peruana y sazonado con nuestras especias secretas. Seguro será una experiencia culinaria que 
                        no olvidarás.</p><br>
                    <a href="parrillas.jsp?id_categoria=4" class="btn boton fourth">Ver detalles</a>
                </div>
            </div>
            <div class="card" style="width: 18rem;">
                <div class="i1">
                    <img src="img/a2.jpg" class="card-img-top" alt="...">
                </div>
                <div class="card-body">
                    <h5 class="card-title">Ají de gallina </h5>
                    <p class="card-text">El ají de gallina cuenta con un sabor es ligeramente picante y muy sabroso y con la textura de la salsa es suave y cremosa. ¡Es un plato que definitivamente debes probar!</p><br><br>
                    <a href="segundos.jsp?id_categoria=3" class="btn boton fourth">Ver detalles</a>
                </div>
            </div>
            <div class="card" style="width: 18rem;">
                <div class="i1">
                    <img src="img/a3.jpg" class="card-img-top" alt="...">
                </div>
                <div class="card-body">
                    <h5 class="card-title">Rocoto relleno</h5>
                    <p class="card-text">El rocoto relleno es un poco picante, pero la mezcla de ingredientes suaviza el sabor y crea un equilibrio perfecto. Es una excelente opción para los amantes de la comida picante.</p><br>
                    <a href="segundos.jsp?id_categoria=3" class="btn boton fourth">Ver detalles</a>
                </div>
            </div>
        </div>

        <!-- CONTENEDOR CONOCENOS-->
        <div class='contenedor-conoce'>
            <div class='cont1'>
                <img src="img/cont1.png">
            </div>
            <div class='cont2'>
                <h1>
                    Disfruta en Familia
                </h1>
                <a href="Nosotros.jsp"><button class="boton first">Conócenos</button></a>
            </div>
            <div class='cont3'>
                <img src="img/cont2.png"> 
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