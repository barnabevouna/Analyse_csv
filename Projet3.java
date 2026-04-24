import java.io.*;
import java.util.*;

// lecture et sauvegarde des fichiers csv du projet
public class Projet3 {

    // lit les fichiers csv et retourne toutes les lignes non vides en forme de liste
    public static List<String> lireLignesCSV(String chemin) {
        List<String> lignes = new ArrayList<>();

        // lit le fichier en lecture pour de meilleures performances
        try {
            BufferedReader lecteur = new BufferedReader(new FileReader(chemin));
            String ligne;

            // lit toutes les lignes jusqu'à la fin
            while ((ligne = lecteur.readLine()) != null) {
                // ignore les lignes vides
                if (!ligne.trim().isEmpty()) {
                    lignes.add(ligne);
                }
            }
            // ferme les fichiers
            lecteur.close();

        } catch (FileNotFoundException e) {
            // affiche si le fichier est introuvable
            System.out.println("Erreur : Fichier introuvable → " + chemin);
        } catch (IOException e) {
            // affiche en cas d'erreur de lecture
            System.out.println("Erreur de lecture : " + e.getMessage());
        }

        return lignes;
    }

    // genere et sauvegarde un rapport statistique dans le chemin
    public static void sauvegarderRapport(MonAnalyse analyse, String chemin) {
        try {
            // permet de lire le nouveau fichier csv
            BufferedWriter ecrivain = new BufferedWriter(new FileWriter(chemin));

            // écrit la liste d'en tete dans le rapport
            ecrivain.write("Colonne,Moyenne,Minimum,Maximum");
            ecrivain.newLine();

            //écriture des statistiques de chaque colonne
            for (int i = 0; i < analyse.getNombreColonnes(); i++) {
                String ligne = analyse.getNomColonnes().get(i) + "," +
                        String.format("%.2f", analyse.getMoyenne(i)) + "," +
                        String.format("%.2f", analyse.getMin(i)) + "," +
                        String.format("%.2f", analyse.getMax(i));
                ecrivain.write(ligne);
                ecrivain.newLine();
            }

            // ferme le fichier apres sauvegarde
            ecrivain.close();
            // affiche rapport sauvegarde
            System.out.println("Rapport sauvegardé dans : " + chemin);

        } catch (IOException e) {
            // affiche lors d'une erreur d'écriture
            System.out.println("Erreur d'écriture : " + e.getMessage());
        }
    }
}
