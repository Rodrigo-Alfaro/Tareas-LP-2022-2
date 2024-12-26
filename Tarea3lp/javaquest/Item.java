
import java.util.Random;

public class Item {
    private Integer precio;
    private Integer recuperar_hp;
    private Integer aumentar_hp_total;
    private Integer aumentar_danio;
    private Integer aumentar_defensa;

    //Constructores
    /*
    Constructor de Item, con 2 variables, una para crear items aleatorios, y otra para
    poner a mano las stats de los items

    @Integer precio: precio del item
    @Integer heal: cantidad de hp que recuperara el item
    @Integer max_hp_up: cantidad aumentar hp total que dara el item
    @Integer damage_buff: cantidad de danio que aumentara el item
    @Integer deff_buff: cantidad de defensa que aumentara el item


    */

    Item(Integer precio, Integer heal, Integer max_hp_up, Integer damage_buff, Integer deff_buff){
        this.precio = precio;
        this.recuperar_hp = heal;
        this.aumentar_hp_total = max_hp_up;
        this.aumentar_danio = damage_buff;
        this.aumentar_defensa = deff_buff;
    }
    Item(){
        Random stats = new Random();
        this.precio = stats.nextInt(350);
        this.recuperar_hp = stats.nextInt(20);
        this.aumentar_hp_total = stats.nextInt(15);
        this.aumentar_danio = stats.nextInt(20);
        this.aumentar_defensa = stats.nextInt(20);
    }
    //getters
    /*
    getters, usados para obtener atributos privados, getAtributo

    @return: atributo deseado
     */
    public Integer getHp(){return this.recuperar_hp;}
    public Integer getMaxHp(){return this.aumentar_hp_total;}
    public Integer getDmg(){return this.aumentar_danio;}
    public Integer getDef(){return this.aumentar_defensa;}
    public Integer getPrecio(){return this.precio;}

    public void aplicar(Jugador pj, Boolean flag){//true shop, else event
        if (flag == true){
            pj.addMoney(this.precio*-1);
        }
        if (this.recuperar_hp+pj.getHp()>=pj.getHpTotal()){// para no helear over the max hp limit
            pj.maxHeal();
        }
        else {
            pj.heal(recuperar_hp);
        }
        pj.moreMaxHp(aumentar_hp_total);
        pj.damageUp(aumentar_danio);
        pj.addDeff(aumentar_defensa);
        pj.getItems_aplicados().add(this);
    }
}

