import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menu_admin extends Conexion{
    public JButton agregarProductosButton;
    public JButton visulizarProductosButton;
    public JPanel menusito;

    public menu_admin() {
        visulizarProductosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Visualizar Administrador");
                frame.setContentPane(new Login().loginsito);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500, 300);
                frame.setPreferredSize(new Dimension(300, 300));
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);

                JFrame menuAdmin = (JFrame) SwingUtilities.getWindowAncestor(menusito);
                menuAdmin.dispose();
            }
        });

        agregarProductosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Agregar Productos");
                frame.setContentPane(new Agregar_Admin().agregarsito);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500, 300);
                frame.setPreferredSize(new Dimension(300, 300));
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);

                JFrame menuAdmin = (JFrame) SwingUtilities.getWindowAncestor(menusito);
                menuAdmin.dispose();
            }
        });
    }
}
