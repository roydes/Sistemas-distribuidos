package proyecto_final.Utiles;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/*
 * Clase encargada de trasnformar una imagen a Bytes y viceversa
 * La imagen en bytes es agregada a los mensajes MBCP.
 */

/**
 *
 * @author ROY
 */
public class Utiles_Video {

    public Utiles_Video() {
    }
    
    public byte[] bufferedImageToByte(BufferedImage imagenCapturada){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(imagenCapturada, "jpg", baos);
        } catch (IOException ex) {
            Logger.getLogger(Utiles_Video.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] ba = baos.toByteArray();
        return ba;
    }
    
    public BufferedImage byteToBufferedImage(byte[] byteArray){
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new ByteArrayInputStream(byteArray));
        } catch (IOException ex) {
            Logger.getLogger(Utiles_Video.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bufferedImage;        
    }
    
}
