<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="Modelo.Promocion"%>
<%@page import="Modelo.Plato"%>
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
        <link rel="stylesheet" type="text/css" href="css/detalleV.css"/>
        <!--Iconos-->
        <script src="https://kit.fontawesome.com/eb496ab1a0.js" crossorigin="anonymous"></script>
        <!--Fuente de letras-->
        <link href="https://fonts.cdnfonts.com/css/redslit" rel="stylesheet">
        <link href="https://fonts.cdnfonts.com/css/milkipa" rel="stylesheet">
        <link href="https://fonts.cdnfonts.com/css/gowun-batang" rel="stylesheet">
        <link href="https://fonts.cdnfonts.com/css/madeilon-duskille" rel="stylesheet">

    </head>
    <%
        // Obtener la fecha actual
        Date fechaActual = new Date();
        // Formatear la fecha en el formato deseado
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormateada = formatoFecha.format(fechaActual);

        List<Object> carrito = (List<Object>) request.getSession().getAttribute("carrito");

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

    <body>
        <div class='titulo'>
            <a href="index.jsp"><img src="img/logo1-blanco.png" class="logo"><h1>LA MESA DEL CHEF</h1></a>
            <div class='contNombre' >
                <p>¿Necesitas ayuda? Llámanos al 203-7064</p>
            </div>
        </div>

        <div class="direccion">
            Dirección: <%= session.getAttribute("direccion")%>
        </div>
        <div class="contCarta">
            <div class="cartaDetalle">
                <h3>Detalles de la compra</h3><br>
                <div class="contDetail"><p id="detalleTitulo">ID Cliente: </p><%= session.getAttribute("id_clienter")%></div>
                <div class="contDetail"><p id="detalleTitulo">Cliente: </p><%= session.getAttribute("nombre")%> </div>
                <div class="contDetail"><p id="detalleTitulo">Platos:</p></div>
                <% if (carrito != null && !carrito.isEmpty()) { %>
                <% for (Object item : carrito) { %>
                <% if (item instanceof Plato) {
                        Plato plato = (Plato) item;
                %>
                - <%= plato.getNombre_plato()%> (<%= plato.getCantidad()%>)<br>

                <% } else if (item instanceof Promocion) {
                    Promocion pro = (Promocion) item;
                %>
                - <%= pro.getNombre_promocion()%> (<%= pro.getCantidad()%>)<br>
                <% } %>
                <% } %>
                <% }%><br>

                <div class="contDetail"><p id="detalleTitulo">Fecha: </p><%= fechaFormateada%></div> 
                <div class="contDetail"><p id="detalleTitulo">Total: </p>S/ <%= total%></div> 
            </div>
            <form id="miFormulario" action="controladorDetalle" method="post">
                <h3>Pago con Tarjeta de Credito</h3>
                <label for="nombre">Nombre en la tarjeta:</label>
                <select id="nombre" >
                    <option value="">Selecciona una opción</option>
                    <option value="Visa">Visa</option>
                    <option value="MasterCard">MasterCard</option>
                    <option value="American Express">American Express</option>
                    <option value="Discover">Discover</option>
                    <option value="Diners Club">Diners Club</option>
                    <option value="JCB">JCB</option>
                    <option value="UnionPay">UnionPay</option>
                    <option value="Mir">Mir</option>
                </select>
                <div class="error-message" id="nombre-error"></div>
                <label for="numero">Número de tarjeta:</label>
                <input type="text" id="numero"  pattern="[0-9]{16}">
                <div class="error-message" id="numero-error"></div>
                <label for="vencimiento">Fecha de vencimiento:</label>
                <input type="date" id="vencimiento"  pattern="(0[1-9]|1[0-2])\/[0-9]{2}">
                <div class="error-message" id="vencimiento-error"></div>
                <label for="cvv">CVV:</label>
                <input type="text" id="cvv"  pattern="[0-9]{3}">
                <div class="error-message" id="cvv-error"></div>
                <input type="submit" value="Pagar" >

            </form>

        </div>
        <div class="contBotones">
            <a href='carrito.jsp' id="volver">Volver</a>
            <form id="form-confirmar-compra" action="controladorDetalle" method="post">
                <input type="hidden" name="id_cliente" value="<%= session.getAttribute("id_clienter")%>">
                <input type="hidden" name="tipo_de_entrega" value="delivery">
                <input type="hidden" name="total_compra" value="<%= total%>">
                <button type="submit" id="confirm" disabled>Confirmar Compra</button>
            </form>
        </div>
        <script>
            const formulario = document.getElementById('miFormulario');
            const nombreError = document.getElementById('nombre-error');
            const numeroError = document.getElementById('numero-error');
            const vencimientoError = document.getElementById('vencimiento-error');
            const cvvError = document.getElementById('cvv-error');

            formulario.addEventListener('submit', function (event) {
                event.preventDefault();

                const nombre = document.getElementById('nombre').value;
                const numero = document.getElementById('numero').value;
                const cvv = document.getElementById('cvv').value;

                nombreError.textContent = '';
                numeroError.textContent = '';
                vencimientoError.textContent = '';
                cvvError.textContent = '';

                if (nombre === '') {
                    nombreError.textContent = 'Por favor, selecciona una opción';
                }

                if (!numero.match(/^\d{16}$/)) {
                    numeroError.textContent = 'El número de tarjeta debe tener 16 dígitos';
                }

                if (!cvv.match(/^\d{3}$/)) {
                    cvvError.textContent = 'El CVV debe tener 3 dígitos';
                }

                if (nombre !== '' && numero.match(/^\d{16}$/) && cvv.match(/^\d{3}$/)) {
                    document.getElementById('confirm').removeAttribute('disabled');
                } else {
                    // Deshabilitar el botón "Confirmar Compra"
                    document.getElementById('confirm').setAttribute('disabled', 'disabled');
                }
            });
        </script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
    </body>
</html>
