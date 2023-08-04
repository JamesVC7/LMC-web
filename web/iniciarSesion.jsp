
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="css/iniciar_sesion.css">
  <title>Iniciar sesión</title>
</head>
<body>
  <section class="registro">
    <div class="cont-login"> 
      <h4>Iniciar sesión</h4>
      <form action="controladorUsuario" method="POST" class="formIniciar">
        <input class="controls" type="email" name="correo" id="correo" placeholder="Ingrese su Correo" required>
        <input class="controls" type="password" name="clave" id="contraseña" placeholder="Ingrese su Contraseña" required>
        <input class="botons" type="submit" value="Ingresar">
      </form>
      <p><a href="crearCuenta.jsp">Crear nueva cuenta</a></p>
    </div>
  </section>
</body>
</html>

