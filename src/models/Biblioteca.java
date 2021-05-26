package models;

import Database.CRUD_Carte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Biblioteca {
    private HashMap<Carte,Integer> carti;
    private List<Autor> autori;
    private List<Sectiune> sectiuni;
    private List<Cititor> cititori;
    private static Biblioteca instance = null;

    //singleton
    private Biblioteca(){
        autori=new ArrayList<>();
        sectiuni=new ArrayList<>();
        //carti=new HashMap<>();
        carti = CRUD_Carte.getCarti();
        cititori=new ArrayList<>();
    }

    public static Biblioteca getInstance(){
        if (instance == null)
            instance = new Biblioteca();
        return instance;
    }

    /*
    public Biblioteca(HashMap<Carte,Integer>  carti, List<Autor> autori, List<Sectiune> sectiuni, List<Cititor> cititori) {
        this.carti = carti;
        this.autori = autori;
        this.sectiuni = sectiuni;
        this.cititori = cititori;
    }*/

    public HashMap<Carte,Integer>  getCarti() {
        return carti;
    }

    public void setCarti(HashMap<Carte,Integer>  carti) {
        this.carti = carti;
    }

    public List<Autor> getAutori() {
        return autori;
    }

    public void setAutori(List<Autor> autori) {
        this.autori = autori;
    }

    public List<Sectiune> getSectiuni() {
        return sectiuni;
    }

    public void setSectiuni(List<Sectiune> sectiuni) {
        this.sectiuni = sectiuni;
    }

    public List<Cititor> getCititori() {
        return cititori;
    }

    public void setCititori(List<Cititor> cititori) {
        this.cititori = cititori;
    }

    public void addSectiune(Sectiune s){
        this.sectiuni.add(s);
    }

    public void addAutor(Autor a){
        this.autori.add(a);
    }

    public void addCarte(Carte c,Integer x){
        if(carti.containsKey(c)) {
            carti.replace(c, carti.get(c) + x);
            CRUD_Carte.adaugaCarte(c);
        }
        else {
            if (!autori.contains(c.getAutor()))
                autori.add(c.getAutor());
            this.carti.put(c, x);
        }
    }
    public void subtractCantitate(Carte c,Integer x){
        if(carti.containsKey(c)) {
            int k = carti.get(c) - x;
            try {
                if (k<0)
                    throw new Exception();
                else
                    carti.replace(c, k);
            } catch (Exception ex) {
                System.out.println("Cantitatea data depaseste cantitatea disponibila in stoc");
            }
        }
        else
            System.out.println("Cartea nu este disponibila in stocul nostru");
    }

    public void printCarti(){
        for(Carte c:carti.keySet()){
            System.out.println(c.getTitlu()+" scrisa de "+c.getAutor().getNume()+"  "+carti.get(c)+" bucati");
        }
    }
    public void printAutori(){
        for(Autor a:autori){
            System.out.println(a.getNume());
        }
    }
    public void printSectiuni(){
        for(Sectiune s:sectiuni){
            System.out.println(s.getNume());
        }
    }
    public void printSectiune(Sectiune s){
        for (Sectiune i:sectiuni){
            if(i==s){
                for(Carte c :s.getCarti())
                    System.out.println(c.getTitlu());
            }
            else
                System.out.println("Sectiunea nu a fost gasita");
        }
    }

    public void findCarte(Carte c){
        if(carti.containsKey(c))
            System.out.println("Cartea "+c.getTitlu()+" este disponibila in "+ carti.get(c) +" exemplare");
        else
            System.out.println("Cartea nu se afla in stocul nostru");
    }

    public void afisSortPret()
    {
        HashMap<Carte,Integer> copy=carti;
        int maxi;
        Carte cpy = null;
        while(!copy.isEmpty()){
            maxi=0;
            for(Carte c: copy.keySet()){
                if(copy.get(c)>maxi) {
                    maxi = copy.get(c);
                    cpy=c;
                }
            }
            System.out.println(cpy.getTitlu());
            copy.remove(cpy);
        }
    }
}
