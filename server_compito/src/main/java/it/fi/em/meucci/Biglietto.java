package it.fi.em.meucci;



public class Biglietto {
    int ID;
    String numeroBiglietto;

    
    public Biglietto(int iD, String numeroBiglietto) {
        ID = iD;
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


    public  void setID(int iD) {
        ID = iD;
    }


    public void setNumeroBiglietto(String numeroBiglietto) {
        this.numeroBiglietto = numeroBiglietto;
    }

    
}
