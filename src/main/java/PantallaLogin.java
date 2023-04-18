import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PantallaLogin extends JFrame{
    private JPanel panel;

    public PantallaLogin(){
        setTitle("Login");
        setBounds(50, 50, 300, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        iniciarComponentes();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void iniciarComponentes(){
        generarPanel();
        login();
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

    public void login(){
        JLabel etiqueta = crearEtiqueta("Usuario: ",20,25,100,20,"Calibri");
        panel.add(etiqueta);

        JLabel etiqueta2 = crearEtiqueta("Contraseña: ",20,70,180,20,"Calibri");
        panel.add(etiqueta2);

        JTextField cajaTexto1 = crearCajaTexto(135,25,120,20);
        panel.add(cajaTexto1);

        JPasswordField cajaTextoContraseña = new JPasswordField();
        cajaTextoContraseña.setBounds(135,70,120,20);
        panel.add(cajaTextoContraseña);

        JButton boton1 = crearBoton("Ingresar",20,120,110,20,"Calibri");
        panel.add(boton1);

        ActionListener eventoClick1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Usuario> listaUsuarios = GestorDatos.leerArchivoDatos("Usuarios.txt");
                int numero = buscarUsuario(cajaTexto1,cajaTextoContraseña);
                if(numero == 1){
                    JOptionPane.showMessageDialog(null,"Hola "+cajaTexto1.getText());
                } else if (numero == 2) {
                    JOptionPane.showMessageDialog(null,"La contraseña es incorrecta, intente nuevamente");
                } else if (numero == 3) {
                    JOptionPane.showMessageDialog(null,"El usuario no esta registrado anteriormente, intente nuevamente");
                }
            }
        };
        boton1.addActionListener(eventoClick1);

        JButton boton2 = crearBoton("Registrar",150,120,110,20,"Calibri");
        panel.add(boton2);

        ActionListener eventoClick2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                PantallaRegistro pantallaRegistro = new PantallaRegistro();
                pantallaRegistro.setVisible(true);
            }
        };
        boton2.addActionListener(eventoClick2);
    }

    public int buscarUsuario(JTextField cajaTexto, JPasswordField cajaTextoContraseña){
        ArrayList<Usuario> listaUsuarios = GestorDatos.leerArchivoDatos("Usuarios.txt");
        String usuario = cajaTexto.getText();
        String contraseña = String.valueOf(cajaTextoContraseña.getPassword());
        for(Usuario usuario1 : listaUsuarios){
            if(usuario1.getNombre().equals(usuario) && usuario1.getContraseña().equals(contraseña)){
                return 1;
            } else if(usuario1.getNombre().equals(usuario) && !usuario1.getContraseña().equals(contraseña)){
                return 2;
            } else if (!usuario1.getNombre().equals(usuario)) {
                return 3;
            }
        }
        return 0;
    }
}
