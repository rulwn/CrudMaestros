/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.CrudMaestro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author raule
 */
public class tbMaestro {

    public String getUUID_Maestro() {
        return UUID_Maestro;
    }

    public void setUUID_Maestro(String UUID_Maestro) {
        this.UUID_Maestro = UUID_Maestro;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int Edad) {
        this.Edad = Edad;
    }

    public double getPeso() {
        return Peso;
    }

    public void setPeso(double Peso) {
        this.Peso = Peso;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    String UUID_Maestro;
    String Nombre;
    int Edad;
    double Peso;
    String correo;
    
    public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addProducto = conexion.prepareStatement("INSERT INTO TBMAESTRO(UUID_MAESTRO, NOMBRE_MAESTRO, EDAD_MAESTRO, PESO_MAESTRO, CORREO_MAESTRO) VALUES (?, ?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            addProducto.setString(1, UUID.randomUUID().toString());
            addProducto.setString(2, getNombre());
            addProducto.setInt(3, getEdad());
            addProducto.setDouble(4, getPeso());
            addProducto.setString(5, getCorreo());
 
            //Ejecutar la consulta
            addProducto.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
    
    public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloMaestro = new DefaultTableModel();
        modeloMaestro.setColumnIdentifiers(new Object[]{"uuid", "Nombre", "Edad", "Peso", "Correo"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbMaestro");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloMaestro.addRow(new Object[]{rs.getString("UUID_MAESTRO"), 
                    rs.getString("NOMBRE_MAESTRO"), 
                    rs.getInt("EDAD_MAESTRO"), 
                    rs.getDouble("PESO_MAESTRO"),
                    rs.getString("CORREO_MAESTRO")
                });
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloMaestro);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
    
    public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
 
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        //borramos 
        try {
            PreparedStatement deleteEstudiante = conexion.prepareStatement("delete from tbMaestro where UUID_MAESTRO = ?");
            deleteEstudiante.setString(1, miId);
            deleteEstudiante.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
    
    public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                //Ejecutamos la Query
                PreparedStatement updateUser = conexion.prepareStatement("update tbMaestro set NOMBRE_MAESTRO = ?, EDAD_MAESTRO = ?, PESO_MAESTRO = ?, CORREO_MAESTRO = ? where UUID_MAESTRO = ?");
                updateUser.setString(1, getNombre());
                updateUser.setInt(2, getEdad());
                updateUser.setDouble(3, getPeso());
                updateUser.setString(4, getCorreo());
                updateUser.setString(5, miUUId);
                updateUser.executeUpdate();
            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no funciona actualizar");
        }
    }
    
    public void cargarDatosTabla(CrudMaestro vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.tbMaestros.getSelectedRow();
        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.tbMaestros.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.tbMaestros.getValueAt(filaSeleccionada, 1).toString();
            String PrecioTB = vista.tbMaestros.getValueAt(filaSeleccionada, 2).toString();
            String CategoriaTB = vista.tbMaestros.getValueAt(filaSeleccionada, 3).toString();
            String Correo = vista.tbMaestros.getValueAt(filaSeleccionada, 4).toString();
            // Establece los valores en los campos de texto
            vista.txtNombre.setText(NombreDeTB);
            vista.txtEdad.setText(PrecioTB);
            vista.txtPeso.setText(CategoriaTB);
            vista.txtCorreo.setText(Correo);
            
        }
    }
    
}
