package gaye.mouhamed;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestionnaireFichier {
    private static final String CHEMIN_FICHIER = "livres.txt";  // voici la fonction qui me permet de specifier le Chemin du fichier où les livres seront stockés

    // cette Méthode me permet de lire tous les livres depuis le fichier
    public static List<Book> lireLivres() {
        List<Book> listeLivres = new ArrayList<>();  
        try (BufferedReader lecteur = new BufferedReader(new FileReader(CHEMIN_FICHIER))) {
            String ligne;
            while ((ligne = lecteur.readLine()) != null) {  
                String[] parties = ligne.split(";");  
                if (parties.length == 2) { 
                    listeLivres.add(new Book(parties[0], parties[1]));  
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  //ca me permet d'afficher une ereur au cas ou
        }
        return listeLivres;  // et ici je Retourne la liste des livres
    }

    //voila la Méthode pour sauvegarder la liste de livres dans le fichier
    public static void ecrireLivres(List<Book> listeLivres) {
        try (BufferedWriter ecrivain = new BufferedWriter(new FileWriter(CHEMIN_FICHIER))) {
            for (Book livre : listeLivres) {
                ecrivain.write(livre.toString());  
                ecrivain.newLine();  // Ajouter un retour à la ligne
            }
        } catch (IOException e) {
            e.printStackTrace();  // Si une erreur survient, l'afficher
        }
    }
}
