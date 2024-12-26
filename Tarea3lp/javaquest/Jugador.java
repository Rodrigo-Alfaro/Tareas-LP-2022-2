import java.util.ArrayList;
import java.util.List;
public class Jugador extends Personaje {
    private List<Item> items_aplicados = new ArrayList<> ();

    //Constructor
     /*
    Constructor de Jugador, asigna los stats iniciales y nombre al jugador

    @String name: nombre del jugador
    */
    public Jugador(String name){
        this.setNombre(name);
        this.setMoney(500);
        this.setHp_actual(20);
        this.setHp_total(20);
        this.setDanio(5);
        this.setDefensa(1);
    }

    //getters
     /*
     Getter de jugador, en este caso hay solo uno,

    @return retorna el atributo items_aplicados
    */
    protected List<Item> getItems_aplicados(){
        return this.items_aplicados;
    }
    void verEstado(){
        System.out.println("Stats Actuales: ");
        System.out.println("Nombre: " + this.getNombre());
        System.out.println("Dinero: "+ this.getDinero());
        System.out.println("Vida: "+this.getHp()+"/"+this.getHpTotal());
        System.out.println("Danio: "+this.getDanio());
        System.out.println("Defensa: "+this.getDefensa());
    }

    public void verItems(){
        Integer i = 1;
        System.out.println("Items: ");
        for (Item item : items_aplicados){
            System.out.println(i+") Heal = "+item.getHp()+
                    "| Max HP = "+item.getMaxHp()+
                    "| dmg up = "+item.getDmg()+
                    "| def up = "+item.getDef()
                    );
            i++;
        }
    }



}
