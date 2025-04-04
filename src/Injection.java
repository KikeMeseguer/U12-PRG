import java.sql.*;
import java.util.Scanner;

public class Injection {
    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/instituto";
        String user = "root";
        String passwd = "";
        try {
            Connection con = DriverManager.getConnection(url, user, passwd);
            Statement s = con.createStatement();
            String tablaCreate = "CREATE TABLE injection (" +
                    "numero INT PRIMARY KEY)";
            System.out.println("Tablas insertadas: " + s.executeUpdate(tablaCreate));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url, user, passwd);
            Statement s = con.createStatement();
            for (int i = 0; i < 10; i++) {
                String sqlInsert = "INSERT INTO injection (numero) VALUES (" + i + ")";
                System.out.println("Filas insertadas: " + s.executeUpdate(sqlInsert));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        borrarRegistro(url, user, passwd);
        consultarRegistros(url, user, passwd);
    }

    static private void borrarRegistro(String url, String user, String passwd) {
        try {
            Connection con = DriverManager.getConnection(url, user, passwd);
            Statement s = con.createStatement();
            Scanner sc = new Scanner(System.in);
            System.out.print("Introduce el número de registro a borrar: ");
            String registro = sc.nextLine();    // 'xxx' OR '1'=1  (1 OR 1=1)
            String sql = "DELETE FROM injection WHERE numero = " + registro;
            System.out.println("Filas eliminadas: " + s.executeUpdate(sql));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static private void consultarRegistros(String url, String user, String passwd) {
        try {
            Connection con = DriverManager.getConnection(url, user, passwd);
            Statement s = con.createStatement();
            Scanner sc = new Scanner(System.in);
            System.out.print("Introduce el número de registro a buscar: ");
            String num = sc.nextLine();
            String sql = "SELECT * FROM injection WHERE numero = " + num;

            ResultSet rsSelect = s.executeQuery(sql);
            System.out.println("Buscar registro:");
            while (rsSelect.next()) {
                int numero = rsSelect.getInt("numero");
                System.out.println("Número: " + numero);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}