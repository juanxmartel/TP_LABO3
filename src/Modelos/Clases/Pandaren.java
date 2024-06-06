package Modelos.Clases;

import Interfaces.IPlacaje;

import java.util.Scanner;

public class Pandaren extends Personaje implements IPlacaje {

    Scanner scanner;
    @Override
    public void atacar(Personaje objetivo) {
        System.out.println("Tus ataques son:");
        System.out.println("1: ataque espada");
        System.out.println("2: placaje");
        double danio=0;
        scanner = new Scanner(System.in);
        int opcion=scanner.nextInt();
        switch (opcion){
            case 1: danio = this.atacarConArma();
            case 2 : danio= this.placaje(objetivo);
            default:
                System.out.println("no elegiste bien la opcion, perdiste tu turno =) ");
        }
        objetivo.recibirDanio(danio);
    }

    @Override
    public double placaje(Personaje enemigo) {
        double danio=20*getNivel();
        danio-= enemigo.getNivel()*3 ;
        if(enemigo instanceof NoMuerto || enemigo instanceof Enano)
        {
            danio=9999999;
            System.out.println("Desarmaste el pobre enemigo");

        }
        System.out.println("Arremete con su pesado cuerpo sobre el enemigo haciendole "+danio);
        return danio;
    }


}
