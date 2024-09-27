/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.tbMaestro;
import Vista.CrudMaestro;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author raule
 */
public class ctrlMaestro implements MouseListener {

    private CrudMaestro vista;
    private tbMaestro modelo;

    public ctrlMaestro(CrudMaestro vista, tbMaestro Modelo) {
        this.vista = vista;
        this.modelo = Modelo;
        vista.tbMaestros.addMouseListener(this);
        Modelo.Mostrar(vista.tbMaestros);
        vista.btnLimpiar.addMouseListener(this);
        vista.btnBorrar.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.btnAgregar.addMouseListener(this);
        

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getSource()== vista.btnAgregar){
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setEdad(Integer.parseInt(vista.txtEdad.getText()));
            modelo.setPeso(Double.parseDouble(vista.txtPeso.getText()));
            modelo.setCorreo(vista.txtCorreo.getText());
            modelo.Guardar();
            modelo.Mostrar(vista.tbMaestros);
        }
        
        if(e.getSource()== vista.btnBorrar){
            modelo.Eliminar(vista.tbMaestros);
            modelo.Mostrar(vista.tbMaestros);
        }
        
        if(e.getSource()== vista.tbMaestros){
            modelo.cargarDatosTabla(vista);
            
        }
        
        if(e.getSource()== vista.btnActualizar){
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setEdad(Integer.parseInt(vista.txtEdad.getText()));
            modelo.setPeso(Double.parseDouble(vista.txtPeso.getText()));
            modelo.setCorreo(vista.txtCorreo.getText());
            modelo.Actualizar(vista.tbMaestros);
            modelo.Mostrar(vista.tbMaestros);
        }
        
        if(e.getSource()== vista.btnLimpiar){
            vista.txtNombre.setText("");
            vista.txtEdad.setText("");
            vista.txtPeso.setText("");
            vista.txtCorreo.setText("");
            
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
