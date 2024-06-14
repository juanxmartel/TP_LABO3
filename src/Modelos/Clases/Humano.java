package Modelos.Clases;

import Enums.TipoArma;
import Interfaces.IPlacaje;
import Interfaces.iCabezazo;
import Modelos.Objeto.Arma;

import java.io.Serializable;
import java.util.Scanner;


/**
 * Clase para una de las tantas clases de personajes "Humano" */
public class Humano extends Personaje implements IPlacaje, iCabezazo , Serializable {

    Scanner scanner;



    public Humano(String nombre, double vida) {
        super(nombre, vida, 1);
        Arma Latigo = new Arma("Latigo",2,20, TipoArma.ELECTRICO);
        this.setArma(Latigo);
    }
    public Humano(String nombre, double vida, int nivel) {
        super(nombre, vida,nivel);
        Arma Latigo = new Arma("Latigo",2,20, TipoArma.ELECTRICO);
        this.setArma(Latigo);
    }

    @Override
    public double placaje (Personaje enemigo) {
        double danio=15*getNivel();
        danio-= enemigo.getNivel()*3 ;
        System.out.println("Arremete con su cuerpo sobre el enemigo haciendole "+danio);
        return danio;
    }

    @Override
    public double cabezazo(Personaje enemigo) {
        double danio=20;
        danio-= enemigo.getNivel()*3 ;
        System.out.println("Arremete con su craneo sobre el enemigo haciendole "+danio);
        return danio;
    }

    public double superioridadRacial(Personaje enemigo){

        double danio=5;
        danio-= enemigo.getNivel()*3 ;
        if(enemigo instanceof Goblin || enemigo instanceof NoMuerto)
        {
            danio=40;
            System.out.println("Te burlaste tan fuerte de el que le sacaste "+danio +" de vida");

        }else {System.out.println("Tu burla fue poco efectiva le hiciste "+danio);}
        return danio;
    }

    @Override
    public void atacar(Personaje objetivo) {
        System.out.println("Tus ataques son:");
        System.out.println("1. Ataque espada");
        System.out.println("2. Cabezazo");
        System.out.println("3. Superioridad racial");
        System.out.println("4. Placaje");
        double danio=0;
        scanner = new Scanner(System.in);
        int opcion=scanner.nextInt();
        switch (opcion){
            case 1: danio = this.atacarConArma();
                break;
            case 2: danio = this.cabezazo(objetivo);
                break;
            case 3: danio = this.superioridadRacial(objetivo);
                break;
            case 4 : danio= this.placaje(objetivo);
                break;
            default:
                System.out.println("no elegiste bien la opcion, perdiste tu turno =) ");
                break;
        }
        objetivo.recibirDanio(danio);
    }
}
