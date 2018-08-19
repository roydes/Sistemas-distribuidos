package proyecto_final.Utiles;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Clase encargada de la serialización de los mensajes
 * 
 */

/**
 *
 * @author ROY
 */
public class Serializacion {
    
    public byte[] serializar(Object obj) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o=null;
        try {
            o = new ObjectOutputStream(b);
        } catch (IOException ex) {
            Logger.getLogger(Serializacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            o.writeObject(obj);
        } catch (IOException ex) {
            Logger.getLogger(Serializacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b.toByteArray();
    }

    public Object deserializar(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
        ObjectInputStream o = new ObjectInputStream(b);
        return o.readObject();
    }
}
