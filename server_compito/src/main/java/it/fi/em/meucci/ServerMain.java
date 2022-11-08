package it.fi.em.meucci;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerMain {


    public ServerSocket serverSocket;     
    public static ArrayList<Biglietto> listaBigl = new ArrayList<>();


    public ServerMain(){

        //Creo i diversi biglietti
        Biglietto b1 = new Biglietto(1, "palco-1a");
        Biglietto b2 = new Biglietto(2, "tribuna-2c");
        Biglietto b3 = new Biglietto(3, "parterre-7a");
        Biglietto b4 = new Biglietto(4,"tribuna-3c");
        Biglietto b5 = new Biglietto(5, "palco-2a");
        Biglietto b6 = new Biglietto(6, "parterre-6c");
        Biglietto b7 = new Biglietto(7, "palco-3a");

        //Inserisco i biglietti appena creati nella lista dei biglietti disponibili
        listaBigl.add(b1);
        listaBigl.add(b2);
        listaBigl.add(b3);
        listaBigl.add(b4);
        listaBigl.add(b5);
        listaBigl.add(b6);
        listaBigl.add(b7);
    }

    public void avvia(){
        try {

            System.out.println("Server avviato.");
            System.out.println("BENVENUTO!");
                                                            //Avvio del server nella porta 5982
            this.serverSocket = new ServerSocket(5982); 

            while (true) {                  //effettuo un ciclo definibile come "infinito" così da dare la possibilità di accettare diversi client l'uno dopo l'altro           

                Socket socket = serverSocket.accept();      //accetto il client
                ServerThread thread = new ServerThread(socket);


                thread.start();                

            }

        } catch (Exception e) {
            System.out.println("Il server e' spento.");
        }        
    }
    

}