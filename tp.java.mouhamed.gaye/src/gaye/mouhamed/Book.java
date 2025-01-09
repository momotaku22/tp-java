package gaye.mouhamed;

public class Book {
    private String titre;  
    private String auteur;  

    // Ici, j'ai créé un constructeur pour initialiser un objet Book avec un titre et un auteur.
    public Book(String titre, String auteur) {
        this.titre = titre;
        this.auteur = auteur;
    }

    // Getter pour le titre du livre
    public String getTitre() {
        return titre;
    }

    // Getter pour l'auteur du livre
    public String getAuteur() {
        return auteur;
    }

    
    // Cela permet de formater le livre sous une forme qui peut être sauvegardée dans un fichier texte (titre;auteur)
    @Override
    public String toString() {
        return titre + ";" + auteur;
    }
}
