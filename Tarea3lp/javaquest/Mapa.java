import java.util.*;

public class Mapa {
    private Integer profundidad;
    private NodoInicial nodo_inicial;
    private Nodo nodo_actual;
    private ArrayList<String> nodos = new ArrayList<String>();
    private SortedSet<Edge> map;
    Integer start = 0;

    //constructor
     /*
    Constructor de Mapa, con 2 variables, settea al objeto definiendo su profundidad, nodo inicial y nodo actual


    @Integer profundidad: profundidad del mapa
    @NodoInicial start: donde se comenzara el mapa

    @return
    */
    public Mapa(Integer profundidad, NodoInicial start) {
        this.profundidad = profundidad;
        this.nodo_inicial = start;
        this.nodo_actual = nodo_inicial;
    }

    //getters
    /*
    getters, usados para acceder a atributos privados, getAtributo

    @return: el atributo deseado
     */
    public List<String> getNodos() {return nodos;}
    public Nodo getNodo_actual(){return this.nodo_actual;}
    public SortedSet<Edge> getMap(){return map;}

    /*
    Define el tipo de cada nodo  ("por la probabilidad dada"), y los mete en una
    lista de strings, con los cuales luego en el main se generan los nodos correspondientes

    */
    public void generarMapa() {
        Random rand = new Random();
        Integer set;
        Integer type = rand.nextInt(10);
        map = GraphGenerator.Generar(this.profundidad);
        set = (map.last().x) + 1;// size to map tipes to tree
        nodos.add("inicio");//
        for (int i = 1; i < set; i++) { // % = 0.1 tienda, 0.6 combate, 0.3 evento
            if (type <= 1) {
                nodos.add("tienda");
            }
            if (type > 1 && type < 4) {
                nodos.add("evento");
            }
            if (type >= 4) {
                nodos.add("combate");
            }
            type = rand.nextInt(10);
        }
        nodos.add("Jefe Final");
    }

    public void verMapa(List<Nodo> list) {
        for (Nodo n : list) {
            if (n.equals(list.get(list.size()-1))) break;// para no printear el jefe final
            System.out.println(n.getId()+" "+this.getNodos().get(n.getId()) +" puede ir a: ");
                for (Nodo next : n.getSiguientes_nodos()) {
                    System.out.println(next.getId()+" "+this.getNodos().get(next.getId()));
            }
        }
    }

    public void avanzar(Jugador pj){
        Scanner input = new Scanner(System.in);
        List<Integer> aux = new ArrayList<>();
        System.out.println("Nodos disponibles para avanzar: ");
        for (Nodo nodos : this.nodo_actual.getSiguientes_nodos()){
            System.out.println(nodos.getId()+" "+this.nodos.get(nodos.getId()));
            aux.add(nodos.getId());
        }
        Integer choice = input.nextInt();
        while (!aux.contains(choice)){
            System.out.println("Eliga una opcion valida");
            choice = input.nextInt();
        }
        for(Nodo n : this.nodo_actual.getSiguientes_nodos()){
            if (choice.equals(n.getId())){
                n.interactuar(pj);// aplicamos y actualizamos
                nodo_actual = n;
            }
        }

    }
}



