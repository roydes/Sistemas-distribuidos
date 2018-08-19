/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_final;

/**
 *
 * @author ROY
 */
public class Proyecto_Final {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
     // TODO code application logic here "228.5.6.7"
     String grupo= "localhost" ;
         //    int puerto=6789;
        //Vista_Proceso v=new  Vista_Proceso();
       // v.main(args,0,0,grupo,6789);
             
        Vista_Proceso v1=new  Vista_Proceso();
        v1.main(args,0,0,grupo,6790);
        /*Vista_Proceso v2=new  Vista_Proceso();
        /*v2.main(args,0,2,grupo,puerto);
       /* Vista_Proceso v3=new  Vista_Proceso();
        v3.main(args,2,3,grupo,puerto);*/
    }
    
}
