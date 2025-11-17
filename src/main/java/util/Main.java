package util;

import util.ConexionBDD;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conexion = null;
        try {
            conexion = ConexionBDD.getConnection(); // intentamos conectar
            if (conexion != null && !conexion.isClosed()) {
                System.out.println("¡Conexión exitosa a la base de datos!");
            } else {
                System.out.println("No se pudo establecer la conexión.");
            }
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        } finally {
            try {
                if (conexion != null && !conexion.isClosed()) {
                    conexion.close(); // cerrar la conexión
                }
            } catch (Exception ex) {
                System.out.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }
}
