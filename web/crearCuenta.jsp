
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="css/crear_cuenta.css">
  <title>Registrarse</title>
  <script>
    function validarFormulario(event) {
      event.preventDefault(); 
      
      var nombres = document.getElementById('nombres').value;
      var apellidos = document.getElementById('apellidos').value;
      var numero = document.getElementById('numero').value;
      var fecha = document.getElementById('fecha').value;
      var direccion = document.getElementById('direccion').value;
      var correo = document.getElementById('correo').value;
      var clave = document.getElementById('clave').value;
      
      var warnings = document.getElementById('warnings');
      warnings.innerHTML = ""; 
      
      if (nombres.trim() === "") {
        warnings.innerHTML += "Por favor, ingrese su nombre.<br>";
      }
      
      if (apellidos.trim() === "") {
        warnings.innerHTML += "Por favor, ingrese su apellido.<br>";
      }
      
      if (numero.trim() === "" || isNaN(numero)) {
        warnings.innerHTML += "Por favor, ingrese un número de teléfono válido.<br>";
      }
      
      if (fecha.trim() === "") {
        warnings.innerHTML += "Por favor, ingrese su fecha de nacimiento.<br>";
      }
      
      if (direccion.trim() === "") {
        warnings.innerHTML += "Por favor, ingrese su direccion.<br>";
      }
      
      if (correo.trim() === "") {
        warnings.innerHTML += "Por favor, ingrese su correo electrónico.<br>";
      }
      
      if (clave.trim() === "") {
        warnings.innerHTML += "Por favor, ingrese su contraseña.<br>";
      }
      
      if (warnings.innerHTML === "") {
        document.getElementById('registro').submit();
      }
    }
  </script>
</head>
<body>
  <form action="controladorCliente" method="POST" id="registro" >
    <section class="registro">
        <h4>Crear cuenta</h4><br>
      <input class="controls" type="text" name="nombres" id="nombres" placeholder="Ingrese su Nombre">
      <input class="controls" type="text" name="apellidos" id="apellidos" placeholder="Ingrese su Apellido">
      <input class="controls" type="text" name="numero" id="numero" placeholder="Ingrese su Nro de teléfono o celular">
      <label for="">Fecha de nacimiento:</label> &nbsp;
      <input class="fecha" type="date" name="fecha" id="fecha">
      <input class="controls" type="text" name="direccion" id="direccion" placeholder="Ingrese su Direccion" required>
      <input class="controls" type="email" name="correo" id="correo" placeholder="Ingrese su Correo" required>
      <input class="controls" type="password" name="clave" id="clave" placeholder="Ingrese su Contraseña">
      <input type="hidden" name="accion" value="Registrar">
      <input class="botons" type="submit" name="accion" value="Registrar" onclick="validarFormulario(event)">
      <p><a href="iniciarSesion.jsp" id="regresar">¿Ya tienes cuenta?</a></p>
      <p class="warnings" id="warnings"></p>
    </section>
  </form>
</body>
</html>

