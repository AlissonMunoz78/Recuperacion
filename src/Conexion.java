
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    // Esta es la conexion a la nube de la base de datos
    String url = "jdbc:mysql://u10rnbplu9w30o4e:rO6gsFKOdP4zWEVECj2s@bi4wakzvsov6sbbmptih-mysql.services.clever-cloud.com:3306/bi4wakzvsov6sbbmptih";
    String user = "u10rnbplu9w30o4e";
    String password = "rO6gsFKOdP4zWEVECj2s";

    public void conectar() {
        try (Connection conexionMysql = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conectado a la base de datos");
        } catch (Exception e) {
            System.out.println("Error de conexion: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
