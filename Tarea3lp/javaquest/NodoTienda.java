import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class NodoTienda extends Nodo{
    private List<Item> inventario = new ArrayList<>();
    //constructor
    /*
    Constructor de tienda, inicializa la tienda con un numero aleatorio de 0-8 items
    */

    public NodoTienda(){
        Random rand = new Random();
        Integer n_items;
        for (n_items = rand.nextInt(8);n_items>0;n_items--){
            Item aux = new Item();
            this.inventario.add(aux);
        }
    }
    public void comprar(Integer pos, Jugador pj){// added Jugador to fetch Item_aplicados
        if(pj.getDinero()>=inventario.get(pos).getPrecio()){
            inventario.get(pos).aplicar(pj,true);
        }
        else System.out.println("No te lo puedes permitir!");
    }
    /*
    Printea los items en el inventario de la tienda, y anade un i el cual representa la posicion del item para comprar,
    la cual sera igual a la posicion del item en el inventario

    */
    private void showItems(){
        Integer i = 0;
        for (Item item : inventario){
            System.out.println(i+") Precio: "+item.getPrecio()+"| Heal = "+item.getHp()+// i indica el index para comprar el item
                    "| Max HP = "+item.getMaxHp()+
                    "| dmg up = "+item.getDmg()+
                    "| def up = "+item.getDef());
                    i++;

        }
    }
    public void interactuar(Jugador pj){
        System.out.println("Bienvenido a la tienda "+pj.getNombre());
        System.out.println("Items Disponibles: ");
        Scanner input = new Scanner(System.in);
        Integer option = 1;
        do {
            System.out.println("Dinero: "+pj.getDinero());
            showItems();
            System.out.println("Deseas comprar algun Item?");
            System.out.println("1):Si\n2):No");
            option = input.nextInt();
            if(option == 2) break;//1 iteration no exit
            System.out.println("Que Item deseas comprar? ");
            Integer item = input.nextInt();
            if (item.intValue() > inventario.size()){
                while(item > inventario.size()){
                    System.out.println("Ingresa un item valido");
                    item = input.nextInt();
                }
            }

            comprar(item, pj);
        }while(option == 1);
    }

}
