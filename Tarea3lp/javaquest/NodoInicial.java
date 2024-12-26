public class NodoInicial extends Nodo {
    //constructor
    public NodoInicial(){}
    public void interactuar(Jugador pj){// deprecated, reemplazado por una introduccion en el main
        System.out.println("Bienvenido a Luthadel "+pj.getNombre()+"" +
                "");
    }
}
