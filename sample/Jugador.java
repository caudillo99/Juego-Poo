package sample;

import java.io.*;

public class Jugador {
    static String Nombre;
    protected int Dificultad;
    protected int Puntos;
    protected double Tiempo;

    public Jugador(){}

    public Jugador(String Nombre, double Tiempo, int Puntos, int Dificultad){
        setNombre(Nombre);
        setTiempo(Tiempo);
        setPuntos(Puntos);
        setDificultad(Dificultad);
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setDificultad(int Dificultad) {
        this.Dificultad = Dificultad;
    }

    public void setTiempo(double Tiempo) {
        this.Tiempo = Tiempo;
    }

    public void setPuntos(int puntos) {
        this.Puntos = puntos;
    }

    public void escritura(Jugador ganador){
        File file = new File("ganadores.txt");
        boolean doesFileExist = file.exists();
        FileWriter archivo = null;
        PrintWriter pw = null;
        try {
            archivo = new FileWriter(file, true);
            pw = new PrintWriter(archivo);
            if (doesFileExist) {
                pw.println(ganador);
            }
            else {
                pw.println(ganador);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != archivo)archivo.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void/*Jugador[]*/ lectura(/*Jugador ganadores[]*/){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            // Apertura del archivo y creacion de BufferedReader parapoder
            // hacer una lectura comoda (disponer del metodo readLine()).archivo = new File ("pruebatexto.txt");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            // Lectura del fichero
            String linea;
            while((linea=br.readLine())!=null)System.out.println(linea);
        }

        catch(Exception e){
            e.printStackTrace();
        }finally{
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }

        Math.random();

        /*return ganadores;*/
    }
}
