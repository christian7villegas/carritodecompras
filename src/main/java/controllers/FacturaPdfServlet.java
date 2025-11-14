package controllers;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.DetalleCarro;
import models.ItemCarro;

import java.io.IOException;

@WebServlet("/generar-factura-pdf")
public class FacturaPdfServlet extends HttpServlet {

    /*
     * Metodo doGet:
     * Recibe la petición, obtiene el carro de la sesión y "dibuja" el PDF.
     * Configura la respuesta como 'application/pdf' para forzar la descarga.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1. Obtener la sesión y recuperar el objeto 'carro'
        HttpSession session = req.getSession();
        DetalleCarro detalleCarro = (DetalleCarro) session.getAttribute("carro");

        // 2. Validación: Si el carro no existe o está vacío, redirigimos al usuario para evitar errores
        if (detalleCarro == null || detalleCarro.getItem().isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/ver-carro");
            return;
        }

        // 3. Configurar la respuesta HTTP para que el navegador sepa que es un archivo PDF
        resp.setContentType("application/pdf");
        // 'Content-Disposition' sugiere al navegador que descargue el archivo con este nombre
        resp.setHeader("Content-Disposition", "attachment; filename=factura_compra.pdf");

        try {
            // 4. Inicializar el documento PDF (iText)
            Document documento = new Document();
            // Conectamos el documento con el flujo de salida (OutputStream) del Servlet
            PdfWriter.getInstance(documento, resp.getOutputStream());
            documento.open();

            // 5. Crear y agregar el Título del documento
            Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLUE);
            Paragraph titulo = new Paragraph("Factura de Compra", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            documento.add(new Paragraph(" ")); // Agregamos un espacio vacío

            // 6. Configurar la Tabla de Productos (5 columnas)
            PdfPTable tabla = new PdfPTable(5);
            tabla.setWidthPercentage(100); // La tabla ocupa el ancho completo

            // 6a. Crear los encabezados de la tabla con estilo
            String[] headers = {"ID", "Producto", "Precio", "Cant.", "Subtotal"};
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(header, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla.addCell(cell);
            }

            // 7. Recorrer los ítems del carro y llenar la tabla
            for (ItemCarro item : detalleCarro.getItem()) {
                tabla.addCell(item.getProducto().getIdProducto().toString()); // Columna ID
                tabla.addCell(item.getProducto().getNombre());        // Columna Nombre

                // Formateamos a 2 decimales usando String.format
                tabla.addCell("$" + String.format("%.2f", item.getProducto().getPrecio()));

                tabla.addCell(String.valueOf(item.getCantidad()));    // Columna Cantidad

                // Subtotal por ítem
                tabla.addCell("$" + String.format("%.2f", item.getSubtotal()));
            }

            // Añadimos la tabla llena al documento
            documento.add(tabla);

            // 8. Calcular y mostrar los Totales (Alineados a la derecha)
            Paragraph totales = new Paragraph();
            totales.setAlignment(Element.ALIGN_RIGHT);
            totales.setSpacingBefore(20); // Espacio antes de los totales

            // Agregamos Subtotal, IVA y Total Final formateados
            totales.add(new Chunk("Subtotal: $" + String.format("%.2f", detalleCarro.getSubtotal()) + "\n"));
            totales.add(new Chunk("IVA (15%): $" + String.format("%.2f", detalleCarro.getTotalIva()) + "\n"));

            Font fontTotal = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            totales.add(new Chunk("Total a Pagar: $" + String.format("%.2f", detalleCarro.getTotal()), fontTotal));

            documento.add(totales);

            // 9. Cerrar el documento (Finaliza la generación del PDF)
            documento.close();

        } catch (DocumentException e) {
            // Manejo de errores propios de la librería iText
            throw new IOException(e);
        }
    }
}