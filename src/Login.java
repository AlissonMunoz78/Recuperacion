//Alisson Muñoz
//Mateo Cardenas
//Richard Padilla

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends Conexion {
    public JButton iniciarButton;
    public JPanel loginsito;
    public JTextField textField1;
    public JPasswordField passwordField1;
    public JComboBox comboBox1;
    public JLabel Usuario;
    public JLabel Contrasenia;
    public JLabel Rol;

    public Login() {

        // Opciones del JComboBox para el modo de registro
        comboBox1.addItem("administrador");
        comboBox1.addItem("usuario");


        // Acción que se ejecuta cuando se hace clic en el botón "iniciarButton"
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Obtener los valores ingresados en los campos de texto y contraseña
                String email = textField1.getText();
                String contrasenia = new String(passwordField1.getPassword());
                String modoSeleccionado = (String) comboBox1.getSelectedItem();

                try (Connection conn = conectar()) { // Usamos el método conectar() de la clase Conexion

                    // Consulta SQL para verificar si el usuario y contraseña coinciden con el rol seleccionado
                    String sql = "SELECT rol FROM Usuarios WHERE nombre = ? AND password = ? AND rol = ?";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, email);
                    pstmt.setString(2, contrasenia);
                    pstmt.setString(3, modoSeleccionado);

                    ResultSet resultSet = pstmt.executeQuery();

                    if (resultSet.next()) {
                        String rol = resultSet.getString("rol");

                        if (rol.equals(modoSeleccionado)) {
                            JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso! Bienvenido " + email);

                            // Abrir la ventana dependiendo del modo de registro
                            if (rol.equals("administrador")) {
                                JFrame frame = new JFrame("Admin");
                                frame.setContentPane(new menu_admin().menusito);
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.setSize(500, 300);
                                frame.setPreferredSize(new Dimension(300, 300));
                                frame.setLocationRelativeTo(null);
                                frame.pack();
                                frame.setVisible(true);

                                // Cerrar la ventana de login
                                JFrame loguinFrame = (JFrame) SwingUtilities.getWindowAncestor(loginsito);
                                loguinFrame.dispose();



                            } else if (rol.equals("usuario")) {
                                JFrame frame = new JFrame("User");
                                frame.setContentPane(new VentanaUsuario().panleusuario);
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.setSize(500, 300);
                                frame.setPreferredSize(new Dimension(900, 400));
                                frame.setLocationRelativeTo(null);
                                frame.pack();
                                frame.setVisible(true);

                                JFrame loguinFrame = (JFrame) SwingUtilities.getWindowAncestor(loginsito);
                                loguinFrame.dispose();
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "El modo seleccionado no coincide con el registrado.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario no encontrado o contraseña incorrecta");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error en la base de datos.");
                }
            }
        });
    }
}
