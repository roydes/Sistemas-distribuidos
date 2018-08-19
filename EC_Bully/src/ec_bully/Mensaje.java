/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec_bully;

import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author ROY
 */
public class Mensaje implements Serializable {
    int id_remetiente; 
    int id_destinatario;
    Tipos_mensaje tipo;
   enum Tipos_mensaje { DESCUBRIMIENTO, ELECCION,OK,COORDINADOR; }
   
    
    
    
    public Mensaje(int id_remetiente,int id_vecino,Mensaje.Tipos_mensaje tipo) {
        this.id_remetiente=id_remetiente;
        this.id_destinatario= id_vecino;
        this.tipo=tipo;
        
    }
    public Mensaje() {
      
    }    

    public int getId_destinatario() {
        return id_destinatario;
    }

    public void setId_destinatario(int id_destinatario) {
        this.id_destinatario = id_destinatario;
    }


   

    public int getId_remetiente() {
        return id_remetiente;
    }

    public void setId_remetiente(int id_remetiente) {
        this.id_remetiente = id_remetiente;
    }

    public Tipos_mensaje getTipo() {
        return tipo;
    }

    public void setTipo(Tipos_mensaje tipo) {
        this.tipo = tipo;
    }
 
    
}
