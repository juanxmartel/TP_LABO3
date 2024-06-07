package Modelos.Clases;

import APIclima.ClimaAPI;
import APIclima.DatosClima;
import Enums.TipoArma;
import Genericas.ListaGenerica;
import Modelos.Objeto.Arma;
import Modelos.Objeto.Item;

import java.util.Scanner;

public class ElfoMago extends Personaje {
    Scanner scanner;

    DatosClima datos = new DatosClima();

    public ElfoMago(String nombre, double vida) {
        super(nombre, vida, 1);
        Arma Baston = new Arma("Baston",6,30, TipoArma.FUEGO);
        this.setArma(Baston);
    }

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
            case 1: danio = this.bolaDeFuego();
            case 2: danio = this.impacTrueno();
            case 3: danio = this.congelar();
            case 4: danio = this.hidroBomba();
            default:
                System.out.println("no elegiste bien la opcion, perdiste tu turno =) ");
        }
        objetivo.recibirDanio(danio);
    }
    public double bolaDeFuego(){
        double danio =0;
        if (datos.getHumedad()>80)
        {
            danio=20;
            System.out.println("Gracias a la humedad tu bola de fuego hace menos daño, hizo"+danio);
        }else {
            danio=35;
            System.out.println("Gracias a la humedad tu bola de fuego hace mas daño, hizo"+danio);
        }
        return danio;
    }
    public double impacTrueno(){
        double danio=0;
        if(datos.getHumedad()>80)
        {
            danio=35;
            System.out.println("Gracias a la humedad tu rayo hace mas daño, hizo"+danio);

        }else{
            danio=25;
            System.out.println("Gracias a la humedad tu rayo hace menos daño, hizo"+danio);
            }
        return danio;
    }

    public double congelar(){
        double danio=0;
        if(datos.getTemperaturaMaxima()<20)
        {
            danio=35;
            System.out.println("Gracias a la baja temperatura tu hielo hace mas daño, hizo"+danio);

        }else{
            danio=15;
            System.out.println("Gracias a la alta temperatura tu hielo se deshielo e hizo menos daño "+danio);
            }
        return danio;
    }

    public double hidroBomba(){
        double danio=0;
        if(datos.getHumedad()>80)
        {
            danio=35;
            System.out.println("Aprovechas el dia humedo para hacer mas daño, hizo"+danio);

        }else{danio=15;
            System.out.println("Por culpa de la baja humedad y poco aprocechamiento del agua hizo menos daño :"+danio);
            }
        return danio;
    }
}
