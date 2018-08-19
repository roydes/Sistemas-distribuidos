package ec_bully;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ROY
 */
public class Transporte {
DatagramSocket socket;
InetAddress grupo;
int puerto;

    public Transporte() {
    }
 public Transporte( DatagramSocket s,InetAddress grupo,int puerto ) {
      this.socket=s;
      this.grupo=grupo;
      this.puerto= puerto;        
    }
  
public void envio(Mensaje M){
Serializacion Serializador= new Serializacion();
byte[] m_enviar = Serializador.serializar(M);
DatagramPacket Mensaje_Salida = new DatagramPacket(m_enviar, m_enviar.length, grupo, puerto);
    try {
        socket.send(Mensaje_Salida);
    } catch (IOException ex) {
        Logger.getLogger(Transporte.class.getName()).log(Level.SEVERE, null, ex);
    }
}
public Mensaje recepcion() throws IOException{
byte[] buffer = new byte[1000];    
Serializacion serializador  = new Serializacion();
DatagramPacket mensaje_Entrante = new DatagramPacket(buffer, buffer.length);
socket.receive(mensaje_Entrante);
Mensaje M= new Mensaje();
    try {
         M= (Mensaje)serializador.deserializar(mensaje_Entrante.getData());
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
    
  

