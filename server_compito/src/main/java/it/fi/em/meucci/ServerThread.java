package it.fi.em.meucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ServerThread extends Thread{

    Socket client = null;

    BufferedReader inDalClient = null;
    DataOutputStream outVersoIlClient = null;

    ServerThread(Socket sock ){
        this.client = sock;
    }

    
    public void comunica() throws Exception{

        inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outVersoIlClient = new DataOutputStream(client.getOutputStream());
        
        ObjectMapper objectMapper = new ObjectMapper();

        while(true){
            String stringaRicevuta = inDalClient.readLine();
            Messaggio messaggio = objectMapper.readValue(stringaRicevuta, Messaggio.class);     //deserialize

            if (messaggio.getListaBiglietti().size() == 0){                     //In caso la lista sia vuota aggiungo tanquillamente i biglietto voluto
                Messaggio mess = new Messaggio(ServerMain.listaBigl);
                outVersoIlClient.writeBytes(objectMapper.writeValueAsString(mess) + "\n");

            } else {


                ArrayList<Biglietto> bigliettiAcquistati = new ArrayList<>();

                for (int i = 0; i < messaggio.getListaBiglietti().size(); i++) {        //In caso questa lista non sia vuota allora vado a verificare la dispnibilità di quel 
                    for (int j = 0; j < ServerMain.listaBigl.size(); j++) {             //preciso bigilietto effettuando un confronto con i loro ID, andando ad aggiungerlo alla lista dei biglietti acquistati
                        if (messaggio.getListaBiglietti().get(i).ID == ServerMain.listaBigl.get(j).ID){ //e rimuovendolo dalla lista di quelli disponibili
                            bigliettiAcquistati.add(messaggio.getListaBiglietti().get(i));
                            ServerMain.listaBigl.remove(j);
                            j--;
                        }
                    }
                }

                Messaggio mess = new Messaggio(bigliettiAcquistati);
                outVersoIlClient.writeBytes(objectMapper.writeValueAsString(mess) + "\n");

            }
        }
    } 

    public void run(){

        try {
            this.comunica();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}