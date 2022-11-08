package it.fi.em.meucci;

import java.util.ArrayList;

public class Messaggio {
    ArrayList<Biglietto> listaBiglietti = new ArrayList<>();


    
    public Messaggio(ArrayList<Biglietto> listaBiglietti) {
        this.listaBiglietti = listaBiglietti;
    }


    public Messaggio (){}

    public ArrayList<Biglietto> getListaBiglietti() {
        return listaBiglietti;
    }

    
    public void setListaBiglietti(ArrayList<Biglietto> listaBiglietti) {
        this.listaBiglietti = listaBiglietti;
    }

    
}


