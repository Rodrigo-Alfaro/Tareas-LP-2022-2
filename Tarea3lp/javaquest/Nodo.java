import java.util.ArrayList;
import java.util.List;

public abstract class Nodo {
    protected Integer id;
    private List<Nodo> siguientes_nodos = new ArrayList<>();
    protected abstract void interactuar(Jugador pj);
    protected void agregarNodo(Nodo nodo){
        this.siguientes_nodos.add(nodo);
    }
    //--getters---------------------
    /*
    Getters para siguientes_nodos y id, nos permiten acceder a los atributos

    @return: el atributo deseado

    */
    public List<Nodo> getSiguientes_nodos(){return siguientes_nodos;}
    public Integer getId(){return id;}
    //setters------------
    /*
    Un setter para id, nos permite modificar dicho atributo

    */
    public void setId(Integer id){this.id = id;}

}
