package Persistencia;

import Modelos.Clases.Personaje;
import Enums.Zona;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clase que se encarga de toda la gestion de archivos*/
public class Archivos {

    private static final String PERSONAJE_ARCHIVO = "personajes.dat";
    private static final String ENEMIGOS_ARCHIVO = "enemigos.dat";

    public static void guardarPersonaje(Personaje personaje) {
        try (FileOutputStream fileOut = new FileOutputStream(personaje.getNombre());
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(personaje);
            System.out.println("Guardado Finalizado " + personaje.getNombre());
        } catch (IOException e) {
            System.err.println("Error al guardar el personaje en el archivo " + personaje.getNombre());
            e.printStackTrace();
        }
    }

    public static Personaje cargarPersonaje(String nombrePersonaje) {
        try (FileInputStream fileIn = new FileInputStream(nombrePersonaje);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (Personaje) in.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("Error: El archivo " + nombrePersonaje + " no fue encontrado.");
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            System.err.println("Error de E/S al intentar cargar el archivo " + nombrePersonaje);
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Clase no encontrada.");
            e.printStackTrace();
            return null;
        }
    }
}

