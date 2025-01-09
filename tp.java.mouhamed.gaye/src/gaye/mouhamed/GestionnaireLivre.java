package gaye.mouhamed;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class GestionnaireLivre extends JFrame {
    private JTextField champTitre, champAuteur;  // pour creer Champs pour entrer le titre et l'auteur
    private DefaultTableModel modeleTable;  // Modèle de table pour afficher les livres
    private JTable table;  // pour afficher les livres

    // la on fait le constructeur du premier fenetre
    public GestionnaireLivre() {
        setTitle("Gestion de Location de Livres");  
        setSize(600, 400);  // Taille de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setLayout(new BorderLayout());  

        // et c'est ici qu'on fait la saisie des info
        JPanel panneauSaisie = new JPanel(new GridLayout(3, 2));  
        panneauSaisie.add(new JLabel("Titre :"));
        champTitre = new JTextField();  
        panneauSaisie.add(champTitre);
        
        panneauSaisie.add(new JLabel("Auteur :"));
        champAuteur = new JTextField();  
        panneauSaisie.add(champAuteur);

        JButton boutonAjouter = new JButton("Ajouter");  // la on rajoute un bouton pour ajouter un livre
        panneauSaisie.add(boutonAjouter);
        add(panneauSaisie, BorderLayout.NORTH);  

        // on a la table pour afficher les livres existants
        modeleTable = new DefaultTableModel(new String[]{"Titre", "Auteur"}, 0);  // on utilise deux colonne ici
        
        table = new JTable(modeleTable);  
        JScrollPane defilement = new JScrollPane(table);  
        add(defilement, BorderLayout.CENTER);  

        JButton boutonSupprimer = new JButton("Supprimer");  // on ajoute un bouton pour supprimer un livre
        add(boutonSupprimer, BorderLayout.SOUTH);  

        // on charge les livres existants depuis le fichier
        chargerLivres();

        // on fait les actoin pour  ajouter un livre
        boutonAjouter.addActionListener(e -> ajouterLivre());

        // on fait les actoin pour supprimer un livre
        boutonSupprimer.addActionListener(e -> supprimerLivre());
    }

    // la on a la méthode pour charger les livres depuis le fichier
    private void chargerLivres() {
        List<Book> listeLivres = GestionnaireFichier.lireLivres();  
        for (Book livre : listeLivres) {
            modeleTable.addRow(new Object[]{livre.getTitre(), livre.getAuteur()});  
        }
    }

    // ici pour ajouter un livre à la table et au fichier
    private void ajouterLivre() {
        String titre = champTitre.getText().trim();  
        String auteur = champAuteur.getText().trim();  

       
        if (!titre.isEmpty() && !auteur.isEmpty()) {
            modeleTable.addRow(new Object[]{titre, auteur});  // Ajouter le livre à la table
            sauvegarderLivresDansFichier();  // Sauvegarder la liste mise à jour dans le fichier
            champTitre.setText("");  
            champAuteur.setText("");  
        } else {
            // et la on ecris un message, afficher un message d'erreur
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ici c'est pour  pour supprimer un livre de la table et du fichier
    private void supprimerLivre() {
        int ligneSelectionnee = table.getSelectedRow();  
        if (ligneSelectionnee != -1) {
            modeleTable.removeRow(ligneSelectionnee);  
            sauvegarderLivresDansFichier();  
        } else {
            // Si aucune ligne n'est sélectionnée, on affiche un message d'erreur
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un livre à supprimer.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    // et ici on a la methode  pour sauvegarder la liste des livres dans le fichier
    private void sauvegarderLivresDansFichier() {
        List<Book> listeLivres = new ArrayList<>();
        for (int i = 0; i < modeleTable.getRowCount(); i++) {
           
            String titre = (String) modeleTable.getValueAt(i, 0);
            String auteur = (String) modeleTable.getValueAt(i, 1);
            listeLivres.add(new Book(titre, auteur));
        }
        GestionnaireFichier.ecrireLivres(listeLivres);  
    }

    // et la on a la méthode principale pour lancer l'application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GestionnaireLivre().setVisible(true));  // Lancer l'application
    }
}
