//Alisson Muñoz
//Mateo Cardenas
//Richard Padilla

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    // Esta es la conexion a la nube de la base de datos
    String url = "jdbc:mysql://u10rnbplu9w30o4e:rO6gsFKOdP4zWEVECj2s@bi4wakzvsov6sbbmptih-mysql.services.clever-cloud.com:3306/bi4wakzvsov6sbbmptih";
    String user = "u10rnbplu9w30o4e";
    String password = "rO6gsFKOdP4zWEVECj2s";

    public static Connection getConexion() {
        return null;
    }

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
