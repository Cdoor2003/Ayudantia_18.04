import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PantallaRegistro extends JFrame {
    private JPanel panel;

    public PantallaRegistro(){
        setTitle("Registro de usuario");
        setBounds(50, 50, 400, 160);
        setLocationRelativeTo(null);
        setResizable(false);
        iniciarComponentes();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void iniciarComponentes(){
        generarPanel();
        registro();
    }

    public void generarPanel(){
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.white);
        this.getContentPane().add(panel);
    }

    public JLabel crearEtiqueta(String texto, int x, int y, int ancho, int alto,String estiloTexto){
        JLabel etiqueta = new JLabel();
        etiqueta.setText(texto);
        etiqueta.setBounds(x,y,ancho,alto);
        etiqueta.setHorizontalAlignment(SwingConstants.LEFT);
        etiqueta.setForeground(Color.BLACK);
        etiqueta.setFont(new Font(estiloTexto,1,alto));
        return etiqueta;
    }

    public JTextField crearCajaTexto(int x, int y, int ancho, int alto){
        JTextField cajaTexto = new JTextField();
        cajaTexto.setBounds(x,y,ancho,alto);
        return cajaTexto;
    }

    public JButton crearBoton(String texto, int x, int y, int ancho, int alto, String estiloTexto){
        JButton boton = new JButton();
        boton.setText(texto);
        boton.setBounds(x,y,ancho,alto);
        boton.setForeground(Color.black);
        boton.setFont(new Font(estiloTexto,1,(alto)));
        return boton;
    }

    public void registro(){
        JLabel etiqueta1 = crearEtiqueta("Ingrese el nombre de usuario y la contraseña",0,10,400,18,"Calibri");
        etiqueta1.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(etiqueta1);

        JLabel etiqueta2 = crearEtiqueta("Nombre usuario: ",60,40,150,18,"Calibri");
        panel.add(etiqueta2);

        JLabel etiqueta3 = crearEtiqueta("Contraseña: ",60,70,120,18,"Calibri");
        panel.add(etiqueta3);

        JTextField cajaTexto1 = crearCajaTexto(215,40,120,18);
        panel.add(cajaTexto1);

        JPasswordField cajaTextoContraseña = new JPasswordField();
        cajaTextoContraseña.setBounds(215,70,120,18);
        panel.add(cajaTextoContraseña);

        JButton boton1 = crearBoton("Registrar",75,100,120,18,"Calibri");
        panel.add(boton1);

        JButton boton2 = crearBoton("Volver",200,100,120,18,"Calibri");
        panel.add(boton2);

        ActionListener eventoClick1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Usuario> listaUsuarios = GestorDatos.leerArchivoDatos("Usuarios.txt");
                Usuario usuario = buscarUsuario(cajaTexto1.getText(),listaUsuarios);
                String contraseña = String.valueOf(cajaTextoContraseña.getPassword());
                if(usuario != null){
                    JOptionPane.showMessageDialog(null,"El usuario ya esta registrado");
                }else{
                    Usuario usuario1 = new Usuario(cajaTexto1.getText(),contraseña);
                    listaUsuarios.add(usuario1);
                    GestorDatos.registrarDatos(listaUsuarios,"Usuarios.txt");
                    JOptionPane.showMessageDialog(null,"El usuario ha sido registrado");
                    dispose();
                    PantallaLogin pantallaLogin = new PantallaLogin();
                    pantallaLogin.setVisible(true);
                }
            }
        };
        boton1.addActionListener(eventoClick1);

        ActionListener eventoClick2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                PantallaLogin pantallaLogin = new PantallaLogin();
                pantallaLogin.setVisible(true);
            }
        };
        boton2.addActionListener(eventoClick2);
    }

    public Usuario buscarUsuario(String nombre,ArrayList<Usuario> listaUsuarios){
        for(Usuario usuario : listaUsuarios){
            if(usuario.getNombre().equals(nombre)){
                return usuario;
            }
        }
        return null;
    }
}
