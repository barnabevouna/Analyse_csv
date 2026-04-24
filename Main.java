import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Lire le fichier CSV
        System.out.println("--- Lecture du fichier CSV ---");
        List<String> lignes = Projet3.lireLignesCSV("C:\\Users\\Barna\\Downloads\\day.csv");

        if (lignes.isEmpty()) {
            System.out.println("Fichier vide ou introuvable.");
            return;
        }

        // 2. Charger les données dans MonAnalyse
        MonAnalyse analyse = new MonAnalyse();
        analyse.charger(lignes);

        // 3. Affiche les statistiques dans la console
        analyse.afficherStatistiques();

        // 4. Sauvegarde le rapport
        Projet3.sauvegarderRapport(analyse, "C:\\Users\\Barna\\Downloads\\rapport.csv");
    }
}