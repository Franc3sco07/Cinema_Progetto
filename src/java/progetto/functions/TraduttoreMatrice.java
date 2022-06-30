package progetto.functions;

import java.util.Arrays;

public class TraduttoreMatrice {
    public static String matriceToString(int[][] matrice) {
        StringBuilder mat = new StringBuilder();

        Arrays.stream(matrice).map(Arrays::toString).forEach(mat::append);

        return mat.toString().replaceAll("\\]\\[", "; ").replaceAll("\\[|\\]", "").replaceAll(",", ":");
    }

    public static int[][] stringToMatrice(String matriceStringa) {
        String[] stringaRighe = matriceStringa.split(";");
        int righe = stringaRighe.length;
        int colonne = stringaRighe[0].split(":").length;
        int[][] matrice = new int[righe][colonne];
        String[] stringaColonne;

        for (int i = 0; i < righe; i++) {
            stringaColonne = stringaRighe[i].split(":");
            for (int j = 0; j < colonne; j++) {
                matrice[i][j] = Integer.parseInt(stringaColonne[j].trim());
            }
        }
        return matrice;
    }


}
