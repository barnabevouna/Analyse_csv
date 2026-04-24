import java.io.*;
import java.util.*;

/**
 * Classe Projet3 — Utilitaires pour la lecture et la sauvegarde de fichiers CSV.
 * Contient des méthodes statiques pour lire un fichier CSV ligne par ligne
 * et pour sauvegarder un rapport statistique dans un fichier CSV.
 */
public class Projet3 {

    /**
     * Lit un fichier CSV et retourne toutes ses lignes non vides sous forme de liste.
     *
     * @param chemin Chemin absolu vers le fichier CSV à lire
     * @return Liste de chaînes de caractères, chaque élément représente une ligne du fichier
     */
    public static List<String> lireLignesCSV(String chemin) {
        List<String> lignes = new ArrayList<>();

        try {
            // Ouvrir le fichier en lecture avec un BufferedReader pour de meilleures performances
            BufferedReader lecteur = new BufferedReader(new FileReader(chemin));
            String ligne;

            // Lire le fichier ligne par ligne jusqu'à la fin
            while ((ligne = lecteur.readLine()) != null) {
                // Ignorer les lignes vides ou contenant uniquement des espaces
                if (!ligne.trim().isEmpty()) {
                    lignes.add(ligne);
                }
            }
            lecteur.close(); // Fermer le fichier après lecture

        } catch (FileNotFoundException e) {
            // Le fichier n'existe pas au chemin spécifié
            System.out.println("Erreur : Fichier introuvable → " + chemin);
        } catch (IOException e) {
            // Erreur lors de la lecture du fichier (ex: permissions, fichier corrompu)
            System.out.println("Erreur de lecture : " + e.getMessage());
        }

        return lignes;
    }

    /**
     * Génère et sauvegarde un rapport statistique CSV à partir des données analysées.
     * Le rapport contient pour chaque colonne : son nom, sa moyenne, son minimum et son maximum.
     *
     * @param analyse Objet MonAnalyse contenant les données et statistiques calculées
     * @param chemin  Chemin absolu du fichier CSV de sortie à créer
     */
    public static void sauvegarderRapport(MonAnalyse analyse, String chemin) {
        try {
            // Ouvrir le fichier en écriture avec un BufferedWriter
            BufferedWriter ecrivain = new BufferedWriter(new FileWriter(chemin));

            // Écrire la ligne d'en-tête du rapport
            ecrivain.write("Colonne,Moyenne,Minimum,Maximum");
            ecrivain.newLine();

            // Parcourir chaque colonne et écrire ses statistiques
            for (int i = 0; i < analyse.getNombreColonnes(); i++) {
                // Construire la ligne CSV : nom, moyenne, min, max (formatés à 2 décimales)
                String ligne = analyse.getNomColonnes().get(i) + "," +
                        String.format("%.2f", analyse.getMoyenne(i)) + "," +
                        String.format("%.2f", analyse.getMin(i)) + "," +
                        String.format("%.2f", analyse.getMax(i));
                ecrivain.write(ligne);
                ecrivain.newLine(); // Passer à la ligne suivante
            }

            ecrivain.close(); // Fermer le fichier après écriture
            System.out.println("Rapport sauvegardé dans : " + chemin);

        } catch (IOException e) {
            // Erreur lors de l'écriture (ex: chemin invalide, permissions insuffisantes)
            System.out.println("Erreur d'écriture : " + e.getMessage());
        }
    }
}
