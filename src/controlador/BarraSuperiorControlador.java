package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vista.dlgUsuarios;
import vista.frmLogin;
import vista.frmPrincipal;

/**
 *
 * @author joisRomero
 */

public class BarraSuperiorControlador implements ActionListener{

    frmPrincipal vista;

    public BarraSuperiorControlador(frmPrincipal vista) {
        this.vista = vista;
        this.vista.itemUsuarios.addActionListener(this);
        this.vista.itemCerrarSesion.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.itemUsuarios){
            dlgUsuarios vistaDlgUsuarios = new dlgUsuarios(vista, true);
            vistaDlgUsuarios.setVisible(true);
        }
        if (e.getSource() == vista.itemCerrarSesion){
            int resp = JOptionPane.showConfirmDialog(null, "¿Desea cerrar la sesión?", "", JOptionPane.YES_NO_OPTION);
            if (resp == 0){
                vista.dispose();
                frmLogin vistaLogin = new frmLogin();
                vistaLogin.setVisible(true);
            }
        }
    }
    
}
