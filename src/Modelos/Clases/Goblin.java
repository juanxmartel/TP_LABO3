package Modelos.Clases;

import java.util.Scanner;

public class Goblin extends Personaje {


    Scanner scanner;

    @Override
    public void atacar(Personaje objetivo) {
        System.out.println("Tus ataques son:");
        System.out.println("1: ataque arma");
        System.out.println("2: ataque rapido");
        double danio=0;
        scanner = new Scanner(System.in);
        int opcion=scanner.nextInt();
        switch (opcion){
            case 1: danio = this.atacarConArma();
            case 2: danio = this.ataqueRapido(objetivo);
            default:
                System.out.println("no elegiste bien la opcion, perdiste tu turno =) ");
        }
        objetivo.recibirDanio(danio);
    }

    public double ataqueRapido(Personaje objetivo)
    {
        double dano=15;
        if(objetivo instanceof Enano)
        {
            dano=30*this.getNivel();
        }

        return dano;
    }


}
