import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Agregar_Admin extends Conexion {
    public JTextField textFieldNombre;
    public JTextField textFieldDescripcion;
    public JTextField textFieldPrecio;
    public JTextField textFieldStock;
    public JButton agregarButton;
    public JButton regresarButton;
    public JButton regresarButton2;
    public JPanel agregarsito;
    public JTabbedPane tabbedPane1;
    public JButton agregarUSERbutton2;
    public JPasswordField passwordFieldUsuario;
    public JTextField textFieldUsuario;
    public JComboBox<String> comboBox1;

    public Agregar_Admin() {

        comboBox1.addItem("administrador");
        comboBox1.addItem("usuario");

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conn = conectar()) {
                    // Validar que el stock sea entre 10 y 350
                    try {
                        int stock = Integer.parseInt(textFieldStock.getText());
                        if (stock < 10 || stock > 350) {
                            JOptionPane.showMessageDialog(null, "El stock debe ser entre un mínimo de 10 y máximo de 350.");
                            return;
                        }

                        // Validar que el precio sea positivo
                        double precio = Double.parseDouble(textFieldPrecio.getText());
                        if (precio <= 0) {
                            JOptionPane.showMessageDialog(null, "El precio debe ser un número positivo.");
                            return;
                        }

                        String sql = "INSERT INTO Productos (nombre, descripcion, precio, stock) VALUES (?, ?, ?, ?)";
                        PreparedStatement statement = conn.prepareStatement(sql);
                        statement.setString(1, textFieldNombre.getText());
                        statement.setString(2, textFieldDescripcion.getText());
                        statement.setDouble(3, precio);
                        statement.setInt(4, stock);

                        statement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "¡Producto agregado correctamente!");

                        // Limpiar campos
                        textFieldNombre.setText("");
                        textFieldDescripcion.setText("");
                        textFieldPrecio.setText("");
                        textFieldStock.setText("");

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Stock y precio deben ser números válidos.");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error en la base de datos.");
                    ex.printStackTrace();
                }
            }
        });

        // Lógica para agregar usuarios
        agregarUSERbutton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conn = conectar()) {
                    // Validar campos vacíos
                    String nombreUsuario = textFieldUsuario.getText();
                    String password = new String(passwordFieldUsuario.getPassword());
                    String rol = (String) comboBox1.getSelectedItem();

                    if (nombreUsuario.isEmpty() || password.isEmpty() || rol == null) {
                        JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos.");
                        return;
                    }

                    String sql = "INSERT INTO Usuarios (nombre, password, rol) VALUES (?, ?, ?)";
                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setString(1, nombreUsuario);
                    statement.setString(2, password); // En producción, hashear esta contraseña
                    statement.setString(3, rol);

                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "¡Usuario agregado correctamente!");

                    textFieldUsuario.setText("");
                    passwordFieldUsuario.setText("");
                    comboBox1.setSelectedIndex(0);

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error en la base de datos.");
                    ex.printStackTrace();
                }
            }
        });

        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Menu Administrador");
                frame.setContentPane(new menu_admin().menusito);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500, 300);
                frame.setPreferredSize(new Dimension(300, 300));
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);

                JFrame agregarAdmin = (JFrame) SwingUtilities.getWindowAncestor(agregarsito);
                agregarAdmin.dispose();
            }
        });

        regresarButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Menu Administrador");
                frame.setContentPane(new menu_admin().menusito);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500, 300);
                frame.setPreferredSize(new Dimension(300, 300));
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);

                JFrame agregarAdmin = (JFrame) SwingUtilities.getWindowAncestor(agregarsito);
                agregarAdmin.dispose();
            }
        });
    }
}
