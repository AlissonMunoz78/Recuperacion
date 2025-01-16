// Richard Padilla
// Alisson
//

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends Conexion{
    public JButton iniciarButton;
    public JPanel loginsito;
    public JTextField textField1;
    public JPasswordField passwordField1;
    public JComboBox comboBox1;
    public JLabel Usuario;
    public JLabel Contrasenia;
    public JLabel Rol;

    public Login() {
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
    }
}
