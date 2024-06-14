import Enums.TodasLasClases;
import Modelos.Clases.*;

import java.util.Random;
import java.util.Scanner;

public class LogicaDelJuego {
    Scanner scanner;
    Personaje personajePrincipal;

    /**
     * Crea un jugador*/
    public Personaje crearJugador(){
        //hay que ver como crear un jugador dependiendo del q elija
        System.out.println("que clase desea ser");
        return null;
    }

    /**
     * crear un input y enviar una excepcion en caso de que este mal el input*/
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

        /**
         * Baja en la consola simulando una limpieza de la misma*/
       public void limpiarConsola()
           {
               for(int i=0;i<100;i++){
                   System.out.println();
               }
           }
    /**
     * Crea un indice aleatorio para luego enviarlo a crear un enemigo*/
    public static TodasLasClases seleccionarClaseAleatoria() {
        TodasLasClases[] clases = TodasLasClases.values();
        int indiceAleatorio = new Random().nextInt(clases.length);
        return clases[indiceAleatorio];
    }
        public void batalla(Personaje enemigo)
        {
            boolean vivo = true;
            while (vivo)
            {
                limpiarConsola();
                System.out.println();
                System.out.println();
                int input = leerUnInputInt("(1) Pelear (2) Correr ",2);
                if(input==1){

                    while (vivo) {
                        System.out.println("Que vas a hacer?");
                        scanner.next();
                        leerUnInputInt("atacar o huir", 2);

                        if (enemigo.getVida() < 0) {
                            vivo = false;
                            System.out.println("ganaste el combate");
                        }
                        if (personajePrincipal.getVida() < 0) {
                            vivo = false;
                            System.out.println("perdiste el combate, tu alma esta en pena");
                        }
                    }
                }

            }
        }


}
