/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_final.Modulo_MBCP;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author ROY
 */
public class Mensaje_MBCP implements Serializable {
    String nombre;
    Tupla ktk;       
    Object datos;
    HashSet<Tupla> H;
    public enum Tipos_mensaje { TEXTO,VIDEO,AUDIO; }
    Tipos_mensaje tipo;
   
    
    
    //ArrayList<Tupla> procesos;

    public Mensaje_MBCP(String nombre, Tupla kt_k, Object datos, HashSet<Tupla> H, Tipos_mensaje tipo) {
        this.nombre = nombre;
        this.ktk = kt_k;
        this.datos = datos;
        this.H = H;
        this.tipo=tipo;
        //this.procesos=procesos;
    }
    public Mensaje_MBCP() {
      
    }    

    public String getNombre() {
        return nombre;
    }

    public Tupla getKtk() {
        return ktk;
    }

    public Object getDatos() {
        return datos;
    }

    public HashSet<Tupla> getH() {
        return H;
    }

    public Tipos_mensaje getTipo() {
        return tipo;
    }

    public void setTipo(Tipos_mensaje tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.ktk);
        hash = 37 * hash + Objects.hashCode(this.datos);
        hash = 37 * hash + Objects.hashCode(this.H);
        hash = 37 * hash + Objects.hashCode(this.tipo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mensaje_MBCP other = (Mensaje_MBCP) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.ktk, other.ktk)) {
            return false;
        }
        if (!Objects.equals(this.datos, other.datos)) {
            return false;
        }
        if (!Objects.equals(this.H, other.H)) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        return true;
    }

   
          public String getHm_String(HashSet<Tupla> Hm) {
        String string="Hm ={ ";
        int i= 0;
        for (Tupla tupla : Hm) {
            string+=tupla;
            if(i<Hm.size()-1)
                string+=",";
            i++;
        }
        
        return string+="}";

    }

    @Override
    public String toString() {
        return nombre + "= (" + ktk.getI() + ", " +ktk.D+", datos="+ datos + ", H=" + getHm_String(H) + ')';
    }
    
    
    
}
