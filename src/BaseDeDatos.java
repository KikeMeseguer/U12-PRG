import java.sql.*;

public class BaseDeDatos {
    public static void main(String[] args) {
        // Driver: jdbc
        // Protocolo: mariadb
        // Conexión: localhost:3306/instituto
        String url = "jdbc:mariadb://localhost:3306/instituto";
        String user = "root";
        String passwd = "";

        try {
            // Establece una conexión con la base de datos
            Connection con = DriverManager.getConnection(url, user, passwd);
            // Objeto que envia consultas SQL a la base de datos
            Statement s = con.createStatement();

            // INSTRUCCIONES PARA TABLAS
            // Añadir tabla
            String tablaCreate = "CREATE TABLE alumnos (" +
                    "NIA INT PRIMARY KEY, " +
                    "nombre VARCHAR(50) NOT NULL," +
                    "curso VARCHAR(5) NOT NULL)";
            // Modificar tabla
            String tablaAlter1 = "ALTER TABLE alumnos ADD fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP";
            String tablaAlter2 = "ALTER TABLE alumnos DROP COLUMN curso";
            // Eliminar tabla
            String tablaDrop = "DROP TABLE alumnos";

            // Ejecuta la instrucciones
//            System.out.println("Tablas insertadas: " + s.executeUpdate(tablaCreate));
//            System.out.println("Tablas modificadas: " + s.executeUpdate(tablaAlter2));
//            System.out.println("Tablas eliminadas: " + s.executeUpdate(tablaDrop));


            // INSTRUCCIONES PARA REGISTRO
            // INSERT
            String sqlInsert = "INSERT INTO alumnos (NIA, nombre) VALUES (11111, 'Carlos')";
            // UPDATE
            String sqlUpdate = "UPDATE alumnos SET nombre = 'Ana' WHERE NIA = 11111";
            // DELETE
            String sqlDelete = "DELETE FROM alumnos WHERE NIA = 11111";

            // Ejecuta la instrucciones
//            System.out.println("Filas insertadas: " + s.executeUpdate(sqlInsert));
//                System.out.println("Filas modificadas: " + s.executeUpdate(sqlUpdate));
//            System.out.println("Filas eliminadas: " + s.executeUpdate(sqlDelete));

            // INSTRUCCIONES PARA CONSULTA
            // SELECT
            String sqlSelect = "SELECT * FROM alumnos WHERE nombre = 'Ana'";
            //
            String sqlNumRegistros = "SELECT COUNT(*) AS total_registros FROM alumnos";
            // SHOW
            String[] sqlShow = {"SHOW DATABASES", "SHOW TABLES"}; // SHOW TABLES / SHOW DATABASES
            // DESCRIBE
            String sqlDescribe = "DESCRIBE alumnos";
            // EXPLAIN
            String sqlExplain = "EXPLAIN SELECT * FROM alumnos";



            ResultSet rsSelect = s.executeQuery(sqlSelect);
            System.out.println("Buscar registro:");
            while (rsSelect.next()) {
                int NIA = rsSelect.getInt("NIA");
                String nombre = rsSelect.getString("nombre");
                System.out.println("NIA: " + NIA + ", Nombre: " + nombre);
            }

            ResultSet rsNumRegistros = s.executeQuery(sqlNumRegistros);
            System.out.println("Número de registros:");
            if (rsNumRegistros.next()) {
                System.out.println(rsNumRegistros.getInt("total_registros"));
            }

            for (int i = 0; i < 2; i++) {
                ResultSet rsShow = s.executeQuery(sqlShow[i]);
                String[] titulo = {"Base de datos:", "Tablas en la base de datos:"};
                System.out.println(titulo[i]);
                while (rsShow.next()) {
                    // Cada fila contiene el nombre de una tabla
                    String tableName = rsShow.getString(1); // La columna 1 tiene el nombre de la tabla
                    System.out.println("- " + tableName);
                }
            }

            ResultSet rsDescribe = s.executeQuery(sqlDescribe);
            System.out.println("Campos en la tabla:");
            while (rsDescribe.next()) {
                String field = rsDescribe.getString("Field");  // Nombre del campo
                String type = rsDescribe.getString("Type");    // Tipo de dato
                String nullValue = rsDescribe.getString("Null"); // Si acepta valores NULL
                String key = rsDescribe.getString("Key");      // Clave primaria o índice
                String defaultValue = rsDescribe.getString("Default"); // Valor por defecto
                String extra = rsDescribe.getString("Extra");  // Información adicional (como AUTO_INCREMENT)

                System.out.println("- Campo: " + field + ", Tipo: " + type +
                        ", NULL: " + nullValue + ", Clave: " + key +
                        ", Por defecto: " + defaultValue + ", Extra: " + extra);
            }

            ResultSet rsExplain = s.executeQuery(sqlExplain);
            System.out.println("Plan de ejecución:");
            while (rsExplain.next()) {
                String id = rsExplain.getString("id");
                String selectType = rsExplain.getString("select_type");
                String table = rsExplain.getString("table");
                String type = rsExplain.getString("type");
                String possibleKeys = rsExplain.getString("possible_keys");
                String key = rsExplain.getString("key");
                String keyLength = rsExplain.getString("key_len");
                String ref = rsExplain.getString("ref");
                String rows = rsExplain.getString("rows");
                String extra = rsExplain.getString("Extra");

                System.out.printf("ID: %s, Tipo: %s, Tabla: %s, Tipo de acceso: %s, "
                                + "Posibles claves: %s, Clave utilizada: %s, Longitud de clave: %s, "
                                + "Referencia: %s, Filas estimadas: %s, Extra: %s%n",
                        id, selectType, table, type, possibleKeys, key, keyLength, ref, rows, extra);
            }


            // Finalizar la conexión con la base de datos
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}