package proyecto_final.Audio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

/*
 * Clase encargada de la captura de audio
 * 
 */

/**
 *
 * @author ROY
 */
public class Captura_Audio {
    
    public static final Logger log = Logger.getLogger(Captura_Audio.class.getName() );
    int identificadorProceso;
    int contadorMensaje;
    
    public Captura_Audio() {     
        log.setLevel(Level.INFO);
        
    }
    
    private AudioFormat getFormato_Audio() {
        float tasa_muestra = 8000;
        int tamaño_muestra = 8;
        int canales = 1;
        boolean señalizado = true;
        boolean bigEndian = true;
        return new AudioFormat(tasa_muestra,
                tamaño_muestra, canales, señalizado, bigEndian);
    }

    public byte [] capturar_Audio() {
        byte [] buffer = new byte[1000];
        try {
            final AudioFormat formato_audio = getFormato_Audio();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, formato_audio);
            final TargetDataLine linea_sonido = (TargetDataLine) AudioSystem.getLine(info);
            int bufferSize = (int) formato_audio.getSampleRate() * formato_audio.getFrameSize();
            buffer = new byte[bufferSize];
            linea_sonido.open();
            linea_sonido.start();
            linea_sonido.read(buffer, 0, buffer.length);
            
            
        } catch (LineUnavailableException e) {
            log.log(log.getLevel(), "La linea no está deisponible!!", e);
            System.exit(-2);
        }
            return buffer;

    }
   
}
