public class Principal {
    public static void main (String[] args){
        inicializador();
    }

    public static void inicializador(){
        PantallaLogin pantallaLogin = new PantallaLogin();
        pantallaLogin.setVisible(true);
    }
}
