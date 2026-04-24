import java.util.List;

/**
 * Classe principale du programme d'analyse CSV.
 * Elle orchestre la lecture du fichier, le chargement des données,
 * l'affichage des statistiques et la sauvegarde du rapport.
 */
public class Main {

    public static void main(String[] args) {

        // Étape 1 : Lire toutes les lignes du fichier CSV
        System.out.println("--- Lecture du fichier CSV ---");
        List<String> lignes = Projet3.lireLignesCSV("C:\\Users\\Barna\\Downloads\\day.csv");

        // Vérifier si le fichier est vide ou introuvable
        if (lignes.isEmpty()) {
            System.out.println("Fichier vide ou introuvable.");
            return; // Arrêter le programme si aucune donnée n'est disponible
        }

        // Étape 2 : Charger les données dans l'objet MonAnalyse
        MonAnalyse analyse = new MonAnalyse();
        analyse.charger(lignes); // Traitement des lignes CSV (en-têtes + données)

        // Étape 3 : Afficher les statistiques (moyenne, min, max) dans la console
        analyse.afficherStatistiques();

        // Étape 4 : Sauvegarder le rapport statistique dans un fichier CSV
        Projet3.sauvegarderRapport(analyse, "C:\\Users\\Barna\\Downloads\\rapport.csv");
    }
}
