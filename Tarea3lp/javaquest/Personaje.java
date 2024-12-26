import java.util.Random;

public class Personaje {
    private String nombre;
    private Integer dinero ;
    private Integer hp_actual;
    private Integer hp_total;
    private Integer danio;
    private Integer defensa;

    //constructores--------------
    /*
    Constructores de Personaje, inicializan un personaje de distintas maneras, solo con nombre, con nombre y stats
    predifinidas, y sin nombre y con stats random
    */

    public Personaje(String name){
        nombre = name;
    }
    public Personaje(String name, Integer hp, Integer dmg, Integer def){
        this.nombre = name;
        this.hp_actual = hp;
        this.hp_total = hp;
        this.danio = dmg;
        this.defensa = def;
        this.dinero = 500;
    }
    public Personaje(){// for enemys combate
        Random stats = new Random();
        this.hp_total = stats.nextInt(15);
        this.maxHeal();
        this.danio = stats.nextInt(10);
        this.defensa = stats.nextInt(7);
        this.dinero = -1;
    }
    //getters
    /*
    Getters, usados para obtener atributos privados, su nombre indica el atributo a retornar, getAtributo

    @return atributo: retornaran el atributo que se desea obtener
     */
    public String getNombre(){return nombre;}
    public Integer getDinero(){return dinero;}
    public Integer getHp(){return hp_actual;}
    public Integer getDanio(){return danio;}
    public Integer getDefensa(){return defensa;}
    public Integer getHpTotal(){return hp_total;}
    //setters
    /*
    Setters, ocupados para settear atributos a una cantidad deseada, su nombre indica el atributo a modificar, setAtributo

    @param atributo: cantidad a settear el atributo
     */
    protected void setMoney(Integer money){ this.dinero = money;}
    protected void setHp_actual(Integer hp){this.hp_actual = hp;}
    protected void setHp_total(Integer hp){this.hp_total = hp;}
    protected void setDanio(Integer danio){this.danio = danio;}
    protected void setDefensa(Integer deff) {this.defensa = deff;}

    protected void setNombre(String name){ nombre = name;}
    /*
    Anade una cantidad determinada de dinero

    @Integer price: dinero a anadir
     */
    protected void addMoney(Integer price){
        dinero+= price;
    }
    /*
    suma una cantidad determinada a la defensa

    @Integer change: cantidad a sumar a la defensa
     */
    protected void addDeff(Integer change){
        defensa+= change;
    }
    /*
    Cura al personaje, es decir aumenta su hp_actual, puede ir por sobre la hp_total y causar errores

    @Integer heal: cantidad a curar

     */
    protected void heal(Integer heal){
        hp_actual+=heal;
    }
    /*
    Aumenta la hp_total

    @Integer hp: cantidad a aumentar
     */
    protected void moreMaxHp(int hp){
    hp_total+= hp;
    }
    /*
    Aumenta el danio

    @Integer up: cantidad a aumentar el danio
     */
    protected void damageUp(Integer up){
        danio+= up;
    }
    /*
    recibe una cantidad determinada de danio

    @Integer damage: danio a recibir
     */
    protected void receiveDamage(Integer damage){
        hp_actual -= Math.abs(damage-this.defensa);
    }
    /*
    cura por completo al personaje, es decir iguala la hp_actual a la hp_total

     */
    protected void maxHeal(){
        this.hp_actual = this.hp_total;
    }
    /*
    Printea los prompts del dano recibido y causado en un combate

    @Personaje enemy: rival del combate
    @boolean flag: true printea prompts para personaje, y false para el enemigo
     */
    private void printDanio(Personaje enemy, boolean flag){ // flag true shows prints personaje atks
        if (flag){
            int damage = this.danio-enemy.getDefensa();
            System.out.println("Tu oponente recibe "+damage+" puntos de danio");
            System.out.println(this.nombre+": "+this.hp_actual+"/"+this.hp_total);
            System.out.println("Oponente: "+enemy.hp_actual+"/"+enemy.hp_total);
        }else{
            int damage = enemy.getDanio()-this.defensa;
            System.out.println(this.nombre+" recibe "+damage+" puntos de danio");
            System.out.println("Oponente: "+enemy.hp_actual+"/"+enemy.hp_total);
            System.out.println(this.nombre+": "+this.hp_actual+"/"+this.hp_total);
        }

    }

    public void combate (Personaje enemy) {
        int coinflip = (int)Math.round(Math.random()*100);// >=50 player goes first, else enemy first
        if (coinflip>=50) { // 1st player
            System.out.println(this.nombre+" Ataca primero");
            while (hp_actual >= 0 && enemy.getHp() >= 0) {
                enemy.receiveDamage(this.getDanio());
                printDanio(enemy, true);
                if (enemy.getHp() <= 0){break;} //enemy dies
                this.receiveDamage(enemy.getDanio());
                printDanio(enemy, false);
                if (this.hp_actual <= 0){break;} // player dies
            }
        } else {// 1st enemy
            while (hp_actual >= 0 && enemy.getHp() >= 0) { //idem, but enemy goes first
                this.receiveDamage(enemy.getDanio());
                printDanio(enemy, false);
                if (this.hp_actual <= 0){break;}
                enemy.receiveDamage(this.getDanio());
                printDanio(enemy,true);
                if (enemy.getHp() <= 0){break;}
                }
            }
        }



}
