/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.tbMaestro;
import Vista.CrudMaestro;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

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

        // KeyListener para el campo Nombre: solo letras y longitud máxima de 100
        vista.txtNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (vista.txtNombre.getText().length() >= 100 || !Character.isLetter(e.getKeyChar()) && e.getKeyChar() != ' ') {
                    e.consume();  // Evita más entradas si supera 100 caracteres o no es letra
                }
            }
        });

        // KeyListener para el campo Edad: solo números y longitud máxima de 3
        vista.txtEdad.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (vista.txtEdad.getText().length() >= 3 || !Character.isDigit(e.getKeyChar())) {
                    e.consume();  // Solo permite 3 dígitos
                }
            }
        });

        // KeyListener para el campo Peso: solo números y un punto, longitud máxima de 5
        vista.txtPeso.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String texto = vista.txtPeso.getText();
                if (texto.length() >= 5 || (!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != '.')) {
                    e.consume();  // Evita más entradas si no es número o punto, o si ya excede 5 caracteres
                }
                if (e.getKeyChar() == '.' && texto.contains(".")) {
                    e.consume();  // Evita más de un punto
                }
            }
        });

        // KeyListener para el campo Correo: validación de formato básico en el botón
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.btnAgregar) {
            // Validar que los campos no estén vacíos
            if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || 
                vista.txtPeso.getText().isEmpty() || vista.txtCorreo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Todos los campos deben estar llenos", "Error", JOptionPane.ERROR_MESSAGE);
                return;  // No se continúa si hay campos vacíos
            }

            // Validar formato de correo
            if (!vista.txtCorreo.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                JOptionPane.showMessageDialog(vista, "El correo no tiene un formato válido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Captura de posibles errores en la conversión de los valores
            try {
                modelo.setNombre(vista.txtNombre.getText());
                modelo.setEdad(Integer.parseInt(vista.txtEdad.getText()));
                modelo.setPeso(Double.parseDouble(vista.txtPeso.getText()));
                modelo.setCorreo(vista.txtCorreo.getText());
                modelo.Guardar();
                modelo.Mostrar(vista.tbMaestros);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "Error al convertir los valores numéricos", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Ocurrió un error al guardar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == vista.btnBorrar) {
            try {
                modelo.Eliminar(vista.tbMaestros);
                modelo.Mostrar(vista.tbMaestros);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Ocurrió un error al borrar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == vista.tbMaestros) {
            modelo.cargarDatosTabla(vista);
        }

        if (e.getSource() == vista.btnActualizar) {
            if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || 
                vista.txtPeso.getText().isEmpty() || vista.txtCorreo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Todos los campos deben estar llenos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!vista.txtCorreo.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                JOptionPane.showMessageDialog(vista, "El correo no tiene un formato válido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                modelo.setNombre(vista.txtNombre.getText());
                modelo.setEdad(Integer.parseInt(vista.txtEdad.getText()));
                modelo.setPeso(Double.parseDouble(vista.txtPeso.getText()));
                modelo.setCorreo(vista.txtCorreo.getText());
                modelo.Actualizar(vista.tbMaestros);
                modelo.Mostrar(vista.tbMaestros);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "Error al convertir los valores numéricos", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Ocurrió un error al actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == vista.btnLimpiar) {
            vista.txtNombre.setText("");
            vista.txtEdad.setText("");
            vista.txtPeso.setText("");
            vista.txtCorreo.setText("");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
}
