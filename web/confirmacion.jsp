<%-- 
    Document   : confirmacion
    Created on : 15/07/2023, 07:24:19 PM
    Author     : james
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/styles.css"/>
        <link rel="stylesheet" type="text/css" href="css/detalleV.css"/>
        <!--Iconos-->
        <script src="https://kit.fontawesome.com/eb496ab1a0.js" crossorigin="anonymous"></script>
        <!--Fuente de letras-->
        <link href="https://fonts.cdnfonts.com/css/redslit" rel="stylesheet">
        <link href="https://fonts.cdnfonts.com/css/milkipa" rel="stylesheet">
        <link href="https://fonts.cdnfonts.com/css/gowun-batang" rel="stylesheet">
        <link href="https://fonts.cdnfonts.com/css/madeilon-duskille" rel="stylesheet">
        <link href="https://fonts.cdnfonts.com/css/queens-display-pro" rel="stylesheet">
        <link href="https://fonts.cdnfonts.com/css/oktah-neue" rel="stylesheet">
    </head>
    <body>
        <div class='titulo'>
            <a href="index.jsp"><img src="img/logo1-blanco.png" class="logo"><h1>LA MESA DEL CHEF</h1></a>
            <div class='contNombre' >
                <p>¿Necesitas ayuda? Llámanos al 203-7064</p>
            </div>
        </div>
        <div class="contentConfirm">
            <div class="contConfirm" >
                <div class="contCheck">
                    <div class="bubble">
                       <form id="form-agregar-pedido" action="controladorPedido" method="post">
                        <input type="hidden" name="accion" value="agregarPedido"> 
                        <button class="agregar" type="submit" id="agregarPedido" name="submit">
                            <i class="fas fa-check"></i></button>
                    </form> 
                        
                    </div>
                </div>
                <div class="contCompra">
                    <h1>Compra exitosa</h1><br>
                    <h3>En unos minutos llegara su pedido</h3>
                </div>
                <div class="contReturn">
                    <a href="controladorCar?accion=confirmarCompra" >Regresar</a>
                    
                </div>
            </div>
        </div>

    </body>
</html>
