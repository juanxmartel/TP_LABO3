import Modelos.Clases.*;

import java.util.Random;
import java.util.Scanner;

import static Modelos.Clases.TodasLasClases.NoMuerto;

public class LogicaDelJuego {
    Scanner scanner;
    Personaje personajePrincipal;

    //crear un jugador

    public Personaje crearJugador(){
        //hay que ver como crear un jugador dependiendo del q elija
        System.out.println("que clase desea ser");

    }

    // crear un input y enviar una excepcion en caso de que este mal el input
   public int leerUnInputInt (String texto, int cantidadOpciones) {
       int opcion;
       scanner= new Scanner(System.in);

       do{
           System.out.println(texto);
           try {
               opcion= scanner.nextInt();

           }catch (Exception e)
           {
               opcion=-1;
               System.out.println("Escribi un numero por favor!");
           }
       }
       while (opcion < 1 || cantidadOpciones > opcion );

       return opcion;
       }

        //simplemente spam de consola para que quede "limpia"
       public void limpiarConsola()
           {
               for(int i=0;i<100;i++){
                   System.out.println();
               }
           }
        //frenar el juego hasta que aprete la tecla
       public void presionarParaContinuar(){
           System.out.println("apretar cualquier tecla para continuar");
           scanner.next();
       }


    //Crea un indice aleatorio para luego enviarlo a crear un enemigo
    public static TodasLasClases seleccionarClaseAleatoria() {
        TodasLasClases[] clases = TodasLasClases.values();
        int indiceAleatorio = new Random().nextInt(clases.length);
        return clases[indiceAleatorio];
    }

    //con el indice podemos llamar a esta instancia y crear la clase aleatoria
    public static Object crearInstancia(TodasLasClases clase) {
        switch (clase) {
            case Enano:
                return new Enano();
            case Humano:
                return new Humano();
            case NoMuerto:
                return new NoMuerto();
            case Pandaren:
                return new Pandaren();
            default:
                throw new IllegalArgumentException("Clase no soportada: " + clase);
        }
    }

        //solo dios y yo sabemos que hace esto, mañana solo dios
       public void batallaAleatoria(){
       limpiarConsola();
       //llamo a crear una clase aleatoria y se lo envio a que instancie el espacio en memoria y devuelva un objeto que sera un enemigo
       Object random = crearInstancia(seleccionarClaseAleatoria());

           System.out.println("te encontraste un "+ random.toString());
            presionarParaContinuar();
            batalla((Personaje) random);
       }
        public void batalla(Personaje enemigo)
        {
            boolean vivo = true;
            while (vivo)
            {
                limpiarConsola();
                //hay que ver como printear la vida nuestra y del enemigo
                System.out.println();
                System.out.println();
                int input = leerUnInputInt("(1) Pelear (2) Correr ",2);
                if(input==1){
                    //inicia la pelea
                    while (vivo)
                        {

                            System.out.println("Que vas a hacer?");
                            scanner.next();
                            leerUnInputInt();
                            //faltan los ataques AUXILIO
                            //en caso de que sea ataque

                            //en caso de que sea defender
                            if (enemigo.getVida()<0){
                                vivo=false;
                                System.out.println("ganaste el combate");
                            }
                            if (personajePrincipal.vida<0){
                                vivo=false;
                                System.out.println("perdiste el combate, tu alma esta en pena");
                            }


                        }
                    System.out.println();
                }

            }
        }


}
