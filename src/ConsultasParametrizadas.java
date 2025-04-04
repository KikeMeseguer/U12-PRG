import java.sql.*;
import java.util.Scanner;

public class ConsultasParametrizadas {
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
    }

    static private void borrarRegistro(String url, String user, String passwd) {
        try {
            Connection con = DriverManager.getConnection(url, user, passwd);
            Statement s = con.createStatement();
            Scanner sc = new Scanner(System.in);
            System.out.print("Introduce el número de registro a borrar: ");
            String registro = sc.nextLine();    // 'xxx' OR '1'=1  (1 OR 1=1)
            ////////////////////////////////////////////////////////////////////////
            String sql = "DELETE FROM injection WHERE numero = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(registro));
            System.out.println("Filas eliminadas: " + ps.executeUpdate());
            ////////////////////////////////////////////////////////////////////////
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}