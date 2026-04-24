import java.util.*;

public class MonAnalyse{

     // Liste des noms de colonnes extraits de la première ligne du dataset

    private List<String> nomColonnes;

    // Liste de listes : chaque sous-liste contient les valeurs numériques d'une colonne
    private List<List<Double>> donnees;

    // Constructeur : il initialise les listes vides.
    public MonAnalyse() {
        this.nomColonnes = new ArrayList<>();
        this.donnees = new ArrayList<>();
    }
    // Charge les données depuis une liste de lignes CSV.
    public void charger(List<String> lignes) {
        // verifier que la liste n'est pas vide
        if (lignes.isEmpty()) {
            System.out.println("Fichier vide.");
            return;
        }

        // extraction des entetes de colonnes
        String[] entetes = lignes.get(0).split(",");
        for (String entete : entetes) {
            nomColonnes.add(entete.trim());
            donnees.add(new ArrayList<>());
        }
             //Traitement des lignes de données
        for (int i = 1; i < lignes.size(); i++) {
            String[] valeurs = lignes.get(i).split(",");

            //parcourir chaque valeur de la ligne
            for (int j = 0; j < valeurs.length && j < nomColonnes.size(); j++) {
                try {

                    // convertir la valeur en nombre decimale et ajouter a la bonne colonne
                    double val = Double.parseDouble(valeurs[j].trim());
                    donnees.get(j).add(val);
                } catch (NumberFormatException e) {

                    // ignorer la valeur si elle n'est pas un nombre

                    System.out.println("Valeur ignorée ligne " +
                            (i+1) + " col " + (j+1) +
                            " : " + valeurs[j]);
                }
            }
        }
    }

       // calcul de la valeur de la moyenne d'une colonne et afficher 0 si elle est vide

    public double calculerMoyenne(int indexColonne) {
        List<Double> col = donnees.get(indexColonne);
        if (col.isEmpty()) return 0.0;
        double somme = 0;
        for (double val : col) somme += val;
        return somme / col.size();
    }
         //calcul de la valeur minimale d'une colonne et afficher 0 si elle est vide


    public double calculerMin(int indexColonne) {
        List<Double> col = donnees.get(indexColonne);
        if (col.isEmpty()) return 0.0;
        double min = col.get(0);
        for (double val : col) {
            if (val < min) min = val;
        }
        return min;
    }
    //calcul de la valeur maximale d'une colonne et afficher 0 si elle est vide

    public double calculerMax(int indexColonne) {
        List<Double> col = donnees.get(indexColonne);
        if (col.isEmpty()) return 0.0;
        double max = col.get(0);
        for (double val : col) {
            if (val > max) max = val;
        }
        return max;
    }
          // afficher les statistiques de chaque colonne

    public void afficherStatistiques() {
        System.out.println("\n=== Statistiques par colonne ===");
        for (int i = 0; i < nomColonnes.size(); i++) {
            System.out.println("\nColonne : " + nomColonnes.get(i));
            System.out.printf("  Moyenne : %.2f%n", calculerMoyenne(i));
            System.out.printf("  Minimum : %.2f%n", calculerMin(i));
            System.out.printf("  Maximum : %.2f%n", calculerMax(i));
        }
    }

    // definir les getters pour permettre aux autres de classes d'acceder a la classe MonAnalyse

    public List<String> getNomColonnes() { return nomColonnes; }
    public int getNombreColonnes() { return nomColonnes.size(); }
    public double getMoyenne(int i) { return calculerMoyenne(i); }
    public double getMin(int i) { return calculerMin(i); }
    public double getMax(int i) { return calculerMax(i); }
}

