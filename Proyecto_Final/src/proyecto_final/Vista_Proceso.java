/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_final;

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.cpp.opencv_core;
import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.imageio.ImageIO;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import proyecto_final.Modulo_MBCP.MBCP;
import proyecto_final.Modulo_MBCP.Tupla;
import proyecto_final.Utiles.Transporte;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import proyecto_final.Audio.Reproductor_Audio;

import proyecto_final.Modulo_MBCP.Mensaje_MBCP;
import proyecto_final.Utiles.Utiles_Video;

/**
 *
 * @author ROY
 */
public class Vista_Proceso extends javax.swing.JFrame {

    private MBCP mbcp;
    private MBCP mbcp_audio;
    private MBCP mbcp_video;
    private Transporte transporte;
    private DatagramSocket socket;
    private ArrayList<Tupla> lista_procesos;
    private ArrayList<String> direcciones_puerto;
    private int j;
    private int cantidad_procesos;
    private InetAddress direccion_ip;
    private int puerto;
    private boolean direcciones_cargadas;
    private boolean parar;

    public Vista_Proceso() {
    }

    public Vista_Proceso(int j, int cant_proceso, String ip, int puerto) {
        this.j = j;
        this.cantidad_procesos = cant_proceso;
        this.puerto = puerto;
         parar= false;
        initComponents();
         this.lista_procesos = new ArrayList<>();
         this.direcciones_puerto= new ArrayList<>();
         
         //direcciones_puerto.add("192.168.1.69,6789");
         //direcciones_puerto.add("192.168.1.69,6790");
         //direcciones_puerto.add("192.168.1.80,6789");
         //direcciones_puerto.add("192.168.1.80,6790");

        try {
            direccion_ip = InetAddress.getByName(ip);

            //inicio_multicast();
            //192.168.1.73
              
            socket = new DatagramSocket(this.puerto);

            //inicio_UDP();
            System.out.println(socket);
            transporte = new Transporte(socket, direccion_ip, this.puerto+this.j,direcciones_puerto);
        } catch (IOException ex) {
            Logger.getLogger(Vista_Proceso.class.getName()).log(Level.SEVERE, null, ex);
        }

        descubrir_proceso(puerto);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.decode("#f1f1f1"));
        this.Contenedor_TabbedPane.setBounds(50, 70, this.getWidth(), this.getHeight());

    }

    private void descubrir_proceso(int puerto) {
        lista_procesos = new ArrayList<>();
        for (int i = 0; i < cantidad_procesos; i++) {
            if (i != j) {
                lista_procesos.add(new Tupla(i, puerto));
            }

        }

    }

    private void inicio_multicast() throws IOException {
        MulticastSocket s = new MulticastSocket(this.puerto);
        socket = s;
        //this.socket.setSoTimeout(5000);
        ((MulticastSocket) socket).joinGroup(direccion_ip);

    }

    private void inicio_UDP() throws IOException {
        DatagramSocket s = new DatagramSocket(this.puerto);
        socket = s;

    }

    public int getCantidad_procesos() {
        return cantidad_procesos;
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        Contenedor_TabbedPane = new javax.swing.JTabbedPane();
        Envio_texto = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        consola_envio_text = new javax.swing.JTextArea();
        Comenzar_envio_texto = new javax.swing.JButton();
        envio_audio = new javax.swing.JPanel();
        comenzar_envio_audio = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        consola_envio_audio = new javax.swing.JTextArea();
        envio_video = new javax.swing.JPanel();
        pantalla_envio_video = new javax.swing.JLabel();
        comenzar_enviar_video = new javax.swing.JButton();
        recepcion_texto = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        Consola_recibos_texto = new javax.swing.JTextArea();
        recepcion_audio = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        Lista_mensajes_espera_audio = new javax.swing.JList<>();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        Consola_recibos_audio = new javax.swing.JTextArea();
        recepcion_video = new javax.swing.JPanel();
        pantalla_recepcion_video = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        Lista_mensajes_espera = new javax.swing.JList<>();
        jLabel7 = new javax.swing.JLabel();
        VT_label = new javax.swing.JLabel();
        CI_label = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        CI_textArea = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        VT_textArea = new javax.swing.JTextArea();
        jScrollPane11 = new javax.swing.JScrollPane();
        consola_general = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        Proceso_label = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        Archivo_Menu = new javax.swing.JMenu();
        cargar_direcciones = new javax.swing.JMenuItem();
        Configuracion_Menu = new javax.swing.JMenu();
        modo_video = new javax.swing.JRadioButtonMenuItem();
        modo_audio = new javax.swing.JRadioButtonMenuItem();
        modo_texto = new javax.swing.JRadioButtonMenuItem();
        modo_pasivo = new javax.swing.JRadioButtonMenuItem();
        Configurar_escenario = new javax.swing.JMenuItem();
        Ejecucion_menu = new javax.swing.JMenu();
        parar_todo = new javax.swing.JMenuItem();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Contenedor_TabbedPane.setPreferredSize(new java.awt.Dimension(1080, 520));

        consola_envio_text.setColumns(20);
        consola_envio_text.setRows(5);
        jScrollPane1.setViewportView(consola_envio_text);

        Comenzar_envio_texto.setText("Comenzar");
        Comenzar_envio_texto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comenzar_envio_textoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Envio_textoLayout = new javax.swing.GroupLayout(Envio_texto);
        Envio_texto.setLayout(Envio_textoLayout);
        Envio_textoLayout.setHorizontalGroup(
            Envio_textoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Envio_textoLayout.createSequentialGroup()
                .addGroup(Envio_textoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Envio_textoLayout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Envio_textoLayout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(Comenzar_envio_texto, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        Envio_textoLayout.setVerticalGroup(
            Envio_textoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Envio_textoLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Comenzar_envio_texto, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        Contenedor_TabbedPane.addTab("Envío de Texto", Envio_texto);

        comenzar_envio_audio.setText("Comenzar ");
        comenzar_envio_audio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comenzar_envio_audioActionPerformed(evt);
            }
        });

        consola_envio_audio.setColumns(20);
        consola_envio_audio.setRows(5);
        jScrollPane2.setViewportView(consola_envio_audio);

        javax.swing.GroupLayout envio_audioLayout = new javax.swing.GroupLayout(envio_audio);
        envio_audio.setLayout(envio_audioLayout);
        envio_audioLayout.setHorizontalGroup(
            envio_audioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(envio_audioLayout.createSequentialGroup()
                .addGroup(envio_audioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(envio_audioLayout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(comenzar_envio_audio, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(envio_audioLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(116, Short.MAX_VALUE))
        );
        envio_audioLayout.setVerticalGroup(
            envio_audioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(envio_audioLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(comenzar_envio_audio, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(241, Short.MAX_VALUE))
        );

        Contenedor_TabbedPane.addTab("Envío de Audio", envio_audio);

        pantalla_envio_video.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        comenzar_enviar_video.setText("Comenzar");
        comenzar_enviar_video.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comenzar_enviar_videoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout envio_videoLayout = new javax.swing.GroupLayout(envio_video);
        envio_video.setLayout(envio_videoLayout);
        envio_videoLayout.setHorizontalGroup(
            envio_videoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, envio_videoLayout.createSequentialGroup()
                .addContainerGap(166, Short.MAX_VALUE)
                .addGroup(envio_videoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(comenzar_enviar_video, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pantalla_envio_video, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(150, 150, 150))
        );
        envio_videoLayout.setVerticalGroup(
            envio_videoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(envio_videoLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(pantalla_envio_video, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(comenzar_enviar_video, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(277, Short.MAX_VALUE))
        );

        Contenedor_TabbedPane.addTab("Envío de Video", envio_video);

        Consola_recibos_texto.setColumns(20);
        Consola_recibos_texto.setRows(5);
        jScrollPane6.setViewportView(Consola_recibos_texto);

        javax.swing.GroupLayout recepcion_textoLayout = new javax.swing.GroupLayout(recepcion_texto);
        recepcion_texto.setLayout(recepcion_textoLayout);
        recepcion_textoLayout.setHorizontalGroup(
            recepcion_textoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recepcion_textoLayout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );
        recepcion_textoLayout.setVerticalGroup(
            recepcion_textoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recepcion_textoLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        Contenedor_TabbedPane.addTab("Recepción de Texto", recepcion_texto);

        jScrollPane8.setViewportView(Lista_mensajes_espera_audio);

        jLabel8.setText("Mensajes en espera");

        Consola_recibos_audio.setColumns(20);
        Consola_recibos_audio.setRows(5);
        jScrollPane9.setViewportView(Consola_recibos_audio);

        javax.swing.GroupLayout recepcion_audioLayout = new javax.swing.GroupLayout(recepcion_audio);
        recepcion_audio.setLayout(recepcion_audioLayout);
        recepcion_audioLayout.setHorizontalGroup(
            recepcion_audioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recepcion_audioLayout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(262, 262, 262)
                .addGroup(recepcion_audioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        recepcion_audioLayout.setVerticalGroup(
            recepcion_audioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recepcion_audioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGroup(recepcion_audioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(recepcion_audioLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(recepcion_audioLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(201, Short.MAX_VALUE))
        );

        Contenedor_TabbedPane.addTab("Recepción de Audio", recepcion_audio);

        pantalla_recepcion_video.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout recepcion_videoLayout = new javax.swing.GroupLayout(recepcion_video);
        recepcion_video.setLayout(recepcion_videoLayout);
        recepcion_videoLayout.setHorizontalGroup(
            recepcion_videoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recepcion_videoLayout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(pantalla_recepcion_video, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(161, Short.MAX_VALUE))
        );
        recepcion_videoLayout.setVerticalGroup(
            recepcion_videoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recepcion_videoLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(pantalla_recepcion_video, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(372, Short.MAX_VALUE))
        );

        Contenedor_TabbedPane.addTab("Recepción de Video", recepcion_video);

        jScrollPane7.setViewportView(Lista_mensajes_espera);

        jLabel7.setText("Mensajes en espera");

        VT_label.setText("VT");

        CI_label.setText("CI");

        CI_textArea.setColumns(20);
        CI_textArea.setRows(5);
        jScrollPane3.setViewportView(CI_textArea);

        VT_textArea.setColumns(20);
        VT_textArea.setRows(5);
        jScrollPane5.setViewportView(VT_textArea);

        consola_general.setColumns(20);
        consola_general.setRows(5);
        jScrollPane11.setViewportView(consola_general);

        jLabel1.setText("Consola General");

        Proceso_label.setText("Proceso:");

        Archivo_Menu.setText("Archivo");

        cargar_direcciones.setText("Cargar direcciones ");
        cargar_direcciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargar_direccionesActionPerformed(evt);
            }
        });
        Archivo_Menu.add(cargar_direcciones);

        jMenuBar1.add(Archivo_Menu);

        Configuracion_Menu.setText("Configuración");

        modo_video.setText("Modo video");
        modo_video.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modo_videoActionPerformed(evt);
            }
        });
        Configuracion_Menu.add(modo_video);

        modo_audio.setText("Modo audio");
        modo_audio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modo_audioActionPerformed(evt);
            }
        });
        Configuracion_Menu.add(modo_audio);

        modo_texto.setText("Modo texto");
        modo_texto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modo_textoActionPerformed(evt);
            }
        });
        Configuracion_Menu.add(modo_texto);

        modo_pasivo.setText("Modo pasivo");
        modo_pasivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modo_pasivoActionPerformed(evt);
            }
        });
        Configuracion_Menu.add(modo_pasivo);

        Configurar_escenario.setText("Configurar escenario");
        Configurar_escenario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Configurar_escenarioActionPerformed(evt);
            }
        });
        Configuracion_Menu.add(Configurar_escenario);

        jMenuBar1.add(Configuracion_Menu);

        Ejecucion_menu.setText("Ejecución");

        parar_todo.setText("Detener");
        parar_todo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parar_todoActionPerformed(evt);
            }
        });
        Ejecucion_menu.add(parar_todo);

        jMenuBar1.add(Ejecucion_menu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Contenedor_TabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Proceso_label, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane11)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                                    .addComponent(VT_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CI_label, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(51, 51, 51))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(Proceso_label)
                        .addGap(18, 18, 18)
                        .addComponent(Contenedor_TabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(VT_label)
                            .addComponent(CI_label)
                            .addComponent(jLabel7))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Configurar_escenarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Configurar_escenarioActionPerformed
       
        if(direcciones_cargadas){
        JPanel panel = new JPanel();
        JTextField cantidad_proceso = new JTextField(5);
        JTextField id_proceso = new JTextField(5);
        panel.add(new JLabel("Id Proceso"));
        panel.add(id_proceso);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Cantidad de Procesos"));
        panel.add(cantidad_proceso);

        int result = JOptionPane.showConfirmDialog(null, panel, "Configuración de Escenario", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            this.j = Integer.valueOf(id_proceso.getText());
            this.cantidad_procesos = Integer.valueOf(cantidad_proceso.getText());
            System.out.println(this.cantidad_procesos);
            descubrir_proceso(puerto);
            mbcp = new MBCP(this.j, this.cantidad_procesos, transporte, this);
            Proceso_label.setText("Proceso "+j);
            CI_label.setText(mbcp.getCI_String());
            VT_label.setText(mbcp.getVT_String());
            recepcionar();
            // mbcp_audio = new MBCP(this.j, this.cantidad_procesos, transporte, Mensaje_MBCP.Tipos_mensaje.AUDIO);
            //mbcp_video = new MBCP(this.j, this.cantidad_procesos, transporte, Mensaje_MBCP.Tipos_mensaje.VIDEO);

        }
        
        
        }
        else{
         JOptionPane.showMessageDialog(this,"Debe cargar las direcciones y puertos del escenario \n"
                + "Por favor diríjase a: Archivo->Cargar direcciones");
        
        }
        
        
    }//GEN-LAST:event_Configurar_escenarioActionPerformed
    private void modo_videoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modo_videoActionPerformed
        // TODO add your handling code here:
        pantalla_envio_video.setEnabled(true);
        comenzar_enviar_video.setEnabled(true);
        modo_audio.setSelected(false);
        modo_texto.setSelected(false);
        modo_pasivo.setSelected(false);

        consola_envio_audio.setEnabled(false);
        consola_envio_text.setEnabled(false);
        pantalla_recepcion_video.setEnabled(false);
        Consola_recibos_audio.setEnabled(true);
        Consola_recibos_texto.setEnabled(true);
        
        comenzar_envio_audio.setEnabled(false);
        Comenzar_envio_texto.setEnabled(false);
        


    }//GEN-LAST:event_modo_videoActionPerformed

    private void comenzar_envio_audioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comenzar_envio_audioActionPerformed
        capturar_enviar_audio();
        //recepcionar();

    }//GEN-LAST:event_comenzar_envio_audioActionPerformed

    private void Comenzar_envio_textoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Comenzar_envio_textoActionPerformed
        // TODO add your handling code here:
        enviar_texto();
       // recepcionar() ;
       
    }//GEN-LAST:event_Comenzar_envio_textoActionPerformed

    private void comenzar_enviar_videoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comenzar_enviar_videoActionPerformed
        capturar_enviar_video();
       // recepcionar() ;

    }//GEN-LAST:event_comenzar_enviar_videoActionPerformed

    private void modo_audioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modo_audioActionPerformed
        // TODO add your handling code here:
        
        comenzar_envio_audio.setEnabled(true);
        consola_envio_audio.setEnabled(true);
        Consola_recibos_texto.setEnabled(true);
        pantalla_recepcion_video.setEnabled(true);
        modo_video.setSelected(false);
        modo_texto.setSelected(false);
        modo_pasivo.setSelected(false);

        Envio_texto.setEnabled(false);
        pantalla_envio_video.setEnabled(false);
        consola_envio_text.setEnabled(false);
        Consola_recibos_audio.setEnabled(false);
        
        comenzar_enviar_video.setEnabled(false);
        Comenzar_envio_texto.setEnabled(false);
        
    }//GEN-LAST:event_modo_audioActionPerformed

    private void modo_textoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modo_textoActionPerformed
        // TODO add your handling code here:
        Comenzar_envio_texto.setEnabled(true);
        consola_envio_text.setEnabled(true);
        modo_video.setSelected(false);
        modo_audio.setSelected(false);
        modo_pasivo.setSelected(false);
        
        pantalla_envio_video.setEnabled(false);
        pantalla_recepcion_video.setEnabled(true);
        consola_envio_audio.setEnabled(false);
        Consola_recibos_texto.setEnabled(false);
        comenzar_enviar_video.setEnabled(false);
        comenzar_envio_audio.setEnabled(false);
    }//GEN-LAST:event_modo_textoActionPerformed

    private void modo_pasivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modo_pasivoActionPerformed
        // TODO add your handling code here:
        Comenzar_envio_texto.setEnabled(false);
        consola_envio_text.setEnabled(false);
        modo_video.setSelected(false);
        modo_audio.setSelected(false);
        modo_texto.setSelected(false); 
       
        comenzar_enviar_video.setEnabled(false);
        comenzar_envio_audio.setEnabled(false);
        Comenzar_envio_texto.setEnabled(false);
        consola_envio_audio.setEnabled(false);
        pantalla_envio_video.setEnabled(false);
        
        pantalla_recepcion_video.setEnabled(true);
        Consola_recibos_texto.setEnabled(true);
        Consola_recibos_audio.setEnabled(true);
        
    }//GEN-LAST:event_modo_pasivoActionPerformed

    private void cargar_direccionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargar_direccionesActionPerformed
        // TODO add your handling code here:
        direcciones_puerto.clear();
        JFileChooser selector_archivo = new JFileChooser();
      selector_archivo.setFileSelectionMode(JFileChooser.FILES_ONLY);

      FileNameExtensionFilter filter = new FileNameExtensionFilter(".ipp", "ipp");
      selector_archivo.setFileFilter(filter);
       int valor = selector_archivo.showOpenDialog(this);

        if (valor == JFileChooser.APPROVE_OPTION) {
            File archivo = selector_archivo.getSelectedFile();
            String linea;
            try {
              FileReader  Fr = new FileReader(archivo);
                BufferedReader buffered_archivo = new BufferedReader(Fr);
                while((linea = buffered_archivo.readLine()) !=null ){
                direcciones_puerto.add(linea);    
                    System.out.println(linea);
                }
                direcciones_cargadas= true;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Vista_Proceso.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Vista_Proceso.class.getName()).log(Level.SEVERE, null, ex);
            }
              
        } 

     
    }//GEN-LAST:event_cargar_direccionesActionPerformed

    private void parar_todoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parar_todoActionPerformed
        parar=true;
    }//GEN-LAST:event_parar_todoActionPerformed

    public void enviar_texto(){
        
       ArrayList<String> textos= new ArrayList<>();
       textos.add("Hola soy proceso "+j+" te estoy enviando un texto");
       textos.add("Estas? soy proceso "+j+" te estoy enviando un texto");
       textos.add("Como estas? soy proceso "+j+" te estoy enviando un texto");
       textos.add("What's up? soy proceso "+j+" te estoy enviando un texto");
       textos.add("Bonjour, comment allez-vous? soy proceso "+j+" te estoy enviando un texto");
       textos.add("Oi, como vai?  soy proceso "+j+" te estoy enviando un texto");
       textos.add("Hallo, wie geht es Ihnen?  soy proceso "+j+" te estoy enviando un texto");
       textos.add("你好你怎麼樣? soy proceso "+j+" te estoy enviando un texto");
       textos.add("こんにちはお元気ですか? soy proceso "+j+" te estoy enviando un texto");
       textos.add("Qué volá acereeee?  soy proceso "+j+" te estoy enviando un texto");



     Thread hilo_envio = new Thread() {

            public void run() {
                while(!parar) {
                    String texto= textos.get((int) (Math.random() * 10))+"\n";
                    mbcp.enviar(texto.getBytes(), "MT", Mensaje_MBCP.Tipos_mensaje.TEXTO);
                    consola_envio_text.setText(texto+consola_envio_text.getText());
                    System.out.println("Transmitiendo texto...");
                    

                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Vista_Proceso.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

        };
        hilo_envio.start();
    
    }
    public void capturar_enviar_audio() {
        try {
            System.out.println("Capturando audio");
            final AudioFormat format_audio = getFormato_Audio();
            DataLine.Info info = new DataLine.Info(
                    TargetDataLine.class, format_audio);
            final TargetDataLine linea_sonido = (TargetDataLine) AudioSystem.getLine(info);
            linea_sonido.open(format_audio);
            linea_sonido.start();
            Runnable runner = new Runnable() {
                int bufferSize = (int) format_audio.getSampleRate()
                        * format_audio.getFrameSize();
                byte buffer[] = new byte[bufferSize];

                ByteArrayOutputStream out;
                Reproductor_Audio r = new Reproductor_Audio();

                public void run() {

                    out = new ByteArrayOutputStream();
                    //boolean running = true;
                    try {
                        while (!parar) {

                            int count = linea_sonido.read(buffer, 0, buffer.length);
                            if (count > 0) {
                                ///out.write(buffer, 0, count);
                                   
                                consola_envio_audio.setText("♪ ♫ ♪ ♫ ... \n"+ consola_envio_audio.getText());
                                mbcp.enviar(buffer, "MA", Mensaje_MBCP.Tipos_mensaje.AUDIO);
                                 //r.reproducir(buffer);
                                Thread.sleep(40);
                            }
                        }
                        out.close();
                    } catch (IOException e) {
                        System.out.println("Excepcion de IO" + e.getMessage());
                        System.exit(-1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Vista_Proceso.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            Thread hilo_captura = new Thread(runner);
            hilo_captura.start();
        } catch (LineUnavailableException e) {
            System.out.println("La linea no está disponible " + e.getMessage());
            System.exit(-2);
        }
    }
    public void capturar_enviar_video() {
        Thread envio_video = new Thread() {
            public void run() {
                CvCapture captura = opencv_highgui.cvCreateCameraCapture(0);
                opencv_highgui.cvSetCaptureProperty(captura, opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, 120);
                opencv_highgui.cvSetCaptureProperty(captura, opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, 200);
                IplImage imagenCapturada = opencv_highgui.cvQueryFrame(captura);
                System.out.println("Enviando video");

                byte[] data = null;

                while ((imagenCapturada = opencv_highgui.cvQueryFrame(captura)) != null && !parar) {

                    // if(showVideo){                          
                    pantalla_envio_video.setIcon(new ImageIcon(imagenCapturada.getBufferedImage()));
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    try {
                        ImageIO.write(imagenCapturada.getBufferedImage(), "jpg", baos);
                    } catch (IOException ex) {
                        Logger.getLogger(Utiles_Video.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    byte[] imagen_bytes = baos.toByteArray();

                    mbcp.enviar(imagen_bytes, "MV", Mensaje_MBCP.Tipos_mensaje.VIDEO);

                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Vista_Proceso.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        };
        envio_video.start();
    }
    private void recepcionar() {
         System.out.println("recepcionando...");
        
        Runnable hilo_recepcion = new Runnable(){
        
       
        public void run(){
        while (!parar) {
            try {
                System.out.println(mbcp.getVT());
                
                mbcp.recepcion();
            } catch (IOException ex) {
                Logger.getLogger(Panel_Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            asignar_modelo(Lista_mensajes_espera, mbcp.getMensajes_en_espera());
            String ci=mbcp.getCI_String();
            String vt=mbcp.getVT_String();        
            CI_textArea.setText(ci+ "\n"+CI_textArea.getText());
            VT_textArea.setText(vt+ "\n"+VT_textArea.getText());
            CI_label.setText(ci);
            VT_label.setText(vt);
        } 
        
        
        
        }
        
        }; 
        Thread hilo= new Thread(hilo_recepcion);
        hilo.start();
       
    }
    public void reproducir_audio(byte[] audio) {
        try {
            InputStream entrada = new ByteArrayInputStream(audio);
            final AudioFormat formato_audio = getFormato_Audio();
            final AudioInputStream flujo_audio_entrada = new AudioInputStream(entrada, formato_audio, audio.length / formato_audio.getFrameSize());
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, formato_audio);
            final SourceDataLine linea = (SourceDataLine) AudioSystem.getLine(info);
            linea.open(formato_audio);
            linea.start();

            Runnable runner = new Runnable() {
                int bufferSize = (int) formato_audio.getSampleRate() * formato_audio.getFrameSize();
                byte buffer[] = new byte[bufferSize];

                public void run() {
                    try {
                        int count;
                        while ((count = flujo_audio_entrada.read(buffer, 0, buffer.length)) != -1&& !parar) {
                            if (count > 0) {
                                linea.write(buffer, 0, count);
                            }
                        }
                        linea.drain();
                        linea.close();
                    } catch (IOException e) {
                        System.err.println("Exepción I/O  " + e);
                        System.exit(-3);
                    }
                }
            };
            Thread hilo_reproduccion = new Thread(runner);
            hilo_reproduccion.start();
        } catch (LineUnavailableException e) {
            System.err.println("La linea no esta disponible!!!" + e);
            System.exit(-4);
        }
    }
   public void reproducir_video(byte[] imagen){ //es un bufferedImage
        BufferedImage buffered_image = byteToBufferedImage(imagen);
        pantalla_recepcion_video.setIcon( new ImageIcon(buffered_image )) ;        
    }
   public void reproducir_texto(String texto){
       Consola_recibos_texto.setText(texto+"\n"+Consola_recibos_texto.getText());
   
   
   }
   
    private void asignar_modelo(JList lista, ArrayList arrayList) {
        lista.setModel(new javax.swing.AbstractListModel<String>() {
            @Override
            public int getSize() {
                return arrayList.size();
            }

            @Override
            public String getElementAt(int i) {
                return arrayList.get(i).toString();
            }
        });

    }

    private AudioFormat getFormato_Audio() {
        float tasa_muestra = 8000;
        int tamaño_muestra = 8;
        int canales = 1;
        boolean señalizado = true;
        boolean bigEndian = true;
        return new AudioFormat(tasa_muestra, tamaño_muestra, canales, señalizado, bigEndian);
    }
 
    public byte[] bufferedImageToByte(BufferedImage imagen){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(imagen, "jpg", baos);
        } catch (IOException ex) {
            Logger.getLogger(Vista_Proceso.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] ba = baos.toByteArray();
        return ba;
    }
    
    public BufferedImage byteToBufferedImage(byte[] byteArray){
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new ByteArrayInputStream(byteArray));
        } catch (IOException ex) {
            Logger.getLogger(Vista_Proceso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bufferedImage;        
    }
  

    /**
     * @param args the command linea_sonido arguments
     */
    public static void main(String args[], int j, int cant_proceso, String ip_Grupo, int puerto) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vista_Proceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista_Proceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista_Proceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista_Proceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("Iniciando Proceso " + j);
                new Vista_Proceso(j, cant_proceso, ip_Grupo, puerto).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Archivo_Menu;
    private javax.swing.JLabel CI_label;
    private javax.swing.JTextArea CI_textArea;
    private javax.swing.JButton Comenzar_envio_texto;
    private javax.swing.JMenu Configuracion_Menu;
    private javax.swing.JMenuItem Configurar_escenario;
    private javax.swing.JTextArea Consola_recibos_audio;
    private javax.swing.JTextArea Consola_recibos_texto;
    private javax.swing.JTabbedPane Contenedor_TabbedPane;
    private javax.swing.JMenu Ejecucion_menu;
    private javax.swing.JPanel Envio_texto;
    private javax.swing.JList<String> Lista_mensajes_espera;
    private javax.swing.JList<String> Lista_mensajes_espera_audio;
    private javax.swing.JLabel Proceso_label;
    private javax.swing.JLabel VT_label;
    private javax.swing.JTextArea VT_textArea;
    private javax.swing.JMenuItem cargar_direcciones;
    private javax.swing.JButton comenzar_enviar_video;
    private javax.swing.JButton comenzar_envio_audio;
    private javax.swing.JTextArea consola_envio_audio;
    private javax.swing.JTextArea consola_envio_text;
    private javax.swing.JTextArea consola_general;
    private javax.swing.JPanel envio_audio;
    private javax.swing.JPanel envio_video;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JRadioButtonMenuItem modo_audio;
    private javax.swing.JRadioButtonMenuItem modo_pasivo;
    private javax.swing.JRadioButtonMenuItem modo_texto;
    private javax.swing.JRadioButtonMenuItem modo_video;
    private javax.swing.JLabel pantalla_envio_video;
    private javax.swing.JLabel pantalla_recepcion_video;
    private javax.swing.JMenuItem parar_todo;
    private javax.swing.JPanel recepcion_audio;
    private javax.swing.JPanel recepcion_texto;
    private javax.swing.JPanel recepcion_video;
    // End of variables declaration//GEN-END:variables

    public MBCP getMbcp() {
        return mbcp;
    }

    public void setMbcp(MBCP mbcp) {
        this.mbcp = mbcp;
    }

    public MBCP getMbcp_audio() {
        return mbcp_audio;
    }

    public void setMbcp_audio(MBCP mbcp_audio) {
        this.mbcp_audio = mbcp_audio;
    }

    public MBCP getMbcp_video() {
        return mbcp_video;
    }

    public void setMbcp_video(MBCP mbcp_video) {
        this.mbcp_video = mbcp_video;
    }

    public Transporte getTransporte() {
        return transporte;
    }

    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public void setSocket(DatagramSocket socket) {
        this.socket = socket;
    }

    public ArrayList<Tupla> getLista_procesos() {
        return lista_procesos;
    }

    public void setLista_procesos(ArrayList<Tupla> lista_procesos) {
        this.lista_procesos = lista_procesos;
    }

    public ArrayList<String> getDirecciones_puerto() {
        return direcciones_puerto;
    }

    public void setDirecciones_puerto(ArrayList<String> direcciones_puerto) {
        this.direcciones_puerto = direcciones_puerto;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public InetAddress getDireccion_ip() {
        return direccion_ip;
    }

    public void setDireccion_ip(InetAddress direccion_ip) {
        this.direccion_ip = direccion_ip;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public JMenu getArchivo_Menu() {
        return Archivo_Menu;
    }

    public void setArchivo_Menu(JMenu Archivo_Menu) {
        this.Archivo_Menu = Archivo_Menu;
    }

    public JLabel getCI_label() {
        return CI_label;
    }

    public void setCI_label(JLabel CI_label) {
        this.CI_label = CI_label;
    }

    public JTextArea getCI_textArea() {
        return CI_textArea;
    }

    public void setCI_textArea(JTextArea CI_textArea) {
        this.CI_textArea = CI_textArea;
    }

    public JButton getComenzar_envio_texto() {
        return Comenzar_envio_texto;
    }

    public void setComenzar_envio_texto(JButton Comenzar_envio_texto) {
        this.Comenzar_envio_texto = Comenzar_envio_texto;
    }

    public JMenu getConfiguracion_Menu() {
        return Configuracion_Menu;
    }

    public void setConfiguracion_Menu(JMenu Configuracion_Menu) {
        this.Configuracion_Menu = Configuracion_Menu;
    }

    public JMenuItem getConfigurar_escenario() {
        return Configurar_escenario;
    }

    public void setConfigurar_escenario(JMenuItem Configurar_escenario) {
        this.Configurar_escenario = Configurar_escenario;
    }

    public JTextArea getConsola_recibos_audio() {
        return Consola_recibos_audio;
    }

    public void setConsola_recibos_audio(JTextArea Consola_recibos_audio) {
        this.Consola_recibos_audio = Consola_recibos_audio;
    }

    public JTextArea getConsola_recibos_texto() {
        return Consola_recibos_texto;
    }

    public void setConsola_recibos_texto(JTextArea Consola_recibos_texto) {
        this.Consola_recibos_texto = Consola_recibos_texto;
    }

    public JTabbedPane getContenedor_TabbedPane() {
        return Contenedor_TabbedPane;
    }

    public void setContenedor_TabbedPane(JTabbedPane Contenedor_TabbedPane) {
        this.Contenedor_TabbedPane = Contenedor_TabbedPane;
    }

    public JPanel getEnvio_texto() {
        return Envio_texto;
    }

    public void setEnvio_texto(JPanel Envio_texto) {
        this.Envio_texto = Envio_texto;
    }

    public JList<String> getLista_mensajes_espera() {
        return Lista_mensajes_espera;
    }

    public void setLista_mensajes_espera(JList<String> Lista_mensajes_espera) {
        this.Lista_mensajes_espera = Lista_mensajes_espera;
    }

    public JList<String> getLista_mensajes_espera_audio() {
        return Lista_mensajes_espera_audio;
    }

    public void setLista_mensajes_espera_audio(JList<String> Lista_mensajes_espera_audio) {
        this.Lista_mensajes_espera_audio = Lista_mensajes_espera_audio;
    }

    
    public JLabel getVT_label() {
        return VT_label;
    }

    public void setVT_label(JLabel VT_label) {
        this.VT_label = VT_label;
    }

    public JTextArea getVT_textArea() {
        return VT_textArea;
    }

    public void setVT_textArea(JTextArea VT_textArea) {
        this.VT_textArea = VT_textArea;
    }

    public JButton getComenzar_enviar_video() {
        return comenzar_enviar_video;
    }

    public void setComenzar_enviar_video(JButton comenzar_enviar_video) {
        this.comenzar_enviar_video = comenzar_enviar_video;
    }

    public JButton getComenzar_envio_audio() {
        return comenzar_envio_audio;
    }

    public void setComenzar_envio_audio(JButton comenzar_envio_audio) {
        this.comenzar_envio_audio = comenzar_envio_audio;
    }

    public JTextArea getConsola_envio_audio() {
        return consola_envio_audio;
    }

    public void setConsola_envio_audio(JTextArea consola_envio_audio) {
        this.consola_envio_audio = consola_envio_audio;
    }

    public JTextArea getConsola_envio_text() {
        return consola_envio_text;
    }

    public void setConsola_envio_text(JTextArea consola_envio_text) {
        this.consola_envio_text = consola_envio_text;
    }

    public JTextArea getConsola_general() {
        return consola_general;
    }

    public void setConsola_general(JTextArea consola_general) {
        this.consola_general = consola_general;
    }

    public JPanel getEnvio_audio() {
        return envio_audio;
    }

    public void setEnvio_audio(JPanel envio_audio) {
        this.envio_audio = envio_audio;
    }

    public JPanel getEnvio_video() {
        return envio_video;
    }

    public void setEnvio_video(JPanel envio_video) {
        this.envio_video = envio_video;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    public JLabel getjLabel7() {
        return jLabel7;
    }

    public void setjLabel7(JLabel jLabel7) {
        this.jLabel7 = jLabel7;
    }

    public JLabel getjLabel8() {
        return jLabel8;
    }

    public void setjLabel8(JLabel jLabel8) {
        this.jLabel8 = jLabel8;
    }

    public JMenuBar getjMenuBar1() {
        return jMenuBar1;
    }

    public void setjMenuBar1(JMenuBar jMenuBar1) {
        this.jMenuBar1 = jMenuBar1;
    }

    public JMenuItem getjMenuItem4() {
        return cargar_direcciones;
    }

    public void setjMenuItem4(JMenuItem jMenuItem4) {
        this.cargar_direcciones = jMenuItem4;
    }

    public JRadioButtonMenuItem getjRadioButtonMenuItem1() {
        return jRadioButtonMenuItem1;
    }

    public void setjRadioButtonMenuItem1(JRadioButtonMenuItem jRadioButtonMenuItem1) {
        this.jRadioButtonMenuItem1 = jRadioButtonMenuItem1;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public JScrollPane getjScrollPane11() {
        return jScrollPane11;
    }

    public void setjScrollPane11(JScrollPane jScrollPane11) {
        this.jScrollPane11 = jScrollPane11;
    }

    public JScrollPane getjScrollPane2() {
        return jScrollPane2;
    }

    public void setjScrollPane2(JScrollPane jScrollPane2) {
        this.jScrollPane2 = jScrollPane2;
    }

    public JScrollPane getjScrollPane3() {
        return jScrollPane3;
    }

    public void setjScrollPane3(JScrollPane jScrollPane3) {
        this.jScrollPane3 = jScrollPane3;
    }

    public JScrollPane getjScrollPane5() {
        return jScrollPane5;
    }

    public void setjScrollPane5(JScrollPane jScrollPane5) {
        this.jScrollPane5 = jScrollPane5;
    }

    public JScrollPane getjScrollPane6() {
        return jScrollPane6;
    }

    public void setjScrollPane6(JScrollPane jScrollPane6) {
        this.jScrollPane6 = jScrollPane6;
    }

    public JScrollPane getjScrollPane7() {
        return jScrollPane7;
    }

    public void setjScrollPane7(JScrollPane jScrollPane7) {
        this.jScrollPane7 = jScrollPane7;
    }

    public JScrollPane getjScrollPane8() {
        return jScrollPane8;
    }

    public void setjScrollPane8(JScrollPane jScrollPane8) {
        this.jScrollPane8 = jScrollPane8;
    }

    public JScrollPane getjScrollPane9() {
        return jScrollPane9;
    }

    public void setjScrollPane9(JScrollPane jScrollPane9) {
        this.jScrollPane9 = jScrollPane9;
    }

    public JRadioButtonMenuItem getModo_audio() {
        return modo_audio;
    }

    public void setModo_audio(JRadioButtonMenuItem modo_audio) {
        this.modo_audio = modo_audio;
    }

    public JRadioButtonMenuItem getModo_pasivo() {
        return modo_pasivo;
    }

    public void setModo_pasivo(JRadioButtonMenuItem modo_pasivo) {
        this.modo_pasivo = modo_pasivo;
    }

    public JRadioButtonMenuItem getModo_texto() {
        return modo_texto;
    }

    public void setModo_texto(JRadioButtonMenuItem modo_texto) {
        this.modo_texto = modo_texto;
    }

    public JRadioButtonMenuItem getModo_video() {
        return modo_video;
    }

    public void setModo_video(JRadioButtonMenuItem modo_video) {
        this.modo_video = modo_video;
    }

    public JLabel getPantalla_envio_video() {
        return pantalla_envio_video;
    }

    public void setPantalla_envio_video(JLabel pantalla_envio_video) {
        this.pantalla_envio_video = pantalla_envio_video;
    }

    public JLabel getPantalla_recepcion_video() {
        return pantalla_recepcion_video;
    }

    public void setPantalla_recepcion_video(JLabel pantalla_recepcion_video) {
        this.pantalla_recepcion_video = pantalla_recepcion_video;
    }

    public JPanel getRecepcion_audio() {
        return recepcion_audio;
    }

    public void setRecepcion_audio(JPanel recepcion_audio) {
        this.recepcion_audio = recepcion_audio;
    }

    public JPanel getRecepcion_texto() {
        return recepcion_texto;
    }

    public void setRecepcion_texto(JPanel recepcion_texto) {
        this.recepcion_texto = recepcion_texto;
    }

    public JPanel getRecepcion_video() {
        return recepcion_video;
    }

    public void setRecepcion_video(JPanel recepcion_video) {
        this.recepcion_video = recepcion_video;
    }



}
