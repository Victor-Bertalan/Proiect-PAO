package models;

public class Autor extends Om{
    private double rating;
    public Autor(){
        this.nume=null;
        this.rating=0;
    }

    public Autor(String nume, double rating) {
        this.nume = nume;
        this.rating=rating;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
