import java.util.*;

/**
 * Classe MonAnalyse — Analyse statistique des données d'un fichier CSV.
 * Elle stocke les noms de colonnes et les données numériques,
 * et fournit des méthodes pour calculer moyenne, minimum et maximum.
 */
public class MonAnalyse {

    // Liste des noms de colonnes extraits de la première ligne du CSV
    private List<String> nomColonnes;

    // Liste de listes : chaque sous-liste contient les valeurs numériques d'une colonne
    private List<List<Double>> donnees;

    /**
     * Constructeur : initialise les listes vides.
     */
    public MonAnalyse() {
        this.nomColonnes = new ArrayList<>();
        this.donnees = new ArrayList<>();
    }

    /**
     * Charge les données depuis une liste de lignes CSV.
     * La première ligne est traitée comme l'en-tête (noms de colonnes).
     * Les lignes suivantes contiennent les valeurs numériques.
     *
     * @param lignes Liste des lignes brutes lues depuis le fichier CSV
     */
    public void charger(List<String> lignes) {
        // Vérifier que la liste n'est pas vide
        if (lignes.isEmpty()) {
            System.out.println("Fichier vide.");
            return;
        }

        // Traitement de la première ligne : extraction des en-têtes de colonnes
        String[] entetes = lignes.get(0).split(",");
        for (String entete : entetes) {
            nomColonnes.add(entete.trim()); // Ajouter le nom de la colonne (sans espaces)
            donnees.add(new ArrayList<>()); // Créer une liste vide pour les données de cette colonne
        }

        // Traitement des lignes de données (à partir de la ligne 2, index 1)
        for (int i = 1; i < lignes.size(); i++) {
            String[] valeurs = lignes.get(i).split(",");

            // Parcourir chaque valeur de la ligne
            for (int j = 0; j < valeurs.length && j < nomColonnes.size(); j++) {
                try {
                    // Convertir la valeur en nombre décimal et l'ajouter à la bonne colonne
                    double val = Double.parseDouble(valeurs[j].trim());
                    donnees.get(j).add(val);
                } catch (NumberFormatException e) {
                    // Si la valeur n'est pas un nombre (ex: texte, date), on l'ignore
                    System.out.println("Valeur ignorée ligne " +
                            (i+1) + " col " + (j+1) +
                            " : " + valeurs[j]);
                }
            }
        }
    }

    /**
     * Calcule la moyenne des valeurs d'une colonne.
     *
     * @param indexColonne Index de la colonne (0 = première colonne)
     * @return La moyenne, ou 0.0 si la colonne est vide
     */
    public double calculerMoyenne(int indexColonne) {
        List<Double> col = donnees.get(indexColonne);
        if (col.isEmpty()) return 0.0;
        double somme = 0;
        for (double val : col) somme += val;
        return somme / col.size();
    }

    /**
     * Calcule la valeur minimale d'une colonne.
     *
     * @param indexColonne Index de la colonne
     * @return Le minimum, ou 0.0 si la colonne est vide
     */
    public double calculerMin(int indexColonne) {
        List<Double> col = donnees.get(indexColonne);
        if (col.isEmpty()) return 0.0;
        double min = col.get(0); // Initialiser avec la première valeur
        for (double val : col) {
            if (val < min) min = val; // Mettre à jour si on trouve une valeur plus petite
        }
        return min;
    }

    /**
     * Calcule la valeur maximale d'une colonne.
     *
     * @param indexColonne Index de la colonne
     * @return Le maximum, ou 0.0 si la colonne est vide
     */
    public double calculerMax(int indexColonne) {
        List<Double> col = donnees.get(indexColonne);
        if (col.isEmpty()) return 0.0;
        double max = col.get(0); // Initialiser avec la première valeur
        for (double val : col) {
            if (val > max) max = val; // Mettre à jour si on trouve une valeur plus grande
        }
        return max;
    }

    /**
     * Affiche les statistiques (moyenne, min, max) de chaque colonne dans la console.
     */
    public void afficherStatistiques() {
        System.out.println("\n=== Statistiques par colonne ===");
        for (int i = 0; i < nomColonnes.size(); i++) {
            System.out.println("\nColonne : " + nomColonnes.get(i));
            System.out.printf("  Moyenne : %.2f%n", calculerMoyenne(i));
            System.out.printf("  Minimum : %.2f%n", calculerMin(i));
            System.out.printf("  Maximum : %.2f%n", calculerMax(i));
        }
    }

    // --- Méthodes d'accès (getters) utilisées par Projet3 ---

    /** Retourne la liste des noms de colonnes */
    public List<String> getNomColonnes() { return nomColonnes; }

    /** Retourne le nombre total de colonnes */
    public int getNombreColonnes() { return nomColonnes.size(); }

    /** Retourne la moyenne de la colonne à l'index i */
    public double getMoyenne(int i) { return calculerMoyenne(i); }

    /** Retourne le minimum de la colonne à l'index i */
    public double getMin(int i) { return calculerMin(i); }

    /** Retourne le maximum de la colonne à l'index i */
    public double getMax(int i) { return calculerMax(i); }
}
