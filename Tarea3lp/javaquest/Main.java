import java.util.*;

public class Main {
    public static void main(String[] args) {
        //---------Bosses----------------------------------------------
        Personaje placeholder = new Personaje();
        Personaje rashek = new Personaje("Rashek", 100, 13, 14);
        Personaje kolos = new Personaje("Guerrero Kolo", 150, 10, 5);
        Personaje inquisidor = new Personaje("Inquisidor", 85, 8, 20);
        Random rand = new Random();
        Integer boss_choice = rand.nextInt(3);
        NodoJefeFinal end = new NodoJefeFinal(placeholder);// escogiendo un boss para la run
        if (boss_choice == 0) {
            NodoJefeFinal aux = new NodoJefeFinal(rashek);
            end = aux;
        }
        if (boss_choice == 1) {
            NodoJefeFinal aux = new NodoJefeFinal(kolos);
            end = aux;
        }
        if (boss_choice == 2) {
            NodoJefeFinal aux = new NodoJefeFinal(inquisidor);
            end = aux;
        }
        //-----Events Items------------------------------------------------
        Item noitem = new Item(0, 0, 0, 0, 0);
        Item res1 = new Item();
        Item res2 = new Item();
        Item res3 = new Item();
        //--------Events--------------------------------------------------------------
        String desc1 = "Te acercas a una hogera, hay un misterioso hombre que se hace llamar Hoid\n" +
                "Te ofrece jugar a un juego, Aceptas?";
        String alter1 = "Tomas los dados, y los lanzas, pero antes de que puedas ver el resultado, Hoid\n " +
                "lanza un dardo a los dados, y ves como se queman en la hogera, Hoid se retira, pero sin " +
                "antes darte un misterioso objeto";
        String alter2 = "Simplemente pasas de largo y te encuentras una roca con forma de dinosaurio, es bonita pero no servira de mucho en un combate";
        NodoEvento event1 = new NodoEvento(desc1, alter1, alter2, res1, noitem);

        String desc2 = "Haz un backflip, escuchas una misteriosa voz en tu mente, lo intentas?";
        String alter3 = "Recuerdas esa vez que viste un video en internet, y tratas de imitarlo, por alguna razon\n" +
                " que ni tu mismo entiendes, lo logras, del cielo vez caer una pequena caja de madera, \n" +
                "esa es tu recompensa, te dice la misteriosa voz...";
        String alter4 = "Crees que te estas volviendo loco, y rehusas a hacerlo, pero la voz vuelve y escuchas\n" +
                " Soy Ruina, y pronto veras mi poder, te dare un presente para que creas en mi..., y una \n" +
                "pequena caja de madera cae enfrente de ti";
        NodoEvento event2 = new NodoEvento(desc2, alter3, alter4, res2, res2);

        String desc3 = "Te encuentras un misterioso frasco de metal, lo bebes?";
        String alter5 = "Descubres que eres un alomantico, puedes quemar metales, pero por limitaciones tecnicas eso no\n " +
                "te servira de mucho en esta aventura.";
        String alter6 = "Quien se beberia un extrano liquido que encontraste en mitad de la nada...";
        NodoEvento event3 = new NodoEvento(desc3, alter5, alter6, res3, noitem);

        List<NodoEvento> eventos = new ArrayList<>();//array with the events
        eventos.add(event1);
        eventos.add(event2);
        eventos.add(event3);

        List<Nodo> nodos_map = new ArrayList<>();// array del cual se hara el mapa

        //--------------pj creation-------------------------------------
        System.out.println("Bienvenido a Luthadel Battles aventurero, introduce tu nombre: ");
        Scanner input = new Scanner(System.in);
        String name = input.next();
        Jugador pj = new Jugador(name);
        System.out.printf("Estas a punto de iniciar tu aventura "+pj.getNombre()+"\n");
        System.out.println("Te adentraras en los bajos fondos de Luthadel y te encontraras a una seguidilla de enemigos, eventos y tiendas hasta llegar al jefe final, \n" +
                "a continuacion se te preguntara la dificultad, se recomienda una dificultad de 5");
        System.out.println("Introduce la dificultad (en numero)");
        Integer diff = input.nextInt();
        System.out.println("Estas listo para iniciar tu aventura, a continuacion se te presentara el mapa, recuerda que puedes verlo cuando desees, \n" +
                "en cada punto se iniciara el evento correspondiente, y entre eventos elegiras a que punto ir, y podras ver tus stats");
        //---------------------------------------map creation------------------------------------------------*/
        NodoInicial start = new NodoInicial();
        Mapa map = new Mapa(5, start);
        map.generarMapa();
        nodos_map.add(start);
        for (String s : map.getNodos()) { //asignando los nodos/creando el mapa
            if (s.equals("combate")) {
                NodoCombate aux = new NodoCombate();
                nodos_map.add(aux);
            }
            if (s.equals("evento")) {
                Random randevent = new Random();
                Integer prob = randevent.nextInt(3);
                if (prob == 0) {
                    nodos_map.add(eventos.get(0));
                }
                if (prob == 1) {
                    nodos_map.add(eventos.get(1));
                }
                if (prob == 2) {
                    nodos_map.add(eventos.get(2));
                }
            }
            if (s.equals("tienda")) {
                NodoTienda aux = new NodoTienda();
                nodos_map.add(aux);
            }
        }
        nodos_map.add(end);
        for (Integer j=0;j<nodos_map.size();j++){
            nodos_map.get(j).setId(j);
        }
        for (Edge e : map.getMap()) {
            nodos_map.get(e.x).agregarNodo(nodos_map.get(e.y));
        }
        //------------------start game--------
        Integer menu_choice;
        while(pj.getHp()>0){
            System.out.println("1)ver el mapa \n2)ver stats\n3)ver items\n4)avanzar");
            menu_choice = input.nextInt();
            switch (menu_choice){
                case 1:
                    map.verMapa(nodos_map);
                    break;
                case 2:
                    pj.verEstado();
                    break;
                case 3:
                    pj.verItems();
                    break;
                case 4:
                    map.avanzar(pj);

            }
        }
    }
}
