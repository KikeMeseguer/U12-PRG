import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class Alumno {
    private int nia;
    private String nombre;

    public Alumno(int nia, String nombre) {
        this.nia = nia;
        this.nombre = nombre;
    }

    public int getNia() {
        return nia;
    }

    public void setNia(int nia) {
        this.nia = nia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private static Connection conexion() {
        Connection con = null;
        String url = "jdbc:mariadb://localhost:3306/instituto";
        try {
            con = DriverManager.getConnection(url, "root", "");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    public void create() {
        Connection con = conexion();
        String sql = "INSERT INTO Alumnos (nia, nombre) VALUES (?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, nia);
            ps.setString(2, nombre);

            ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Alumno read() {
        Alumno alumno = null;
        Connection con = conexion();
        String sql = "SELECT * FROM Alumnos WHERE NIA = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, nia); // asignamos la clave a buscar
            ResultSet rs = ps.executeQuery();

            if (rs.next()) { // si hay un registro
                String nombre = rs.getString("nombre");
                alumno = new Alumno(nia, nombre);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return alumno;
    }

    public static Alumno read(int nia) {
        Alumno alumno = null;
        Connection con = conexion();
        String sql = "SELECT * FROM alumnos WHERE NIA = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, nia); // asignamos la clave a buscar
            ResultSet rs = ps.executeQuery();

            if (rs.next()) { // si hay un registro
                String nombre = rs.getString("nombre");
                alumno = new Alumno(nia, nombre);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return alumno;
    }

    public void update() {
        Connection con = conexion();
        String sql = "UPDATE Alumnos SET nombre = ? WHERE NIA = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, this.nombre);
            ps.setInt(2, this.nia); // asignamos la clave a buscar

            ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete() {
        Connection con = conexion();
        String sql = "DELETE FROM Alumnos WHERE NIA = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, this.nia); // asignamos la clave a buscar
            ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
