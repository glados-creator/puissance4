public class model {
    private final int[][] grille; // 0 = vide, 1 = joueur 1 (rouge), 2 = joueur 2 (jaune)
    private final int largeur;
    private final int hauteur;

    public model(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.grille = new int[hauteur][largeur];
    }

    public int getwidth() {
        return largeur;
    }

    public int getheight() {
        return hauteur;
    }

    /**
     * Place une pièce dans la colonne spécifiée pour le joueur donné.
     * @param col La colonne où placer la pièce.
     * @param joueur Le joueur (1 pour rouge, 2 pour jaune).
     * @return La ligne où la pièce a été placée, ou -1 si la colonne est pleine.
     */
    public int placerPiece(int col, int joueur) {
        for (int row = hauteur - 1; row >= 0; row--) {
            if (grille[row][col] == 0) { // Si la cellule est vide
                grille[row][col] = joueur;
                return row; // Retourner la ligne où la pièce a été placée
            }
        }
        return -1; // La colonne est pleine
    }

    /**
     * Vérifie si le dernier mouvement a conduit à une victoire.
     * @param col La colonne du dernier mouvement.
     * @param row La ligne du dernier mouvement.
     * @return true si un joueur a gagné, sinon false.
     */
    public boolean checkVictory(int col, int row) {
        int joueur = grille[row][col];
        if (joueur == 0) {
            return false;
        }

        // Vérifier les quatre directions (horizontal, vertical, diagonal)
        return checkDirection(col, row, 1, 0, joueur) // Horizontal
                || checkDirection(col, row, 0, 1, joueur) // Vertical
                || checkDirection(col, row, 1, 1, joueur) // Diagonale descendante
                || checkDirection(col, row, 1, -1, joueur); // Diagonale ascendante
    }
    

    /**
     * Vérifie la condition de victoire dans une direction donnée.
     * @param col La colonne de départ.
     * @param row La ligne de départ.
     * @param dCol La direction de la colonne.
     * @param dRow La direction de la ligne.
     * @param joueur Le joueur à vérifier (1 ou 2).
     * @return true si le joueur a 4 pièces consécutives dans la direction donnée.
     */
    private boolean checkDirection(int col, int row, int dCol, int dRow, int joueur) {
        int count = 0;

        // Vérifier dans la direction positive
        for (int i = 0; i < 4; i++) {
            int currentCol = col + i * dCol;
            int currentRow = row + i * dRow;
            if (currentCol >= 0 && currentCol < largeur && currentRow >= 0 && currentRow < hauteur 
                    && grille[currentRow][currentCol] == joueur) {
                count++;
            } else {
                break;
            }
        }

        

        // Vérifier dans la direction négative
        for (int i = 1; i < 4; i++) {
            int currentCol = col - i * dCol;
            int currentRow = row - i * dRow;
            if (currentCol >= 0 && currentCol < largeur && currentRow >= 0 && currentRow < hauteur 
                    && grille[currentRow][currentCol] == joueur) {
                count++;
            } else {
                break;
            }
        }

        return count >= 4;
    }

    public void reset() {
        for (int row = 0; row < hauteur; row++) {
            for (int col = 0; col < largeur; col++) {
                grille[row][col] = 0;
            }
        }
    }
}
