package Modelos.Clases;

import Enums.TipoArma;
import Interfaces.IPlacaje;
import Interfaces.iCabezazo;
import Modelos.Objeto.Arma;

import java.io.Serializable;
import java.util.Scanner;


/**
 * Clase para una de las tantas clases de personajes "Enano" */
public class Enano extends Personaje implements IPlacaje, iCabezazo, Serializable {


    public Enano(String nombre, double vida) {
        super(nombre, vida + 30 ,1);
        Arma garrote = new Arma("Garrote",30,20, TipoArma.HIELO);
        this.setArma(garrote);
    }
    public Enano(String nombre, double vida, int nivel) {
        super(nombre, vida + 30 ,nivel);
        Arma garrote = new Arma("Garrote",30,20, TipoArma.HIELO);
        this.setArma(garrote);
    }


    public void atacar(Personaje objetivo){
        Scanner scanner;
        System.out.println("Tus ataques son:");
        System.out.println("1. Placaje");
        System.out.println("2. Ataque con espada");
        System.out.println("3. Cabezazo");
        double danio=0;
        scanner = new Scanner(System.in);
        int opcion = scanner.nextInt();
        switch (opcion){
            case 1: danio = this.placaje(objetivo);
            break;
            case 2: danio = this.atacarConArma();
            break;
            case 3 : danio = this.cabezazo(objetivo);
            break;
            default:
                System.out.println("no elegiste bien la opcion, perdiste tu turno 🙂 ");
        }
        objetivo.recibirDanio(danio);
    }

    @Override
        public double placaje (Personaje enemigo) {
        double danio=20*getNivel();
        danio-= enemigo.getNivel()*3 ;
        if(enemigo instanceof NoMuerto)
        {
            danio=9999999;
            System.out.println("Desarmaste el pobre cadaver");

        }
        System.out.println("Arremete con su pequeño cuerpo sobre el enemigo haciendole "+danio);
        return danio;
    }

    @Override
    public double cabezazo(Personaje enemigo) {
        double danio=20;
        danio-= enemigo.getNivel()*3 ;
        if(enemigo instanceof Pandaren)
        {
            System.out.println("Le pegaste en la rodilla y te hiciste "+danio +"de daño");
            this.setVida(getVida()-danio);
            danio=0;

        }
        System.out.println("Arremete con su pequeño craneo sobre el enemigo haciendole "+danio);
        return danio;
    }
}


