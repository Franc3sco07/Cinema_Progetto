package progetto.database;

/**
 * Classe Gestione_db
 * Implementazione di vari metodi per gestire i dati
 */

import progetto.functions.GestioneFile;
import progetto.functions.ValidatoreCampi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class Gestione_db {
    private static final String relativePath = "Database/";

    public static Optional<BufferedReader> getTable(String tableName) {
        try {
            BufferedReader f = GestioneFile.openFile(relativePath + tableName);
            f.readLine();
            return Optional.of(f);
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

        return Optional.empty();
    }

    public static String deleteRow(String ID, String tableName) {

        ArrayList<String> dati = new ArrayList<>();

        if (GestioneFile.readExceptID(ID, relativePath + tableName, dati)) {
            return GestioneFile.writeFile(relativePath + tableName, dati);
        } else {
            return "errore nella cancellazione dell'elemento";
        }

    }

    public static String modifyRow(String ID, String tableName, String modifyElement) {
        ArrayList<String> dati = new ArrayList<>();

        if (GestioneFile.readExceptID(ID, relativePath + tableName, dati)) {
            dati.add(modifyElement);
            return GestioneFile.writeFile(relativePath + tableName, dati);
        } else {
            return "errore nella modifica dell'elemento";
        }

    }

    public static String insertRow(String tableName, String insertElement) {
        try {
            BufferedReader file = GestioneFile.openFile(relativePath + tableName);

            Collection<String> dati = new ArrayList<>(file.lines().parallel().toList());

            int MaxID = dati.parallelStream()
                    .filter(s -> ValidatoreCampi.isNumeric(s.split(",")[0].trim()))
                    .map(s -> Integer.parseInt(s.split(",")[0].trim()))
                    .max(Integer::compareTo).get();
            dati.add((MaxID + 1) + "," + insertElement);

            String mess = GestioneFile.writeFile(relativePath + tableName, dati);
            if (mess.equals("ok")) {
                return "" + ++MaxID;
            }
            return mess;

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return "errore nell'inserimento del nuovo elemento";
    }

    public static String getRow(String tableName, String ID) {
        try {
            BufferedReader file = GestioneFile.openFile(relativePath + tableName);

            String l;
            while ((l = file.readLine()) != null) {
                String[] line = l.split(",");

                if (line[0].equals(ID)) {
                    return l;
                }

            }
            return "errore, elemento non trovato";

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return "errore apertura file";
    }


}
