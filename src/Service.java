import Database.*;
import models.Autor;
import models.Biblioteca;
import models.Carte;
import models.Sectiune;

import java.util.Scanner;

public class Service {

    private static Service instance = null;
    private Scanner scanner;
    private Biblioteca b;
    private java.sql.Connection connection;

    private Service(){
        this.b = Biblioteca.getInstance();
        this.scanner = new Scanner(System.in);
        this.connection = Connection.getInstance().getConnection();
    }

    public static Service getInstance(){
        if (instance == null)
            instance = new Service();
        return instance;
    }

    private void introduceAutor(){
        System.out.println("nume:");
        String n = scanner.nextLine();
        System.out.println("rating:");
        double r = scanner.nextDouble();
        Autor a = new Autor(n, r);
        b.addAutor(a);
        CRUD_Autor.adaugaAutor(n, r);
    }

    private void adaugaCarti(){
        System.out.println("numele cartii:");
        String titlu = scanner.nextLine();
        System.out.println("numele autorului:");
        String autor = scanner.nextLine();
        Autor aut = new Autor();
        boolean ok = false; //e o implementare oribila, voi modifica
        for (Autor a : b.getAutori())
            if (a.getNume().equals(autor)) {
                ok = true;
                aut = a;
            }
        if (!ok)
            System.out.println("Autorul nu se afla in baza noastra de date");
        else {
            System.out.println("Pretul:");
            int p = scanner.nextInt();
            Carte c = new Carte(titlu, aut, p);
            System.out.println("Introduceti cantitatea:");
            Integer cant = scanner.nextInt();
            b.addCarte(c, cant);
            System.out.println("Cartea a fost adaugata cu succes.");
        }
    }

    private void scoateCarti(){
        System.out.println("Introduceti numele cartii");
        String nume = scanner.nextLine();
        System.out.println("Introduceti numarul cartilor");
        int nr = scanner.nextInt();
        boolean ok = false;
        for (Carte c : b.getCarti().keySet()) {
            if (c.getTitlu().equals(nume)) {
                b.subtractCantitate(c, nr);
                ok = true;
            }
        }
        if (!ok)
            System.out.println("Cartea nu se afla in baza noastra de date");
    }

    private void cautaCarte(){
        System.out.println("numele cartii:");
        String titlu = scanner.nextLine();
        System.out.println("numele autorului:");
        String autor = scanner.nextLine();
        Autor aut = new Autor();
        boolean ok = false; //e o implementare oribila, voi modifica
        for (Autor a : b.getAutori())
            if (a.getNume().equals(autor)) {
                ok = true;
                aut = a;
            }
        if (!ok)
            System.out.println("Autorul nu se afla in baza noastra de date");
        else {
            System.out.println("Editia la ce pret va intereseaza?");
            int p = scanner.nextInt();
            Carte c = new Carte(titlu, aut, p);
            b.findCarte(c);
        }
    }

    private void adaugaSectiune(){
        System.out.println("Numele sectiunii:");
        String s = scanner.nextLine();
        Sectiune sect = new Sectiune();
        sect.setNume(s);
        b.addSectiune(sect);
        System.out.println("Sectiunea a fost adaugata cu succes.");
    }

    private void adaugaCarteSectiune(){
        System.out.println("numele cartii:");
        String titlu = scanner.nextLine();
        System.out.println("numele autorului:");
        String autor = scanner.nextLine();
        Autor aut = new Autor();
        boolean ok = false; //e o implementare oribila, voi modifica
        for (Autor a : b.getAutori())
            if (a.getNume().equals(autor)) {
                ok = true;
                aut = a;
            }
        if (!ok)
            System.out.println("Autorul nu se afla in baza noastra de date");
        else {
            System.out.println("Pretul:");
            int p = scanner.nextInt();
            Carte c = new Carte(titlu, aut, p);
            System.out.println("Numele sectiunii:");
            String s = scanner.nextLine();
            Sectiune sect = new Sectiune();
            sect.setNume(s);
            sect.addCarte(c);
            b.addSectiune(sect);
            System.out.println("Cartea a fost adaugata cu succes.");
        }
    }

    private void afiseazaSectiune(){
        System.out.println("Numele sectiunii:");
        String num = scanner.nextLine();
        boolean ok = false;
        for (Sectiune s : b.getSectiuni()) {
            if (s.getNume().equals(num)) {
                b.printSectiune(s);
                ok = true;
            }
        }
        if (!ok)
            System.out.println("Sectiunea nu a fost gasita");
    }

    public void alegeOptiune(){
        while(true) {
            System.out.println("MENIU");
            System.out.println("1.Introduceti un nou Autor in baza noastra de date");
            System.out.println("2.Adaugati un numar de carti in stocul nostru");
            System.out.println("3.Scadeti un numar de carti in stocul nostru");
            System.out.println("4.Afisati intreg stocul");
            System.out.println("5.Cautati daca o anumita carte se afla in stocul nostru");
            System.out.println("6.Adaugati o sectiune in baza noastra de date");
            System.out.println("7.Adaugati o carte intr-o anumita sectiune");
            System.out.println("8.Afisati toate sectiunile");
            System.out.println("9.Afisati o anumita sectiune");
            System.out.println("10.Afiseaza cartile sortate descrescator dupa pret");
            System.out.println("11.Afisati toti autorii");
            System.out.println("12.Stergeti un autor");
            System.out.println("13.Editeaza ratingul unui autor");
            System.out.println("14.Sterge o carte");
            System.out.println("15.Modifica pretul unei carti");
            System.out.println("16.Sterge o sectiune");
            System.out.println("17.Modifica numele unei sectiuni");
            System.out.println("18.Adauga un cititor");
            System.out.println("19.Sterge un cititor");
            System.out.println("20.Modifica numele unui cititor");
            System.out.println("21.Exit");
            int x = scanner.nextInt();
            scanner.nextLine();
            switch (x) {
                case (1): {
                    this.introduceAutor();
                    break;
                }
                case (2): {
                    this.adaugaCarti();
                    break;
                }
                case (3): {
                    this.scoateCarti();
                    break;
                }
                case (4): {
                    b.printCarti();
                    break;
                }
                case (5): {
                    this.cautaCarte();
                    break;
                }
                case (6): {
                    this.adaugaSectiune();
                    break;
                }
                case (7): {
                    this.adaugaCarteSectiune();
                    break;
                }
                case (8): {
                    b.printSectiuni();
                    break;
                }
                case (9): {
                    this.afiseazaSectiune();
                    break;
                }
                case (10): {
                    b.afisSortPret();
                    break;
                }
                case (11): {
                    CRUD_Autor.afiseazaAutori();
                    break;
                }
                case (12): {
                    System.out.println("Numele autorului: ");
                    String name = scanner.nextLine();
                    CRUD_Autor.stergeAutor(name);
                    break;
                }
                case (13): {
                    System.out.println("Numele autorului: ");
                    String name = scanner.nextLine();
                    System.out.println("Noul rating: ");
                    double rating = scanner.nextDouble();
                    CRUD_Autor.schimbaRating(name, rating);
                    break;
                }
                case (14): {
                    System.out.println("Numele cartii: ");
                    String name = scanner.nextLine();
                    CRUD_Carte.stergeCarte(name);
                    break;
                }
                case(15): {
                    System.out.println("Numele cartii: ");
                    String name = scanner.nextLine();
                    System.out.println("Noul pret: ");
                    int pret = scanner.nextInt();
                    CRUD_Carte.updatePret(name, pret);
                    break;
                }
                case (16): {
                    System.out.println("Numele sectiunii: ");
                    String name = scanner.nextLine();
                    CRUD_Sectiuni.stergeSectiune(name);
                    break;
                }
                case(17): {
                    System.out.println("Numele sectiunii: ");
                    String name = scanner.nextLine();
                    System.out.println("Noul nume: ");
                    String nume = scanner.nextLine();
                    CRUD_Sectiuni.updateSectiune(name, nume);
                    break;
                }
                case (18): {
                    System.out.println("Numele cititorului: ");
                    String name = scanner.nextLine();
                    CRUD_Cititor.adaugaCititor(name);
                    break;
                }
                case (19): {
                    System.out.println("Numele cititorului: ");
                    String name = scanner.nextLine();
                    CRUD_Cititor.stergeCititor(name);
                    break;
                }
                case(20): {
                    System.out.println("Numele cititorului: ");
                    String name = scanner.nextLine();
                    System.out.println("Noul nume: ");
                    String nume = scanner.nextLine();
                    CRUD_Cititor.updateCititor(name, nume);
                    break;
                }
                case (21): {
                    return;
                }
                default: {
                    System.out.println("input invalid");
                }
            }
        }
    }
}
