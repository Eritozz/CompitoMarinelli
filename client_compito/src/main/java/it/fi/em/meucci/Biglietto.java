package it.fi.em.meucci;


public class Biglietto {
    int ID;
    String numeroBiglietto;

    
    public Biglietto(int id, String numeroBiglietto) {
        ID = id;
        this.numeroBiglietto = numeroBiglietto;
    }


    public Biglietto() {
    }


    public  int getID() {
        return ID;
    }


    public String getNumeroBiglietto() {
        return numeroBiglietto;
    }


    public  void setID(int id) {
        ID = id;
    }


    public void setNumeroBiglietto(String numeroBiglietto) {
        this.numeroBiglietto = numeroBiglietto;
    }

    
}