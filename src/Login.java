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
        comboBox1.addItem("Administrador");
        comboBox1.addItem("Usuario");

        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String email = textField1.getText();
                String contrasenia = new String(passwordField1.getPassword());
                String modoSeleccionado = (String) comboBox1.getSelectedItem();

                try (Connection conn = conectar()) { // Usamos el método conectar() de la clase Conexion

                    String sql = "SELECT rol FROM Usuarios WHERE nombre = ? AND password = ?";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, email);
                    pstmt.setString(2, contrasenia);

                    ResultSet resultSet = pstmt.executeQuery();

                    if (resultSet.next()) {
                        String rol = resultSet.getString("rol");

                        if (rol.equals(modoSeleccionado)) {
                            JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso! Bienvenido " + email);

                            // Abrir la ventana dependiendo del modo de registro
                            if (rol.equals("Administrador")) {
                                JFrame frame = new JFrame("Admin");
                                frame.setContentPane(new Login().loginsito);
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.setSize(500, 300);
                                frame.setPreferredSize(new Dimension(300, 300));
                                frame.setLocationRelativeTo(null);
                                frame.pack();
                                frame.setVisible(true);

                            } else if (rol.equals("Usuario")) {
                                JFrame frame = new JFrame("User");
                                frame.setContentPane(new Login().loginsito);
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.setSize(500, 300);
                                frame.setPreferredSize(new Dimension(300, 300));
                                frame.setLocationRelativeTo(null);
                                frame.pack();
                                frame.setVisible(true);
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
