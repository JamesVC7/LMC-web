<%-- 
    Document   : dashboard
    Created on : 20/05/2023, 02:37:04 PM
    Author     : james
--%>


<%@page import="Modelo.Contacto"%>
<%@page import="Modelo.ContactoDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="Modelo.Plato"%>
<%@page import="Modelo.PlatoDAO"%>
<%@page import="java.util.Arrays"%>
<%@page import="Modelo.Promocion"%>
<%@page import="Modelo.PromocionDAO"%>
<%@page import="Modelo.DetalleDAO"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Detalle"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--Main Navigation-->
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/stylesDash.css">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <!--Iconos-->
        <script src="https://kit.fontawesome.com/eb496ab1a0.js" crossorigin="anonymous"></script>
        <!--Fuente de letras-->
        <link href="https://fonts.cdnfonts.com/css/redslit" rel="stylesheet">
        <link href="https://fonts.cdnfonts.com/css/bestorika" rel="stylesheet">
        <link href="https://fonts.cdnfonts.com/css/lato" rel="stylesheet">
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
                            class="list-group-item list-group-item-action py-2 ripple active"
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
                        <a href="dashboard_promociones.jsp" class="list-group-item list-group-item-action py-2 ripple"
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
            <div class="divContIni">
                <h1 id="nombreAdmin">Bienvenido <%=session.getAttribute("nombre")%></h1>
                <div class="contentGrafics">
                    <div class="contBar">
                        <p>Cantidad de promociones</p>
                        <div class="container mt-5 chart-container">
                            <canvas id="barrasChart"></canvas>
                        </div>
                    </div>
                    <div class="contPie">
                        <p>Cantidad de platos por categoria</p>
                        <div class="container mt-5 ">
                            <canvas id="pieChart"></canvas>
                        </div>
                    </div>
                </div>
                <%
                    // Obtener los datos de las promociones y cantidades desde el DAO
                    PromocionDAO promocionDAO = new PromocionDAO();
                    List<Promocion> promociones = promocionDAO.listarCantidad();

                    // Arreglos para almacenar los datos del gráfico
                    String[] labels = new String[promociones.size()];
                    int[] data = new int[promociones.size()];

                    // Llenar los arreglos con los datos de las promociones y cantidades
                    for (int i = 0; i < promociones.size(); i++) {
                        Promocion promocion = promociones.get(i);
                        labels[i] = String.valueOf(promocion.getIdPromocion());
                        data[i] = promocion.getCantidad();
                    }
                %>
                <%
                    // Obtener los datos de la cantidad de platos por categoría desde el DAO
                    PlatoDAO platoDAO = new PlatoDAO();
                    List<Integer> cantidadPlatosPorCategoria = platoDAO.obtenerCantidadPlatosPorCategoria();

                    // Arreglo para almacenar los datos del gráfico de pie
                    int[] dataPie = new int[cantidadPlatosPorCategoria.size()];

                    // Llenar el arreglo con los datos de la cantidad de platos por categoría
                    for (int i = 0; i < cantidadPlatosPorCategoria.size(); i++) {
                        dataPie[i] = cantidadPlatosPorCategoria.get(i);
                    }
                %>
                <script>
                    // Datos para el gráfico de barras
                    var dataBarras = {
                        labels: <%= Arrays.toString(labels)%>, // Convertir el arreglo a una cadena de texto
                        datasets: [{
                                label: 'Promociones',
                                data: <%= Arrays.toString(data)%>, // Convertir el arreglo a una cadena de texto
                                backgroundColor: 'rgba(54, 162, 235, 0.6)',
                                borderColor: 'rgba(54, 162, 235, 1)',
                                borderWidth: 1
                            }]
                    };

                    // Opciones del gráfico de barras
                    var optionsBarras = {
                        plugins: {
                            legend: {
                                labels: {
                                    color: 'white'
                                }
                            }
                        },
                        scales: {
                            y: {
                                beginAtZero: true,
                                ticks: {
                                    color: 'white'
                                }
                            },
                            x: {
                                ticks: {
                                    color: 'white'
                                }
                            }
                        }
                    };

                    // Crear el gráfico de barras
                    var barrasChart = new Chart('barrasChart', {
                        type: 'bar',
                        data: dataBarras,
                        options: optionsBarras
                    });


                    // Datos para el gráfico de pie
                    var dataPie = {
                        labels: ['Desayunos', 'Entradas', 'Segundos', 'Parrillas', 'Postres', 'Bebidas'],
                        datasets: [{
                                data: <%= Arrays.toString(dataPie)%>,
                                backgroundColor: ['rgba(255, 99, 132, 0.6)', 'rgba(54, 162, 235, 0.6)', 'rgba(255, 206, 86, 0.6)',
                                    'rgba(75, 192, 192, 0.6)', 'rgba(153, 102, 255, 0.6)', 'rgba(553, 102, 255, 0.6)'],
                                borderColor: ['rgba(255, 99, 132, 1)', 'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)', 'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)'],
                                borderWidth: 1
                            }]
                    };

                    // Opciones del gráfico de pie
                    var optionsPie = {
                        plugins: {
                            legend: {
                                labels: {
                                    color: 'white'
                                }
                            }
                        },
                        responsive: true
                    };

                    // Crear el gráfico de pie
                    var pieChart = new Chart('pieChart', {
                        type: 'pie',
                        data: dataPie,
                        options: optionsPie
                    });


                </script>

                <div class="contentStates">
                    <div class="contTable">
                        <h2>Ultimas 5 Ordenes</h2>
                        <div class="container mt-5">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="ultimos">
                                    <thead>
                                        <tr id="header">
                                            <th>Id</th>
                                            <th>Tipo de Entrega</th>
                                            <th>Fecha</th>
                                            <th>Total</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <%
                                            DetalleDAO detaildao = new DetalleDAO();
                                            List<Detalle> ultimasOrdenes = detaildao.obtenerUltimasCincoOrdenes();
                                            Iterator<Detalle> iter = ultimasOrdenes.iterator();
                                            Detalle orden = null;
                                            while (iter.hasNext()) {
                                                orden = iter.next();
                                        %>
                                        <tr>
                                            <td><%= orden.getId_detalle()%></td>
                                            <td><%= orden.getTipo_entrega()%></td>
                                            <td><%= orden.getFecha()%></td>
                                            <td><%= orden.getTotal()%></td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </tbody>

                                </table>
                            </div>
                        </div>
                    </div>
                                    
                      <div class="Comentar">
                          <p id="Comentarp" >COMENTARIOS</p><br>
                        <%
                            ContactoDAO contactdao = new ContactoDAO();
                            List<Contacto> contact = contactdao.obtenerUltimosDosComentarios();
                            for (Contacto contacto : contact) {%>

                            <div class="contComent">
                            <p><%= contacto.getNombre()%></p>
                            <p><%= contacto.getMensaje()%></p>
                        </div>
                        <% }%>
                    </div>   
                </div>


                <!-- MDB -->

                <script type="text/javascript" src="js/mdb.min.js"></script>

                <script type="text/javascript" src="script.js"></script>

                </body>
                </html>

