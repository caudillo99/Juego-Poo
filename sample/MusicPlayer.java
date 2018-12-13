package sample;

import java.util.ArrayList;
import javax.sound.sampled.*;
import java.io.File;

public class MusicPlayer implements Runnable {
    private ArrayList<String> musicFiles;
    private  int currentSongIndex;

    public MusicPlayer(String...files)
    {
        musicFiles = new ArrayList<String>();
        for (String file:files)
            musicFiles.add("C:\\Users\\caudi\\Documents\\juego actualizado\\Audio\\"+file+".wav");
    }

    private void playSound(String fileName) //al metodo se le envia una cadena con el nombre del archivo de audio
    {
        try
        {
            File soundFile = new File(fileName);    // se crea objeto tipo file del paquete java.io.file
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile); //Se declara un objeto que soporta archivos de audio
            AudioFormat format = ais.getFormat(); // El tipo de formato que tendra la cancion .wav, .mov, (hay una tabla con los formatos que soporta JAVA)
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info); //con AudioSystem.getline permite obtener la informacion del formato del sonido que se va a cargar
            clip.open(ais); // se abre el "ais" que es la cancion o el sonido
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN); //FloatControl permite manipular los sonidos asi como el volumen.
            //tiene el control sobre los valores de punto flotante como lo es sonido.
            gainControl.setValue(-10);
            clip.start();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void run()
    {

        playSound(musicFiles.get(currentSongIndex));
    } //metodo que activa la cancion
}
