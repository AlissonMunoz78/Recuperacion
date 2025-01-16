// Richard Padilla
//
//

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {
    public JButton iniciarButton;
    public JPanel loginsito;

    public Login() {
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Esta es la conexion a la nube de la base de datos
                String url = "jdbc:mysql://u10rnbplu9w30o4e:rO6gsFKOdP4zWEVECj2s@bi4wakzvsov6sbbmptih-mysql.services.clever-cloud.com:3306/bi4wakzvsov6sbbmptih";
                String user = "u10rnbplu9w30o4e";
                String password = "rO6gsFKOdP4zWEVECj2s";







                JFrame frame = new JFrame("Alisson");
                frame.setContentPane(new Login().loginsito);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500, 300);
                frame.setPreferredSize(new Dimension(300, 300));
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);

            }
        });
    }
}
