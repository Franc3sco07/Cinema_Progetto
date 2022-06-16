package progetto.database;

import progetto.functions.GestioneFile;
import progetto.functions.ValidatoreCampi;

import java.io.*;
import java.util.*;

public class Gestione_db {
    private static final String relativePath = "src/main/java/progetto/database/";

    public static BufferedReader getTable(String tableName) {
        try{
            BufferedReader f = GestioneFile.openFile(relativePath+tableName);
            f.readLine(); // non memorizziamo la prima riga
            return f;
        }
        catch (FileNotFoundException e) {}
        catch (IOException e) { }

        return null;
    }

    public static String deleteRow(String ID, String tableName ) {

        ArrayList<String> dati = new ArrayList<>();

        if (GestioneFile.readExceptID(ID, relativePath+tableName, dati)){
            return GestioneFile.writeFile(relativePath+tableName, dati);
        } else {
            return "errore nella cancellazione dell'elemento";
        }

    }

    public static String modifyRow(String ID, String tableName, String modifyElement ){
            ArrayList<String> dati = new ArrayList<>();

            if (GestioneFile.readExceptID(ID, relativePath+tableName, dati)){
                dati.add(modifyElement);

                return GestioneFile.writeFile(relativePath+tableName, dati);
            } else {
                return "errore nella modifica dell'elemento";
            }

    }

    public static String insertRow(String tableName, String insertElement){
        try {
            BufferedReader file = GestioneFile.openFile(relativePath+tableName);

            ArrayList<String> dati = new ArrayList<>();
            int MaxID = 0;

            String l;
            while ((l = file.readLine()) != null) {
                String[] line = l.split(",");
                dati.add(l);

                if (ValidatoreCampi.isNumeric(line[0]) && Integer.parseInt(line[0]) > MaxID){
                    MaxID = Integer.parseInt(line[0]);
                }

            }
            dati.add( (MaxID+1) + "," + insertElement );
            String mess = GestioneFile.writeFile(relativePath+tableName, dati);
            if(mess.equals("ok")){
                return ""+ ++MaxID;
            }
            return mess;

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }


        return "errore nell'inserimento del nuovo elemento";
    }

    public static String getRow(String tableName, String ID) {
        try {
            BufferedReader file = GestioneFile.openFile(relativePath+tableName);

            String l;
            while ((l = file.readLine()) != null) {
                String[] line = l.split(",");

                if ( line[0].equals(ID)){
                    return l;
                }

            } return "errore elemento non trovato";

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return "errore apertura file";
    }


}
