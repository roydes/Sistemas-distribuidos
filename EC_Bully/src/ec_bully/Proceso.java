/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec_bully;

import ec_bully.Mensaje.Tipos_mensaje;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ROY
 */
public class Proceso {

    int Id;
    int Id_coordinador;
    ArrayList<Integer> procesos_mayor_id;
    String nombre;
    MulticastSocket socket;
    Transporte transporte;
    boolean participante;
    String Log;

    public Proceso(int Id, ArrayList<Integer> procesos_mayor_id ) {
        this.Id = Id;
        this.nombre = "Proces" + Id;
        this.participante = false;
        this.procesos_mayor_id= procesos_mayor_id;

        try {
            InetAddress grupo = InetAddress.getByName("228.5.6.10");

            socket = new MulticastSocket(6789);
            socket.joinGroup(grupo);
            //this.socket.setSoTimeout(5000);
            transporte = new Transporte(socket, grupo, 6789);

        } catch (IOException ex) {
            Logger.getLogger(Proceso.class.getName()).log(Level.SEVERE, null, ex);
        }
      

    }

    public void enviar_A_Mayores() {

        if (procesos_mayor_id.isEmpty()) {
            System.out.println(nombre + "¡¡¡ Es elegido como coordinador con ID= " + Id + " !!!");
            System.out.println(nombre + " Emite mensaje de coordinador");
            Id_coordinador = Id;
            Mensaje M_coordinador = new Mensaje(Id, -1, Tipos_mensaje.COORDINADOR);
            transporte.envio(M_coordinador);

        } else {
               participante = true;
            for (Integer id_mayor : procesos_mayor_id) {
                Mensaje M = new Mensaje(Id, id_mayor, Mensaje.Tipos_mensaje.ELECCION);
                transporte.envio(M);

            }
           System.out.println(nombre + " Envia a mensaje de elecciona procesos:" + Lista_toString(procesos_mayor_id));
            
            esperar_eleccion();

        }

    }
    

    public void esperar_eleccion() {

        Mensaje M = new Mensaje();
        int cont=0;
        if(participante){
        while(cont<procesos_mayor_id.size()){
         try {
               System.out.println("Esperando mensajes OK");
                M = transporte.recepcion();
            } catch (IOException ex) {
                Logger.getLogger(Proceso.class.getName()).log(Level.SEVERE, null, ex);
            }
        if(M.getTipo().equals(Mensaje.Tipos_mensaje.OK )&& M.getId_destinatario()==Id){
            System.out.println("Se recibe mensaje de OK del Proceso"+M.getId_remetiente());
        cont++;
        }
        
        }
            while (true) {
                try {
               System.out.println("Esperando mensajes CORDINADOR");
                M = transporte.recepcion();
            } catch (IOException ex) {
                Logger.getLogger(Proceso.class.getName()).log(Level.SEVERE, null, ex);
            }
              if (M.getTipo() == Tipos_mensaje.COORDINADOR &&  M.getId_remetiente()!=Id ) {
                participante = false;
                Id_coordinador = M.getId_remetiente();
                System.out.println(nombre + " actualiza su coordinador es ahora Proceso" + Id_coordinador);
                break;
            }
                
            }
        
        }
        else{
           while (true) {
            try {
               System.out.println("Esperando mensajes...");
                M = transporte.recepcion();
            } catch (IOException ex) {
                Logger.getLogger(Proceso.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (M.getId_destinatario() == Id && M.getTipo() == Tipos_mensaje.ELECCION) {
                System.out.println("Se recibe mensaje de eleccion con ID: "+M.getId_remetiente());
                System.out.println("Presion enter para continuar?");
                Scanner s = new Scanner(System.in);
                 s.nextLine();
                 System.out.println("Envia mensaje de OK al proceso "+M.getId_remetiente());
                 Mensaje M_OK= new Mensaje(Id, M.getId_remetiente(), Mensaje.Tipos_mensaje.OK);
                transporte.envio(M_OK);
                 if(Id!=Id_coordinador)
                 { enviar_A_Mayores();}
                 else{
                 Mensaje M_CORD= new Mensaje(Id_coordinador, M.getId_remetiente(), Tipos_mensaje.COORDINADOR);
                 transporte.envio(M_CORD);
                 
                 }

            } 

        }
        
        
        }
        
        
     

    }

    public int getId_proceso() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getCoordinador() {
        return Id_coordinador;
    }

    public void setCoordinador(int Id_coordinador) {
        this.Id_coordinador = Id_coordinador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void escribir_Log(String linea) {
        System.out.println("Proceso" + Id + " " + linea);
        Log = "\n" + "Proceso" + Id + " " + linea;

    }

    public int getId_coordinador() {
        return Id_coordinador;
    }

    public void setId_coordinador(int Id_coordinador) {
        this.Id_coordinador = Id_coordinador;
    }

    public MulticastSocket getSocket() {
        return socket;
    }

    public void setSocket(MulticastSocket socket) {
        this.socket = socket;
    }

    public Transporte getTransporte() {
        return transporte;
    }

    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }

    public boolean esParticipante() {
        return participante;
    }

    public void setParticipante(boolean participante) {
        this.participante = participante;
    }

    public String getLog() {
        return Log;
    }

    public void setLog(String Log) {
        this.Log = Log;
    }

    public String Lista_toString(ArrayList<Integer> lista) {
        String string = " =[ ";
        if (lista != null) {
            int i = 0;
            for (Integer P : lista) {
                string += "Proceso" + P;
                if (i < lista.size() - 1) {
                    string += ",";
                }
                i++;
            }

        }

        return string += "]";

    }

}
