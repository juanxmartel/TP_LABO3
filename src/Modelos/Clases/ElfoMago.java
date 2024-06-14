package Modelos.Clases;

import APIclima.ClimaAPI;
import APIclima.DatosClima;
import Enums.TipoArma;
import Genericas.ListaGenerica;
import Modelos.Objeto.Arma;
import Modelos.Objeto.Item;

import java.io.Serializable;
import java.util.Scanner;


/**
 * Clase para una de las tantas clases de personajes "ElfoMago"*/
public class ElfoMago extends Personaje implements Serializable {
    Scanner scanner;

    DatosClima datos = ClimaAPI.obtenerDatosClima();


    public ElfoMago(String nombre, double vida) {
        super(nombre, vida, 1);
        Arma Baston = new Arma("Baston",6,30, TipoArma.FUEGO);
        this.setArma(Baston);
    }
    public ElfoMago(String nombre, double vida, int nivel) {
        super(nombre, vida, nivel);
        Arma Baston = new Arma("Baston",6,30, TipoArma.FUEGO);
        this.setArma(Baston);
    }

    @Override
    public void atacar(Personaje objetivo) {
        ClimaAPI.actualizarDatosClima("2024-05-05");
        System.out.println("Tus ataques son:");
        System.out.println("1. Bola de fuego");
        System.out.println("2. Impactrueno");
        System.out.println("3. Congelar");
        System.out.println("4. Hidrobomba");
        double danio=0;
        int opcion=scanner.nextInt();
        switch (opcion){
            case 1: danio = this.bolaDeFuego();
            break;
            case 2: danio = this.impacTrueno();
                break;
            case 3: danio = this.congelar();
                break;
            case 4: danio = this.hidroBomba();
                break;
            default:
                System.out.println("No elegiste bien la opcion, perdiste tu turno =) ");
                break;
        }
        objetivo.recibirDanio(danio);
    }
    public double bolaDeFuego(){
        double danio =0;
        if (datos.getHumedad()>80)
        {
            danio=20;
            System.out.println("Por la alta la humedad tu bola de fuego hace menos daño, hizo"+danio);
        }else {
            danio=35;
            System.out.println("Gracias a la baja humedad tu bola de fuego hace mas daño, hizo"+danio);
        }
        return danio;
    }
    public double impacTrueno(){
        double danio=0;
        if(datos.getHumedad()>80)
        {
            danio=35;
            System.out.println("Gracias al poder de la humedad tu rayo hace mas daño, hizo"+danio);

        }else{
            danio=25;
            System.out.println("Por culpa de la baja humedad tu rayo hace menos daño, hizo"+danio);
            }
        return danio;
    }

    public double congelar(){
        double danio=0;
        if(datos.getTemperaturaMaxima()<20)
        {
            danio=35;
            System.out.println("Gracias a la baja temperatura tu hielo hoy hace mas daño, hizo"+danio);

        }else{
            danio=15;
            System.out.println("Por la alta temperatura tu hielo se fue derritiendo e hizo menos daño "+danio);
            }
        return danio;
    }

    public double hidroBomba(){
        double danio=0;
        if(datos.getHumedad()>80)
        {
            danio=35;
            System.out.println("Aprovechas el dia humedo para hacer mas daño, hiciste "+danio);

        }else{danio=15;
            System.out.println("Por culpa de la baja humedad y poco aprovechamiento del agua, se hizo menos daño :"+danio);
            }
        return danio;
    }
}
