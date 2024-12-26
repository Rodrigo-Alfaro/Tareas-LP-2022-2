public class NodoJefeFinal extends Nodo{
    private Personaje enemigo;
    //constructor
    /*
    Constructor de NodoJefeFinal, crea al objeto con un enemigo, el cual sera seleccionado desde main, ya que es un boss y 
    tendra stats un poco mas elevadas que los enemigos normales
    */
    public NodoJefeFinal(Personaje enemigo){this.enemigo = enemigo;}
    public void interactuar(Jugador pj){
        pj.combate(enemigo);
        if(pj.getHp()<=0){
            System.out.println("Game over");
        }else {
            System.out.println("Venciste a "+enemigo.getNombre());
            System.out.println("Felicitaciones, has completado Luthadel battles, \n" +
                    "pero recuerda, siempre hay otro secreto...");
            System.exit(0);
        }
    }
}
