<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Iniciar sesión</title>

    <!-- BOOTSTRAP 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container d-flex justify-content-center align-items-center vh-100">
    <div class="card shadow p-4" style="width: 350px;">

        <h3 class="text-center mb-4">🔐 Iniciar sesión</h3>

        <form action="<%= request.getContextPath() %>/login" method="post">

            <div class="mb-3">
                <label for="username" class="form-label">Usuario</label>
                <input type="text"
                       class="form-control"
                       id="username"
                       name="username"
                       placeholder="Ingrese su usuario"
                       required>
            </div>

            <div class="mb-3">
                <label for="password" class="form-label">Contraseña</label>
                <input type="password"
                       class="form-control"
                       id="password"
                       name="password"
                       placeholder="Ingrese su contraseña"
                       required>
            </div>

            <button type="submit" class="btn btn-primary w-100">
                Entrar
            </button>

        </form>

    </div>
</div>

<!-- BOOTSTRAP JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
