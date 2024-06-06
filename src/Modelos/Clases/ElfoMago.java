package Modelos.Clases;

import java.util.Scanner;

public class ElfoMago extends Personaje {
    Scanner scanner;

    @Override
    public void atacar(Personaje objetivo) {
        System.out.println("Tus ataques son:");
        System.out.println("1: Bola de fuego");
        System.out.println("2: Impactrueno");
        System.out.println("3: Congelar");
        System.out.println("4: Hidrobomba");
        double danio=0;
        int opcion=scanner.nextInt();
        switch (opcion){
            case 1: danio = this.;
            case 2: danio = this.;
            default:
                System.out.println("no elegiste bien la opcion, perdiste tu turno =) ");
        }
        objetivo.recibirDanio(danio);
    }
}
