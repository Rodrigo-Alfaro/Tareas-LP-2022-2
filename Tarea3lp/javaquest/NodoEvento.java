import java.util.Random;
import java.util.Scanner;

public class NodoEvento extends Nodo{
    private String descripcion;
    private String alternativa1;
    private String alternativa2;
    private Item resultado1;
    private Item resultado2;
    private String text1 = "Te sientes determinado";
    private String text2 = "Sabes que es una desicion arriesgada";
    private String text3 = "No es la mejor alternativa, pero es lo que crees correcto";
    private String text4 = "Con cada empujon, hay un tiron, una consecuencia, y viviras las consecuencias de tus acciones";
    public NodoEvento(String descripcion, String alternativa1, String alternativa2, Item resultado1, Item resultado2){
        this.descripcion = descripcion;
        this.alternativa1 = alternativa1;
        this.alternativa2 = alternativa2;
        this.resultado1 = resultado1;
        this.resultado2 = resultado2;
    }
    /*
    Escoge unos de los atributos text1-4 dado un numero, el cual luego es printeado, esta funcion es utilizada en interactuar.
    dado un numero random, para printear una "quote random"

    @Integer tex: este numero definira el text a printear

    */
    private void text(Integer tex){
        if (tex == 0){
            System.out.println(text1);
        }
        if (tex == 1){
            System.out.println(text2);
        }
        if (tex == 2){
            System.out.println(text3);
        }
        if (tex == 3){
            System.out.println(text4);
        }
    }
    public void interactuar(Jugador pj) {
        Random text = new Random();
        Integer tex = text.nextInt(4);
        System.out.println(this.descripcion);
        Scanner input = new Scanner(System.in);
        System.out.println("1)Si\n2)No");
        Integer option = input.nextInt();
        System.out.println(option);
        while (option != 1 && option != 2) {
            System.out.println("Opcion no valida, ...");
            option = input.nextInt();
        }
        text(tex);
        if (option == 1) {
            System.out.println(this.alternativa1);
            resultado1.aplicar(pj,false);
        }
        if (option == 2) {
            System.out.println(this.alternativa2);
            resultado2.aplicar(pj,false);
        }
    }
}

