package models;

import java.util.HashSet;

public class Sectiune {
    private String nume;
    private HashSet<Carte> carti;

    public Sectiune(){
        carti=new HashSet<Carte>();
        nume="";
    }
    public Sectiune(String nume, HashSet<Carte> carti) {
        this.nume = nume;
        this.carti = carti;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public HashSet<Carte> getCarti() {
        return carti;
    }

    public void setCarti(HashSet<Carte> carti) {
        this.carti = carti;
    }
    public void addCarte(Carte carte){
        this.carti.add(carte);
    }
}
