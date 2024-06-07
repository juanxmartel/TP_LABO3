package Persistencia;
import Modelos.Clases.Personaje;
import java.io.*;

public class Archivos {

        String nombreArchivo = "personajes.txt";

        public static void crearArchivos(String nombreArchivo) {
            File archivo = new File(nombreArchivo);
        }

        public static void guardarPersonaje(Personaje personaje, String archivo) {
            try (FileOutputStream fileOut = new FileOutputStream(archivo);
                 ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(personaje);
                System.out.println("Guardado Finalizado " + archivo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public static Personaje cargarPersonaje(String archivo) {
        try (FileInputStream fileIn = new FileInputStream(archivo);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (Personaje) in.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("Error: El archivo " + archivo + " no fue encontrado.");
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            System.err.println("Error de E/S al intentar cargar el archivo " + archivo);
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("Error: La clase Personaje no se pudo encontrar.");
            e.printStackTrace();
            return null;
        }
    }}
