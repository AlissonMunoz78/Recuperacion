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
    public JPanel agregarsito;
    public JTabbedPane tabbedPane1;

    public Agregar_Admin() {
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try (Connection conn = conectar()) {

                    // Validar que el stock sea entre 10 y 350
                    try {
                        int stock = Integer.parseInt(textFieldStock.getText());
                        if (stock < 10 || stock > 350) {
                            JOptionPane.showMessageDialog(null, "El stock debe ser entre un mínimo de 10 y máximo de 350");
                            return;
                        }

                        // Validar que el precio sea positivo
                        double precio = Double.parseDouble(textFieldPrecio.getText());
                        if (precio <= 0) {
                            JOptionPane.showMessageDialog(null, "El precio debe ser un numero positivo.");
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

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Stock y precio deben ser números válidos.");
                    }

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
    }
}
