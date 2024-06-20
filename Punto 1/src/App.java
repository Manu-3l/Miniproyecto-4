import java.util.*;
import java.util.Map.Entry;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame implements ActionListener{

    // Mapa para almacenar los nombres de los estudiantes y sus calificaciones.
    HashMap<String,Float> scores = new HashMap<String,Float>();
    float average = 0;

    // Componentes de la interfaz gráfica.
    Container contenedor;
    JTextField campo1, campo2;
    JButton boton1, boton2;
    ArrayList<String> listStudents = new ArrayList<String>();
 
    // Método para calcular el promedio de las calificaciones.
    public void averageCal(){
        float x = 0;
        // Itera sobre las entradas del mapa y suma las calificaciones.
        for(Entry<String, Float> entry : scores.entrySet()){
            x += entry.getValue();
            
        }
        // Calcula el promedio dividiendo la suma total por el número de entradas.
        average = x / scores.size();
    }

    // Método para agregar un estudiante y su calificación al mapa.
    public void adding(String a){
        try{
            // Verifica si el estudiante ya existe en el mapa.
            if(!scores.containsKey(campo1.getText())){
                float x = Float.parseFloat(a); // Convierte la calificación a float.
                scores.put(campo1.getText(), x); // Añade el estudiante y su calificación al mapa.
                JOptionPane.showMessageDialog(contenedor, "Estudiante agregado exitosamente");
            }else{
                JOptionPane.showMessageDialog(contenedor, "Estudiante ya existe");
            }
            
            }catch (NumberFormatException e) {
                // Manejo de excepción si la calificación no es un número válido.
                JOptionPane.showMessageDialog(contenedor, "El numero introduccido no es un Float");
                e.printStackTrace();
            }
    }

    // Método para verificar si la calificación de un estudiante está por encima del promedio.
    public boolean isAverage(String c){
        if(scores.get(c) > average){
            return true;
        }else{
            return false;
        }
    }

    // Constructor de la clase App.
    public App(){
        setTitle("Sistema de calificaciones"); // Establece el título de la ventana.
        contenedor = getContentPane(); // Obtiene el contenedor principal.
        FlowLayout layout = new FlowLayout(FlowLayout.LEADING); // Crea un layout de flujo.
        contenedor.setLayout(layout); // Establece el layout al contenedor.
        setTitle("Sistema de calificaciones");
        contenedor = getContentPane();
        FlowLayout layout = new FlowLayout(FlowLayout.LEADING);
        contenedor.setLayout(layout);

        // Añade los componentes de la interfaz gráfica al contenedor.
        JLabel label = new JLabel("Nombre del estudiante: ");
        contenedor.add(label);

        campo1 = new JTextField(10);
        contenedor.add(campo1);

        JLabel label2 = new JLabel("Calificación del estudiante: ");
        contenedor.add(label2);

        campo2 = new JTextField(10);
        contenedor.add(campo2);

        boton1 = new JButton("Agregar");
        boton1.addActionListener(this);
        contenedor.add(boton1, FlowLayout.RIGHT);

        boton2 = new JButton("Listar");
        boton2.addActionListener(this);
        contenedor.add(boton2);
        
        // Configura la operación de cierre y el tamaño de la ventana.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,300);
        setVisible(true);       
        

    }

    // Método que se ejecuta cuando ocurre una acción (clic en un botón).
    @Override
    public void actionPerformed(ActionEvent e) {
        JList <String> lista1;
        averageCal(); // Calcula el promedio de las calificaciones.
        
        // Si se presiona el botón de agregar.
        averageCal();
        if(e.getSource() == boton1){
            adding(campo2.getText()); 
            
        }else if(e.getSource() == boton2){
            // Si se presiona el botón de listar, agrega a la lista los estudiantes que están por encima del promedio.
            for(Entry<String, Float> entry : scores.entrySet()){
                if(isAverage(entry.getKey()) == true){
                    listStudents.add(entry.getKey());                    
                }
                    
            }
            // Muestra una lista de estudiantes con calificaciones superiores al promedio.
            lista1 = new JList<String>(listStudents.toArray(new String[listStudents.size()]));
            JOptionPane.showMessageDialog(contenedor, lista1, "Estudiantes superiores a la media", 1);

        }
    }

    // Método principal para ejecutar la aplicación.
    public static void main(String[] args) throws Exception {
        App app = new App();
    }

}
