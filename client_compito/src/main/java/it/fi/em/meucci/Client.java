package it.fi.em.meucci;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Client {
    
    String serverName = "localhost";
    int port = 5982;
    Socket socket;    
    String stringaInviata;
    String stringaRicevuta;
    
    

    BufferedReader  tastiera;
    DataOutputStream  outVersoIlServer;
    BufferedReader  inDalServer;

    public Socket connetti(){
        
        try {
            
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            socket = new Socket(serverName, port);

            ObjectMapper objectMapper = new ObjectMapper();

            
            outVersoIlServer = new DataOutputStream(socket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(socket.getInputStream() ));


            ArrayList<Biglietto> listaVuota = new ArrayList<Biglietto>();
            Messaggio mess = new Messaggio(listaVuota);

            outVersoIlServer.writeBytes(objectMapper.writeValueAsString(mess) + "\n");

            String bigliettiDisponibili = inDalServer.readLine();
            Messaggio messaggio = objectMapper.readValue(bigliettiDisponibili, Messaggio.class);

            System.out.println("I biglietti disponibili sono i seguenti: ");
                                                                                    //effettuto la visualizzazione di tutti i biglietti disponibili
                                                                                    //(in caso un biglietto sia stato acquistato non veràà visualizzato tra questi)
            for (int i = 0; i < messaggio.listaBiglietti.size(); i++) {   
                System.out.println("-ID: " + messaggio.listaBiglietti.get(i).ID +" -Numero: "+ messaggio.listaBiglietti.get(i).numeroBiglietto);
                System.out.println("---------------------------");
            }

        } catch (Exception e) {
            System.out.println("Errore di connessione,si prega di riprovare.");
        }

        return socket;
    }


    public void comunica(){

        try {
            
                ArrayList<Biglietto> bigliettiDaAcquistare = new ArrayList<>();
                System.out.print("Si prega di scrivere gli ID dei biglietti che si voglio acquistare: ");
                stringaRicevuta = tastiera.readLine();
                String[] ar = stringaRicevuta.split("-"); //il trattino identifica il "requisito" per poter comprare più biglietti in contemporanea

                for (int i = 0 ; i<ar.length; i++){
                    Biglietto b = new Biglietto(Integer.parseInt( ar[i]), " ");
                    bigliettiDaAcquistare.add(b);
                }

                ObjectMapper objectMapper = new ObjectMapper();

                Messaggio mess = new Messaggio(bigliettiDaAcquistare);
                outVersoIlServer.writeBytes(objectMapper.writeValueAsString(mess) + "\n");

                String risp = inDalServer.readLine();
                Messaggio messaggioRicevuto = objectMapper.readValue(risp, Messaggio.class);

                System.out.println("I biglietti acquistati sono i seguenti: "); //faccio visualizare a schermo i biglietti che sono stati acquistati dal quel client in quel momento
                for (int i = 0; i < messaggioRicevuto.listaBiglietti.size(); i++) {
                    System.out.println("L'ID del biglietto acquistato e' il seguente: " + messaggioRicevuto.listaBiglietti.get(i).ID+ messaggioRicevuto.listaBiglietti.get(i).numeroBiglietto);
                }
                System.out.println("Grazie per aver usufruito del nostro servizio, alla prossima!");

                
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
