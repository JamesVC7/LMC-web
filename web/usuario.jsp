<%-- 
    Document   : usuario
    Created on : 19/07/2023, 06:56:41 PM
    Author     : james
--%>

<%@page import="Modelo.Detalle"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>La Mesa del Chef</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/styles.css"/>
        <link rel="stylesheet" type="text/css" href="css/usuario.css"/>
        <!--Iconos-->
        <script src="https://kit.fontawesome.com/eb496ab1a0.js" crossorigin="anonymous"></script>
        <!--Fuente de letras-->
        <link href="https://fonts.cdnfonts.com/css/redslit" rel="stylesheet">
        <link href="https://fonts.cdnfonts.com/css/milkipa" rel="stylesheet">   
        <link href="https://fonts.cdnfonts.com/css/madeilon-duskille" rel="stylesheet">
        <link href="https://fonts.cdnfonts.com/css/retrocycles-2" rel="stylesheet">
        <link href="https://fonts.cdnfonts.com/css/lato" rel="stylesheet">
    </head>
    <body>
        <div class='titulo'>
            <a href="index.jsp"><img src="img/logo1-blanco.png" class="logo"><h1>LA MESA DEL CHEF</h1></a>
            <div class='contNombre' >
                <p>¿Necesitas ayuda? Llámanos al 203-7064</p>
            </div>
        </div>
        <h1 id="hello">Bienvenido <%=session.getAttribute("nombre")%></h1>

        <div class="ContenedorD">
            <div class="contentDatos">
                <h3>Datos Personales</h3></div>
            <div class="contData">
                <div class="divData">
                    <p>Nombre</p>
                    <p id="dato"><%=session.getAttribute("nombre")%></p>
                </div>
                <div class="divData">
                    <p>Apellidos</p>
                    <p id="dato"><%=session.getAttribute("apellido")%></p>
                </div>
                <div class="divData">
                    <p>Direccion</p>
                    <p id="dato"><%=session.getAttribute("direccion")%></p>
                </div>
                <div class="divData">
                    <p>Telefono</p>
                    <p id="dato"><%=session.getAttribute("celular")%></p>
                </div>
                <div class="divData">
                    <p>Correo</p>
                    <p id="dato"><%=session.getAttribute("correo")%></p>
                </div>
            </div>
        </div>
        <diV class="contTabla">
            <div class="head">
            <h2>Mis ordenes</h2> 
            <form action="controladorUsuario" method="post">
                <input type="hidden" name="accion" value="actualizarOrdenes">
                <button type="submit" id="refresh"><i class="fas fa-sync-alt"></i></button>
            </form></div><br>
            <table class="table table-striped">
                <thead id="header">
                    <tr>
                        <th scope="col">ID de Orden</th>
                        <th scope="col">Fecha</th>
                        <th scope="col">Total</th>
                    </tr>
                </thead>
                <tbody>
                    <% List<Detalle> ordenesUsuario = (List<Detalle>) session.getAttribute("ordenesUsuario"); %>
                    <% if (ordenesUsuario != null && !ordenesUsuario.isEmpty()) { %>
                    <% for (Detalle orden : ordenesUsuario) {%>
                    <tr>
                        <td><%= orden.getId_detalle()%></td>
                        <td><%= orden.getFecha()%></td>
                        <td><%= orden.getTotal()%></td>
                    </tr>
                    <% } %>
                    <% } else { %>
                    <tr>
                        <td colspan="3">No hay órdenes disponibles para este usuario.</td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </diV>
    </body>
</html>
