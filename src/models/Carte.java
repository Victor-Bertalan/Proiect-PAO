package models;

public class Carte {
    private String titlu;
    private Autor autor;
    private int pret;

    public Carte(String titlu, Autor autor, int pret) {
        this.titlu = titlu;
        this.autor = autor;
        this.pret=pret;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public int getPret() {
        return pret;
    }
}
