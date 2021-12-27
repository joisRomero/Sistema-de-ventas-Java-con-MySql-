package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import modelo.Usuario;
import modelo.UsuarioDAO;
import vista.frmLogin;
import vista.frmPrincipal;

/**
 *
 * @author joisRomero
 */
public class LoginControlador implements ActionListener, KeyListener {

    private Usuario usuario;
    private UsuarioDAO usuarioDAO;
    private frmLogin vista;

    public LoginControlador(Usuario usuario, UsuarioDAO usuarioDAO, frmLogin vista) {
        this.usuario = usuario;
        this.usuarioDAO = usuarioDAO;
        this.vista = vista;
        this.vista.btnIngresar.addActionListener(this);
        this.vista.txtClave.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnIngresar) {
            login();
        }
    }

    private boolean estasVaciosLosCampos() {
        return (vista.txtUsuario.getText().isEmpty() || 
                String.valueOf(vista.txtClave.getPassword()).isEmpty());
    }

    private void login() {
        if (estasVaciosLosCampos()) {
            JOptionPane.showMessageDialog(vista, "Todos los campos tienen que estar "
                    + "llenos", "", JOptionPane.ERROR_MESSAGE);
        } else {
            String nombreUsuario = vista.txtUsuario.getText();
            String clave = String.valueOf(vista.txtClave.getPassword());
            
            try {
                usuario = usuarioDAO.login(nombreUsuario, clave);
                if (usuario.getNombreUsuario() == null){
                    JOptionPane.showMessageDialog(vista, "Usuario o contraseña "
                            + "incorrectos", "", JOptionPane.ERROR_MESSAGE);
                } else if (usuario.getEstado().equals("Inactivo")) {
                    JOptionPane.showMessageDialog(vista, "Usuario inactivo",
                            "", JOptionPane.ERROR_MESSAGE);
                } else {
                    frmPrincipal vtnPrincipal = new frmPrincipal();
                    vtnPrincipal.setVisible(true);
                    vista.dispose();
                }
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            login();
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

}
