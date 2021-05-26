package models;

import java.util.HashSet;

public class Cititor extends Om{
    public HashSet<Carte> cartiDetinute;

    public Cititor(String nume, HashSet<Carte> cartiDetinute) {
        this.nume = nume;
        this.cartiDetinute = cartiDetinute;
    }

    public HashSet<Carte> getCartiDetinute() {
        return cartiDetinute;
    }

    public void setCartiDetinute(HashSet<Carte> cartiDetinute) {
        this.cartiDetinute = cartiDetinute;
    }
}
