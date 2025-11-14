<%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 13/11/2025
  Time: 8:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="models.*" %>
<% DetalleCarro detalleCarro = (DetalleCarro) session.getAttribute("carro"); %>
<html>
<head>
    <title>Carro de Compras</title>

    <!-- BOOTSTRAP 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body class="bg-light">

<div class="container py-5">
    <h1 class="mb-4 text-center">🛒 Carro de Compras</h1>

    <% if (detalleCarro == null || detalleCarro.getItem().isEmpty()) { %>

    <div class="alert alert-info text-center">
        Tu carro de compras está vacío.
    </div>

    <div class="text-center mt-3">
        <a href="<%= request.getContextPath() %>/productos.html" class="btn btn-primary">
            Ir a Comprar
        </a>
    </div>

    <% } else { %>

    <div class="table-responsive shadow-sm rounded">
        <table class="table table-striped table-bordered align-middle">
            <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Precio</th>
                <th>Cantidad</th>
                <th>Subtotal</th>
            </tr>
            </thead>
            <tbody>
            <% for (ItemCarro item : detalleCarro.getItem()) { %>
            <tr>
                <td><%= item.getProducto().getIdProducto() %></td>
                <td><%= item.getProducto().getNombre() %></td>
                <td>$<%= String.format("%.2f", item.getProducto().getPrecio()) %></td>
                <td><%= item.getCantidad() %></td>
                <td>$<%= String.format("%.2f", item.getSubtotal()) %></td>
            </tr>
            <% } %>

            <tr class="fw-bold">
                <td colspan="4" class="text-end">Subtotal:</td>
                <td>$<%= String.format("%.2f", detalleCarro.getSubtotal()) %></td>
            </tr>
            <tr class="fw-bold">
                <td colspan="4" class="text-end">IVA 15%:</td>
                <td>$<%= String.format("%.2f", detalleCarro.getTotalIva()) %></td>
            </tr>
            <tr class="fw-bold table-success">
                <td colspan="4" class="text-end">Total:</td>
                <td>$<%= String.format("%.2f", detalleCarro.getTotal()) %></td>
            </tr>

            </tbody>
        </table>
    </div>

    <div class="d-flex justify-content-center gap-3 mt-4">
        <a href="<%= request.getContextPath() %>/productos.html" class="btn btn-primary">
            Seguir Comprando
        </a>
        <a href="<%= request.getContextPath()%>/generar-factura-pdf" target="_blank" class="btn btn-danger">
            📄 Descargar Factura PDF
        </a>
        <a href="<%= request.getContextPath() %>/index.html" class="btn btn-secondary">
            Volver
        </a>
    </div>

    <% } %>
</div>

<!-- BOOTSTRAP JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
