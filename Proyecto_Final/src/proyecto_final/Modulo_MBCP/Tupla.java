/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_final.Modulo_MBCP;

import java.io.Serializable;

/**
 *
 * @author ROY
 */
public class Tupla implements Serializable{
    int I;
    int D;

    public Tupla(int I, int D) {
        this.I = I;
        this.D = D;
    }

    public int getI() {
        return I;
    }

    public int getD() {
        return D;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.I;
        hash = 37 * hash + this.D;
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
        final Tupla other = (Tupla) obj;
        if (this.I != other.I) {
            return false;
        }
        if (this.D != other.D) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "(" + I +" , "+ D + ')';
    }
    
    
}
