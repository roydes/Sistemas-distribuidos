package proyecto_final.Audio;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/*
 * Clase encargada de la reprodución de del audio proveniente de los mensajes
 * MBCP
 */

/**
 *
 * @author ROY
 */
public class Reproductor_Audio {
    
    public Reproductor_Audio() {
    }
    private AudioFormat getFormato_Audio() {
        float tasa_muestra = 8000;
        int tamaño_muestra = 8;
        int canales = 1;
        boolean señalizado = true;
        boolean bigEndian = true;
        return new AudioFormat(tasa_muestra,tamaño_muestra, canales, señalizado, bigEndian);
    }

    public void reproducir(byte[] audio){
        try {
            InputStream entrada = new ByteArrayInputStream(audio);
            final AudioFormat formato_audio = getFormato_Audio();
            final AudioInputStream flujo_audio_entrada = new AudioInputStream(entrada, formato_audio, audio.length / formato_audio.getFrameSize());
            DataLine.Info info = new DataLine.Info( SourceDataLine.class, formato_audio);
            final SourceDataLine linea = (SourceDataLine) AudioSystem.getLine(info);
            linea.open(formato_audio);
            linea.start();

            Runnable runner = new Runnable() {
                int bufferSize = (int) formato_audio.getSampleRate()
                        * formato_audio.getFrameSize();
                byte buffer[] = new byte[bufferSize];

                public void run() {
                    try {
                        int count;
                        while ((count = flujo_audio_entrada.read(buffer, 0, buffer.length)) != -1) {
                            if (count > 0) {
                                linea.write(buffer, 0, count);
                            }
                        }
                        linea.drain();
                        linea.close();
                    } catch (IOException e) {
                        System.err.println("Exepción I/O  " + e);
                        System.exit(-3);
                    }
                }
            };
            Thread hilo_reproduccion = new Thread(runner);
            hilo_reproduccion.start();
        } catch (LineUnavailableException e) {
            System.err.println("La linea no esta disponible!!!" + e);
            System.exit(-4);
        }
    }
  
    
    public void reproducir_archivo(){
    InputStream in;
  
    
        try {
            in = new FileInputStream(new File("C:\\Users\\ROY\\Downloads\\music_marimba_chord.wav"));
               AudioStream as= new AudioStream(in);
               AudioPlayer.player.start(as);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reproductor_Audio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Reproductor_Audio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
