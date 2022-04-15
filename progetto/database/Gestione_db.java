package progetto.database;

import progetto.functions.GestioneFile;
import progetto.model.Film;

import java.io.*;
import java.util.*;

public class Gestione_db {
    private static final String relativePath = ".\\progetto\\database\\";

    public static BufferedReader getTable(String tableName) {
        return GestioneFile.openFile(relativePath+tableName);
    }

    public static String deleteRow(String ID, String tableName ) {

        try{
            BufferedReader file = GestioneFile.openFile(relativePath+tableName);

            ArrayList<String> info = new ArrayList<>();

            boolean trovato = false;

            String l;
            while ((l = file.readLine()) != null) {
                String[] dati = l.split(",");

                if(!dati[0].equals(ID)){
                    info.add(l);
                    System.out.println(l);
                } else {
                    trovato = true;
                }
            }
            if (trovato){
                return GestioneFile.writeFile(relativePath+tableName, info);
            } else {
                return "errore, ID non trovato";
            }

        } catch (IOException e){}

        return "errore durante la lettura";


    }

    // probabilmente possiamo passare direttamente una striga invece della lista
    public static String modifyRow(String ID, String tableName, List<String> modifyElement ){
        return null;
    }

    public static String insertRow(String tableName, List<String> insertElement){
        return null;
    }

}
