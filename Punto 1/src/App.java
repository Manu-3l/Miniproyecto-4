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
    // Se usó el metodo de tablas Hash en este ejercicio usando el nombre como
    // codigo unico o llave, es mas probable que existan 2 o mas personas con la misma nota
    // que 2 o mas personas con el mismmo nombre.
    // Mapa para almacenar los nombres de los estudiantes y sus calificaciones.
    HashMap<String,Float> scores = new HashMap<String,Float>();
    float average = 0;


    Container contenedor;
    JTextField campo1, campo2;
    JButton boton1, boton2;
    ArrayList<String> listStudents = new ArrayList<String>();
    JList <String> lista1;

    public void averageCal(){
        float x = 0;
        for(Entry<String, Float> entry : scores.entrySet()){
            x += entry.getValue();
            
        }
        average = x / scores.size();
    }

    public void adding(String a){
        try{
            if(!scores.containsKey(campo1.getText())){
                float x = Float.parseFloat(a);
                scores.put(campo1.getText(), x);
                JOptionPane.showMessageDialog(contenedor, "Estudiante agregado exitosamente");
            }else{
                JOptionPane.showMessageDialog(contenedor, "Estudiante ya existe");
            }
            
            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(contenedor, "El numero introduccido no es un Float");
                e.printStackTrace();
            }
    }

    public boolean isAverage(String c){
        if(scores.get(c) > average){
            return true;
        }else{
            return false;
        }
    }

    public App(){
        setTitle("Sistema de calificaciones");
        contenedor = getContentPane();
        FlowLayout layout = new FlowLayout();
        contenedor.setLayout(layout);

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
        contenedor.add(boton1);

        boton2 = new JButton("Listar");
        boton2.addActionListener(this);
        contenedor.add(boton2);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,300);
        setVisible(true);       
        

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        averageCal();
        if(e.getSource() == boton1){
            adding(campo2.getText()); 
            
                      
            
        }else if(e.getSource() == boton2){
            for(Entry<String, Float> entry : scores.entrySet()){
                if(isAverage(entry.getKey()) == true){
                    listStudents.add(entry.getKey());                    
                }
                    
            }
            lista1 = new JList<String>(listStudents.toArray(new String[listStudents.size()]));
            JOptionPane.showMessageDialog(contenedor, lista1, "Estudiantes superiores a la media", 1);

        }
    }

    public static void main(String[] args) throws Exception {
        new App();
    }

}
