/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec_bully;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ROY
 */
public class Proceso_EC1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("Iniciando Proceso Bully...");
        System.out.println("Introducsca ID");
        Scanner s= new Scanner(System.in);
        ArrayList<Integer> procesos_mayor_id= new ArrayList<>();

        int id=s.nextInt(); 
        System.out.println("Introducsca la cantidad de procesos con mayor ID");
        int cantidad =s.nextInt();
        for (int i = 0; i < cantidad; i++) {
            System.out.println("Introdusca el ID:");
            int Id =s.nextInt();
            procesos_mayor_id.add(Id);
        }
        Proceso p = new Proceso(id,procesos_mayor_id);
        s.nextLine();
        System.out.println("Introducsca C para comenzar elección o E para esperar la eleccion");
        String entrada=s.nextLine();
        if("C".equals(entrada))
            p.enviar_A_Mayores();
        else if("E".equals(entrada)){
        p.esperar_eleccion();
        }

        
    }
    
}
