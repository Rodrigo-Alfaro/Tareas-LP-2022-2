public class NodoCombate extends Nodo{
    public Personaje enemigo = new Personaje();

    public void interactuar(Jugador pj){
        pj.combate(enemigo);
        if(pj.getHp()<=0){
            System.out.println("Game over");
            System.exit(0);
        }else {
            System.out.println("Derrotaste a tu oponente!!!");
        }
    }
}
