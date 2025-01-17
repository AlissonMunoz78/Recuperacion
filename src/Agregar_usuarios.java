import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Agregar_usuarios  extends JFrame{
    private JButton agregarButton;
    private JTextField textUsuario;
    private JButton regresarButton;
    private JPanel panelAgregarUsuarios;
    private JPasswordField passwordField1;

    public Agregar_usuarios() {
        this.setTitle("Agregar Usuarios");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.add(panelAgregarUsuarios);

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarUsuario();
            }
        });

        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new VentanaUsuario();
            }
        });

        this.setVisible(true);
    }

    private void agregarUsuario() {

        String usuario = textUsuario.getText();
        String passArray = passwordField1.getText();
        String password = new String(passArray);

        if (usuario.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return;
        }

        try (Connection conn = Conexion.getConexion()) {
            if (conn != null) {
                String sql = "SELECT usuario, password FROM usuarios";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, usuario);
                    ps.setString(2, password);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(this, "¡Usuario registrado con éxito!");
                    textUsuario.setText("");
                    passwordField1.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar usuario: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Agregar_usuarios();
    }
}


