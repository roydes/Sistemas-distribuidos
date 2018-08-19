/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_final.Modulo_MBCP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import proyecto_final.Utiles.Transporte;
import proyecto_final.Vista_Proceso;

/*
 * Clase encargada de implementar el protocolo de comunicación causal
 * 
 */

/**
 *
 * @author ROY
 */
public class MBCP {
    //identificador del proceso
    int j;
    HashSet<Tupla> CI;
    int[] VT;
    ArrayList<Mensaje_MBCP> mensajes_en_espera;
    ArrayList<Mensaje_MBCP> mensajes_entregados;
    Transporte transporte;
    String log;
    Vista_Proceso vista;
    
    //Mensaje_MBCP.Tipos_mensaje Tipo_recepcion;

    public MBCP(int j, HashSet<Tupla> CI, int[] VT) {
        this.j = j;
        this.CI = CI;
        this.VT = VT;
        log="";
    }
        public MBCP(int j, int cantidad_procesos, Transporte t, Vista_Proceso vista) {
           this.vista= vista; 
            log="";
        this.j = j;
        this.CI = new HashSet<>();
        this.VT = new int[cantidad_procesos];
        Arrays.fill(VT,0);
            System.out.println(getVT_String());
        //this.VT.stream().forEach( i->{i=0;});
        mensajes_en_espera= new ArrayList<>();
        mensajes_entregados= new ArrayList<>();
        transporte= t;
            System.out.println("Inicio con " +getVT_String()); 
    }
    public void recepcion() throws IOException {
       log="";
    
        Iterator<Mensaje_MBCP> iterador= mensajes_en_espera.iterator();
         while(iterador.hasNext()){
          if(recepcionar(iterador.next(),"lista de mensajes en espera")){
            iterador.remove();
            }
         }
       Mensaje_MBCP M= transporte.recepcion();
       
       if(M.getKtk().getI()!=j)
       recepcionar(M,"buffer de entrada");    
    }
    
  /*
    public boolean es_para_este_proceso(Mensaje_MBCP M){
        for (Tupla proceso : M.getProcesos()) {
           if(j==proceso.getI())
           { 
               System.out.println(M+"  se recibe en P"+j);
               return true;
           }
        }
        System.out.println(M+" no es para P"+j);
        return false;
    
    }
    */
    private boolean recepcionar(Mensaje_MBCP M,String entrada_mensajes){
     // Primera condición
     
     System.out.println("Recepcionando mensajes de "+entrada_mensajes+" de tipo "+ M.getTipo());
     log+="Recepcionando mensajes de "+entrada_mensajes+"\n";
     int k=M.getKtk().getI();
     int tk=M.getKtk().getD();
     HashSet<Tupla> Hm=M.getH();
     boolean primera_condicion=false;
     boolean segunda_condicion=true;
        System.out.println(getVT_String());
       primera_condicion= (tk==VT[k]+1);
        for (Tupla l_tl : Hm) {
            int l= l_tl.getI();
            int tl= l_tl.getD();
            if(!(tl<=VT[l])){
            segunda_condicion=false;
            break;
             }
        }
        if(primera_condicion && segunda_condicion){
      System.out.println("Entrega "+M+" en P"+j);

        entregar(M);
        log+="Se entrega "+M+"\n";
        log+=getVT_String(k)+"+"+getVT_String(k)+"+1 = ";
        VT[k]=VT[k]+1;
        log+=getVT_String()+"\n";
        Iterator<Tupla> iterador=CI.iterator();
         while(iterador.hasNext()){
         Tupla ci= iterador.next();
         int s= ci.getI();
                if(s==k)
                {log+="s==k para un ci"+ci+ "de CI \n";
                  log+=getCI_String()+"/"+ci+"\n\n";
                    iterador.remove();
                }
         }
        log+="CI ∪ (k,tk)= "+getCI_String()+"∪"+ M.getKtk()+ "\n";
        CI.add(M.getKtk());
        log+=getCI_String()+"\n";
        log+="CI/Hm= "+getCI_String()+"/"+ getHm_String(Hm)+"\n";
        CI.removeAll(Hm);
        log+=getCI_String()+"\n\n";
        }
        else {
        log+="En espera: "+M+"\n\n";    
        esperar(M);
        
        }
     
    
    this.vista.getConsola_general().setText(log+"\n\n"+this.vista.getConsola_general().getText());
    
    return primera_condicion && segunda_condicion;
    }    

     public Mensaje_MBCP enviar(Object datos, String nombre, Mensaje_MBCP.Tipos_mensaje tipo){
      System.out.println("Enviando "+tipo); 
     log+="VT["+j+"]= VT["+j+"]+1 \n";
    VT[j]=VT[j]+1;
    log+=getVT_String()+"\n";
    HashSet<Tupla> H= new HashSet<>(CI);
    Tupla k_tk = new Tupla(j, VT[j]);
       
    Mensaje_MBCP M= new Mensaje_MBCP(nombre, k_tk, datos, H,  tipo);
    transporte.envio_multicast(M); 
    
    log+=M+"\n"; 
    CI.clear();
    log+=getCI_String()+"\n";
    System.out.println(log);
    this.vista.getConsola_general().setText(log+"\n\n"+this.vista.getConsola_general().getText());
    return M;
    }
   
     public void renviar(Mensaje_MBCP M, ArrayList<Tupla> procesos){
   
    System.out.println("Enviando a procesos "+procesos);
    //M.setProcesos(procesos);
    transporte.envio(M);  
     log+="Renvio de "+M+"\n";
    log+="Para procesos "+procesos+"\n\n";
         
    }
    
    private void entregar(Mensaje_MBCP M) {
        mensajes_entregados.add(M);
        Mensaje_MBCP.Tipos_mensaje tipo= M.getTipo();

        if(tipo.equals(Mensaje_MBCP.Tipos_mensaje.AUDIO)){
        byte[]audio= (byte[])M.getDatos();
        vista.reproducir_audio(audio);
        vista.getConsola_recibos_audio().setText("♪ ♫ ♪ ♫ ... \n"+ vista.getConsola_recibos_audio().getText());
        }
          if(tipo.equals(Mensaje_MBCP.Tipos_mensaje.VIDEO)){
              
             byte[]imagen= (byte[])M.getDatos(); 
          vista.reproducir_video(imagen);
          }
          
          
          if(tipo.equals(Mensaje_MBCP.Tipos_mensaje.TEXTO)){
               String texto= new String((byte[])M.getDatos());
               vista.reproducir_texto(texto);
              
          }
        
    }
    

    private void esperar(Mensaje_MBCP M) {
        if(!this.mensajes_en_espera.contains(M))
        {mensajes_en_espera.add(M);}
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public HashSet<Tupla> getCI() {
        return CI;
    }

    public int[] getVT() {
        return VT;
    }

    public void setVT(int[] VT) {
        this.VT = VT;
    }
    
    public String getVT_String(int index) {
        String vt_string="VT(P"+j+")["+index+"] =(";
        for (int i = 0; i < VT.length; i++) {
            vt_string+= VT[i];
            if(i<VT.length-1)
                vt_string+=",";
            
        }
        return vt_string+=")";

    }
        public String getVT_String() {
        String vt_string="VT(P"+j+")=(";
        for (int i = 0; i < VT.length; i++) {
            vt_string+= VT[i];
            if(i<VT.length-1)
                vt_string+=",";
            
        }
        return vt_string+=")";

    }
    public String getCI_String() {
        String string="CI(P"+j+")={";
        int i= 0;
        for (Tupla tupla : CI) {
            string+=tupla;
            if(i<CI.size()-1)
                string+=",";
            i++;
        }
        
        return string+="}";

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
        
    public ArrayList<Mensaje_MBCP> getMensajes_en_espera() {
        return mensajes_en_espera;
    }

    public ArrayList<Mensaje_MBCP> getMensajes_entregados() {
        return mensajes_entregados;
    }

    public String getLog() {
        return log;
    }
   

    
    
    
}
