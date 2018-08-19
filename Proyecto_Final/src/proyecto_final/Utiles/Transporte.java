package proyecto_final.Utiles;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyecto_final.Modulo_MBCP.Mensaje_MBCP;

/*
 * clase encargada de gestionar la capa de transporte. Establecer la conexión  
 * mediante sockets TCP. Enviar y recibir los mensajes provenientes de la capa
 * de Red.
 */

/**
 *
 * @author ROY
 */

public class Transporte {
DatagramSocket socket;
InetAddress grupo;
int puerto;
private ArrayList<String> direcciones_puerto;

    public Transporte() {
    }
 public Transporte( DatagramSocket s,InetAddress grupo,int puerto , ArrayList<String> direcciones_puerto) {
      this.socket=s;
      this.grupo=grupo;
      this.puerto= puerto; 
      this.direcciones_puerto= direcciones_puerto;
      
         if(socket.equals(null)){
            
                System.out.println("no se inicializa el socket");
            }
    }
  
public void envio(Mensaje_MBCP M){
Serializacion Serializador= new Serializacion();
byte[] m_enviar = Serializador.serializar(M);
DatagramPacket Mensaje_Salida = new DatagramPacket(m_enviar, m_enviar.length, grupo, puerto);
    try {
        socket.send(Mensaje_Salida);
    } catch (IOException ex) {
        Logger.getLogger(Transporte.class.getName()).log(Level.SEVERE, null, ex);
    }
}

public void envio_multicast(Mensaje_MBCP M){
  
    
Serializacion Serializador= new Serializacion();
byte[] m_enviar = Serializador.serializar(M);
    for (String direccion : this.direcciones_puerto) {
        
        String[] temp= direccion.split(",");
       DatagramPacket Mensaje_Salida= null ;
       
       
       
         
    try {
         DatagramSocket aSocket = new DatagramSocket();
          aSocket.setSoTimeout(2000);
         InetAddress direc=  InetAddress.getByName(temp[0]);
        int puerto= Integer.valueOf(temp[1]);
       
            Mensaje_Salida = new DatagramPacket(m_enviar, m_enviar.length, direc, puerto);
            System.out.println(temp[0]);
            boolean confirmado=false;
            int cont=0;
            while(!confirmado && cont<=5){
                cont++;
        aSocket.send(Mensaje_Salida);
        //esperar confirmación
        byte[] buffer = new byte[100000];
            DatagramPacket confirmacion = new DatagramPacket(buffer, buffer.length);
            
            try{
            aSocket.receive(confirmacion);
              confirmado= true;
           Mensaje_MBCP M_conformacion= new Mensaje_MBCP();
 
         M_conformacion= (Mensaje_MBCP)Serializador.deserializar(confirmacion.getData());
    
           //if(M_conformacion.equals(M)&& confirmacion.getAddress().equals(direc) && confirmacion.getPort()==puerto){
            //  
                System.out.println(puerto+" confirma mensaje ");
            
          //  }
            
            }
            
            catch(SocketTimeoutException e)
            {
                System.out.println("Reenviando mensje "+M);
            } catch (ClassNotFoundException ex) {
                 Logger.getLogger(Transporte.class.getName()).log(Level.SEVERE, null, ex);
             }
            
        
        

            }
    } catch (IOException ex) {
        Logger.getLogger(Transporte.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    }

}


public Mensaje_MBCP recepcion() throws IOException{    
byte[] buffer = new byte[100000];    
Serializacion serializador  = new Serializacion();
DatagramPacket mensaje_Entrante = new DatagramPacket(buffer, buffer.length);
socket.receive(mensaje_Entrante);
DatagramSocket aSocket = new DatagramSocket();
DatagramPacket confirmacion = new DatagramPacket(mensaje_Entrante.getData(),mensaje_Entrante.getLength(), mensaje_Entrante.getAddress(), mensaje_Entrante.getPort());
aSocket.send(confirmacion);
Mensaje_MBCP M= new Mensaje_MBCP();
    try {
         M= (Mensaje_MBCP)serializador.deserializar(mensaje_Entrante.getData());
         aSocket.send(confirmacion);
         
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Transporte.class.getName()).log(Level.SEVERE, null, ex);
    }
return M;
}


    public DatagramSocket getSocket() {
        return socket;
    }

    public void setSocket(DatagramSocket socket) {
        this.socket = socket;
    }

    public InetAddress getGrupo() {
        return grupo;
    }

    public void setGrupo(InetAddress grupo) {
        this.grupo = grupo;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

}
    
  

